package model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import model.dao.base.DataItem;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author :YanBo Zhang
 * @date :Created in 2021 2021/4/13 20:33
 * @description:
 * @modifiedBy By:
 * @version :
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Account extends DataItem {
    private String username;
    private BigDecimal balance;
    private List<Long> orderId;
    private Integer premiumLevel;
    private Integer freeLiveLessonNum;
    private Long createTime;
    private Long premiumEndTime;
    private Map<Long,Integer> notStartPremium;

    public Account(String username, BigDecimal balance, List<Long> orderId, Integer premiumLevel, Integer freeLiveLessonNum, Long createTime, Long premiumEndTime) {
        this.username = username;
        this.balance = balance;
        this.orderId = orderId;
        this.premiumLevel = premiumLevel;
        this.freeLiveLessonNum = freeLiveLessonNum;
        this.createTime = createTime;
        this.premiumEndTime = premiumEndTime;
        this.notStartPremium = new HashMap<>();
    }

    public Account(@NonNull Long id, String username, BigDecimal balance, List<Long> orderId, Integer premiumLevel, Integer freeLiveLessonNum, Long createTime, Long premiumEndTime, HashMap<Long, Integer> notStartPremium) {
        super(id);
        this.username = username;
        this.balance = balance;
        this.orderId = orderId;
        this.premiumLevel = premiumLevel;
        this.freeLiveLessonNum = freeLiveLessonNum;
        this.createTime = createTime;
        this.premiumEndTime = premiumEndTime;
        this.notStartPremium = notStartPremium;
    }
}
