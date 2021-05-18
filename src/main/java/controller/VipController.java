package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.entity.Account;
import model.entity.ReturnEntity;
import model.entity.User;
import model.service.AccountService;
import model.service.UserService;
import model.utils.DateUtils;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;


public class VipController {
    public AnchorPane vipPane;
    public AnchorPane vipCard;
    public String name;
    public Label userName;
    public Label vipTime;

    UserService userService = new UserService();

    @FXML
    public void initialize(){
        this.name = LoginController.userName;
        userName.setText(name);
        User user = (User) userService.getUser(name).getObject();
        //Image image = new Image( );
        AccountService accountService=new AccountService();
        ReturnEntity returnEntity=accountService.getAccount(name);
        Account account=null;
        if (returnEntity.getCode()==200) {
            account= (Account) returnEntity.getObject();
        }
        Date date=DateUtils.timeStampToDate(account.getPremiumEndTime()*1000);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String transformDate=simpleDateFormat.format(date);
        vipTime.setText(transformDate);
    }

    public void init() {
        this.name = LoginController.userName;
        userName.setText(name);
        User user = (User) userService.getUser(name).getObject();
        //Image image = new Image( );
    }
    public void closeVip(MouseEvent mouseEvent) {
        Stage stage = (Stage) vipCard.getScene().getWindow();
        AnchorPane anchorPane= (AnchorPane) stage.getScene().getRoot();
        anchorPane.getChildren().remove(this.vipPane);
    }

    public void closeTheVip() {
        try {
            Stage stage = (Stage) vipPane.getScene().getWindow();
            AnchorPane anchorPane = (AnchorPane) stage.getScene().getRoot();
            anchorPane.getChildren().remove(this.vipPane);
        } catch (Exception ignore) {

        }
    }
    public void showVip(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) vipPane.getScene().getWindow();
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
        closeTheVip();
    }
}
