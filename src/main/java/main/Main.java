package main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.dao.base.DataHouse;

import java.io.IOException;

public class Main extends Application {

    static DataHouse db;
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/fxml/LoginPage.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        db= DataHouse.getInstance();
        db.init("src/main/resources/database");
    }

    public static void main(String[] args) {

        launch(args);
    }
}
