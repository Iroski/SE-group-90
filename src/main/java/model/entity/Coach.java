package model.entity;

import lombok.*;
import model.dao.base.DataItem;

import java.util.List;

@Data()
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Coach extends DataItem {
    String name;
    String gender;
    Integer height;
    Integer weight;
    Integer age;
    String description;
    String course;
    String photoPath;
    List<Long> BookedTime;

    public Coach(Long id,String name, String gender, Integer height, Integer weight, Integer age, String description, String course, String photoPath, List<Long> bookedTime) {
        super(id);
        this.name = name;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.description = description;
        this.course = course;
        this.photoPath = photoPath;
        BookedTime = bookedTime;
    }

    public Coach(String name, String gender, Integer height, Integer weight, Integer age, String description, String course, String photoPath, List<Long> bookedTime) {
        this.name = name;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.description = description;
        this.course = course;
        this.photoPath = photoPath;
        BookedTime = bookedTime;
    }

}
