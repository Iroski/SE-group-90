package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.entity.*;
import model.service.AccountService;
import model.service.CoachService;
import model.service.OrderService;
import model.utils.DateUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;


public class BookingPageController {

    public Circle coach_photo;
    public ChoiceBox<LocalDate> dateChoose;
    public Label coach_name;
    public CheckBox firstLesson;
    public CheckBox secondLesson;
    public CheckBox thirdLesson;
    public CheckBox fourthLesson;
    public CheckBox fifthLesson;
    public VBox lessonTime;


    LinkedList<LocalDate> day_list;
    LinkedList<LocalTime> time_list;
    LinkedList<CheckBox> time_box_list;
    LocalDate selectedDate = null;
    LocalTime selectedTime = null;
    Coach coach;

    @FXML
    public void initialize(){

    }

    public void init(){
        this.coach = (Coach) coach_photo.getUserData();
        System.out.println(coach);
        long coach_id = coach.getId();
        //long coach_id = 0;
        Image image = new Image("view/images/coach.jpg");
        coach_photo.setFill(new ImagePattern(image));
        day_list = new LinkedList<>();
        day_list.add(LocalDate.now().plusDays(1));
        day_list.add(LocalDate.now().plusDays(2));
        day_list.add(LocalDate.now().plusDays(3));
        day_list.add(LocalDate.now().plusDays(4));
        day_list.add(LocalDate.now().plusDays(5));
        dateChoose.getItems().addAll(day_list);

        time_list = new LinkedList<>();
        time_list.add(LocalTime.of(9,0,0));
        time_list.add(LocalTime.of(10,0,0));
        time_list.add(LocalTime.of(14,0,0));
        time_list.add(LocalTime.of(15,0,0));
        time_list.add(LocalTime.of(16,0,0));

        time_box_list = new LinkedList<>();
        time_box_list.add(firstLesson);
        time_box_list.add(secondLesson);
        time_box_list.add(thirdLesson);
        time_box_list.add(fourthLesson);
        time_box_list.add(fifthLesson);

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

        dateChoose.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) { // old new
                int index = dateChoose.getSelectionModel().getSelectedIndex();
                setCheckBox(reserved[index]);
                selectedDate = day_list.get(index);
            }
        });
        dateChoose.getSelectionModel().selectFirst();

        for(CheckBox cb : time_box_list){
            cb.selectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                    if(cb.isSelected() == false) return;
                    for(int i = 0; i < 5; ++i){
                        if(time_box_list.get(i) == cb){
                            selectedTime = time_list.get(i);
                            System.out.println(selectedTime);
                        }
                        else{
                            time_box_list.get(i).setSelected(false);
                        }
                    }
                }
            });
        }


    }

    public void setCheckBox(boolean[] reserved){
        for(int i = 0; i < 5; ++i){
            if(reserved[i]){
                HBox box = (HBox) lessonTime.getChildren().get(i);
                Label label = (Label) box.getChildren().get(0);
                CheckBox c_box = (CheckBox) box.getChildren().get(1);
                label.setTextFill(Color.GRAY);
                c_box.setVisible(false);  // has been reserved. can not be booked
            }
        }
    }

    public void closeThePage(MouseEvent mouseEvent) {
        Stage stage= (Stage) coach_photo.getScene().getWindow();
        stage.close();
    }

    public void chooseFinished(MouseEvent mouseEvent) throws IOException {  //出个弹窗
        Stage stage=new Stage();
        stage.setTitle("Confirmation");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/fxml/ReserveLessonConfirmation.fxml"));
        AnchorPane layout = loader.load();
        ReserveLessonConfirmation reserveLessonConfirmation = loader.getController();
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.showAndWait();
        if (reserveLessonConfirmation.tempLabel.getText() == "yes") {
            if(selectedDate != null && selectedTime != null){
                Stage stage1 = (Stage)coach_photo.getScene().getWindow();
                stage1.close();
                LocalDateTime localDateTime = LocalDateTime.of(selectedDate, selectedTime);
                Date date = Date.from(localDateTime.atZone(ZoneOffset.ofHours(8)).toInstant());
                Long lessonTime = DateUtils.dateToTimeStamp(date);

                //createOrder(coach, user, lessonTime);
            }

        }
    }

    public void createOrder(Coach coach, User user, Long lessonTime){
        OrderService orderService = new OrderService();
        Long createTime = DateUtils.dateToTimeStamp(new Date());
        LiveLesson liveLesson = new LiveLesson(user.getName(), coach.getName(), lessonTime, 0, createTime);
        int premiumType = getPremiumType(user);
        BigDecimal money = BigDecimal.valueOf(30);  // 暂时定价为30一节课
        Order order = new Order(user.getName(), 1, createTime, premiumType, null, money, 0, createTime);
        ReturnEntity returnEntity = orderService.createLiveLessonOrder(user.getName(), order, liveLesson);
        switch (returnEntity.getCode()){
            case 200: // successful
                Long id = (Long) returnEntity.getObject();
                payForLessonOrder(user, order, liveLesson);
                break;
            case 400: // bad input time

                break;
            case 4043: // live lesson table not found

                break;
            case 4044: // coach not found

                break;
            case 4042: // account not exist

                break;
            case 5000: // database error

                break;
        }
    }

    public int getPremiumType(User user){
        AccountService accountService = new AccountService();
        ReturnEntity returnEntity = accountService.getAccount(user.getName());
        int code = returnEntity.getCode();
        int premiumType = -1;
        switch (code){
            case 200: // successful
                Account account = (Account) returnEntity.getObject();
                premiumType = account.getPremiumLevel();
                break;
            case 4042: // account not exist

                break;
            case 5000: // database error

                break;
        }
        return premiumType;
    }

    public void payForLessonOrder(User user, Order order, LiveLesson liveLesson){
        AtomicBoolean isFreeByPremium = isFreeByPremium(user);
        if(isFreeByPremium.get()){
            order.setMoney(BigDecimal.valueOf(0));
        }

        AtomicBoolean paid = new AtomicBoolean(false);
        // pay page

        OrderService orderService = new OrderService();
        if(paid.get()){
            int code = orderService.payLiveLessonOrder(user.getName(), order, liveLesson, isFreeByPremium);
            switch (code){
                case 200: // successful

                    break;
                case 4042: // account not exist

                    break;
                case 4047: // order not found

                    break;
                case 5001: // not enough balance

                    break;
                case 5002: // no enough free lesson

                    break;
                case 5000: // database error

                    break;
            }
        }
    }

    public AtomicBoolean isFreeByPremium(User user){
        AccountService accountService = new AccountService();
        ReturnEntity returnEntity = accountService.getAccount(user.getName());
        AtomicBoolean res = new AtomicBoolean(false);
        switch (returnEntity.getCode()){
            case 200: // successful
                Account account = (Account) returnEntity.getObject();
                int premiumType = account.getPremiumLevel();
                int freeTime = account.getFreeLiveLessonTime();
                if(premiumType != 0 && freeTime > 0){
                    res.set(true);
                }
                break;
            case 4042: // account not exist

                break;
            case 5000: // database error

                break;
        }
        return res;
    }
}

