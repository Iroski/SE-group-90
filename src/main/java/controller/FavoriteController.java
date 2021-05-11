package controller;

import component.VideoBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import model.entity.ReturnEntity;
import model.entity.Video;
import model.service.UserService;
import model.service.VideoService;

import java.io.IOException;
import java.util.List;

/**
 * @author smile
 * @version 1.0
 * @description: TODO
 * @date 5/11/2021 19:21
 */
public class FavoriteController {
    public FlowPane flowPane;
    public ImageView searchImage;
    public TextField searchText;
    public ComboBox category;
    List<Video> list;

    @FXML
    public void initialize() throws IOException {
        category.getItems().addAll(
                "All videos",
                "running",
                "yoga",
                "biking"
        );
        category.getSelectionModel().selectFirst();
        category.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @SneakyThrows
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                handleSelect();
            }
        });
        UserService userService = new UserService();
        ReturnEntity returnEntity = userService.getFavoriteByName(LoginController.userName);
        list = (List<Video>) returnEntity.getObject();
        flowPane.setOrientation(Orientation.HORIZONTAL);
        flowPane.setPadding(new Insets(39));
        flowPane.setHgap(39);
        flowPane.setVgap(50);

        MainPageController.previousPage = "Videos";
        flowPane.getChildren().clear();
        for(int i=0; i<list.size(); i++){
            VideoBox videoBox = new VideoBox(list.get(i).getStaticVideo().getCoverPath(),list.get(i).getStaticVideo().getVideoName());
            videoBox.setOnMouseClicked(mouseEvent -> {
                try {
                    showVideo(mouseEvent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            flowPane.getChildren().add(videoBox);
        }

        searchImage.setImage(new Image("view/images/searchImage.png"));
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
        Stage stage = (Stage) category.getScene().getWindow();
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

    public void filterVideosByTag(String tag) throws IOException {
        flowPane.getChildren().clear();
        int counter = 0;
        List<String> tagName;
//        Stage stage = (Stage) all.getScene().getWindow();
//        stage.setTitle(tag);
        for (Video value : list) {
            if(value.getTagsName()!=null) {
                tagName = value.getTagsName();
                for (String s : tagName) {
                    if (tag.equals(s)) {
                        counter++;
                        VideoBox videoBox = new VideoBox(value.getStaticVideo().getCoverPath(),value.getStaticVideo().getVideoName());
                        videoBox.setOnMouseClicked(mouseEvent1 -> {
                            try {
                                showVideo(mouseEvent1);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                        flowPane.getChildren().add(videoBox);
                    }
                }
            }
        }
        if(counter==0){
            Label noVideo = new Label("Sorry, there isn't any video with this tag");
            noVideo.setAlignment(Pos.CENTER);
            noVideo.setPrefSize(500,200);
            flowPane.getChildren().add(noVideo);
        }
    }

    public void showAllVideos() {
//        Stage stage = (Stage) all.getScene().getWindow();
//        stage.setTitle("Videos");
        MainPageController.previousPage = "Videos";
        flowPane.getChildren().clear();
        for(int i=0; i<list.size(); i++){
            VideoBox videoBox = new VideoBox(list.get(i).getStaticVideo().getCoverPath(),list.get(i).getStaticVideo().getVideoName());
            videoBox.setOnMouseClicked(mouseEvent -> {
                try {
                    showVideo(mouseEvent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            flowPane.getChildren().add(videoBox);
        }
    }

    public void SearchVideo(MouseEvent mouseEvent) throws IOException{
        String text = searchText.getText();
        //Todo
        if(text.equals("camel1")){
            showAllVideos();
        }
    }

    public void handleSelect() throws IOException {
        String selection = category.getValue().toString();
        if(selection.equals("All videos")){
            showAllVideos();
        }
        else{
            filterVideosByTag(selection);
        }
    }

}