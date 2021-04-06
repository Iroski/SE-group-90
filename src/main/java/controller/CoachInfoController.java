package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.dao.entity.Coach;

import java.io.IOException;

public class CoachInfoController {
    public Text name;
    public Text sex;
    public Text height;
    public Text weight;
    public Text age;
    public Text course;
    public Button reserve;
    public Button back;
    public Label courseLabel;
    public Label ageLabel;
    public Label weightLabel;
    public Label heightLabel;
    public Label sexLabel;
    public Label nameLabel;
    public ImageView photo;
    public Label description;
    private Coach choosedCoach;

    public void backToCoach(MouseEvent mouseEvent) throws IOException {

        Stage stage = (Stage) photo.getScene().getWindow();
        stage.setTitle("Coaches");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/fxml/CoachPage.fxml"));
        AnchorPane coaches = (AnchorPane) loader.load();
        // Set person overview into the center of root layout.
        AnchorPane anchorPane= (AnchorPane) stage.getScene().getRoot();
        anchorPane.getChildren().remove(2);
        anchorPane.getChildren().add(2, coaches);

        coaches.setLayoutX(200);
        coaches.setLayoutY(75);
    }

    public void reserve(MouseEvent mouseEvent) throws IOException {
        choosedCoach= (Coach) photo.getUserData();
        Stage stage=new Stage();
        stage.setTitle("Reserve");
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/fxml/BookingPage.fxml"));
        AnchorPane layout= loader.load();
        BookingPageController bookingPageController=loader.getController();
        bookingPageController.coach_photo.setUserData(choosedCoach);
        bookingPageController.coach_name.setText(choosedCoach.getName());
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    public void initialize() {
        description.setWrapText(true);

    }
}
