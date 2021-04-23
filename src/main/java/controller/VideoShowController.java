package controller;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;


public class VideoShowController {
    public Button playBT;
    public Button stopBT;
    public Button maxBT;
    public Button volumeBT;
    public Button backButton;
    public Label timeLB;
    public Slider processSD;
    public Slider volumeSD;
    public MediaView mediaView;
    public VBox controlBar;
    public BorderPane mediaPane;
    public AnchorPane anchorPane;

    //image of components
    private String playIcon  = getClass().getResource("/view/images/play.png").toString();
    private String pauseIcon  = getClass().getResource("/view/images/pause.png").toString();
    private String stopIcon  = getClass().getResource("/view/images/stop.png").toString();
    private String volOffIcon  = getClass().getResource("/view/images/volume_off.png").toString();
    private String volOnIcon  = getClass().getResource("/view/images/volume_On.png").toString();
    private String maxIcon  = getClass().getResource("/view/images/max.png").toString();

    private MediaPlayer mediaPlayer;
    private Media media;
    private String url;     //the URL address of videos
    private Scene scene ;

    private boolean atEndOfMedia = false;    //if the video finishes
    private final boolean repeat = false;   //if the video repeats
    private double volumeValue;      //store the volume value before muted
    private Duration duration ;        //the duration of the video
    private int mediaHeight;        //the size of video
    private int mediaWidth;

    private int currentHeight;    //the size of MediaPlayer
    private int currentWidth;

    public void initialize() {
        //set the images of components
        setIcon(playBT,playIcon,25);
        setIcon(stopBT,stopIcon,25);
        setIcon(volumeBT,volOnIcon,15);
        setIcon(maxBT,maxIcon,25);

        java.io.File file = new java.io.File("src/main/resources/view/videos/testVideo.mp4");
        url = file.toURI().toString();
        media = new Media(url);
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);

        scene = playBT.getScene();

        mediaPlayer.setCycleCount(repeat ? MediaPlayer.INDEFINITE : 1);
        //When the video is ready, update the data of progress bar, time tag and volume bar, and set the layout size
        mediaPlayer.setOnReady(new Runnable(){
            @Override
            public void run() {
                duration = mediaPlayer.getMedia().getDuration();
                volumeValue = mediaPlayer.getVolume();

                mediaHeight = media.getHeight();
                mediaWidth = media.getWidth();

                //Set layout size
                setSize(1000,675);

                //Set UI changes for full screen: the toolbar appears only when the mouse enters MediaView
                EventHandler onScreen = new EventHandler<InputEvent>(){
                    @Override
                    public void handle(InputEvent event) {
                        controlBar.setVisible(true);
                    }
                };
                EventHandler offScreen = new EventHandler<InputEvent>(){
                    @Override
                    public void handle(InputEvent event) {
                        controlBar.setVisible(false);
                    }
                };
                if(scene != null){
                    ((Stage)scene.getWindow()).fullScreenProperty().addListener(new ChangeListener<Boolean>() {
                        @Override
                        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                            if (newValue.booleanValue()) {
                                controlBar.setVisible(false);
                                mediaPane.addEventHandler(MouseEvent.MOUSE_CLICKED, onScreen);
                                controlBar.addEventHandler(MouseEvent.MOUSE_EXITED, offScreen);
                            }else{
                                controlBar.setVisible(true);
                                mediaPane.removeEventHandler(MouseEvent.MOUSE_CLICKED,onScreen);
                                controlBar.removeEventHandler(MouseEvent.MOUSE_EXITED,offScreen);
                            }
                        }
                    });
                }
                updateValues();
            }
        });

        //When the current progress of mediaplayer changes, the data of progress bar, time tag and volume bar will change
        mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>(){
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                updateValues();
            }
        });
    }

    //set stop or play the video when click the MediaView
    @FXML
    private void setMediaViewOnClick(){
        mediaView.setOnMouseClicked(event -> {
            if(media == null)
                return;
            MediaPlayer.Status status = mediaPlayer.getStatus();
            if(status == MediaPlayer.Status.UNKNOWN || status == MediaPlayer.Status.HALTED ){
                return;
            }
            //When the videos stop
            if(status == MediaPlayer.Status.PAUSED || status == MediaPlayer.Status.READY || status == MediaPlayer.Status.STOPPED){
                if(atEndOfMedia){
                    mediaPlayer.seek(mediaPlayer.getStartTime());
                    atEndOfMedia = false;
                }
                mediaPlayer.play();
                setIcon(playBT,pauseIcon,25);
            }else{
                //When the video plays
                mediaPlayer.pause();
                setIcon(playBT,playIcon,25);
            }
        });
    }

    //Set play button action
    @FXML
    private void setPlayButton(){
        playBT.setOnAction((ActionEvent e)->{
            if(media == null)
                return;
            MediaPlayer.Status status = mediaPlayer.getStatus();
            if(status == MediaPlayer.Status.UNKNOWN || status == MediaPlayer.Status.HALTED ){
                return;
            }
            //When the videos stop
            if(status == MediaPlayer.Status.PAUSED || status == MediaPlayer.Status.READY || status == MediaPlayer.Status.STOPPED){
                if(atEndOfMedia){
                    mediaPlayer.seek(mediaPlayer.getStartTime());
                    atEndOfMedia = false;
                }
                mediaPlayer.play();
                setIcon(playBT,pauseIcon,25);
            }else{
                //When the video plays
                mediaPlayer.pause();
                setIcon(playBT,playIcon,25);
            }
        });
    }

    //Set stop button action
    @FXML
    private void setStopButton(){
        stopBT.setOnAction((ActionEvent e )->{
            if(media == null)
                return;
            mediaPlayer.stop();
            setIcon(playBT,playIcon,25);
        } );
    }

    //Set processSlider action
    @FXML
    private void setProcessSlider(){
        processSD.valueProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if(processSD.isValueChanging()){
                    //Add the judgment that slider is changing, otherwise, due to the existence of update thread, mediaplayer will continue to loop around
                    mediaPlayer.seek(duration.multiply(processSD.getValue()/100.0));
                }
            }
        });
    }

    //Set volume button action
    @FXML
    private void setVolumeButton(){
        volumeBT.setOnAction((ActionEvent e)->{
            if(media == null)
                return;

            if(mediaPlayer.getVolume()>0){
                volumeValue = mediaPlayer.getVolume();
                volumeSD.setValue(0);
                setIcon(volumeBT,volOffIcon,25);
            }else{
                mediaPlayer.setVolume(volumeValue);
                volumeSD.setValue(volumeValue * 100);
                setIcon(volumeBT,volOnIcon,15);
            }
        });
    }

    //set volumeSlide action
    @FXML
    private void setVolumeSD(){
        volumeSD.valueProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                mediaPlayer.setVolume(newValue.doubleValue()/100);
            }
        });
    }

    //Update video data (progress bar, time tag, volume bar data)
    protected void updateValues(){
        if(processSD != null && timeLB!=null && volumeSD != null && volumeBT != null){
            Platform.runLater(new Runnable(){
                @Override
                public void run() {
                    Duration currentTime = mediaPlayer.getCurrentTime();
                    timeLB.setText(formatTime(currentTime,duration));    //set time label
                    processSD.setDisable(duration.isUnknown());   //Hide progress bar when time cannot be read
                    if(!processSD.isDisabled() && duration.greaterThan(Duration.ZERO) && !processSD.isValueChanging()){
                        processSD.setValue(currentTime.toMillis()/duration.toMillis() * 100);   //set slide
                    }
                    if(!volumeSD.isValueChanging()){
                        volumeSD.setValue((int)Math.round(mediaPlayer.getVolume() *100));   //set volume
                        if(mediaPlayer.getVolume() == 0){        //set volume button
                            setIcon(volumeBT,volOffIcon,20);
                        }else{
                            setIcon(volumeBT,volOnIcon,20);
                        }
                    }
                }
            });
        }
    }

    //Format the duration data for playing time tags
    protected String formatTime(Duration elapsed,Duration duration){
        //Two duartion parameters are converted to HH: mm: SS and output
        int intElapsed = (int)Math.floor(elapsed.toSeconds());
        int elapsedHours = intElapsed / (60 * 60);
        int elapsedMinutes = (intElapsed - elapsedHours *60 *60)/ 60;
        int elapsedSeconds = intElapsed - elapsedHours * 60 * 60 - elapsedMinutes * 60;
        if(duration.greaterThan(Duration.ZERO)){
            int intDuration = (int)Math.floor(duration.toSeconds());
            int durationHours = intDuration / (60 * 60);
            int durationMinutes = (intDuration - durationHours *60 * 60) / 60;
            int durationSeconds = intDuration - durationHours * 60 * 60 - durationMinutes * 60;

            if(durationHours > 0){
                return String.format("%02d:%02d:%02d / %02d:%02d:%02d",elapsedHours,elapsedMinutes,elapsedSeconds,durationHours,durationMinutes,durationSeconds);
            }else{
                return String.format("%02d:%02d / %02d:%02d",elapsedMinutes,elapsedSeconds,durationMinutes,durationSeconds);
            }
        }else{
            if(elapsedHours > 0){
                return String.format("%02d:%02d:%02d / %02d:%02d:%02d",elapsedHours,elapsedMinutes,elapsedSeconds);
            }else{
                return String.format("%02d:%02d / %02d:%02d",elapsedMinutes,elapsedSeconds);
            }
        }
    }

    //get icon of components
    private void setIcon(Button button,String path,int size){
        Image icon = new Image(path);
        ImageView imageView = new ImageView(icon);
        imageView.setFitWidth(size);
        imageView.setFitHeight((int)(size * icon.getHeight() / icon.getWidth()));
        button.setGraphic(imageView);
        //set icon to light up when clicked
        ColorAdjust colorAdjust = new ColorAdjust();
        button.setOnMousePressed(event ->  {
            colorAdjust.setBrightness(0.5);
            button.setEffect(colorAdjust);
        });
        button.setOnMouseReleased(event -> {
            colorAdjust.setBrightness(0);
            button.setEffect(colorAdjust);
        });
    }

    //Set player size
    public void setSize(int width,int height){
        currentWidth = width;
        currentHeight  = height;
        setUISuitable();
    }

    //UI control adaptive size
    private void setUISuitable(){
        anchorPane.setPrefSize(currentWidth,currentHeight);
        anchorPane.setBottomAnchor(controlBar, 0.0);    //set the location of slide
        anchorPane.setTopAnchor(mediaPane,((double)currentHeight  - (double)currentWidth *(double)mediaHeight / (double)mediaWidth - 50)/2);  //set the location of video pane
        mediaView.setFitWidth(currentWidth);       //set the size of mediaView
        mediaView.setFitHeight((double)currentWidth*(double)mediaHeight / (double)mediaHeight);
        controlBar.setPrefWidth(currentWidth);  //set the width of tools
    }

    public void backToHome(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setTitle("Home");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/fxml/MainPage.fxml"));
        AnchorPane home = (AnchorPane) loader.load();
        // Set person overview into the center of root layout.
        AnchorPane anchorPane= (AnchorPane) stage.getScene().getRoot();
        anchorPane.getChildren().remove(2);
        anchorPane.getChildren().add(2, home);

        home.setLayoutX(200);
        home.setLayoutY(75);
    }
}
