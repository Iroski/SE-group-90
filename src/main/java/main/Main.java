package main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.dao.CoachDao;
import model.dao.base.DataBase;
import model.service.CoachService;

import java.io.IOException;

public class Main extends Application {

    static DataBase db;
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/fxml/LoginPage.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        db=DataBase.getInstance();
        db.init("src/main/resources/database");
    }

    public static void main(String[] args) {

        launch(args);
    }
}
