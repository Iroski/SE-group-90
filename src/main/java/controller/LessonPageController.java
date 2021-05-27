package controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.entity.LiveLesson;
import model.entity.ReturnEntity;
import model.enumPackage.OrderStatus;
import model.service.AccountService;
import model.service.LiveLessonService;
import model.service.OrderService;
import model.utils.DateUtils;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import static controller.LoginController.userName;


public class LessonPageController {
    public AnchorPane lessonsPane;
    private ObservableList<LiveLesson> lessonData = FXCollections.observableArrayList();

    public TableView<LiveLesson> lessonTableView;
    public TableColumn<LiveLesson, String> lessonName;
    public TableColumn<LiveLesson, String> lessonTime;
    public TableColumn<LiveLesson, String> coach;
    public TableColumn buttonColumn;
    public TableColumn targetDescriptionColumn;

    LiveLessonService liveLessonService;

    @FXML
    private void initialize(){
        lessonName.setStyle("");
    }

    public void init() {
        liveLessonService = new LiveLessonService();
        ReturnEntity returnEntity = liveLessonService.getNotStartNotCanceledLiveLessonByUsername(userName);
        if(returnEntity.getCode() == 4043){
            System.out.println("table not exist!");
        }
        else if(returnEntity.getCode() == 5000){
            System.out.println("Data base error!");
        }
        else{ // code == 200
            List<LiveLesson> liveLessonTable = (List<LiveLesson>) returnEntity.getObject();
            liveLessonTable = sortTableByTime(liveLessonTable);
            lessonData = FXCollections.observableArrayList(liveLessonTable);
        }

        lessonName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCoachName()+"'s live lesson for "+cellData.getValue().getUsername()));
        lessonTime.setCellValueFactory(cellData -> new SimpleStringProperty(DateUtils.timeStampToString(cellData.getValue().getLessonTime())));
        coach.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCoachName()));
        lessonTableView.setItems(lessonData);

        buttonColumn.setCellFactory((col)->{
                    TableCell<LiveLesson, String> cell = new TableCell<LiveLesson, String>(){
                        @Override
                        protected void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);

                            /*this.setText(null);
                            this.setGraphic(null);
                            TableRow<LiveLesson> currentRow = getTableRow();
                            if (!isEmpty()) {
                                currentRow.setStyle("-fx-background-color:#aafff5");
                            }*/

                            Button button1 = new Button("Cancel");
                            button1.setOnMouseEntered(e->button1.setCursor(Cursor.HAND));
                            button1.setStyle("-fx-background-color: #00bcff;-fx-text-fill: #ffffff;");
                            button1.setOnMouseClicked((col) -> {
                                LiveLesson l = lessonData.get(getIndex());
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.titleProperty().set("Confirm to cancel");
                                alert.setHeaderText("Are you sure you want to cancel the lesson now?");
                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.get() == ButtonType.OK){
                                    int status = l.getStatus();
                                    OrderService orderService = new OrderService();
                                    int code;
                                    if(status == OrderStatus.NOT_PAYED.getCode()){
                                        code = orderService.cancelLiveLessonOrderBeforePay(userName, l);
                                    }
                                    else{
                                        code = orderService.cancelLiveLessonOrderAfterPay(userName, l);
                                    }
                                    if(code != 200){
                                        System.out.println("lesson cancel error");
                                    }
                                    init();
                                }
                            });

                            Button button2 = new Button("Enter");
                            button2.setOnMouseEntered(e->button2.setCursor(Cursor.HAND));
                            button2.setStyle("-fx-background-color: #00bcff;-fx-text-fill: #ffffff");
                            button2.setOnMouseClicked((col) -> {
                                LiveLesson l = lessonData.get(getIndex());
                                enterLesson(l);
                            });

                            Button button3 = new Button("Pay");
                            AtomicBoolean notPay = new AtomicBoolean(false);
                            button3.setOnMouseEntered(e->button3.setCursor(Cursor.HAND));
                            button3.setStyle("-fx-background-color: #00bcff;-fx-text-fill: #ffffff");
                            try{
                                LiveLesson l = lessonData.get(getIndex());
                                if(l.getStatus() == 0){
                                    notPay.set(true);
                                }
                                else{
                                    notPay.set(false);
                                }
                            }catch (Exception e){}

                            button3.setOnMouseClicked((col) -> {
                                LiveLesson l = lessonData.get(getIndex());
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.titleProperty().set("Confirm to pay");
                                alert.setHeaderText("Are you sure you want to pay now?");
                                Optional<ButtonType> result = alert.showAndWait();
                                OrderService orderService = new OrderService();
                                AccountService accountService = new AccountService();
                                if (result.get() == ButtonType.OK){
                                    int code = orderService.payLiveLessonOrder(userName, l, new AtomicBoolean((int)accountService.getFreeLessonNumByUsername(userName).getObject() > 0));
                                    if(code == 200){
                                        alert = new Alert(Alert.AlertType.INFORMATION);
                                        alert.titleProperty().set("Successful");
                                        alert.headerTextProperty().set("You have paid for the lesson successfully!");
                                        alert.show();
                                    }
                                    else if(code == 5001){
                                        alert = new Alert(Alert.AlertType.INFORMATION);
                                        alert.titleProperty().set("Fail");
                                        alert.headerTextProperty().set("You don not have enough money, top up please!");
                                        alert.show();
                                        try {
                                            goToAccount();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    else if(code != 200){
                                        System.out.println("pay error");
                                    }
                                    init();
                                }
                            });

                            HBox buttons = new HBox(button2, button1);
                            if(notPay.get()){
                                buttons.getChildren().add(button3);
                            }
                            buttons.setSpacing(15);

                            if (empty) {
                                setText(null);
                                setGraphic(null);
                            } else {
                                this.setGraphic(buttons);
                            }
                        }
                    };
                    return cell;
                }
        );

        targetDescriptionColumn.setCellFactory((col)->{
                    TableCell<LiveLesson, String> cell = new TableCell<LiveLesson, String>(){
                        @Override
                        protected void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            Button button = new Button("Show exercise");
                            button.setOnMouseEntered(e->button.setCursor(Cursor.HAND));
                            button.setStyle("-fx-background-color: #00bcff;-fx-text-fill: #ffffff;");
                            try{
                                LiveLesson l = lessonData.get(getIndex());
                                String exercise = l.getSpecificExercise();
                                if(exercise.equals("")){
                                    return;
                                }
                                button.setOnMouseClicked((col) -> {
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.titleProperty().set("");
                                    alert.setHeaderText(exercise);
                                    alert.setGraphic(null);
                                    alert.show();
                                });
                            }catch (Exception e){}

                            if (empty) {
                                setText(null);
                                setGraphic(null);
                            } else {
                                this.setAlignment(Pos.CENTER);
                                this.setGraphic(button);
                            }
                        }
                    };
                    return cell;
                }
        );

        lessonTableView.setVisible(true);
    }

    public void enterLesson(LiveLesson lesson){
        Stage stage = (Stage) lessonsPane.getScene().getWindow();
        stage.setTitle("Living room");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/fxml/LivePage.fxml"));
        AnchorPane lessons = null;
        try {
            lessons = (AnchorPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Set person overview into the center of root layout.
        AnchorPane anchorPane= (AnchorPane) stage.getScene().getRoot();
        anchorPane.getChildren().remove(2);
        anchorPane.getChildren().add(2, lessons);

        lessons.setLayoutX(200);
        lessons.setLayoutY(75);
    }

    public List<LiveLesson> sortTableByTime(List<LiveLesson> list){
        int size = list.size();
        Collections.sort(list, new Comparator<LiveLesson>() {
            public int compare(LiveLesson l1, LiveLesson l2) {
                if(null == l1.getLessonTime()) {
                    return 1;
                }
                if(null == l2.getLessonTime()) {
                    return -1;
                }
                return l1.getLessonTime().compareTo(l2.getLessonTime());
            }
        });

        return list;
    }

    public void goToAccount() throws IOException {
        Stage stage = (Stage) lessonsPane.getScene().getWindow();
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