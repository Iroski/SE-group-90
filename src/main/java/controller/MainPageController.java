package controller;

import com.sun.source.tree.WhileLoopTree;
import component.VideoBox;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import model.entity.ReturnEntity;
import model.entity.Video;
import model.service.UserService;
import model.service.VideoService;

import java.io.IOException;
import java.util.List;

public class MainPageController {
    public Button test;
    public static String previousPage;
    List<Video> list;
    public static String path;
    public FlowPane leftPane;
    public FlowPane rightPane;
    private int index = 0;
    private int slideNum = 0;
    public void initialize() {

        previousPage = "Home";
        VideoService videoService = new VideoService();
        int num = 6;
        slideNum = 2;
        ReturnEntity returnEntity = videoService.getRandomVideosWithNum(num);
        list = (List<Video>) returnEntity.getObject();
        leftPane.setOrientation(Orientation.HORIZONTAL);
        leftPane.setPadding(new Insets(50));
//        leftPane.setHgap(39);
//        leftPane.setVgap(50);
        rightPane.setOrientation(Orientation.HORIZONTAL);
        rightPane.setPadding(new Insets(50));
        rightPane.setHgap(20);
        rightPane.setVgap(20);
        VideoBox videoBox;
        videoBox = new VideoBox(list.get(0).getStaticVideo().getCoverPath(),list.get(0).getStaticVideo().getVideoName());
        videoBox.setOnMouseClicked(mouseEvent -> {
            try {
                showVideo(mouseEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        videoBox.setWidth(413);
        leftPane.getChildren().add(videoBox);
        for(int i=slideNum;i<num;i++){
            videoBox = new VideoBox(list.get(i).getStaticVideo().getCoverPath(),list.get(i).getStaticVideo().getVideoName());
            videoBox.setOnMouseClicked(mouseEvent -> {
                try {
                    showVideo(mouseEvent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            videoBox.setWidth(180);
            rightPane.getChildren().add(videoBox);
        }
        test.setVisible(false);
    }

    public void showVideo(MouseEvent mouseEvent) throws IOException {
        UserService service = new UserService();
        VideoBox videoBox = (VideoBox) mouseEvent.getSource();
        Label label = (Label) videoBox.getChildren().get(1);
        for (Video value : list) {
            if(value.getStaticVideo().getVideoName().equals(label.getText())) {
                MainPageController.path = value.getStaticVideo().getFilePath();
                Long id = value.getId();
                service.setHistoryByName(LoginController.userName,id);
                //System.out.println(id);
                break;
            }
        }
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

    public void prevVideo(){
        index = (index+slideNum-1)%slideNum;
        VideoBox videoBox = new VideoBox(list.get(index).getStaticVideo().getCoverPath(),list.get(index).getStaticVideo().getVideoName());
        videoBox.setOnMouseClicked(mouseEvent -> {
            try {
                showVideo(mouseEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        videoBox.setWidth(413);
        leftPane.getChildren().clear();
        leftPane.getChildren().add(videoBox);
    }

    public void nextVideo(){
        index = (index+1)%slideNum;
        VideoBox videoBox = new VideoBox(list.get(index).getStaticVideo().getCoverPath(),list.get(index).getStaticVideo().getVideoName());
        videoBox.setOnMouseClicked(mouseEvent -> {
            try {
                showVideo(mouseEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        videoBox.setWidth(413);
        leftPane.getChildren().clear();
        leftPane.getChildren().add(videoBox);
    }
}
