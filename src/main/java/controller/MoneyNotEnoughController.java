package controller;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MoneyNotEnoughController {
    public Button okButton;

    public void clickOk(MouseEvent mouseEvent) {
        Stage stage= (Stage) okButton.getScene().getWindow();
        stage.close();
    }
}
