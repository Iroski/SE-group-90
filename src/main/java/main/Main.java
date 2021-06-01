package main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.dao.base.DataHouse;
import model.service.AccountService;
import model.service.LiveLessonService;
import model.utils.ResourceLoader;

import java.io.IOException;

public class Main extends Application {

    static DataHouse db;
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/fxml/LoginPage.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.getIcons().add(new Image("/view/images/FIT.png"));
        primaryStage.setResizable(false);
        primaryStage.show();

//        db= DataHouse.getInstance();
//        db.init("src/main/resources/database");
        db= DataHouse.getInstance();
        db.init("src/main/resources/database");
        ResourceLoader.staticVideoLoader("src/main/resources/view/videos");
        ResourceLoader.staticDefaultProfilePhotoLoader("src/main/resources/view/images/default/profilephoto");
        new AccountService().createAccountForDeletedInfo();
        new LiveLessonService().createInfoForDeleteInfo();
    }

}
