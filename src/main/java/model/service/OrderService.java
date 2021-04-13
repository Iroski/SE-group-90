package model.service;

import common.CommunicationStatus;
import model.dao.OrderDao;
import model.entity.Coach;
import model.entity.LiveLesson;
import model.entity.Order;
import model.entity.ReturnEntity;
import model.enumPackage.OrderStatus;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author :YanBo Zhang
 * @date :Created in 2021 2021/4/13 22:31
 * @description:
 * @modifiedBy By:
 * @version :
 */
public class OrderService {
    OrderDao orderDao;
    public OrderService(){
        orderDao=new OrderDao();
    }

    public int payPremiumOrder(String username,Order order,Integer type, Long premiumDuration){
        try{
            AccountService accountService=new AccountService();
            int accountServiceCode=accountService.updateBalance(username,order.getMoney());
            if(accountServiceCode!=200)
                return accountServiceCode;
            accountServiceCode=accountService.setPremium(username,type,premiumDuration);
            if(accountServiceCode!=200)
                return accountServiceCode;
            order.setState(OrderStatus.IS_PAYED.getCode());
            orderDao.updateOrder(order);
        }catch (RuntimeException e){
            return CommunicationStatus.INTERNAL_ERROR.getCode();
        }
        return this.saveOrder(order);
    }

    public int cancelPremiumOrder(Order order){
        try{
            order.setState(OrderStatus.IS_CANCELED.getCode());
            return this.updateOrder(order);
        }catch (RuntimeException e){
            return CommunicationStatus.INTERNAL_ERROR.getCode();
        }
    }

    public ReturnEntity createPremiumOrder(String username, Order order){
        try{
            order= (Order) orderDao.saveOrder(order); //get order id;
            int accountServiceCode=new AccountService().addOrderId(username,order.getId());
            if(accountServiceCode!=200)
                return new ReturnEntity(accountServiceCode,null);
        }catch (RuntimeException e){
            return new ReturnEntity(CommunicationStatus.INTERNAL_ERROR.getCode(),null);
        }
        return new ReturnEntity(CommunicationStatus.OK.getCode(),order);
    }

    public int payLiveLessonOrder(String username, Order order, LiveLesson liveLesson, AtomicBoolean isFreeByPremium){
        try{
            AccountService accountService=new AccountService();
            int accountServiceCode=accountService.updateBalance(username,order.getMoney());
            if(accountServiceCode!=200)
                return accountServiceCode;
            if(isFreeByPremium.get()){
                accountServiceCode=accountService.minusFreeTimeOfPremium(username);
                if(accountServiceCode!=200)
                    return accountServiceCode;
            }
            int liveLessonCode=new LiveLessonService().updateLessonStateByType(username,liveLesson,"PAYED");
            if(liveLessonCode!=200)
                return liveLessonCode;
            order.setState(OrderStatus.IS_PAYED.getCode());
            orderDao.updateOrder(order);
        }catch (RuntimeException e){
            return CommunicationStatus.INTERNAL_ERROR.getCode();
        }
        return this.saveOrder(order);
    }

    public int cancelLiveLessonOrderAfterPay(String username, LiveLesson liveLesson){
        try{
            Optional<Order> sOrderOption=this.getOrderByLiveLessonCreateTime(liveLesson.getCreateTime());
            if(sOrderOption.isEmpty())
                return CommunicationStatus.ORDER_NOT_FOUND.getCode();
            BigDecimal balance=sOrderOption.get().getMoney();

            int accountCode=new AccountService().updateBalance(username,balance.multiply(new BigDecimal("-1")));
            if(accountCode!=200)
                return accountCode;
            return this.cancelLiveLessonOrderBeforePay(username,liveLesson);
        }catch (RuntimeException e){
            return CommunicationStatus.INTERNAL_ERROR.getCode();
        }
    }

    public int cancelLiveLessonOrderBeforePay(String username, LiveLesson liveLesson){
        Order order;
        try{
            int liveLessonCode=new LiveLessonService().updateLessonStateByType(username,liveLesson,"CANCELED");
            if(liveLessonCode!=200)
                return liveLessonCode;
            int coachServiceCode=new CoachService().updateTimeList(liveLesson.getCoachName(),liveLesson.getLessonTime(),"REMOVE");
            if(coachServiceCode!=200)
                return coachServiceCode;
            Optional<Order> sOrderOption=this.getOrderByLiveLessonCreateTime(liveLesson.getCreateTime());
            if(sOrderOption.isEmpty())
                return CommunicationStatus.ORDER_NOT_FOUND.getCode();
            order=sOrderOption.get();
            order.setState(OrderStatus.IS_CANCELED.getCode());
            orderDao.updateOrder(order);
        }catch (RuntimeException e){
            return CommunicationStatus.INTERNAL_ERROR.getCode();
        }
        return this.saveOrder(order);
    }

    public ReturnEntity createLiveLessonOrder(String username, Order order, LiveLesson liveLesson){
        try{
            order= (Order) orderDao.saveOrder(order); //get order id;
            int accountServiceCode=new AccountService().addOrderId(username,order.getId());
            if(accountServiceCode!=200)
                return new ReturnEntity(accountServiceCode,null);

            int coachServiceCode=new CoachService().updateTimeList(liveLesson.getCoachName(),liveLesson.getLessonTime(),"ADD");
            if(coachServiceCode!=200)
                return new ReturnEntity(coachServiceCode,null);

            int liveLessonCode=new LiveLessonService().insertLesson(username,liveLesson);
            if(liveLessonCode!=200)
                return new ReturnEntity(liveLessonCode,null);
        }catch (RuntimeException e){
            return new ReturnEntity(CommunicationStatus.INTERNAL_ERROR.getCode(),null);
        }
        return new ReturnEntity(CommunicationStatus.OK.getCode(),order);
    }

    public int updateOrder(Order order){
        try{
            if(!this.isOrderExist(order))
                return CommunicationStatus.ORDER_NOT_FOUND.getCode();

            orderDao.updateOrder(order);
        }catch (RuntimeException e){
            return CommunicationStatus.INTERNAL_ERROR.getCode();
        }
        return CommunicationStatus.OK.getCode();
    }

    protected int saveOrder(Order order){
        try{
            orderDao.saveOrder(order);
        }catch (RuntimeException e){
            return CommunicationStatus.INTERNAL_ERROR.getCode();
        }
        return CommunicationStatus.OK.getCode();
    }

    protected boolean isOrderExist(Order sOrder){
        return orderDao.getAllOrder().stream().anyMatch(order -> order.equals(sOrder));
    }

    protected Optional<Order> getOrderByLiveLessonCreateTime(Long createTime){
        return orderDao.getAllOrder().stream().filter(order -> order.getLiveLessonCreateTime().equals(createTime)).findAny();
    }
}
