package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ReserveLessonConfirmation {
    public Button yesButton;
    public Button noButton;
    public Label tempLabel;
    public void chooseYes(MouseEvent mouseEvent) {  //到时候再写
        Stage stage= (Stage) yesButton.getScene().getWindow();
        tempLabel.setText("yes");
        stage.close();
    }
    public void chooseNo(MouseEvent mouseEvent) {  //到时候再写
        Stage stage= (Stage) yesButton.getScene().getWindow();
        tempLabel.setText("no");
        stage.close();
    }

    @FXML
    public void initialize() {
        tempLabel.setText("");
        tempLabel.setVisible(false);
    }
}
