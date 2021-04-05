package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.Main;

import java.io.IOException;

public class LoginController {
    @FXML
    public void showSignUpPage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/view/fxml/" +
                "SignUpPage.fxml"));
        AnchorPane page = loader.load();
        Stage signUpStage = new Stage();
        signUpStage.setTitle("SignUpPage");
        Scene scene = new Scene(page);
        signUpStage.setScene(scene);
        SignUpController controller = loader.getController();
        controller.setSignUpStage(signUpStage);
        signUpStage.showAndWait();
    }
}
