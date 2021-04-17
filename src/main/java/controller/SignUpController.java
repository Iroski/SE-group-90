package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.entity.ReturnEntity;
import model.entity.User;
import model.service.UserService;
import model.utils.CheckUserInfoValidation;
import model.utils.Encryption;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author :Yifei Cao
 * @date :
 * @description:
 * @modifiedBy By:
 * @version :
 */
public class SignUpController {
    private Stage singUpStage;
    @FXML
    public Button submit;
    @FXML
    public TextField userName;
    @FXML
    public TextField email;
    @FXML
    public TextField password;
    @FXML
    public TextField phoneNumber;
    @FXML
    public ComboBox gender;
    @FXML
    public TextField height;
    @FXML
    public TextField weight;
    @FXML
    public TextField age;

    User user;
    @FXML
    public void initialize() {
        gender.getItems().addAll(
                "null",
                "female",
                "male"
        );
        gender.getSelectionModel().selectFirst();

        user = new User("","","","","",null,null,null,new ArrayList<Long>());
    }
    /**
     * Sets the stage of this dialog.
     *
     * @param SignUpStage
     */
    public void setSignUpStage(Stage SignUpStage) {
        this.singUpStage = SignUpStage;
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        singUpStage.close();
    }

    @FXML
    public void handleSubmit(MouseEvent mouseEvent) throws IOException {
        String userName = this.userName.getText();
        if(userName.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.titleProperty().set("Error");
            alert.headerTextProperty().set("User name is necessary!");
            alert.showAndWait();
            return;
        }
        String email = this.email.getText();
        System.out.println(email);
        if(!CheckUserInfoValidation.checkEmail(email)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.titleProperty().set("Error");
            alert.headerTextProperty().set("Email error!");
            alert.showAndWait();
            return;
        }
//        if(email.equals("")){
//            System.out.println("email不能为空");
//            return;
//        }
        String password = this.password.getText();
        if(password.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.titleProperty().set("Error");
            alert.headerTextProperty().set("Password is necessary!");
            alert.showAndWait();
            return;
        }
        password = Encryption.getMD5Str(password);
        String phoneNumber = this.phoneNumber.getText();
        if(!CheckUserInfoValidation.checkMobile(phoneNumber)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.titleProperty().set("Error");
            alert.headerTextProperty().set("Phone number error!");
            alert.showAndWait();
            return;
        }
        String gender = this.gender.getValue().toString();

        if(!this.height.getText().equals("")&&!CheckUserInfoValidation.isNumeric(this.height.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.titleProperty().set("Error");
            alert.headerTextProperty().set("Height must be an integer!");
            alert.showAndWait();
            return;
        }

        if(!this.weight.getText().equals("")&&!CheckUserInfoValidation.isFloat(this.weight.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.titleProperty().set("Error");
            alert.headerTextProperty().set("Weight must be a float!");
            alert.showAndWait();
            return;
        }

        if(!this.age.getText().equals("")&&!CheckUserInfoValidation.isNumeric(this.age.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.titleProperty().set("Error");
            alert.headerTextProperty().set("Age must be a integer!");
            alert.showAndWait();
            return;
        }
        user.setName(userName);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phoneNumber);
        user.setGender(gender);
        if(!this.height.getText().equals("")){
            Integer height = Integer.parseInt(this.height.getText());
            user.setHeight(height);
        }
        if(!this.weight.getText().equals("")){
            Double weight = Double.parseDouble(this.weight.getText());
            user.setWeight(weight);
        }
        if(!this.weight.getText().equals("")){
            Integer age = Integer.parseInt(this.age.getText());
            user.setAge(age);
        }
        UserService service = new UserService();
        int code = service.saveUser(user);
        if(code == 200){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("Congratulation");
            alert.headerTextProperty().set("Successful sign up!");
            alert.showAndWait();
            handleCancel();
        }
        else if(code == 4001){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.titleProperty().set("Error");
            alert.headerTextProperty().set("Sorry, this user name already exists!");
            alert.showAndWait();
        }
        else if(code == 5000){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.titleProperty().set("Congratulation");
            alert.headerTextProperty().set("database error!");
            alert.showAndWait();
        }

    }
}
