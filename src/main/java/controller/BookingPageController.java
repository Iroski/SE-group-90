package LZH_Pages;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;


public class BookingPageController {

    public Circle coach_photo;
    public ChoiceBox<LocalDate> dateChoose;
    public ChoiceBox<LocalTime> timeChoose;
    @FXML
    public void initialize(){
        Image image = new Image("pictures/user_picture1.png");
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

}

