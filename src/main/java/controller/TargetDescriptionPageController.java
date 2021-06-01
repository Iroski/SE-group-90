package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



public class TargetDescriptionPageController {
    public AnchorPane descPane;
    public Button okButton;
    public TextArea textArea;

    @FXML
    public void initialize(){}

    public void closeWindow(MouseEvent mouseEvent){
        Stage stage = (Stage) textArea.getScene().getWindow();
        AnchorPane anchorPane = (AnchorPane) stage.getScene().getRoot();
        anchorPane.getChildren().remove(this.descPane);
    }

    public void setText(String exercise){
        textArea.textProperty().set(exercise);
    }
}
