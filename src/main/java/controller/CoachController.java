package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.entity.Coach;

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
    public GridPane gridPane;
    public AnchorPane pane5;
    public AnchorPane pane6;
    public AnchorPane pane7;
    public AnchorPane pane8;
    public AnchorPane pane9;
    public AnchorPane pane10;
    public AnchorPane pane11;
    public AnchorPane pane12;
    private Coach choosedCoach;

    @FXML
    public void initialize() {

    }


    public void chooseTheCoach(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) circle1.getScene().getWindow();
        stage.setTitle("Coach information");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/fxml/CoachInfo.fxml"));
        AnchorPane coachesInfo = (AnchorPane) loader.load();
        // Set person overview into the center of root layout.
        AnchorPane anchorPane= (AnchorPane) stage.getScene().getRoot();
        anchorPane.getChildren().remove(2);
        anchorPane.getChildren().add(2, coachesInfo);

        coachesInfo.setLayoutX(200);
        coachesInfo.setLayoutY(75);
        AnchorPane pane= (AnchorPane) mouseEvent.getSource();
        choosedCoach=(Coach)pane.getUserData();
        CoachInfoController coachInfoController=loader.getController();
        coachInfoController.photo.setUserData(choosedCoach);
        coachInfoController.age.setText(String.valueOf(choosedCoach.getAge()));
        coachInfoController.name.setText(choosedCoach.getName());
        coachInfoController.height.setText(String.valueOf(choosedCoach.getHeight()));
        coachInfoController.weight.setText(String.valueOf(choosedCoach.getWeight()));
        coachInfoController.sex.setText(choosedCoach.getGender());
        coachInfoController.course.setText(choosedCoach.getCourse());
        Image image=new Image(choosedCoach.getPhotoPath());
        coachInfoController.photo.setImage(image);
        coachInfoController.description.setText("  This teacher is very handsome and clever, oh !!!!!!" +
                "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }
}
