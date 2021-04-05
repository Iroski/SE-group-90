package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.dao.entity.Coach;

import java.io.IOException;

import static javafx.scene.paint.Color.*;

public class MainPageController {
    @FXML
    Button b_home;

    Image image = new Image("view/images/1.png");

    public void buttonColorChange1(MouseEvent me){
        Button b = (Button) me.getSource();
        if(me.getEventType().equals(MouseEvent.MOUSE_ENTERED))
            b.setTextFill(PINK);
        else if(me.getEventType().equals(MouseEvent.MOUSE_EXITED))
            b.setTextFill(BLACK);
    }

    public void buttonColorChange2(MouseEvent me){
        Button b = (Button) me.getSource();
        if(me.getEventType().equals(MouseEvent.MOUSE_ENTERED))
            b.setTextFill(PINK);
        else if(me.getEventType().equals(MouseEvent.MOUSE_EXITED))
            b.setTextFill(WHITE);
    }

    public void goToCoach(MouseEvent mouseEvent) throws IOException {
        //choosedCoach = (Coach) photo.getUserData();

        Stage stage = (Stage) b_home.getScene().getWindow();
        stage.setTitle("Coaches");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/fxml/Coach.fxml"));
        AnchorPane coaches = (AnchorPane) loader.load();
        // Set person overview into the center of root layout.
        AnchorPane anchorPane= (AnchorPane) stage.getScene().getRoot();
        anchorPane.getChildren().remove(3);
        anchorPane.getChildren().add(3, coaches);

        coaches.setLayoutX(200);
        coaches.setLayoutY(75);
    }
}
