package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.entity.ReturnEntity;
import model.entity.Video;
import model.service.VideoService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainPageController {
    public Button test;
    public ImageView camel;
    List<Video> list;
    public static String path;
    @FXML
    public void initialize() {
        VideoService videoService = new VideoService();
        ReturnEntity returnEntity = videoService.getAllVideos();
        list = (List<Video>) returnEntity.getObject();
        camel.setImage(new Image("view/images/1.png"));
        test.setVisible(false);
    }

    public void showVideo() throws IOException {
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

    public void clickVideo(MouseEvent mouseEvent) throws IOException {
        ImageView imageView = (ImageView) mouseEvent.getSource();
        String videoName = imageView.getId();
        for(int i=0; i<list.size(); i++){
            if(videoName.equals(list.get(i).getStaticVideo().getVideoName())){
                path = list.get(i).getStaticVideo().getFilePath();
                System.out.println(path);
                break;
            }
        }
        showVideo();
    }
}
