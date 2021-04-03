package org.qmul.dao.entity;

import lombok.*;
import org.qmul.dao.base.DataItem;

/**
 * @author ：Yubo Wang
 * @date ：Created in 2021 2021/4/3 21:00
 * @description：
 * @modified By：
 * @version:
 */
@Data()
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class User extends DataItem {
    private String email;
    private String name;
    private String password;
    private String phone;
    private String gender;
    private double weight;
    private int height;
    private int age;

    public User(long id, String email, String name, String password, String phone, String gender, double weight, int height, int age) {
        super(id);
        this.email = email;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.age = age;
    }
}
