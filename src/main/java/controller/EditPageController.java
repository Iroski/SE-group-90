package controller;

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

/**
 * @author smile
 * @version 1.0
 * @description: TODO
 * @date 5/10/2021 22:59
 */
public class EditPageController {
    private Stage editStage;
    public ComboBox gender;
    public TextField userName;
    public TextField height;
    public TextField weight;
    public Button save;
    @FXML
    public void initialize(){
        save.setStyle("-fx-border-color: #000000 ; -fx-background-color: #000000 ; -fx-border-radius: 5px ");
        gender.getItems().addAll(
                "secret",
                "female",
                "male"
        );
        UserService service = new UserService();
        ReturnEntity returnEntity = service.getUser(LoginController.userName);
        User user = (User) returnEntity.getObject();
        userName.setText(user.getName());
        if(user.getGender().equals("female")){
            gender.getSelectionModel().select(1);
        }
        else if(user.getGender().equals("male")){
            gender.getSelectionModel().select(2);
        }
        else{
            gender.getSelectionModel().selectFirst();
        }
        try{
            height.setText(user.getHeight().toString());
        }catch (NullPointerException e) {
            height.setText(null);
        }
        try{
            weight.setText(user.getWeight().toString());
        }catch (NullPointerException e) {
            weight.setText(null);
        }

    }
    public void setEditStage(Stage editStage) {
        this.editStage = editStage;
    }
    /**
     * Confirm the amount user have entered.
     */
    public void handleSave(MouseEvent mouseEvent) {
        UserService service = new UserService();
        ReturnEntity returnEntity = service.getUser(LoginController.userName);
        User user = (User) returnEntity.getObject();
        user.setName(userName.getText());
        user.setGender(gender.getValue().toString());
        System.out.println("height:"+height.getText());
        if(height.getText() != null &&!CheckUserInfoValidation.isNumeric(height.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.titleProperty().set("Error");
            alert.headerTextProperty().set("Height must be an integer!");
            alert.showAndWait();
            return;
        }
        if(height.getText() == null){
            user.setHeight(null);
        }
        else{
            user.setHeight(Integer.parseInt(height.getText()));
        }
        if(weight.getText() != null &&!CheckUserInfoValidation.isFloat(weight.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.titleProperty().set("Error");
            alert.headerTextProperty().set("Weight must be a float!");
            alert.showAndWait();
            return;
        }
        if(weight.getText() == null){
            user.setWeight(null);
        }
        else{
            user.setWeight(Double.valueOf(weight.getText()));
        }
        int code = service.updateUser(user);
        if(code == 200) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.titleProperty().set("Success");
            alert.headerTextProperty().set("Save Successfully!");
            alert.showAndWait();
        } else if(code == 4041) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.titleProperty().set("Error");
            alert.headerTextProperty().set("user not exist");
            alert.showAndWait();
        } else if(code == 5000) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.titleProperty().set("Error");
            alert.headerTextProperty().set("database error");
            alert.showAndWait();
        }
        editStage.close();
    }
}
