package controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class VipPageForOrdinaryController {
    public AnchorPane vipPane;
    public HBox choosePay;
    public Rectangle annually;
    public Rectangle monthly;
    public AnchorPane getStart;
    public AnchorPane annuallyPane;
    public AnchorPane monthlyPane;

    @FXML
    public void setChoosePay(MouseEvent mouseEvent){
        AnchorPane pane = (AnchorPane)mouseEvent.getSource();
        if(pane == annuallyPane){
            monthly.setFill(Color.rgb(255,255,255));
            annually.setFill(Color.rgb(255,223,169));
        }
        else if(pane == monthlyPane){
            annually.setFill(Color.rgb(255,255,255));
            monthly.setFill(Color.rgb(255,223,169));
        }
    }

    public void closeVip(MouseEvent mouseEvent) {
        Stage stage = (Stage) annually.getScene().getWindow();
        AnchorPane anchorPane= (AnchorPane) stage.getScene().getRoot();
        anchorPane.getChildren().remove(this.vipPane);
    }

    public void startPay(MouseEvent mouseEvent){

    }
}
