package controller;

import javafx.collections.ObservableList;
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
import model.entity.Coach;
import model.entity.ReturnEntity;
import model.service.CoachService;
import model.utils.DateUtils;

import java.io.IOException;
import java.time.*;
import java.util.LinkedList;
import java.util.List;


public class BookingPageController {

    public Circle coach_photo;
    public ChoiceBox<LocalDate> dateChoose;
    public Label coach_name;
    public CheckBox firstLesson;
    public CheckBox secondLesson;
    public CheckBox thirdLesson;
    public CheckBox fourthLesson;
    public CheckBox fifthLesson;


    LinkedList<LocalDate> day_list;
    LinkedList<LocalTime> time_list;
    @FXML
    public void initialize(){
        Coach coach = (Coach) coach_photo.getUserData();
        long coach_id = coach.getId();
        Image image = new Image("view/images/coach.jpg");
        coach_photo.setFill(new ImagePattern(image));
        day_list = new LinkedList<>();
        day_list.add(LocalDate.now().plusDays(1));
        day_list.add(LocalDate.now().plusDays(2));
        day_list.add(LocalDate.now().plusDays(3));
        day_list.add(LocalDate.now().plusDays(4));
        day_list.add(LocalDate.now().plusDays(5));
        dateChoose.getItems().addAll(day_list);
        dateChoose.getSelectionModel().selectFirst();

        time_list.add(LocalTime.of(9,0,0));
        time_list.add(LocalTime.of(10,0,0));
        time_list.add(LocalTime.of(14,0,0));
        time_list.add(LocalTime.of(15,0,0));
        time_list.add(LocalTime.of(16,0,0));

        boolean [][] reserved = new boolean[5][5];

        CoachService coachService = new CoachService();
        ReturnEntity returnEntity = coachService.getReservedTimeById(coach_id);
        if(returnEntity.getCode() == 4044){
            // 教练不存在

        }
        else if(returnEntity.getCode() == 5000){
            System.out.println("Data base error!");
        }
        else{ // code == 200 successful
            List<Long> bookedTime = (List)returnEntity.getObject();
            for(Long time : bookedTime){
                //String t = DateUtils.timeStampToString(time); // yyyy-MM-dd HH:mm:ss
                LocalDateTime localDateTime = Instant.ofEpochMilli(time).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
                LocalDate localDate = localDateTime.toLocalDate();
                for(int i = 0; i < 5; ++i){
                    if(day_list.get(i).equals(localDate)){
                        LocalTime localTime = localDateTime.toLocalTime();
                        for(int j = 0; j < 5; ++j){
                            if(localTime.equals(time_list.get(i))){
                                reserved[i][j] = true;
                            }
                        }
                    }
                }
            }
        }


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

