package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
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
    public Label monthlyPrice;
    public Label quarterlyPrice;
    public Label annualPrice;
    public Button backButton;
    UserService userService;
    private AnchorPane chosenPane;
    private double monthlyPay = 30;
    private double quarterlyPay = 75;
    private double annuallyPay = 250;
    /**
     * This function is used to init the vip page.
     */
    public void init(){
        String name = LoginController.userName;
        userName.setText(name);
        this.userService = new UserService();
        User user = (User) userService.getUser(name).getObject();
        userImage.setFill(new ImagePattern(new Image(user.getProfilePhotoPath())));
        monthlyPrice.setText(String.valueOf(monthlyPay));
        quarterlyPrice.setText(String.valueOf(quarterlyPay));
        annualPrice.setText(String.valueOf(annuallyPay));
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

    /**
     * This function is used to change the color of the chosen vip pane
     * user is going to buy the vip for sure
     * @param: mouseEvent will be triggered after the user click any of the vip pane
     * @throws: IOException
     */
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
        else if (pane==quarterlyPane){
            quarterly.setFill(Color.rgb(255,223,169));
            annually.setFill(Color.rgb(255,255,255));
            monthly.setFill(Color.rgb(255,255,255));
            chosenPane = quarterlyPane;
        }
    }

    /**
     * This function is used to check whether the user's money is enough for the chosen vip or whether the
     * user is going to buy the vip for sure
     * @param: mouseEvent will be triggered after the user click the button "purchase"
     * @throws: IOException
     */
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
        else if (chosenPane==quarterlyPane){
            if (account.getBalance().compareTo(BigDecimal.valueOf(quarterlyPay)) >= 0.00) {
                showConfirmationPage(quarterlyPay);
            } else {
                goToAccount();
                showMoneyNotEnoughPage();
            }
        }
    }

    /**
     * This function is used to go to the account page if the user's money is not enough
     * @throws: IOException
     */
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

    /**
     * This function is used to show the information of the user's money is not enough
     * @throws: IOException
     */
    public void showMoneyNotEnoughPage () throws IOException {
        ButtonType confirm=new ButtonType("OK", ButtonBar.ButtonData.FINISH);
        Alert alert=new Alert(Alert.AlertType.INFORMATION,"",confirm);
        alert.setTitle("INFORMATION");
        alert.setContentText("Please charge first!");
        alert.setHeaderText("Your money is not enough!");
        alert.showAndWait();
    }

    /**
     * This function is used to show the information and the user can decide whether he or she is
     * willing to pay.
     * @throws: IOException
     */
    public void showConfirmationPage (Double pay)  {
        try {
            String text = "";
            if (pay.equals(monthlyPay)) {
                text = new String("Are you sure to buy the monthly vip?");
            } else if (pay.equals(quarterlyPay)) {
                text = new String("Are you sure to buy the quarterly vip?");
            } else {
                text = new String("Are you sure to buy the yearly vip?");
            }
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.NO);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", yes, no);
            alert.setTitle("confirmation");
            alert.setHeaderText(text);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == yes) {
                int type = 1;
                if (pay.equals(quarterlyPay)) {
                    type = 2;
                } else if (pay.equals(annuallyPay)) {
                    type = 3;
                }
                String userName = LoginController.userName;
                AccountService accountService = new AccountService();
                //accountService.updateBalance(userName, BigDecimal.valueOf(pay));
                OrderService orderService = new OrderService();

                Order order = new Order(userName, 0, (long) 0, type, 1, BigDecimal.valueOf(pay), 0, DateUtils.dateToTimeStamp(new Date()));
                orderService.createPremiumOrder(userName, order);
                orderService.payPremiumOrder(userName, order);
                showSuccess();
                BasePageController basePageController = (BasePageController) vipInf.getUserData();
                basePageController.init();
                showVip();
            }
        } catch(Exception ignored) {

        }
    }

    /**
     * This function is used to show the information of buying the vip successfully
     */
    public void showSuccess() {
        ButtonType confirm=new ButtonType("OK", ButtonBar.ButtonData.FINISH);
        Alert alert=new Alert(Alert.AlertType.INFORMATION,"",confirm);
        alert.setTitle("INFORMATION");
        alert.setContentText("");
        alert.setHeaderText("Successful!");
        alert.showAndWait();
    }

    /**
     * This function is used to fresh the page of vip page after the user buy the vip successfully
     * @throws: IOException
     */
    public void showVip() throws IOException {
        Stage stage = (Stage) vipInf.getScene().getWindow();
        stage.setTitle("VIP");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/fxml/VipPage.fxml"));
        AnchorPane account = loader.load();
        VipPageController controller=loader.getController();
        controller.init();
        AnchorPane anchorPane= (AnchorPane) stage.getScene().getRoot();
        anchorPane.getChildren().remove(2);
        anchorPane.getChildren().add(2, account);
        account.setLayoutX(200);
        account.setLayoutY(75);
    }

    public void goToHome() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setTitle("Home");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/fxml/MainPage.fxml"));
        AnchorPane home = (AnchorPane) loader.load();
        // Set person overview into the center of root layout.
        AnchorPane anchorPane= (AnchorPane) stage.getScene().getRoot();
        anchorPane.getChildren().remove(2);
        anchorPane.getChildren().add(2, home);

        home.setLayoutX(200);
        home.setLayoutY(75);
    }
}
