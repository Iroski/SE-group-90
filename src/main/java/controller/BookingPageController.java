package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalTime;


public class BookingPageController {

    public Circle coach_photo;
    public ChoiceBox<LocalDate> dateChoose;
    public ChoiceBox<LocalTime> timeChoose;
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

    public void chooseFinished(MouseEvent mouseEvent) {
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("nigeiwoligiaogiao");
        alert.setContentText("Are you sure to choose these lessons?");
        alert.setHeaderText("giao");
    }
}

