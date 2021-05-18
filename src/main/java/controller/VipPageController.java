package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.entity.Account;
import model.entity.Order;
import model.entity.ReturnEntity;
import model.entity.User;
import model.service.AccountService;
import model.service.OrderService;
import model.service.UserService;
import model.utils.DateUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;


public class VipPageController {
    public Rectangle annually;
    public Rectangle quarterly;
    public Rectangle monthly;

    public AnchorPane getStart;

    public AnchorPane annuallyPane;
    public AnchorPane quarterlyPane;
    public AnchorPane monthlyPane;
    public Label userName;
    public Label vipInf;
    public Circle userImage;
    UserService userService;
    private AnchorPane chosenPane;
    private double monthlyPay = 30;
    private double quarterlyPay = 75;
    private double annuallyPay = 250;

    @FXML
    public void initialize(){

    }

    public void init(){
        String name = LoginController.userName;
        userName.setText(name);
        this.userService=new UserService();
        User user = (User) userService.getUser(name).getObject();
        userImage.setFill(new ImagePattern(new Image("/view/images/coach.jpg")));

        AccountService accountService=new AccountService();
        ReturnEntity returnEntity=accountService.getAccount(name);
        Account account=null;
        if (returnEntity.getCode()==200) {
            account= (Account) returnEntity.getObject();
        }
        if (account.getPremiumLevel()==0) {
            vipInf.setText("You are not VIP");
        }
        else if (account.getPremiumLevel()==1) {
            vipInf.setText("You are monthly VIP");
        }
        else if (account.getPremiumLevel()==2) {
            vipInf.setText("You are quarterly VIP");
        }
        else if (account.getPremiumLevel()==3) {
            vipInf.setText("You are annually VIP");
        }
    }

    public void setChoosePay(MouseEvent mouseEvent){
        AnchorPane pane = (AnchorPane)mouseEvent.getSource();
        if(pane == annuallyPane){
            monthly.setFill(Color.rgb(255,255,255));
            annually.setFill(Color.rgb(255,223,169));
            quarterly.setFill(Color.rgb(255,255,255));
            chosenPane = annuallyPane;
        }
        else if(pane == monthlyPane){
            annually.setFill(Color.rgb(255,255,255));
            monthly.setFill(Color.rgb(255,223,169));
            quarterly.setFill(Color.rgb(255,255,255));
            chosenPane = monthlyPane;
        }
        else{
            quarterly.setFill(Color.rgb(255,223,169));
            annually.setFill(Color.rgb(255,255,255));
            monthly.setFill(Color.rgb(255,255,255));
            chosenPane = quarterlyPane;
        }
    }


    public void startPay(MouseEvent mouseEvent) throws IOException {
        String userName = LoginController.userName;
        AccountService accountService=new AccountService();
        ReturnEntity returnEntity=accountService.getAccount(userName);
        Account account = null;
        if (returnEntity.getCode()==200) {
            account= (Account) returnEntity.getObject();
        }
        else {
            System.out.println("get account error");
        }
        if (chosenPane==monthlyPane) {
            if (account.getBalance().compareTo(BigDecimal.valueOf(monthlyPay))>=0.00) {
                showConfirmationPage(monthlyPay);
            }
            else {
                goToAccount();
                showMoneyNotEnoughPage();
            }
        }
        else if (chosenPane==annuallyPane) {
            if (account.getBalance().compareTo(BigDecimal.valueOf(annuallyPay))>=0.00) {
                showConfirmationPage(annuallyPay);
            }
            else {
                goToAccount();
                showMoneyNotEnoughPage();
            }
        }
        else {
            if (account.getBalance().compareTo(BigDecimal.valueOf(quarterlyPay)) >= 0.00) {
                showConfirmationPage(quarterlyPay);
            } else {
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
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("INFORMATION");
        alert.setContentText("Please charge first!");
        alert.setHeaderText("Your money is not enough!");
        alert.showAndWait();
    }

    public void showConfirmationPage (Double pay) throws IOException {
        String text="";
        if (pay.equals(30.0)) {
            text = new String("Are you sure to buy the monthly vip?");
        }
        else {
            text = new String("Are you sure to buy the yearly vip?");
        }
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("confirmation");
        //alert.setContentText(text);
        alert.setHeaderText(text);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            int type=1;
            int plusDay=30;
            int premiumNum=1;
            if (pay.equals(quarterlyPay)) {
                type=2;
                plusDay=90;
                premiumNum=3;
            }
            else if (pay.equals(annuallyPay)) {
                type=3;
                plusDay=365;
                premiumNum=12;
            }
            else if (pay.equals(quarterlyPay)) {
                type=2;
                plusDay=90;
                premiumNum=3;
            }
            String userName=LoginController.userName;
            AccountService accountService=new AccountService();
            accountService.updateBalance(userName, BigDecimal.valueOf(pay));
            LocalDate finishDate=LocalDate.now().plusDays(plusDay);
            Date date = Date.from(finishDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            accountService.setPremium(userName,type,premiumNum);

            OrderService orderService=new OrderService();
            Order order=new Order(userName,0,0L,type,premiumNum,
                    BigDecimal.valueOf(pay),0, DateUtils.dateToTimeStamp(new Date()));
            orderService.createPremiumOrder(userName,order);
            orderService.payPremiumOrder(userName,order);

            showSuccess();
            goToAccount();
        }
    }
    public void showSuccess() {
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("INFORMATION");
        alert.setContentText("");
        alert.setHeaderText("Successful!");
        alert.showAndWait();
    }
}
