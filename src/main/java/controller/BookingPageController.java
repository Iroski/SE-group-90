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
import main.Main;
import model.entity.*;
import model.enumPackage.TargetType;
import model.service.*;
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
    public CheckBox customization;
    public ChoiceBox<String> targets;


    LinkedList<LocalDate> day_list;
    LinkedList<LocalTime> time_list;
    LinkedList<CheckBox> time_box_list;
    LocalDate selectedDate = null;
    LocalTime selectedTime = null;
    Coach coach;
    User user;
    String userName;
    private int lessonPrice = 30;
    private boolean isCustomized = false;
    private String target = "";

    @FXML
    public void initialize(){
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
    }

    public void init(){
        this.coach = (Coach) coach_photo.getUserData();
        String userName = LoginController.userName;
        UserService userService = new UserService();
        ReturnEntity returnEntity1 = userService.getUser(userName);
        this.user = (User) returnEntity1.getObject();
        this.userName = user.getName();
        long coach_id = coach.getId();
        Image image = new Image(coach.getPhotoPath());
        coach_photo.setFill(new ImagePattern(image));

        boolean [][] reserved = new boolean[5][5];

        CoachService coachService = new CoachService();
        ReturnEntity returnEntity = coachService.getReservedTimeById(coach_id);
        if(returnEntity.getCode() == 4044){
            // 教练不存在
            System.out.println("coach not exist");
        }
        else if(returnEntity.getCode() == 5000){
            System.out.println("Data base error!");
        }
        else{ // code == 200 successful
            List<Long> bookedTime = (List)returnEntity.getObject();
            for(Long time : bookedTime){
                LocalDateTime localDateTime = Instant.ofEpochMilli(time).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
                LocalDate localDate = localDateTime.toLocalDate();
                if(!localDate.isAfter(LocalDate.now())){
                    continue;
                }
                for(int i = 0; i < 5; ++i){
                    if(day_list.get(i).equals(localDate)){
                        LocalTime localTime = localDateTime.toLocalTime();
                        for(int j = 0; j < 5; ++j){
                            if(localTime.equals(time_list.get(j))){
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
        dateChoose.setStyle("-fx-font-size: 22px");
        targets.setStyle("-fx-font-size: 22px");

        for(CheckBox cb : time_box_list){
            cb.selectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                    if(cb.isSelected() == false) return;
                    for(int i = 0; i < 5; ++i){
                        if(time_box_list.get(i) == cb){
                            selectedTime = time_list.get(i);
                        }
                        else{
                            time_box_list.get(i).setSelected(false);
                        }
                    }
                }
            });
        }

        targets.setVisible(false);
        for(TargetType type : TargetType.values()){
            targets.getItems().add(type.getDescription());
        }
        customization.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if(customization.isSelected() == true){
                    targets.setVisible(true);
                    targets.getSelectionModel().selectFirst();
                    isCustomized = true;
                }
                else{
                    targets.setVisible(false);
                    isCustomized = false;
                    target = "";
                }
            }
        });

        targets.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) { // old new
                target = targets.getSelectionModel().getSelectedItem();
            }
        });
        targets.getSelectionModel().selectFirst();
    }

    public void setCheckBox(boolean[] reserved){
        for(int i = 0; i < 5; ++i){
            HBox box = (HBox) lessonTime.getChildren().get(i);
            Label label = (Label) box.getChildren().get(0);
            CheckBox c_box = (CheckBox) box.getChildren().get(1);
            if(reserved[i]){
                label.setTextFill(Color.GRAY);
                c_box.setVisible(false);  // has been reserved. can not be booked
            }
            else{
                label.setTextFill(Color.BLACK);
                c_box.setVisible(true);
            }
        }
    }

    public void closeThePage(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) coach_photo.getScene().getWindow();
        stage.setTitle("Coach information");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/fxml/CoachInfo.fxml"));

        AnchorPane coaches = (AnchorPane) loader.load();
        AnchorPane anchorPane = (AnchorPane) stage.getScene().getRoot();
        anchorPane.getChildren().remove(2);
        anchorPane.getChildren().add(2, coaches);

        CoachInfoController coachInfoController = loader.getController();
        coachInfoController.photo.setUserData(coach);
        coachInfoController.init();

        coaches.setLayoutX(200);
        coaches.setLayoutY(75);
    }

    public void chooseFinished(MouseEvent mouseEvent) throws IOException {  //出个弹窗
        if(selectedTime == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("Lesson time is not chose");
            alert.headerTextProperty().set("You have not chose the lesson time\nPlease choose the time before you click 'OK'");
            alert.show();
            return;
        }
        Stage stage = new Stage();
        stage.setTitle("Confirmation");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/fxml/ReserveLessonConfirmation.fxml"));
        AnchorPane layout = loader.load();
        ReserveLessonConfirmation reserveLessonConfirmation = loader.getController();
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.showAndWait();
        if (reserveLessonConfirmation.choose == true) {
            if(selectedDate != null && selectedTime != null){
                LocalDateTime localDateTime = LocalDateTime.of(selectedDate, selectedTime);
                Date date = Date.from(localDateTime.atZone(ZoneOffset.ofHours(8)).toInstant());
                Long lessonTime = DateUtils.dateToTimeStamp(date);

                createOrder(coach, user, lessonTime);
                this.init();
            }

        }
    }

    public void createOrder(Coach coach, User user, Long lessonTime) throws IOException {
        OrderService orderService = new OrderService();
        Long createTime = DateUtils.dateToTimeStamp(new Date());
        LiveLesson liveLesson = new LiveLesson(user.getName(), coach.getName(), lessonTime, 0, isCustomized,target,"", createTime);
        int premiumType = getPremiumType(user);
        BigDecimal money = BigDecimal.valueOf(lessonPrice);  // 暂时定价为30一节课
        Order order = new Order(user.getName(), 1, createTime, premiumType, null, money, 0, createTime);
        ReturnEntity returnEntity = orderService.createLiveLessonOrder(user.getName(), order, liveLesson);

        switch (returnEntity.getCode()){
            case 200: // successful
                order = (Order) returnEntity.getObject();
                payForLessonOrder(user, order, liveLesson);
                break;
            case 400: // bad input time
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.titleProperty().set("Failure");
                alert.headerTextProperty().set("You have already booked a live lesson at this time");
                alert.show();
                break;
            case 4043: // live lesson table not found
                System.out.println("order error 4043");
                break;
            case 4044: // coach not found
                System.out.println("order error 4044");
                break;
            case 4042: // account not exist
                System.out.println("order error 4042");
                break;
            case 5000: // database error
                System.out.println("order error 5000");
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

    public void payForLessonOrder(User user, Order order, LiveLesson liveLesson) throws IOException {
        AtomicBoolean isFreeByPremium = isFreeByPremium(user);
        if(isFreeByPremium.get()){
            order.setMoney(BigDecimal.valueOf(0));
        }

        AtomicBoolean paid;
        paid = showIfPay(); // show and return

        OrderService orderService = new OrderService();
        if(paid.get()){
            int code = orderService.payLiveLessonOrder(user.getName(), liveLesson, isFreeByPremium);
            switch (code){
                case 200: // successful
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.titleProperty().set("Success");
                    alert.headerTextProperty().set("You have booked the lesson successfully");
                    alert.show();
                    break;
                case 4042: // account not exist
                    System.out.println("pay error 4042");
                    break;
                case 4047: // order not found
                    System.out.println("pay error 4047");
                    break;
                case 5001: // not enough balance
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.titleProperty().set("Fail");
                    alert.headerTextProperty().set("You don not have enough money, top up please!");
                    alert.show();
                    goToAccount();
                    break;
                case 5002: // no enough free lesson
                    System.out.println("pay error 5002");
                    break;
                case 5000: // database error
                    System.out.println("pay error 5000");
                    break;
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("Success");
            alert.headerTextProperty().set("You have booked the lesson successfully\nPlease pay for it before your lesson begin!");
            alert.show();
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
                int freeTime = account.getFreeLiveLessonNum();
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

    public AtomicBoolean showIfPay() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/view/fxml/" + "PayForOrder.fxml"));
        AnchorPane page = loader.load();
        Stage payOrder = new Stage();
        payOrder.setTitle("Pay For the Order");
        Scene scene = new Scene(page);
        payOrder.setScene(scene);
        PayForOrderController controller = loader.getController();
        payOrder.showAndWait();
        return controller.getIfPay();
    }

    public void goToAccount() throws IOException {
        Stage stage = (Stage) coach_photo.getScene().getWindow();
        stage.setTitle("Profile");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/fxml/" + "Account.fxml"));
        AnchorPane account = (AnchorPane) loader.load();
        // Set person overview into the center of root layout.
        AnchorPane anchorPane= (AnchorPane) stage.getScene().getRoot();
        anchorPane.getChildren().remove(2);
        anchorPane.getChildren().add(2, account);
        account.setLayoutX(200);
        account.setLayoutY(75);
    }

}

