package model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import model.dao.base.DataItem;

import java.math.BigDecimal;

/**
 * @author :YanBo Zhang
 * @date :Created in 2021 2021/4/13 20:34
 * @description:
 * @modifiedBy By:
 * @version :
 */
@Data()
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Order extends DataItem {
    private String username;
    private Integer type;
    //type==1
    private Long liveLessonCreateTime;
    //type==0
    private Integer premiumType;
    private Long premiumDuration;


    private BigDecimal money;
    private Integer state;
    private Long createTime;

    public Order(String username, Integer type, Long liveLessonCreateTime, Integer premiumType, Long premiumDuration, BigDecimal money, Integer state, Long createTime) {
        this.username = username;
        this.type = type;
        this.liveLessonCreateTime = liveLessonCreateTime;
        this.premiumType = premiumType;
        this.premiumDuration = premiumDuration;
        this.money = money;
        this.state = state;
        this.createTime = createTime;
    }

    public Order(@NonNull Long id, String username, Integer type, Long liveLessonCreateTime, Integer premiumType, Long premiumDuration, BigDecimal money, Integer state, Long createTime) {
        super(id);
        this.username = username;
        this.type = type;
        this.liveLessonCreateTime = liveLessonCreateTime;
        this.premiumType = premiumType;
        this.premiumDuration = premiumDuration;
        this.money = money;
        this.state = state;
        this.createTime = createTime;
    }
}
