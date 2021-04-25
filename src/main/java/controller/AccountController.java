package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.Main;
import model.entity.Account;
import model.entity.ReturnEntity;
import model.entity.User;
import model.service.AccountService;
import model.service.UserService;

import java.io.IOException;
import java.math.BigDecimal;

public class AccountController {
    public Label l1;
    public Label l2;
    public Label l3;
    public Label l4;
    public Label l5;
    public Label l6;
    public Label l7;
    public Label l8;
    public Button topup;
    public Label balance;
    public String showBalance;
    public static int amount = 0;
    AccountService accountService = new AccountService();
    @FXML
    public void initialize() {
        topup.setStyle("-fx-border-color: #87ff34 ; -fx-background-color: #87ff34 ; -fx-border-radius: 5px ");
        Account account;
        ReturnEntity returnEntity = accountService.getAccount(LoginController.userName);
        account = (Account) returnEntity.getObject();
        showBalance = "$"+account.getBalance().toString();
        balance.setText(showBalance);
        resetSelected();
    }

    public void TopUp(MouseEvent mouseEvent) throws IOException {
        AccountService accountService = new AccountService();
        BigDecimal orderMoney =BigDecimal.valueOf((int)amount*(-1));
        int code = accountService.updateBalance(LoginController.userName,orderMoney);
        initialize();
        Alert alert;
        if(code == 200){
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("Thanks for topping up!");
            alert.headerTextProperty().set("You have topped up successfully!");
        }
        else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.titleProperty().set("Error!");
            alert.headerTextProperty().set("Sorry! Please try again later!");
        }
        alert.showAndWait();
    }

    /**
     * Handle selecting.
     * @author Yifei Cao
     */
    public void selectAmount(MouseEvent mouseEvent) throws IOException {
        resetSelected();
        Label label = (Label) mouseEvent.getSource();
        label.setStyle("-fx-border-color: #ffb64a ; -fx-border-width: 3 ; -fx-background-color: #ffdfa9 ");
        if(label.getText().startsWith("$")){
            amount = Integer.parseInt(label.getText().substring(1));
        }
        else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/fxml/" + "CustomPage.fxml"));
            AnchorPane page = loader.load();
            Stage CustomAmount = new Stage();
            CustomAmount.setTitle("CustomAmount");
            Scene scene = new Scene(page);
            CustomAmount.setScene(scene);
            CustomController controller = loader.getController();
            controller.setSignUpStage(CustomAmount);
            CustomAmount.showAndWait();
        }
        balance.setText(showBalance + " + " + amount);
    }

    /**
     * Reset all the top up buttons so that none of them are being selected.
     * @author Yifei Cao
     */
    public void resetSelected(){
        l1.setStyle("-fx-border-color: #ffb64a ; -fx-border-width: 3 ; -fx-background-color: #ffffff ");
        l2.setStyle("-fx-border-color: #ffb64a ; -fx-border-width: 3 ; -fx-background-color: #ffffff ");
        l3.setStyle("-fx-border-color: #ffb64a ; -fx-border-width: 3 ; -fx-background-color: #ffffff ");
        l4.setStyle("-fx-border-color: #ffb64a ; -fx-border-width: 3 ; -fx-background-color: #ffffff ");
        l5.setStyle("-fx-border-color: #ffb64a ; -fx-border-width: 3 ; -fx-background-color: #ffffff ");
        l6.setStyle("-fx-border-color: #ffb64a ; -fx-border-width: 3 ; -fx-background-color: #ffffff ");
        l7.setStyle("-fx-border-color: #ffb64a ; -fx-border-width: 3 ; -fx-background-color: #ffffff ");
        l8.setStyle("-fx-border-color: #ffb64a ; -fx-border-width: 3 ; -fx-background-color: #ffffff ");
    }
}
