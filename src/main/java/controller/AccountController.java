package controller;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.entity.Coach;

import java.awt.*;
import java.io.IOException;

public class AccountController {

    public javafx.scene.control.Button topup;
    public TextField balance;

    @FXML
    public void initialize() {
        balance.setText("10000");
        balance.setEditable(false);
    }

    public void TopUp(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) topup.getScene().getWindow();
        stage.setTitle("TopUp");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/fxml/TopUp.fxml"));
        AnchorPane topup = (AnchorPane) loader.load();
        // Set person overview into the center of root layout.
        AnchorPane anchorPane= (AnchorPane) stage.getScene().getRoot();
        anchorPane.getChildren().remove(2);
        anchorPane.getChildren().add(2, topup);

        topup.setLayoutX(200);
        topup.setLayoutY(75);
    }
}
