package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.util.concurrent.atomic.AtomicBoolean;

public class PayForOrderController {
    private AtomicBoolean ifPay  = new AtomicBoolean(false);

    @FXML
    public Button pay;
    @FXML
    public  Button notPay;

    @FXML
    public void initialize() {}

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
}
