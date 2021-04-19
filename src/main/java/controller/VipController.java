package controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VipController {
    public AnchorPane vipPane;
    @FXML
    AnchorPane vipCard;

    @FXML
    private void initialize(){

    }

    public void closeVip(MouseEvent mouseEvent) {
        Stage stage = (Stage) vipCard.getScene().getWindow();
        AnchorPane anchorPane= (AnchorPane) stage.getScene().getRoot();
        anchorPane.getChildren().remove(this.vipPane);
    }
}
