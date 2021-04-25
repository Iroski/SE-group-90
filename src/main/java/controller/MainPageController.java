package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainPageController {
    public Button test;
    public ImageView video1;

    @FXML
    public void initialize() {
        video1.setImage(new Image("view/images/1.png"));
        test.setVisible(false);
    }

    public void showVideo(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) test.getScene().getWindow();
        stage.setTitle("Video Show");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/fxml/VideoShow.fxml"));
        AnchorPane video = (AnchorPane) loader.load();
        // Set person overview into the center of root layout.
        AnchorPane anchorPane= (AnchorPane) stage.getScene().getRoot();
        anchorPane.getChildren().remove(2);
        anchorPane.getChildren().add(2, video);

        video.setLayoutX(200);
        video.setLayoutY(75);
    }

}
