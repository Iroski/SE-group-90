package controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import model.entity.LiveLesson;
import model.entity.LiveLessonTable;
import model.entity.ReturnEntity;
import model.service.LiveLessonService;
import model.utils.DateUtils;

import java.awt.event.MouseEvent;
import java.time.LocalDateTime;

public class LessonPageController {
    private ObservableList<LiveLesson> lessonData = FXCollections.observableArrayList();
    @FXML
    private TableView<LiveLesson> lessonTableView;
    @FXML
    private TableColumn<LiveLesson, String> lessonName;
    @FXML
    private TableColumn<LiveLesson, String> lessonTime;
    @FXML
    private TableColumn<LiveLesson, String> coach;
    @FXML
    private TableColumn buttonColumn;

    LiveLessonService liveLessonService;

    @FXML
    private void initialize() {
        liveLessonService = new LiveLessonService();
        ReturnEntity returnEntity = liveLessonService.getLiveLessonTableByUsername("Heluyao");
        if(returnEntity.getCode() == 4043){
            // 表不存在

        }
        else if(returnEntity.getCode() == 5000){
            System.out.println("Data base error!");
        }
        else{ // code == 200
            LiveLessonTable liveLessonTable = (LiveLessonTable) returnEntity.getObject();
            lessonData = (ObservableList<LiveLesson>) liveLessonTable.getLessonList();
        }
        lessonData.add(new LiveLesson("HeLuyao", "Goubo", new Long(1), 1, new Long(1)));
        lessonName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUsername()));
        lessonTime.setCellValueFactory(cellData -> new SimpleStringProperty(DateUtils.timeStampToString(cellData.getValue().getLessonTime())));
        coach.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCoachName()));
        lessonTableView.setItems(lessonData);

        buttonColumn.setCellFactory((col)->{
                    TableCell<LiveLesson, String> cell = new TableCell<LiveLesson, String>(){
                        @Override
                        protected void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            Button button1 = new Button("Cancel");
                            button1.setOnMouseEntered(e->button1.setCursor(Cursor.HAND));
                            button1.setStyle("-fx-background-color: #00bcff;-fx-text-fill: #ffffff;");
                            button1.setOnMouseClicked((col) -> {
                                //获取list列表中的位置，进而获取列表对应的信息数据
                                LiveLesson l = lessonData.get(getIndex());
                                //按钮事件自己添加
                            });

                            Button button2 = new Button("Enter");
                            button2.setOnMouseEntered(e->button2.setCursor(Cursor.HAND));
                            button2.setStyle("-fx-background-color: #00bcff;-fx-text-fill: #ffffff");
                            button2.setOnMouseClicked((col) -> {
                                //获取list列表中的位置，进而获取列表对应的信息数据
                                LiveLesson l = lessonData.get(getIndex());
                                //按钮事件自己添加
                            });
                            HBox buttons = new HBox(button2, button1);
                            buttons.setAlignment(Pos.CENTER);
                            buttons.setSpacing(15);

                            if (empty) {
                                //如果此列为空默认不添加元素
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

        lessonTableView.setVisible(true);
    }
}