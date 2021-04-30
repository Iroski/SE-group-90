package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.entity.Account;
import model.entity.ReturnEntity;
import model.entity.User;
import model.service.AccountService;
import model.service.UserService;

import java.io.IOException;
import java.math.BigDecimal;

public class VipPageForOrdinaryController {
    public AnchorPane vipPane;
    public HBox choosePay;
    public Rectangle annually;
    public Rectangle monthly;
    public AnchorPane getStart;
    public AnchorPane annuallyPane;
    public AnchorPane monthlyPane;
    private AnchorPane choosedPane;
    private double monthlyPay=30;
    private double annuallyPay=350;
    @FXML
    public void setChoosePay(MouseEvent mouseEvent){
        AnchorPane pane = (AnchorPane)mouseEvent.getSource();
        if(pane == annuallyPane){
            monthly.setFill(Color.rgb(255,255,255));
            annually.setFill(Color.rgb(255,223,169));
            choosedPane=annuallyPane;
        }
        else if(pane == monthlyPane){
            annually.setFill(Color.rgb(255,255,255));
            monthly.setFill(Color.rgb(255,223,169));
            choosedPane=monthlyPane;
        }
    }

    public void closeVip(MouseEvent mouseEvent) {
        Stage stage = (Stage) annually.getScene().getWindow();
        AnchorPane anchorPane= (AnchorPane) stage.getScene().getRoot();
        anchorPane.getChildren().remove(this.vipPane);
    }

//    public void closeTheVip() {
//        Stage stage = (Stage) annually.getScene().getWindow();
//        AnchorPane anchorPane= (AnchorPane) stage.getScene().getRoot();
//        anchorPane.getChildren().remove(this.vipPane);
//    }

    public void startPay(MouseEvent mouseEvent) throws IOException {
        String userName = LoginController.userName;
        AccountService accountService=new AccountService();
        ReturnEntity returnEntity=accountService.getAccount(userName);
        Account account=null;
        if (returnEntity.getCode()==200) {
            account= (Account) returnEntity.getObject();
        }
        else {
            System.out.println("?");
        }
        if (choosedPane==monthlyPane) {
            if (account.getBalance().compareTo(BigDecimal.valueOf(monthlyPay))>=0.00) {
                showConfirmationPage(monthlyPay);
            }
            else {
//                Stage stage = (Stage) annually.getScene().getWindow();
//                AnchorPane anchorPane= (AnchorPane) stage.getScene().getRoot();
//                anchorPane.getChildren().remove(this.vipPane);
                goToAccount();
                showMoneyNotEnoughPage();
            }
        }
        else {
            if (account.getBalance().compareTo(BigDecimal.valueOf(annuallyPay))>=0.00) {
                showConfirmationPage(annuallyPay);
            }
            else {
//                Stage stage = (Stage) annually.getScene().getWindow();
//                AnchorPane anchorPane= (AnchorPane) stage.getScene().getRoot();
//                anchorPane.getChildren().remove(this.vipPane);
                goToAccount();
                showMoneyNotEnoughPage();
            }
        }
    }
    public void goToAccount() throws IOException {
        Stage stage = (Stage) annually.getScene().getWindow();
        stage.setTitle("Profile");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/fxml/Account.fxml"));
        AnchorPane account = (AnchorPane) loader.load();
        // Set person overview into the center of root layout.
        AnchorPane anchorPane= (AnchorPane) stage.getScene().getRoot();
        anchorPane.getChildren().remove(2);
        anchorPane.getChildren().add(2, account);
        account.setLayoutX(200);
        account.setLayoutY(75);
    }
    public void showMoneyNotEnoughPage () throws IOException {
        Stage stage=new Stage();
        stage.setTitle("Confirmation");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/fxml/MoneyNotEnoughPage.fxml"));
        AnchorPane layout = loader.load();
        MoneyNotEnoughController moneyNotEnoughController = loader.getController();
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void showConfirmationPage (Double pay) throws IOException {
        Stage stage=new Stage();
        stage.setTitle("Confirmation");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/fxml/PayVipConfirmationPage.fxml"));
        AnchorPane layout = loader.load();
        PayVipConfirmationController payVipConfirmationController = loader.getController();
        if (pay.equals(30.0)) {
            payVipConfirmationController.label1.setText("Are you sure to buy the monthly");
        }
        payVipConfirmationController.yesButton.setUserData(pay);
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
