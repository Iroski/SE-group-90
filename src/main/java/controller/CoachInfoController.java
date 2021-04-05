package test.tests.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import test.tests.model.coach;

import java.io.IOException;

public class coachInfoController {
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


    public void backToCoach(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) photo.getScene().getWindow();
        stage.setTitle("Coaches");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/coach.fxml"));
        Parent coachViewParent = loader.load();
        Scene newScene = new Scene(coachViewParent);
        stage.setScene(newScene);
        coachController coachController=loader.getController();
        stage.show();
    }
    @FXML
    public void initialize() {
        description.setWrapText(true);

    }
}
