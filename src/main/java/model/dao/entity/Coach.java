package model.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import model.dao.base.DataItem;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class Coach extends DataItem {
    String name;
    String gender;
    int height;
    int weight;
    int age;
    String description;
    String course;
    String photoPath;
    List<Long> BookedTime;

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public Coach(Long id, String name, String sex, int height, int weight, int age, String course,String photoPath) {
        super.setId(id);
        this.name = name;
        this.gender = sex;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.course = course;
        this.photoPath=photoPath;
        this.description="";
    }

    @Override
    public @NonNull Long getId() {
        return super.getId();
    }

    @Override
    public void setId(@NonNull Long id) {
        super.setId(id);
    }

    @Override
    public String toString() {
        return "coach{" +
                "name='" + name + '\'' +
                ", sex='" + gender + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", age=" + age +
                ", course='" + course + '\'' +
                '}';
    }

}
