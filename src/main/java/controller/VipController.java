package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.entity.Account;
import model.entity.ReturnEntity;
import model.entity.User;
import model.service.AccountService;
import model.service.UserService;
import model.utils.DateUtils;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class VipController {
    public AnchorPane vipPane;
    public AnchorPane vipCard;
    public String name;
    public Label userName;
    public Label vipTime;
    public Circle userImage;
    public Button closeButton;

    UserService userService = new UserService();

    /**
     * This function is used to init the page if the user is a vip
     */
    public void init() {
        this.name = LoginController.userName;
        userName.setText(name);
        User user = (User) userService.getUser(name).getObject();
        this.userImage.setFill(new ImagePattern(new Image(user.getProfilePhotoPath())));
        AccountService accountService=new AccountService();
        ReturnEntity returnEntity=accountService.getAccount(name);
        Account account=null;
        if (returnEntity.getCode()==200) {
            account= (Account) returnEntity.getObject();
        }
        else {
            System.out.println("ERROR!");
        }
        Date date=DateUtils.timeStampToDate(account.getPremiumEndTime()*1000);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String transformDate=simpleDateFormat.format(date);
        vipTime.setText(transformDate);
    }
    /**
     * This function is used to close the vip page if the user's mouse leave the vip page zone
     * @param: mouseEvent will be triggered after the user's mouse leave the zone of vip page
     * @throws: IOException
     */
    public void closeVip(MouseEvent mouseEvent) {
        closeTheVip();
    }

    /**
     * This function is used to close the vip page if the user goes to the big vip page
     */
    public void closeTheVip() {
        try {
            Stage stage = (Stage) vipPane.getScene().getWindow();
            AnchorPane anchorPane = (AnchorPane) stage.getScene().getRoot();
            anchorPane.getChildren().remove(this.vipPane);
        } catch (Exception ignore) {

        }
    }
    /**
     * This function is used to go to the big vip page if the user want to buy a vip
     * @param: mouseEvent will be triggered after the user click the button to buy the vip
     * @throws: IOException
     */
    public void showVip(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) vipPane.getScene().getWindow();
        stage.setTitle("VIP");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/fxml/VipPage.fxml"));
        AnchorPane account = loader.load();
        VipPageController controller=loader.getController();
        controller.vipInf.setUserData(this.vipCard.getUserData());
        controller.init();
        AnchorPane anchorPane= (AnchorPane) stage.getScene().getRoot();
        anchorPane.getChildren().remove(2);
        anchorPane.getChildren().add(2, account);
        account.setLayoutX(200);
        account.setLayoutY(75);
        closeTheVip();
    }
}
