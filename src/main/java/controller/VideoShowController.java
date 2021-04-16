package controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

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
