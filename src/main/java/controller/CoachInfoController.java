package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import model.entity.Coach;

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
    public TextFlow description;
    private Coach choosedCoach;

    /**
     * This function is used to jump back to the coach page which is uses to show all the coaches.
     * @param: mouseEvent will be triggered after click the button "return"
     * @throws: IOException
     */
    public void backToCoach(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) photo.getScene().getWindow();
        stage.setTitle("Coaches");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/fxml/CoachPage.fxml"));
        AnchorPane coaches = (AnchorPane) loader.load();
        // Set person overview into the center of root layout.
        AnchorPane anchorPane = (AnchorPane) stage.getScene().getRoot();
        anchorPane.getChildren().remove(2);
        anchorPane.getChildren().add(2, coaches);

        coaches.setLayoutX(200);
        coaches.setLayoutY(75);
    }

    /**
     * This function is used to jump to the booking page which is uses to show all the information
     * of a coach's class schedule
     * @param: mouseEvent will be triggered after click the button "reverse"
     * @throws: IOException
     */
    public void reserve(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) photo.getScene().getWindow();
        stage.setTitle("Reserve");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/fxml/BookingPage.fxml"));
        AnchorPane reserve = (AnchorPane)loader.load();
        BookingPageController bookingPageController = loader.getController();
        Coach choosedCoach = (Coach) photo.getUserData();
        bookingPageController.coach_photo.setUserData(choosedCoach);
        bookingPageController.coach_photo.setFill(new ImagePattern(new Image(choosedCoach.getPhotoPath())));
        bookingPageController.coach_name.setText(choosedCoach.getName());
        AnchorPane anchorPane = (AnchorPane) stage.getScene().getRoot();
        anchorPane.getChildren().remove(2);
        anchorPane.getChildren().add(2, reserve);

        reserve.setLayoutX(200);
        reserve.setLayoutY(75);
        bookingPageController.init();
    }

    /**
     * This function is used to init the coach information page.
     */
    public void init() {
        choosedCoach=(Coach)photo.getUserData();
        age.setText(String.valueOf(choosedCoach.getAge()));
        name.setText(choosedCoach.getName());
        height.setText(String.valueOf(choosedCoach.getHeight()));
        weight.setText(String.valueOf(choosedCoach.getWeight()));
        sex.setText(choosedCoach.getGender());
        course.setText(choosedCoach.getCourse());
        Image image=new Image(choosedCoach.getPhotoPath());
        photo.setImage(image);
        Text text=new Text(choosedCoach.getDescription());
        text.setStyle("-fx-font: 24 arial;");
        description.getChildren().add(text);
    }
}
