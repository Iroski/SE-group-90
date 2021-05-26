package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.concurrent.atomic.AtomicBoolean;

public class PayForOrderController {
    private AtomicBoolean ifPay  = new AtomicBoolean(false);

    public Button pay;
    public Button notPay;
    public VBox infoBox;

    @FXML
    public void initialize() {
    }

    public AtomicBoolean getIfPay(){
        return this.ifPay;
    }

    private void setIfPay(boolean b){
        this.ifPay.set(b);
    }

    public void handleChoose(MouseEvent mouseEvent){
         Button button = (Button) mouseEvent.getSource();
         if(button.equals(pay)){
             setIfPay(true);
         }
         else{
             setIfPay((false));
         }
         Stage stage = (Stage) pay.getScene().getWindow();
         stage.close();
    }

    public void setLabel(){
        Label label = (Label) infoBox.getChildren().get(0);
        label.setText("The order is free this time as you have free lesson");
    }
}
