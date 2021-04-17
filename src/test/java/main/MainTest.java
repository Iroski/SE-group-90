package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.dao.base.DataHouse;
import model.utils.ResourceLoader;

import java.io.IOException;

public class MainTest extends Application {

    static DataHouse db;
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/fxml/LoginPage.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();

        db= DataHouse.getInstance();
        db.init("src/test/resources/database");
        ResourceLoader.staticVideoLoader("src/test/resources/view/videos");
    }

    public static void main(String[] args) {

        launch(args);
    }
}
