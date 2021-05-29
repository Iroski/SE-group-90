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
    public double lessonPrice = 30;
    public Button pay;
    public Button notPay;
    public VBox infoBox;

    @FXML
    public void initialize() {
    }

    public void init(){
        Label label = (Label) infoBox.getChildren().get(0);
        String info = "";
        if(lessonPrice == BookingPageController.lessonPrice){
            info = "You should pay ￡" + String.format("%.2f", lessonPrice) + " for the live lesson";
        }else{
            info = "You should pay ￡" + String.format("%.2f", lessonPrice) + " for the live lesson "
                    + "(Original price: ￡" + String.format("%.2f", BookingPageController.lessonPrice) + ")";
        }
        label.setText(info);
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

    public void setPrice(double price) {
        this.lessonPrice = price;
    }
}
