package controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class VideoPageController {
    public Button test;
    @FXML
    public void initialize() {
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

    public void filterVideosByTag(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) test.getScene().getWindow();
        stage.setTitle("Videos");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/fxml/FilteredVideo.fxml"));
        AnchorPane video = (AnchorPane) loader.load();
        // Set person overview into the center of root layout.
        AnchorPane anchorPane= (AnchorPane) stage.getScene().getRoot();
        anchorPane.getChildren().remove(2);
        anchorPane.getChildren().add(2, video);

        video.setLayoutX(200);
        video.setLayoutY(75);
    }

    public void showAllVideos(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) test.getScene().getWindow();
        stage.setTitle("Videos");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/fxml/VideoPage.fxml"));
        AnchorPane video = (AnchorPane) loader.load();
        // Set person overview into the center of root layout.
        AnchorPane anchorPane= (AnchorPane) stage.getScene().getRoot();
        anchorPane.getChildren().remove(2);
        anchorPane.getChildren().add(2, video);

        video.setLayoutX(200);
        video.setLayoutY(75);
    }
}
