package controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.entity.User;
import model.service.AccountService;
import model.service.UserService;
import java.io.IOException;



public class VipPageForOrdinaryController {
    public AnchorPane vipPane;
    public AnchorPane getStart;

    public AnchorPane vipCard;
    public Label userName;
    public Circle userImage;
    public Label vipInf;
    private String name;

    private UserService userService = new UserService();
    private AccountService accountService = new AccountService();

    public void init(){
        this.name = LoginController.userName;
        userName.setText(name);
        User user = (User) userService.getUser(name).getObject();
        //Image image = new Image( );
    }

    @FXML
    public void initialize(){

    }

    public void closeVip(MouseEvent mouseEvent) {
        Stage stage = (Stage) vipPane.getScene().getWindow();
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
        AnchorPane anchorPane= (AnchorPane) stage.getScene().getRoot();
        anchorPane.getChildren().remove(2);
        anchorPane.getChildren().add(2, account);
        account.setLayoutX(200);
        account.setLayoutY(75);
        closeTheVip();
    }
}
