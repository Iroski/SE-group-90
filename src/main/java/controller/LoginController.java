package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.Main;

import java.io.IOException;

public class LoginController {
    public Button LoginButton;

    @FXML
    public void showSignUpPage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/view/fxml/" + "SignUpPage.fxml"));
        AnchorPane page = loader.load();
        Stage signUpStage = new Stage();
        signUpStage.setTitle("SignUpPage");
        Scene scene = new Scene(page);
        signUpStage.setScene(scene);
        SignUpController controller = loader.getController();
        controller.setSignUpStage(signUpStage);
        signUpStage.showAndWait();
    }

    public void goToMainPage (MouseEvent mouseEvent) throws IOException {

        Stage stage = (Stage) LoginButton.getScene().getWindow();
        stage.close();
        stage=new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/fxml/BaseMainPage.fxml"));
        AnchorPane rootLayout=(AnchorPane) loader.load();
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
}
