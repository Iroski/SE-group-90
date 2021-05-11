package controller;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ReserveLessonConfirmation {
    public Button yesButton;
    public Button noButton;
    public Label quesLabel;
    public boolean choose;

    public void chooseYes(MouseEvent mouseEvent) {  //到时候再写
        Stage stage= (Stage) yesButton.getScene().getWindow();
        choose = true;
        stage.close();
    }

    public void chooseNo(MouseEvent mouseEvent) {  //到时候再写
        Stage stage= (Stage) yesButton.getScene().getWindow();
        choose = false;
        stage.close();
    }

    @FXML
    public void initialize() {

    }
}
