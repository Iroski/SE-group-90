package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.Main;
import model.entity.ReturnEntity;
import model.entity.User;
import model.service.UserService;
import java.io.IOException;


public class ProfileController {
    public Label UserName;
    public Label Gender;
    public Label Height;
    public Label Weight;
    public Button editProfile;
    public ImageView imageView;
    public User user;
    @FXML
    public void initialize() throws NullPointerException {
        UserService service = new UserService();
        ReturnEntity returnEntity = service.getUser(LoginController.userName);
        User user = (User) returnEntity.getObject();
        UserName.setText(user.getName());
        Gender.setText(user.getGender());
        Image image = new Image(user.getProfilePhotoPath());
        imageView.setImage(image);
        try{
            Height.setText(user.getHeight().toString());
        }catch (NullPointerException e) {
            Height.setText(null);
        }
        try{
            Weight.setText(user.getWeight().toString());
        }catch (NullPointerException e) {
            Weight.setText(null);
        }
    }

    public void goToEditPage(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/view/fxml/" + "EditPage.fxml"));
        AnchorPane page = loader.load();
        //page.getChildren().add(imageView);
        Stage editStage = new Stage();
        editStage.setTitle("EditPage");
        Scene scene = new Scene(page);
        editStage.setScene(scene);
        EditPageController controller = loader.getController();
        controller.setEditStage(editStage);
        editStage.showAndWait();
        initialize();
    }

    public void changeImage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/view/fxml/" + "ChooseImage.fxml"));
        AnchorPane page = loader.load();
        Stage chooseImageStage = new Stage();
        chooseImageStage.setTitle("ChooseImage");
        Scene scene = new Scene(page);
        chooseImageStage.setScene(scene);
        ChooseImageController controller = loader.getController();
        controller.setChooseImageStage(chooseImageStage);
        chooseImageStage.showAndWait();
        initialize();
    }
}
