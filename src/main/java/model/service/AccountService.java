package model.service;

import common.CommunicationStatus;
import model.dao.AccountDao;
import model.entity.Account;
import model.entity.ReturnEntity;
import model.enumPackage.PremiumType;
import model.exception.database.DataItemNotExists;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author :YanBo Zhang
 * @version :
 * @date :Created in 2021 2021/4/13 20:46
 * @description:
 * @modifiedBy By:
 */
public class AccountService {
    AccountDao accountDao;

    public AccountService() {
        accountDao = new AccountDao();
    }

    public void createAccountForSignUp(String username) {
        Optional<Account> sAccount = accountDao.getAllAccount().stream().filter(account -> account.getUsername().equals(username)).findAny();
        if (sAccount.isPresent())
            return;
        accountDao.saveAccount(new Account(username, new BigDecimal("0.0"), new ArrayList<Long>(),0, 0 ,System.currentTimeMillis()/1000, (long) 0));
    }

    public int updateAccount(Account account) {
        try {
            accountDao.updateAccount(account);
        } catch (DataItemNotExists e) {
            return CommunicationStatus.ACCOUNT_NOT_FOUND.getCode();
        } catch (RuntimeException e) {
            return CommunicationStatus.INTERNAL_ERROR.getCode();
        }
        return CommunicationStatus.OK.getCode();
    }



    public int updateBalance(String username,BigDecimal orderMoney) {
        try {
            Optional<Account> sAccountOption = this.getAccountByUsername(username);
            if (sAccountOption.isEmpty())
                return CommunicationStatus.ACCOUNT_NOT_FOUND.getCode();
            Account account=sAccountOption.get();


            BigDecimal subBalance = account.getBalance().subtract(orderMoney);
            int isValid = (subBalance).compareTo(BigDecimal.ZERO);
            if (isValid <= 0)
                return CommunicationStatus.NO_ENOUGH_BALANCE.getCode();

            account.setBalance(subBalance);
            accountDao.updateAccount(account);
        } catch (DataItemNotExists e) {
            return CommunicationStatus.ACCOUNT_NOT_FOUND.getCode();
        } catch (RuntimeException e) {
            return CommunicationStatus.INTERNAL_ERROR.getCode();
        }
        return CommunicationStatus.OK.getCode();
    }

    public ReturnEntity getAccount(String username) {
        try {
            Optional<Account> sAccountOption = this.getAccountByUsername(username);
            if (sAccountOption.isEmpty())
                return new ReturnEntity(CommunicationStatus.ACCOUNT_NOT_FOUND.getCode(), null);

            //check if still be premium when login
            Account sAccount = sAccountOption.get();
            if (sAccount.getPremiumEndTime() < System.currentTimeMillis()/1000) {
                sAccount.setPremiumLevel(0);

                int updateCode;
                if ((updateCode = this.updateAccount(sAccount)) != 200)
                    return new ReturnEntity(updateCode, null);
            }


            return new ReturnEntity(CommunicationStatus.OK.getCode(), sAccount);
        } catch (RuntimeException e) {
            return new ReturnEntity(CommunicationStatus.INTERNAL_ERROR.getCode(), null);
        }
    }

    public ReturnEntity isPremium(String username) {
        try {
            Optional<Account> sAccount = accountDao.getAllAccount().stream().filter(account -> account.getUsername().equals(username)).findAny();
            if (sAccount.isEmpty())
                return new ReturnEntity(CommunicationStatus.ACCOUNT_NOT_FOUND.getCode(), null);
            return new ReturnEntity(CommunicationStatus.OK.getCode(), new AtomicBoolean(sAccount.get().getPremiumLevel() != PremiumType.NOT_PREMIUM.getType()));
        } catch (RuntimeException e) {
            return new ReturnEntity(CommunicationStatus.INTERNAL_ERROR.getCode(), null);
        }
    }

    public int setPremium(String username, Integer type, Integer premiumNum) {
        try {
            Optional<Account> sAccountOption = this.getAccountByUsername(username);
            if (sAccountOption.isEmpty())
                return CommunicationStatus.ACCOUNT_NOT_FOUND.getCode();

            Account sAccount = sAccountOption.get();
            sAccount=PremiumType.getPremiumByType(type).setPremium(sAccount,premiumNum);
            return this.updateAccount(sAccount);
        } catch (RuntimeException e) {
            return CommunicationStatus.INTERNAL_ERROR.getCode();
        }
    }

    public ReturnEntity getBargainByUsername(String username){
        try {
            Optional<Account> sAccountOption = this.getAccountByUsername(username);
            if (sAccountOption.isEmpty())
                return new ReturnEntity(CommunicationStatus.ACCOUNT_NOT_FOUND.getCode(), null);

            return new ReturnEntity(CommunicationStatus.OK.getCode(), PremiumType.getPremiumByType(sAccountOption.get().getPremiumLevel()).getBargain());
        } catch (RuntimeException e) {
            return new ReturnEntity(CommunicationStatus.INTERNAL_ERROR.getCode(), null);
        }
    }

    protected int minusFreeTimeOfPremium(String username){
        try {
            Optional<Account> sAccountOption = this.getAccountByUsername(username);
            if (sAccountOption.isEmpty())
                return CommunicationStatus.ACCOUNT_NOT_FOUND.getCode();

            Account sAccount = sAccountOption.get();
            int freeTime=sAccount.getFreeLiveLessonNum();
            if(freeTime<1)
                return CommunicationStatus.NO_ENOUGH_FREE_LESSON.getCode();
            sAccount.setFreeLiveLessonNum(freeTime-1);
            return CommunicationStatus.OK.getCode();
        } catch (RuntimeException e) {
            return CommunicationStatus.INTERNAL_ERROR.getCode();
        }
    }

    protected int addOrderId(String username,Long orderId){
        try{
            Optional<Account> sAccountOption=this.getAccountByUsername(username);
            if(sAccountOption.isEmpty())
                return CommunicationStatus.ACCOUNT_NOT_FOUND.getCode();

            Account sAccount=sAccountOption.get();
            List<Long> orderList=sAccount.getOrderId();
            orderList.add(orderId);
            sAccount.setOrderId(orderList);
            return this.updateAccount(sAccount);
        }catch (RuntimeException e){
            return CommunicationStatus.INTERNAL_ERROR.getCode();
        }
    }

    protected boolean isAccountExist(String username) {
        return getAccountByUsername(username).isPresent();
    }

    protected Optional<Account> getAccountByUsername(String username) {
        return accountDao.getAllAccount().stream().filter(account -> account.getUsername().equals(username)).findAny();
    }
}
