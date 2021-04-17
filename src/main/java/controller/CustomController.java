package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.Main;
import model.service.UserService;
import model.utils.CheckUserInfoValidation;

import java.io.IOException;

/**
 * @author Yifei Cao
 * @version 1.0
 * @description: TODO
 * @date 4/17/2021 21:31
 */
public class CustomController {
    private Stage singUpStage;
    public TextField amountTextField;
    public void setSignUpStage(Stage SignUpStage) {
        this.singUpStage = SignUpStage;
    }

    /**
     * Confirm the amount user have entered.
     */
    public void handleConfirm(MouseEvent mouseEvent) {
        String amount = amountTextField.getText();
        if(!CheckUserInfoValidation.isNumeric(amount)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.titleProperty().set("Error!");
            alert.headerTextProperty().set("Please enter an integer!");
            alert.showAndWait();
        }
        else {
            TopUpController.amount = Integer.parseInt(amount);
            singUpStage.close();
        }
    }
}
