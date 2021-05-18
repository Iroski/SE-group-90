package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.entity.User;
import model.service.UserService;

import java.awt.*;
import java.io.IOException;


public class VipController {
    public AnchorPane vipPane;
    @FXML
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
    }

    public void closeVip(MouseEvent mouseEvent) {
        Stage stage = (Stage) vipCard.getScene().getWindow();
        AnchorPane anchorPane= (AnchorPane) stage.getScene().getRoot();
        anchorPane.getChildren().remove(this.vipPane);
    }

    public void showVip(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) vipPane.getScene().getWindow();
        stage.setTitle("VIP");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/fxml/VipPage.fxml"));
        AnchorPane account = loader.load();
        AnchorPane anchorPane= (AnchorPane) stage.getScene().getRoot();
        anchorPane.getChildren().remove(2);
        anchorPane.getChildren().add(2, account);
        account.setLayoutX(200);
        account.setLayoutY(75);
    }
}
