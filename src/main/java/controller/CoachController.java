package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import main.Main;
import model.dao.entity.Coach;

import java.io.IOException;

public class CoachController {

    public Circle circle4;
    public Label name4;
    public Label description4;
    public Circle circle1;
    public Label name1;
    public Label description1;
    public Circle circle2;
    public Label name2;
    public Label description2;
    public Circle circle3;
    public Label name3;
    public Label description3;
    public Circle circle9;
    public Label name9;
    public Label description9;
    public Circle circle5;
    public Label name5;
    public Label description5;
    public Circle circle6;
    public Label name6;
    public Label description6;
    public Circle circle7;
    public Label name7;
    public Label description7;
    public Circle circle8;
    public Label name8;
    public Label description8;
    public Circle circle10;
    public Label name10;
    public Label description10;
    public Circle circle11;
    public Label name11;
    public Label description11;
    public Circle circle12;
    public Label name12;
    public Label description12;
    public AnchorPane pane1;
    public AnchorPane pane2;
    public AnchorPane pane3;
    public AnchorPane pane4;
    private Coach choosedCoach;

    @FXML
    public void initialize() {
        Image image=new Image("view/images/1.png");
        circle1.setFill(new ImagePattern(image));
        circle2.setFill(new ImagePattern(image));
        circle3.setFill(new ImagePattern(image));
        circle4.setFill(new ImagePattern(image));
        circle5.setFill(new ImagePattern(image));
        circle6.setFill(new ImagePattern(image));
        circle7.setFill(new ImagePattern(image));
        circle8.setFill(new ImagePattern(image));
        circle9.setFill(new ImagePattern(image));
        circle10.setFill(new ImagePattern(image));
        circle11.setFill(new ImagePattern(image));
        circle12.setFill(new ImagePattern(image));

        pane1.setUserData(new Coach(1,"He luyao","man",170,45,11,"chinese","view/images/coach.jpg"));
        //image4.setImage(image);
        pane2.setUserData(new Coach(2,"He luyao2","man2",170,45,11,"english","view/images/coach.jpg"));
        description4.setText("身高：170cm 体重：180Kg");
        description1.setText("身高：170cm 体重：180Kg");
        description2.setText("身高：170cm 体重：180Kg");
        description3.setText("身高：170cm 体重：180Kg");
        description5.setText("身高：170cm 体重：180Kg");
        name1.setText("hly1");
        name2.setText("hly2");
        name3.setText("hly3");
        name4.setText("hly4");
        name5.setText("hly5");
        name6.setText("hly6");
        name7.setText("hly7");
        name8.setText("hly8");
        name9.setText("hly9");
        name10.setText("hly10");
        name11.setText("hly11");
        name12.setText("hly12");
    }


    public void chooseTheCoach(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) circle1.getScene().getWindow();
        stage.setTitle("Coach information");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/fxml/CoachInfo.fxml"));
        AnchorPane coachesInfo = (AnchorPane) loader.load();
        // Set person overview into the center of root layout.
        AnchorPane anchorPane= (AnchorPane) stage.getScene().getRoot();
        anchorPane.getChildren().remove(3);
        anchorPane.getChildren().add(3, coachesInfo);

        coachesInfo.setLayoutX(200);
        coachesInfo.setLayoutY(75);
        AnchorPane pane= (AnchorPane) mouseEvent.getSource();
        choosedCoach=(Coach)pane.getUserData();
        CoachInfoController coachInfoController=loader.getController();
        coachInfoController.photo.setUserData(choosedCoach);
        coachInfoController.age.setText(String.valueOf(choosedCoach.getAge()));
        coachInfoController.name.setText(choosedCoach.getName());
        coachInfoController.height.setText(String.valueOf(choosedCoach.getHeight()));
        coachInfoController.weight.setText(String.valueOf(choosedCoach.getHeight()));
        coachInfoController.sex.setText(choosedCoach.getSex());
        coachInfoController.course.setText(choosedCoach.getCourse());
        Image image=new Image(choosedCoach.getPhotoPath());
        coachInfoController.photo.setImage(image);
        coachInfoController.description.setText("  This teacher is very handsome and clever oh !!!!!!" +
                "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }
}
