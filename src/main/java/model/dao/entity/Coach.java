package test.tests.model;

public class coach {
    int id;
    String name;
    String sex;
    int height;
    int weight;
    int age;
    String course;
    String photoPath;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public coach(int id, String name, String sex, int height, int weight, int age, String course, String photoPath) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.course = course;
        this.photoPath = photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public coach(int id, String name, String sex, int height, int weight, int age, String course) {
        this.id=id;
        this.name = name;
        this.sex = sex;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.course = course;
    }


    @Override
    public String toString() {
        return "coach{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", age=" + age +
                ", course='" + course + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
