package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.entity.ReturnEntity;
import model.entity.User;
import model.service.UserService;

import java.io.IOException;

public class ProfileController {
    public TextField UserName;
    public TextField Gender;
    public TextField Height;
    public TextField Weight;
    public Button editProfile;
    public Button saveProfile;
    public User user;
    @FXML
    public void initialize() {
        UserService service = new UserService();
        ReturnEntity returnEntity = service.getUser(LoginController.userName);
        User user = (User) returnEntity.getObject();
        UserName.setText(user.getName());
        Gender.setText(user.getGender());
        Height.setText(user.getHeight().toString());
        Weight.setText(user.getWeight().toString());
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
        int height = Integer.parseInt(Height.getText());
        Double weight = Double.valueOf(Weight.getText());
        UserService service = new UserService();
        ReturnEntity returnEntity = service.getUser(LoginController.userName);
        User user = (User) returnEntity.getObject();
        user.setName(username);
        user.setGender(gender);
        user.setHeight(height);
        user.setWeight(weight);
        int returnNum = service.updateUser(user);
        if(returnNum == 200) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.titleProperty().set("Success");
            alert.headerTextProperty().set("Save Succeddfully!");
            alert.showAndWait();
        } else if(returnNum == 4041) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.titleProperty().set("Error");
            alert.headerTextProperty().set("user not exist");
            alert.showAndWait();
        } else if(returnNum == 5000) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.titleProperty().set("Error");
            alert.headerTextProperty().set("database error");
            alert.showAndWait();
        }
        UserName.setEditable(false);
        Gender.setEditable(false);
        Height.setEditable(false);
        Weight.setEditable(false);
        editProfile.setVisible(true);
        saveProfile.setVisible(false);
    }
}
