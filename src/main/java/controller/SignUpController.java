package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.entity.User;
import model.utils.CheckUserInfoValidation;
import model.utils.Encryption;

import java.io.IOException;

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

        user = new User("","","","","",null,null,null,null);
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
            System.out.println("userName不能为空");
            return;
        }
        String email = this.email.getText();
        System.out.println(email);
        if(!CheckUserInfoValidation.checkEmail(email)){
            System.out.println("email error");
            return;
        }
//        if(email.equals("")){
//            System.out.println("email不能为空");
//            return;
//        }
        String password = this.password.getText();
        if(password.equals("")){
            System.out.println("password不能为空");
            return;
        }
        password = Encryption.getMD5Str(password);
        String phoneNumber = this.phoneNumber.getText();
        if(!CheckUserInfoValidation.checkMobile(phoneNumber)){
            System.out.println("phoneNumber error");
            return;
        }
        String gender = this.gender.getValue().toString();

        if(!this.height.getText().equals("")&&!CheckUserInfoValidation.isNumeric(this.height.getText())){
            System.out.println("height error");
            return;
        }

        if(!this.weight.getText().equals("")&&!CheckUserInfoValidation.isFloat(this.weight.getText())){
            System.out.println("weight error");
            return;
        }

        if(!this.age.getText().equals("")&&!CheckUserInfoValidation.isNumeric(this.age.getText())){
            System.out.println("age error");
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
        System.out.println("好耶！");
        handleCancel();
    }
}
