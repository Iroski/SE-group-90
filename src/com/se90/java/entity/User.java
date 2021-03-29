package com.se90.java.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * @author ：YanBo Zhang
 * @date ：Created in 2021 2021/3/29 16:50
 * @description：
 * @modified By：
 * @version:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @NonNull private String email;
    @NonNull private String name;
    @NonNull private String password;
    private String phone;
    private String gender;
    private double weight;
    private int height;
    private int age;

}
