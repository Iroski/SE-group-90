package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.entity.Account;
import model.entity.Order;
import model.service.AccountService;
import model.service.OrderService;
import model.utils.DateUtils;

import java.awt.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

public class PayVipConfirmationController {
    public Button yesButton;
    public Button noButton;
    public Label label1;

    public void chooseYes(MouseEvent mouseEvent) throws IOException {
        int type=1;
        int plusDay=30;
        Double pay= (Double) yesButton.getUserData();
        if (pay==360) {
            type=2;
            plusDay=365;
        }
        String userName=LoginController.userName;
        AccountService accountService=new AccountService();
        accountService.updateBalance(userName, BigDecimal.valueOf(pay));
        LocalDate finishDate=LocalDate.now().plusDays(plusDay);
        Date date = Date.from(finishDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        accountService.setPremium(userName,type, DateUtils.dateToTimeStamp(date));

        OrderService orderService=new OrderService();
        Order order=new Order(userName,0,null,type,DateUtils.dateToTimeStamp(date),
                BigDecimal.valueOf(pay),1,DateUtils.dateToTimeStamp(new Date()));
        orderService.createPremiumOrder(userName,order);
        Stage stage= (Stage) yesButton.getScene().getWindow();
        stage.close();
        goToMain();
    }

    public void chooseNo(MouseEvent mouseEvent) {
        Stage stage= (Stage) yesButton.getScene().getWindow();
        stage.close();
    }

    public void init() {

    }
    public void goToMain() throws IOException {
//        Stage stage = (Stage) vipPane.getScene().getWindow();
//        stage.setTitle("Profile");
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("/view/fxml/MainPage.fxml"));
//        AnchorPane account = (AnchorPane) loader.load();
//        // Set person overview into the center of root layout.
//        AnchorPane anchorPane= (AnchorPane) stage.getScene().getRoot();
//        anchorPane.getChildren().remove(2);
//        anchorPane.getChildren().add(2, account);
//        account.setLayoutX(200);
//        account.setLayoutY(75);
    }
}
