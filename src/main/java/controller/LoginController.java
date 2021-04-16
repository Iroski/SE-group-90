package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import main.Main;
import model.entity.ReturnEntity;
import model.service.UserService;
import model.utils.Encryption;

import java.io.IOException;

/**
 * @author :Yifei Cao
 * @date :
 * @description:
 * @modifiedBy By:
 * @version :
 */
public class LoginController {
    @FXML
    public Button LoginButton;

    @FXML
    private ImageView imageView;

    @FXML
    private TextField account;

    @FXML
    private PasswordField password;

    public static String userName = "test";
    @FXML
    public void initialize() {
        Rectangle rectangle = new Rectangle(imageView.prefWidth(-1), imageView.prefHeight(-1));
        //Rectangle2D rectangle2 = new Rectangle2D(0, 0, 100, 100);
        rectangle.setArcWidth(100);
        rectangle.setArcHeight(100);
        imageView.setClip(rectangle);
        //imageView.setViewport(rectangle2);
    }

    /**
     * Open the MainPage.
     */
    @FXML
    public void showSignUpPage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/view/fxml/" + "SignUpPage.fxml"));
        AnchorPane page = loader.load();
        //page.getChildren().add(imageView);
        Stage signUpStage = new Stage();
        signUpStage.setTitle("SignUpPage");
        Scene scene = new Scene(page);
        signUpStage.setScene(scene);
        SignUpController controller = loader.getController();
        controller.setSignUpStage(signUpStage);
        signUpStage.showAndWait();
    }

    /**
     * Open the MainPage.
     */
    public void goToMainPage() throws IOException {
        Stage stage = (Stage) LoginButton.getScene().getWindow();
        stage.close();
        stage=new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/fxml/BaseMainPage.fxml"));
        AnchorPane rootLayout=(AnchorPane) loader.load();
        //BasePageController basePageController=loader.getController();
        //basePageController.userName.setUserData();
        Scene scene=new Scene(rootLayout);
        stage.setScene(scene);
        stage.setTitle("MainPage");
        FXMLLoader loader2 = new FXMLLoader();
        loader2.setLocation(getClass().getResource("/view/fxml/MainPage.fxml"));
        AnchorPane mainPage = (AnchorPane) loader2.load();
        // Set person overview into the center of root layout.
        AnchorPane anchorPane= (AnchorPane) stage.getScene().getRoot();
        anchorPane.getChildren().add(2, mainPage);
        mainPage.setLayoutX(200);
        mainPage.setLayoutY(75);
        stage.show();
    }

    /**
     * check the legality of inputs. If so, admit to login.
     */
    @FXML
    public void login(MouseEvent mouseEvent) throws IOException {
        if(account.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.titleProperty().set("Error");
            alert.headerTextProperty().set("Please input username!");
            alert.showAndWait();
            return;
        }
        if(password.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.titleProperty().set("Error");
            alert.headerTextProperty().set("Please input password!");
            alert.showAndWait();
            return;
        }
        UserService service = new UserService();
        ReturnEntity returnEntity = service.login(account.getText(), Encryption.getMD5Str(password.getText()));
        int code = returnEntity.getCode();
        userName = account.getText();
        if(code == 200){
            goToMainPage();
        }
        else if(code == 4004){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.titleProperty().set("Error");
            alert.headerTextProperty().set("wrong password");
            alert.showAndWait();
        }
        else if(code == 4041){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.titleProperty().set("Error");
            alert.headerTextProperty().set("user not exist");
            alert.showAndWait();
        }
        else if(code == 5000){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.titleProperty().set("Error");
            alert.headerTextProperty().set("database error");
            alert.showAndWait();
        }
    }
}
