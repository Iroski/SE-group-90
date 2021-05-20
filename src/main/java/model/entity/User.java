package model.entity;

import lombok.*;
import model.dao.base.DataItem;

import java.util.List;

/**
 * @author ：Yubo Wang
 */
@Data()
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class User extends DataItem {
    private String email;
    private String name;
    private String password;
    private String phone;
    private String gender;
    private Double weight;
    private Integer height;
    private Integer age;
    private List<Long> history;
    private List<Long> favorite;

    public User(String email, String name, String password, String phone, String gender, Double weight, Integer height, Integer age, List<Long> history, List<Long> favorite) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.history = history;
        this.favorite = favorite;
    }

    public User(@NonNull Long id, String email, String name, String password, String phone, String gender, Double weight, Integer height, Integer age, List<Long> history, List<Long> favorite) {
        super(id);
        this.email = email;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.history = history;
        this.favorite = favorite;
    }
}
