package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class TopUpController {
    public javafx.scene.control.Button backButton;
    @FXML
    public void initialize() {}

    public void backToAccount() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setTitle("Account");
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
}
