package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.Main;
import model.service.AccountService;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * @description: TODO
 * @author Yuhang Lu
 * @date
 * @version 1.0
 * @modifiedBy Yifei Cao
 */
public class TopUpController {
    public Button backButton;
    public Label l1;
    public Label l2;
    public Label l3;
    public Label l4;
    public Label l5;
    public Label l6;
    public Label l7;
    public Label l8;
    public Button confirmButton;
    public Label amountLabel;
    public static int amount = 0;
    @FXML
    public void initialize() {
    }

    public void backToAccount(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setTitle("Account");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/fxml/Account.fxml"));
        AnchorPane account = (AnchorPane) loader.load();
        // Set person overview into the center of root layout.
        AnchorPane anchorPane= (AnchorPane) stage.getScene().getRoot();
        anchorPane.getChildren().remove(2);
        anchorPane.getChildren().add(2, account);

        account.setLayoutX(200);
        account.setLayoutY(75);
    }

    /**
     * Handle selecting.
     * @author Yifei Cao
     */
    public void selectAmount(MouseEvent mouseEvent) throws IOException {
        resetSelected();
        Label label = (Label) mouseEvent.getSource();
        label.setStyle("-fx-border-color: pink ; -fx-background-color: pink ");
        if(label.getText().startsWith("$")){
            amount = Integer.parseInt(label.getText().substring(1));
            amountLabel.setText("$"+amount);
            //l8.setText("Custom");
        }
        else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/fxml/" + "CustomPage.fxml"));
            AnchorPane page = loader.load();
            Stage CustomAmount = new Stage();
            CustomAmount.setTitle("CustomAmount");
            Scene scene = new Scene(page);
            CustomAmount.setScene(scene);
            CustomController controller = loader.getController();
            controller.setSignUpStage(CustomAmount);
            CustomAmount.showAndWait();
            amountLabel.setText("$"+amount);
        }
    }
    /**
     * Reset all the top up buttons so that none of them are being selected.
     * @author Yifei Cao
     */
    public void resetSelected(){
        l1.setStyle("-fx-border-color: black ; -fx-background-color: white ");
        l2.setStyle("-fx-border-color: black ; -fx-background-color: white ");
        l3.setStyle("-fx-border-color: black ; -fx-background-color: white ");
        l4.setStyle("-fx-border-color: black ; -fx-background-color: white ");
        l5.setStyle("-fx-border-color: black ; -fx-background-color: white ");
        l6.setStyle("-fx-border-color: black ; -fx-background-color: white ");
        l7.setStyle("-fx-border-color: black ; -fx-background-color: white ");
        l8.setStyle("-fx-border-color: black ; -fx-background-color: white ");
    }

    /**
     * Submit topping up.
     * @author Yifei Cao
     */
    public void handleConfirm(MouseEvent mouseEvent) throws IOException{
        AccountService accountService = new AccountService();
        BigDecimal orderMoney =BigDecimal.valueOf((int)amount*(-1));
        int code = accountService.updateBalance(LoginController.userName,orderMoney);
        if(code == 200){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("Thanks for topping up!");
            alert.headerTextProperty().set("You have topped up successfully!");
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.titleProperty().set("Error!");
            alert.headerTextProperty().set("Sorry! Please try again later!");
            alert.showAndWait();
        }
    }
}
