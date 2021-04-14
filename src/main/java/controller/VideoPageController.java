package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;


public class VideoPageController {
    public javafx.scene.control.Button test;
    @FXML
    public void initialize() {
        test.setVisible(false);
    }

    public void showVideo(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) test.getScene().getWindow();
        java.io.File file = new java.io.File("src/main/resources/view/images/test1.mp4");
        String url = file.toURI().toString();
        Media media = new Media(url);
        MediaPlayer mPlayer = new MediaPlayer(media);
        MediaView mView = new MediaView(mPlayer);
        mView.setFitWidth(1500);
        mView.setFitHeight(700);
        Button pBut=new Button(">");
        //判断是否要求播放视频
        pBut.setOnAction(e->
        {
            if(pBut.getText().equals(">"))
            {
                //开始播放，并更改按钮上的文字
                mPlayer.play();
                pBut.setText("||");
            }
            else
            {
                //播放停止，并更改按钮上的文字
                mPlayer.pause();
                pBut.setText(">");
            }
        });
        //创建重新播放按钮
        Button rBut=new Button("<<");
        //返回到起点播放
        rBut.setOnAction(e->mPlayer.seek(Duration.ZERO));
        //创建滑动条
        Slider sVol=new Slider();
        //设置滑动条的最小宽度、首选宽度
        sVol.setMinWidth(30);
        sVol.setPrefWidth(150);
        /*
        默认滑动条的刻度值为100，这里设置滑动条一开始所在的值在50，
        也就是说滑动条处于中间位置
         */
        sVol.setValue(50);
        //将滑动条除以100（滑动条的长度）得到的百分率与播放器的音量绑定在一起
        mPlayer.volumeProperty().bind(sVol.valueProperty().divide(100));
        HBox hB=new HBox(10);
        hB.setAlignment(Pos.CENTER);
        Label vol=new Label("音量");
        hB.getChildren().addAll(pBut,rBut,vol,sVol);
        BorderPane bPane=new BorderPane();
        bPane.setCenter(mView);
        bPane.setBottom(hB);
        Scene scene=new Scene(bPane);
        stage.setTitle("视频播放器");
        stage.setScene(scene);
        stage.show();
    }

}
