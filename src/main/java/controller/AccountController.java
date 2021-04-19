package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.entity.Account;
import model.entity.ReturnEntity;
import model.entity.User;
import model.service.AccountService;
import model.service.UserService;

import java.io.IOException;

public class AccountController {

    public javafx.scene.control.Button topup;
    public Label balance;
    AccountService accountService = new AccountService();
    @FXML
    public void initialize() {
        Account account;
        ReturnEntity returnEntity = accountService.getAccount(LoginController.userName);
        account = (Account) returnEntity.getObject();
        balance.setText("$"+account.getBalance().toString());
    }

    public void TopUp(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) topup.getScene().getWindow();
        stage.setTitle("TopUp");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/fxml/TopUp.fxml"));
        AnchorPane topup = (AnchorPane) loader.load();
        // Set person overview into the center of root layout.
        AnchorPane anchorPane= (AnchorPane) stage.getScene().getRoot();
        anchorPane.getChildren().remove(2);
        anchorPane.getChildren().add(2, topup);

        topup.setLayoutX(200);
        topup.setLayoutY(75);
    }
}
