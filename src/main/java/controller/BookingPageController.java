package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;


public class BookingPageController {

    public Circle coach_photo;
    public ChoiceBox<LocalDate> dateChoose;
    public Label coach_name;
    public CheckBox firstLesson;
    public CheckBox secondLesson;
    public CheckBox thirdLesson;
    public CheckBox fourthLesson;
    public CheckBox fifthLesson;

    @FXML
    public void initialize(){
        Image image = new Image("view/images/coach.jpg");
        coach_photo.setFill(new ImagePattern(image));
        dateChoose.getItems().addAll(LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now().plusDays(2), LocalDate.now().plusDays(3), LocalDate.now().plusDays(4));
        dateChoose.getSelectionModel().selectFirst();
        /*ArrayList<LocalDate> d = new ArrayList<>();
        d.add(LocalDate.now());
        ObservableList<LocalDate> dates = FXCollections.observableList(d);
        dateChoose.setItems(dates);*/

        /*ArrayList<LocalDate> d = new ArrayList<>();
        d.add(LocalDate.now());
        ObservableList<LocalDate> dates = FXCollections.observableList(d);
        dateChoose.setItems(dates);*/
    }


    public void closeThePage(MouseEvent mouseEvent) {
        Stage stage= (Stage) coach_photo.getScene().getWindow();
        stage.close();
    }

    public void chooseFinished(MouseEvent mouseEvent) throws IOException {  //出个弹窗
        Stage stage=new Stage();
        stage.setTitle("Confirmation");
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/fxml/ReserveLessonConfirmation.fxml"));
        AnchorPane layout= loader.load();
        ReserveLessonConfirmation reserveLessonConfirmation=loader.getController();
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.showAndWait();
        if (reserveLessonConfirmation.tempLabel.getText()=="yes") {
            Stage stage1=(Stage)coach_photo.getScene().getWindow();
            stage1.close();
        }
    }
}

