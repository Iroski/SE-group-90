package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.entity.ReturnEntity;
import model.entity.Video;
import model.service.UserService;

import java.io.IOException;
import java.util.ArrayList;

public class HistoryController {

    public AnchorPane historyPane;
    public VBox allHistory;
    public AnchorPane h1Pane;
    public ImageView history1;
    public Label description1;
    public Label author1;

    public AnchorPane h2Pane;
    public ImageView history2;
    public Label description2;
    public Label author2;

    public AnchorPane h3Pane;
    public ImageView history3;
    public Label description3;
    public Label author3;

    public AnchorPane h4Pane;
    public ImageView history4;
    public Label description4;
    public Label author4;

    public AnchorPane h5Pane;
    public ImageView history5;
    public Label description5;
    public Label author5;

    public AnchorPane h6Pane;
    public ImageView history6;
    public Label description6;
    public Label author6;

    private Image image=new Image("view/images/1.jpg");


    /**
     * This function is used to init the history page
     */
    @FXML
    public void initialize() {
        ArrayList<Video> historyList = null;
        UserService Service = new UserService();
        ReturnEntity returnEntity = Service.getHistoryByName(LoginController.userName);
        if (returnEntity.getCode() == 5000) {
            System.out.println("Data base error!");
        }
        else {
            historyList = (ArrayList<Video>) returnEntity.getObject();
        }
        ImageView[] historys = {history1, history2, history3, history4, history5, history6};
        Label[] authors = {author1, author2, author3, author4, author5, author6};
        Label[] descriptions = {description1, description2, description3, description4, description5, description6};
        AnchorPane[] panes = {h1Pane, h2Pane, h3Pane, h4Pane, h5Pane, h6Pane};
        if (historyList.size()==0) {
            description1.setText("No history Yet");
            panes[0].setStyle("-fx-background-color: white");
        }
        try {
            int flag=0;
            for (int i = 0; i < 6; i++) {
                if (i<historyList.size()) {
                    if (historyList.get(i).getStaticVideo().getCoverPath() == null) {
                        historys[i].setImage(image);
                    } else
                        historys[i].setImage(new Image(historyList.get(i).getStaticVideo().getCoverPath()));
                    authors[i].setText(historyList.get(i).getStaticVideo().getAuthor());
                    descriptions[i].setText(historyList.get(i).getStaticVideo().getVideoName());
                    panes[i].setUserData(historyList.get(i).getStaticVideo().getFilePath());
                    panes[i].setStyle("-fx-background-color: white; -fx-border-color: black");
                    flag++;
                } else {
                    break;
                }
            }
            for (int i=flag;i<6;i++) {
                panes[i].setOnMouseEntered(this::closeHistory);
            }
        } catch (Exception ignored) {

        }
    }

    /**
     * This function is used to close the history page if the mouse leave the zone.
     * @param: mouseEvent will be triggered the mouse leave the zone of history page
     * @throws: IOException
     */
    public void closeHistory(MouseEvent mouseEvent) {
        closeTheHistory();
    }

    /**
     * This function is used to close the history page if the user choose to watch one of the videos.
     * @param: mouseEvent will be triggered after the user clicked any of the videos
     * @throws: IOException
     */
    public void closeTheHistory () {
        try {
            Stage stage = (Stage) historyPane.getScene().getWindow();
            AnchorPane anchorPane = (AnchorPane) stage.getScene().getRoot();
            anchorPane.getChildren().remove(this.historyPane);
        } catch (Exception ignored) {

        }
    }

    /**
     * This function is used to go to the video which is chosen by the user.
     * @param: mouseEvent will be triggered after the user clicked any of the videos
     * @throws: IOException
     */
    public void showVideo(MouseEvent mouseEvent) throws IOException {
        AnchorPane selectedHistory= (AnchorPane) mouseEvent.getSource();
        if (selectedHistory.getUserData()==null) {
            return ;
        }
        Stage stage = (Stage) historyPane.getScene().getWindow();
        stage.setTitle("Video Show");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/fxml/VideoShow.fxml"));
        MainPageController.path = (String) selectedHistory.getUserData();
        AnchorPane video = (AnchorPane) loader.load();
        AnchorPane anchorPane = (AnchorPane) stage.getScene().getRoot();
        anchorPane.getChildren().remove(2);
        anchorPane.getChildren().add(2, video);
        video.setLayoutX(200);
        video.setLayoutY(75);
        closeTheHistory();
    }
}
