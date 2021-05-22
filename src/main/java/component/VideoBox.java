package component;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.entity.Video;

/**
 * @author Yifei Cao
 * @version 1.0
 * @description: TODO
 * @date 4/25/2021 20:41
 */
public class VideoBox extends VBox {
    ImageView imageView;
    public VideoBox(String imagePath, String videoName){
        Image image = new Image(imagePath);
        Label label = new Label(videoName);
        label.setStyle("-fx-font-size: 22 ; -fx-text-fill: white");
        imageView = new ImageView(image);
        imageView.setFitWidth(198);
        imageView.setPreserveRatio(true);
        this.getChildren().add(imageView);
        this.getChildren().add(label);
        this.setStyle("-fx-border-color: #04b9f9 ; -fx-background-color: #04b9f9 ; -fx-border-radius: 5px ");
        this.setStyle("-fx-border-color: #98e6ff ; -fx-background-color: #04b9f9 ; -fx-border-radius: 5px ");
        this.setAlignment(Pos.CENTER);
    }
    public void setWidth(int width){
        imageView.setFitWidth(width);
    }

    public void setHeight(int height){
        imageView.setFitHeight(height);
    }
}
