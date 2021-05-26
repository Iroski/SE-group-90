package model.service;

import common.CommunicationStatus;
import model.dao.AccountDao;
import model.entity.Account;
import model.entity.ReturnEntity;
import model.entity.User;
import model.enumPackage.PremiumType;
import model.exception.database.DataItemNotExists;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

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

    /**
     * create by: YanBo Zhang
     * description: Automatically create a account for the new user.
     * only should be used when call the "saveUser" method in UserService
     * create time: 2021/4/13 20:46
     *
     * @return void
     * @Param: username
     */
    protected void createAccountForSignUp(String username) {
        Optional<Account> sAccount = accountDao.getAllAccount().stream().filter(account -> account.getUsername().equals(username)).findAny();
        if (sAccount.isPresent())
            return;
        accountDao.saveAccount(new Account(username, new BigDecimal("0.0"), new ArrayList<>(), 0, 0, System.currentTimeMillis() / 1000, (long) 0));
    }

    /**
     * create by: YanBo Zhang
     * description: test method
     * Automatically create a account for the users whose account was delete in last test.
     * create time: 2021/4/13 20:46
     *
     * @return void
     * @Param: void
     */
    public void createAccountForDeletedInfo() {
        UserService userService = new UserService();
        List<String> usernameList = userService.getAllUsers().stream().map(User::getName).collect(Collectors.toList());
        List<String> accNameList = accountDao.getAllAccount().stream().map(Account::getUsername).collect(Collectors.toList());
        for (String a : usernameList) {
            if (!accNameList.contains(a))
                createAccountForSignUp(a);
        }
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

    /**
     * create by: YanBo Zhang
     * description: for both "save" and "withdraw"
     * create time: 2021/5/26 17:26
     * @Param: username
     * @Param: orderMoney
     * @return int
     */
    public int updateBalance(String username, BigDecimal orderMoney) {
        try {
            Optional<Account> sAccountOption = this.getAccountByUsername(username);
            if (sAccountOption.isEmpty())
                return CommunicationStatus.ACCOUNT_NOT_FOUND.getCode();
            Account account = sAccountOption.get();


            BigDecimal subBalance = account.getBalance().subtract(orderMoney);
            int isValid = (subBalance).compareTo(BigDecimal.ZERO);
            if (isValid < 0)
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

            Account sAccount = sAccountOption.get();

            int updateCode;
            if ((updateCode = this.updatePremium(sAccount)) != 200)
                return new ReturnEntity(updateCode, null);


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
            sAccount = PremiumType.getPremiumByType(type).setPremium(sAccount, premiumNum);
            return this.updateAccount(sAccount);
        } catch (RuntimeException e) {
            return CommunicationStatus.INTERNAL_ERROR.getCode();
        }
    }

    public ReturnEntity getBargainByUsername(String username) {
        try {
            Optional<Account> sAccountOption = this.getAccountByUsername(username);
            if (sAccountOption.isEmpty())
                return new ReturnEntity(CommunicationStatus.ACCOUNT_NOT_FOUND.getCode(), null);

            return new ReturnEntity(CommunicationStatus.OK.getCode(), PremiumType.getPremiumByType(sAccountOption.get().getPremiumLevel()).getBargain());
        } catch (RuntimeException e) {
            return new ReturnEntity(CommunicationStatus.INTERNAL_ERROR.getCode(), null);
        }
    }

    public ReturnEntity getFreeLessonNumByUsername(String username){
        try {
            Optional<Account> sAccountOption = this.getAccountByUsername(username);
            if (sAccountOption.isEmpty())
                return new ReturnEntity(CommunicationStatus.ACCOUNT_NOT_FOUND.getCode(), null);

            return new ReturnEntity(CommunicationStatus.OK.getCode(), sAccountOption.get().getFreeLiveLessonNum());
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
            int freeTime = sAccount.getFreeLiveLessonNum();
            if (freeTime < 1)
                return CommunicationStatus.NO_ENOUGH_FREE_LESSON.getCode();
            sAccount.setFreeLiveLessonNum(freeTime - 1);
            return CommunicationStatus.OK.getCode();
        } catch (RuntimeException e) {
            return CommunicationStatus.INTERNAL_ERROR.getCode();
        }
    }

    protected int addOrderId(String username, Long orderId) {
        try {
            Optional<Account> sAccountOption = this.getAccountByUsername(username);
            if (sAccountOption.isEmpty())
                return CommunicationStatus.ACCOUNT_NOT_FOUND.getCode();

            Account sAccount = sAccountOption.get();
            List<Long> orderList = sAccount.getOrderId();
            orderList.add(orderId);
            sAccount.setOrderId(orderList);
            return this.updateAccount(sAccount);
        } catch (RuntimeException e) {
            return CommunicationStatus.INTERNAL_ERROR.getCode();
        }
    }

    protected boolean isAccountExist(String username) {
        return getAccountByUsername(username).isPresent();
    }

    protected Optional<Account> getAccountByUsername(String username) {
        return accountDao.getAllAccount().stream().filter(account -> account.getUsername().equals(username)).findAny();
    }

    protected List<Account> getAllAccounts() {
        return accountDao.getAllAccount();
    }

    protected int updatePremium(Account account) {
        try {
            Map<Long, Integer> notStartList = account.getNotStartPremium();
            long curTime = System.currentTimeMillis() / 1000;
            if (account.getPremiumEndTime() > curTime) {    //indicate that the user still has premium, but need to determine which one
                if (!notStartList.isEmpty()) {
                    List<Map.Entry<Long, Integer>> list = notStartList.entrySet().stream().filter(e -> e.getKey() <= curTime).collect(Collectors.toList());
                    Optional<Map.Entry<Long, Integer>> optionalFirstPremium = list.stream().max(Map.Entry.comparingByKey());
                    if (optionalFirstPremium.isPresent()) {   //this shows that the premium type may changed
                        Map.Entry<Long, Integer> firstPremium = optionalFirstPremium.get();
                        account.setPremiumLevel(firstPremium.getValue());
                    }
                    for (Map.Entry<Long, Integer> e : list) {    //remove all the used premium
                        account.getNotStartPremium().remove(e.getKey());
                    }

                }
            } else {  //indicate that user not have premium now
                account.setPremiumLevel(PremiumType.NOT_PREMIUM.getType());
            }

            return this.updateAccount(account);
        } catch (RuntimeException e) {
            return CommunicationStatus.INTERNAL_ERROR.getCode();
        }
    }
}
