package model.service;

import common.CommunicationStatus;
import model.dao.OrderDao;
import model.entity.*;
import model.enumPackage.OrderStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * @author :YanBo Zhang
 * @version :
 * @date :Created in 2021 2021/4/13 22:31
 * @description:
 * @modifiedBy By:
 */
public class OrderService {
    OrderDao orderDao;

    public OrderService() {
        orderDao = new OrderDao();
    }

    public ReturnEntity getOrdersByName(String username) {
        try {
            Optional<Account> myAccountOption = new AccountService().getAccountByUsername(username);
            if (myAccountOption.isEmpty())
                return new ReturnEntity(CommunicationStatus.ACCOUNT_NOT_FOUND.getCode(), null);
            List<Long> orderId = myAccountOption.get().getOrderId();
            List<Order> orders = orderDao.getAllOrder().stream().filter(order -> orderId.contains(order.getId())).collect(Collectors.toList());
            return new ReturnEntity(CommunicationStatus.OK.getCode(), orders);
        } catch (RuntimeException e) {
            return new ReturnEntity(CommunicationStatus.INTERNAL_ERROR.getCode(), null);
        }
    }

    public ReturnEntity createPremiumOrder(String username, Order order) {
        try {
            order = (Order) orderDao.saveOrder(order); //get order id;
            int accountServiceCode = new AccountService().addOrderId(username, order.getId());
            if (accountServiceCode != 200)
                return new ReturnEntity(accountServiceCode, null);
        } catch (RuntimeException e) {
            return new ReturnEntity(CommunicationStatus.INTERNAL_ERROR.getCode(), null);
        }
        return new ReturnEntity(CommunicationStatus.OK.getCode(), order);
    }

    public int payPremiumOrder(String username, Order order) {
        try {
            if(!this.isOrderExist(order))
                return CommunicationStatus.ORDER_NOT_FOUND.getCode();
            AccountService accountService = new AccountService();
            int accountServiceCode = accountService.updateBalance(username, order.getMoney());
            if (accountServiceCode != 200)
                return accountServiceCode;
            accountServiceCode = accountService.setPremium(username, order.getPremiumType(), order.getPremiumNum());
            if (accountServiceCode != 200)
                return accountServiceCode;
            order.setState(OrderStatus.IS_PAYED.getCode());
            orderDao.updateOrder(order);
        } catch (RuntimeException e) {
            return CommunicationStatus.INTERNAL_ERROR.getCode();
        }
        return CommunicationStatus.OK.getCode();
    }

    public int cancelPremiumOrder(Order order) {
        try {
            if(!this.isOrderExist(order))
                return CommunicationStatus.ORDER_NOT_FOUND.getCode();
            order.setState(OrderStatus.IS_CANCELED.getCode());
            return this.updateOrder(order);
        } catch (RuntimeException e) {
            return CommunicationStatus.INTERNAL_ERROR.getCode();
        }
    }


    public ReturnEntity createLiveLessonOrder(String username, Order order, LiveLesson liveLesson) {
        try {
            int coachServiceCode = new CoachService().updateTimeList(liveLesson.getCoachName(), liveLesson.getLessonTime(), "ADD");
            if (coachServiceCode != 200)
                return new ReturnEntity(coachServiceCode, null);

            int liveLessonCode = new LiveLessonService().insertLesson(username, liveLesson);
            if (liveLessonCode != 200){
                new CoachService().updateTimeList(liveLesson.getCoachName(), liveLesson.getLessonTime(), "REMOVE");
                return new ReturnEntity(liveLessonCode, null);
            }


            order = (Order) orderDao.saveOrder(order); //get order id;
            int accountServiceCode = new AccountService().addOrderId(username, order.getId());
            if (accountServiceCode != 200)
                return new ReturnEntity(accountServiceCode, null);


        } catch (RuntimeException e) {
            return new ReturnEntity(CommunicationStatus.INTERNAL_ERROR.getCode(), null);
        }
        return new ReturnEntity(CommunicationStatus.OK.getCode(), order);
    }

    public int payLiveLessonOrder(String username, Order order, LiveLesson liveLesson, AtomicBoolean isFreeByPremium) {
        try {
            if(!this.isOrderExist(order))
                return CommunicationStatus.ORDER_NOT_FOUND.getCode();
            AccountService accountService = new AccountService();
            int accountServiceCode = accountService.updateBalance(username, order.getMoney());
            if (accountServiceCode != 200)
                return accountServiceCode;
            if (isFreeByPremium.get()) {
                accountServiceCode = accountService.minusFreeTimeOfPremium(username);
                if (accountServiceCode != 200)
                    return accountServiceCode;
            }
            int liveLessonCode = new LiveLessonService().updateLessonStateByType(username, liveLesson, "PAYED");
            if (liveLessonCode != 200)
                return liveLessonCode;
            order.setState(OrderStatus.IS_PAYED.getCode());
            orderDao.updateOrder(order);
        } catch (RuntimeException e) {
            return CommunicationStatus.INTERNAL_ERROR.getCode();
        }
        return CommunicationStatus.OK.getCode();
    }

    public int cancelLiveLessonOrderAfterPay(String username, LiveLesson liveLesson) {
        try {
            Optional<Order> sOrderOption = this.getOrderByLiveLessonCreateTime(liveLesson.getCreateTime());
            if (sOrderOption.isEmpty())
                return CommunicationStatus.ORDER_NOT_FOUND.getCode();
            BigDecimal balance = sOrderOption.get().getMoney();

            int liveLessonCode = new LiveLessonService().updateLessonStateByType(username, liveLesson, "CANCELED");
            if (liveLessonCode != 200)
                return liveLessonCode;

            int accountCode = new AccountService().updateBalance(username, balance.multiply(new BigDecimal("-1")));
            if (accountCode != 200)
                return accountCode;
            return this.cancelLiveLessonOrderBeforePay(username, liveLesson);
        } catch (RuntimeException e) {
            return CommunicationStatus.INTERNAL_ERROR.getCode();
        }
    }

    public int cancelLiveLessonOrderBeforePay(String username, LiveLesson liveLesson) {
        Order order;
        try {
            Optional<Order> sOrderOption = this.getOrderByLiveLessonCreateTime(liveLesson.getCreateTime());
            if (sOrderOption.isEmpty())
                return CommunicationStatus.ORDER_NOT_FOUND.getCode();
            int liveLessonCode = new LiveLessonService().updateLessonStateByType(username, liveLesson, "CANCELED");
            if (liveLessonCode != 200)
                return liveLessonCode;
            int coachServiceCode = new CoachService().updateTimeList(liveLesson.getCoachName(), liveLesson.getLessonTime(), "REMOVE");
            if (coachServiceCode != 200)
                return coachServiceCode;
            order = sOrderOption.get();
            order.setState(OrderStatus.IS_CANCELED.getCode());
            orderDao.updateOrder(order);
        } catch (RuntimeException e) {
            return CommunicationStatus.INTERNAL_ERROR.getCode();
        }
        return CommunicationStatus.OK.getCode();
    }


    public int updateOrder(Order order) {
        try {
            if (!this.isOrderExist(order))
                return CommunicationStatus.ORDER_NOT_FOUND.getCode();

            orderDao.updateOrder(order);
        } catch (RuntimeException e) {
            return CommunicationStatus.INTERNAL_ERROR.getCode();
        }
        return CommunicationStatus.OK.getCode();
    }

    public int saveOrder(Order order) {
        try {
            orderDao.saveOrder(order);
        } catch (RuntimeException e) {
            return CommunicationStatus.INTERNAL_ERROR.getCode();
        }
        return CommunicationStatus.OK.getCode();
    }

    protected boolean isOrderExist(Order sOrder) {
        return orderDao.getAllOrder().stream().anyMatch(order -> order.equals(sOrder));
    }

    protected Optional<Order> getOrderByLiveLessonCreateTime(Long createTime) {
        return orderDao.getAllOrder().stream().filter(order -> order.getLiveLessonCreateTime().equals(createTime)).findAny();
    }
}
