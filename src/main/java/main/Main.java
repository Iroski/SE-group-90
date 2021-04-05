package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author ：Yubo Wang
 * @date ：2021-04-04 17:35
 * @description：
 * @modified By：
 * @version:
 */
public class Main extends Application {
    private Stage primaryStage;
    public AnchorPane rootLayout;

    public AnchorPane getRootLayout() {
        return rootLayout;
    }

    public void setRootLayout(AnchorPane rootLayout) {
        this.rootLayout = rootLayout;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage=primaryStage;
        initRootLayout();
        showPersonOverview();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/fxml/BaseMainPage.fxml"));
            rootLayout = (AnchorPane) loader.load();
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showPersonOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/fxml/Coach.fxml"));
            AnchorPane coaches = (AnchorPane) loader.load();
            // Set person overview into the center of root layout.
            rootLayout.getChildren().add( 3, coaches);
            coaches.setLayoutX(200);
            coaches.setLayoutY(75);
            // Give the controller access to the main app.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        launch(args);
//    }
}