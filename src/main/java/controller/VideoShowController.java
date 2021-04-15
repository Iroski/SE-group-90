package controller;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class VideoShowController {
    public MediaView mView;
    public void initialize() {
        java.io.File file = new java.io.File("src/main/resources/view/images/test.mp4");
        String url = file.toURI().toString();
        Media media = new Media(url);
        MediaPlayer mPlayer = new MediaPlayer(media);
        mView.setMediaPlayer(mPlayer);
    }

}
