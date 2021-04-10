package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.entity.Coach;

import java.io.IOException;

public class ProfileController {
    public TextField UserName;
    public TextField Gender;
    public TextField Height;
    public TextField Weight;
    public Button editProfile;
    public Button saveProfile;
    @FXML
    public void initialize() {
        UserName.setText("lu.yuhang");
        Gender.setText("Male");
        Height.setText("175");
        Weight.setText("95");
        UserName.setEditable(false);
        Gender.setEditable(false);
        Height.setEditable(false);
        Weight.setEditable(false);
        saveProfile.setVisible(false);
    }

    public void EditTheProfile(MouseEvent mouseEvent) throws IOException {
        UserName.setEditable(true);
        Gender.setEditable(true);
        Height.setEditable(true);
        Weight.setEditable(true);
        editProfile.setVisible(false);
        saveProfile.setVisible(true);
    }

    public void SaveTheProfile(MouseEvent mouseEvent) throws IOException {
        String username = UserName.getText();
        String gender = Gender.getText();
        String height = Height.getText();
        String weight = Weight.getText();
        UserName.setText(username);
        Gender.setText(gender);
        Height.setText(height);
        Weight.setText(weight);
        UserName.setEditable(false);
        Gender.setEditable(false);
        Height.setEditable(false);
        Weight.setEditable(false);
        editProfile.setVisible(true);
        saveProfile.setVisible(false);
    }
}
