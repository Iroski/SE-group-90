package model.entity;

import lombok.*;
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
@NoArgsConstructor
public class Order extends DataItem {
    private String username;
    private Integer type;
    //type==1
    private Long liveLessonCreateTime;
    //type==0
    private Integer premiumType;
    private Integer premiumNum;


    private BigDecimal money;
    private Integer state;
    private Long createTime;

    public Order(String username, Integer type, Long liveLessonCreateTime, Integer premiumType, Integer premiumNum, BigDecimal money, Integer state, Long createTime) {
        this.username = username;
        this.type = type;
        this.liveLessonCreateTime = liveLessonCreateTime;
        this.premiumType = premiumType;
        this.premiumNum = premiumNum;
        this.money = money;
        this.state = state;
        this.createTime = createTime;
    }

    public Order(@NonNull Long id, String username, Integer type, Long liveLessonCreateTime, Integer premiumType, Integer premiumNum, BigDecimal money, Integer state, Long createTime) {
        super(id);
        this.username = username;
        this.type = type;
        this.liveLessonCreateTime = liveLessonCreateTime;
        this.premiumType = premiumType;
        this.premiumNum = premiumNum;
        this.money = money;
        this.state = state;
        this.createTime = createTime;
    }
}
