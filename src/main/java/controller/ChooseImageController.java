package controller;

import component.VideoBox;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.entity.ReturnEntity;
import model.entity.StaticImage;
import model.entity.User;
import model.entity.Video;
import model.service.StaticImageService;
import model.service.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Yifei Cao
 * @version 1.0
 * @description: TODO
 * @date 5/23/2021 19:15
 */
public class ChooseImageController {
    public ImageView imageView1;
    public ImageView imageView2;
    public ImageView imageView3;
    public ImageView imageView4;
    public Rectangle border1;
    public Rectangle border2;
    public Rectangle border3;
    public Rectangle border4;
    private Stage chooseImageStage;
    private ArrayList<String> path = new ArrayList<>();
    private int index = -1;
    @FXML
    public void initialize(){
        border1.setVisible(false);
        border2.setVisible(false);
        border3.setVisible(false);
        border4.setVisible(false);
        StaticImageService staticImageService = new StaticImageService();
        ReturnEntity returnEntity = staticImageService.getDefaultProfilePhotos();
        List<StaticImage> list = (List<StaticImage>) returnEntity.getObject();
        for (int i = 0; i<list.size();i++) {
            path.add(list.get(i).getFilePath());
        }
    }
    public void setChooseImageStage(Stage chooseImageStage) {
        this.chooseImageStage = chooseImageStage;
    }

    public void handleConfirm(MouseEvent mouseEvent) {
        if(index != -1){
            UserService service = new UserService();
            ReturnEntity returnEntity = service.getUser(LoginController.userName);
            User user = (User) returnEntity.getObject();
            user.setProfilePhotoPath(path.get(index));
        }
        BasePageController.changeImage(path.get(index));
        chooseImageStage.close();
    }

    public void handleChoose(MouseEvent mouseEvent){
        UserService service = new UserService();
        ImageView imageView = (ImageView) mouseEvent.getSource();
        border1.setVisible(false);
        border2.setVisible(false);
        border3.setVisible(false);
        border4.setVisible(false);
        if(imageView == imageView1){
            border1.setVisible(true);
            index = 0;
        }
        else if(imageView == imageView2){
            border2.setVisible(true);
            index = 1;
        }
        else if(imageView == imageView3){
            border3.setVisible(true);
            index = 2;
        }
        else {
            border4.setVisible(true);
            index = 3;
        }
    }
}