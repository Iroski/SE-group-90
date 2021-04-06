package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import javafx.util.Callback;
import lombok.SneakyThrows;
import model.dao.entity.Coach;

import java.util.ArrayList;

public class CoachPageController {
    public Pagination pages;
    int number;
    int pageNumber;
    ArrayList<Coach> coachArrayList=new ArrayList<>();

    @FXML
    public void initialize() {
        for (int i=0;i<100;i++) {
            coachArrayList.add(new Coach(1,"He luyao","man",170,45,11,"chinese","view/images/1.png"));
        }
        number=coachArrayList.size();
        if (number % 12 == 0) {
            pageNumber = number / 12;
        } else {
            pageNumber = number / 12 + 1;
        }
        pages.setPageCount(pageNumber);
        pages.setCurrentPageIndex(0);
        pages.setPageFactory(new Callback<Integer, Node>() {
            @SneakyThrows
            @Override
            public Node call(Integer integer) {
                if (integer<pageNumber-1) {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/view/fxml/Coach.fxml"));
                    AnchorPane coaches = (AnchorPane) loader.load();
                    CoachController coachController = loader.getController();
                    Coach coach = coachArrayList.get(integer*12+0);
                    coachController.pane1.setUserData(coach);
                    coachController.circle1.setFill(new ImagePattern(new Image(coach.getPhotoPath())));
                    coachController.description1.setText(coach.getCourse());
                    coachController.name1.setText(coach.getName());

                    coach = coachArrayList.get(integer*12+1);
                    coachController.pane2.setUserData(coach);
                    coachController.circle2.setFill(new ImagePattern(new Image(coach.getPhotoPath())));
                    coachController.description2.setText(coach.getCourse());
                    coachController.name2.setText(coach.getName());

                    coach = coachArrayList.get(integer*12+2);
                    coachController.pane3.setUserData(coach);
                    coachController.circle3.setFill(new ImagePattern(new Image(coach.getPhotoPath())));
                    coachController.description3.setText(coach.getCourse());
                    coachController.name3.setText(coach.getName());

                    coach = coachArrayList.get(integer*12+3);
                    coachController.pane4.setUserData(coach);
                    coachController.circle4.setFill(new ImagePattern(new Image(coach.getPhotoPath())));
                    coachController.description4.setText(coach.getCourse());
                    coachController.name4.setText(coach.getName());

                    coach = coachArrayList.get(integer*12+4);
                    coachController.pane5.setUserData(coach);
                    coachController.circle5.setFill(new ImagePattern(new Image(coach.getPhotoPath())));
                    coachController.description5.setText(coach.getCourse());
                    coachController.name5.setText(coach.getName());

                    coach = coachArrayList.get(integer*12+5);
                    coachController.pane6.setUserData(coach);
                    coachController.circle6.setFill(new ImagePattern(new Image(coach.getPhotoPath())));
                    coachController.description6.setText(coach.getCourse());
                    coachController.name6.setText(coach.getName());

                    coach = coachArrayList.get(integer*12+6);
                    coachController.pane7.setUserData(coach);
                    coachController.circle7.setFill(new ImagePattern(new Image(coach.getPhotoPath())));
                    coachController.description7.setText(coach.getCourse());
                    coachController.name7.setText(coach.getName());

                    coach = coachArrayList.get(integer*12+7);
                    coachController.pane8.setUserData(coach);
                    coachController.circle8.setFill(new ImagePattern(new Image(coach.getPhotoPath())));
                    coachController.description8.setText(coach.getCourse());
                    coachController.name8.setText(coach.getName());

                    coach = coachArrayList.get(integer*12+8);
                    coachController.pane9.setUserData(coach);
                    coachController.circle9.setFill(new ImagePattern(new Image(coach.getPhotoPath())));
                    coachController.description9.setText(coach.getCourse());
                    coachController.name9.setText(coach.getName());

                    coach = coachArrayList.get(integer*12+9);
                    coachController.pane10.setUserData(coach);
                    coachController.circle10.setFill(new ImagePattern(new Image(coach.getPhotoPath())));
                    coachController.description10.setText(coach.getCourse());
                    coachController.name10.setText(coach.getName());

                    coach = coachArrayList.get(integer*12+10);
                    coachController.pane11.setUserData(coach);
                    coachController.circle11.setFill(new ImagePattern(new Image(coach.getPhotoPath())));
                    coachController.description11.setText(coach.getCourse());
                    coachController.name11.setText(coach.getName());

                    coach = coachArrayList.get(integer*12+11);
                    coachController.pane12.setUserData(coach);
                    coachController.circle12.setFill(new ImagePattern(new Image(coach.getPhotoPath())));
                    coachController.description12.setText(coach.getCourse());
                    coachController.name12.setText(coach.getName());

                    return coaches;
                }
                else {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/view/fxml/Coach.fxml"));
                    AnchorPane coaches = (AnchorPane) loader.load();
                    CoachController coachController = loader.getController();
                    Coach coach=coachArrayList.get(integer*12+0);
                    coachController.pane1.setUserData(coach);
                    coachController.circle1.setFill(new ImagePattern(new Image(coach.getPhotoPath())));
                    coachController.description1.setText(coach.getCourse());
                    coachController.name1.setText(coach.getName());
                    if ((integer*12+1)<number) {
                        coach=coachArrayList.get(integer*12+1);
                        coachController.pane2.setUserData(coach);
                        coachController.circle2.setFill(new ImagePattern(new Image(coach.getPhotoPath())));
                        coachController.description2.setText(coach.getCourse());
                        coachController.name2.setText(coach.getName());
                    }
                    else {
                        coachController.circle2.setVisible(false);
                        coachController.description2.setVisible(false);
                        coachController.name2.setVisible(false);
                        coachController.pane2.setVisible(false);
                    }
                    if ((integer*12+2)<number) {
                        coach=coachArrayList.get(integer*12+2);
                        coachController.pane3.setUserData(coach);
                        coachController.circle3.setFill(new ImagePattern(new Image(coach.getPhotoPath())));
                        coachController.description3.setText(coach.getCourse());
                        coachController.name3.setText(coach.getName());
                    }
                    else {
                        coachController.circle3.setVisible(false);
                        coachController.description3.setVisible(false);
                        coachController.name3.setVisible(false);
                        coachController.pane3.setVisible(false);
                    }
                    if ((integer*12+3)<number) {
                        coach=coachArrayList.get(integer*12+3);
                        coachController.pane4.setUserData(coach);
                        coachController.circle4.setFill(new ImagePattern(new Image(coach.getPhotoPath())));
                        coachController.description4.setText(coach.getCourse());
                        coachController.name4.setText(coach.getName());
                    }
                    else {
                        coachController.circle4.setVisible(false);
                        coachController.description4.setVisible(false);
                        coachController.name4.setVisible(false);
                        coachController.pane4.setVisible(false);
                    }
                    if ((integer*12+4)<number) {
                        coach=coachArrayList.get(integer*12+4);
                        coachController.pane5.setUserData(coach);
                        coachController.circle5.setFill(new ImagePattern(new Image(coach.getPhotoPath())));
                        coachController.description5.setText(coach.getCourse());
                        coachController.name5.setText(coach.getName());
                    }
                    else {
                        coachController.circle5.setVisible(false);
                        coachController.description5.setVisible(false);
                        coachController.name5.setVisible(false);
                        coachController.pane5.setVisible(false);
                    }
                    if ((integer*12+5)<number) {
                        coach=coachArrayList.get(integer*12+5);
                        coachController.pane6.setUserData(coach);
                        coachController.circle6.setFill(new ImagePattern(new Image(coach.getPhotoPath())));
                        coachController.description6.setText(coach.getCourse());
                        coachController.name6.setText(coach.getName());
                    }
                    else {
                        coachController.circle6.setVisible(false);
                        coachController.description6.setVisible(false);
                        coachController.name6.setVisible(false);
                        coachController.pane6.setVisible(false);
                    }
                    if ((integer*12+6)<number) {
                        coach=coachArrayList.get(integer*12+6);
                        coachController.pane7.setUserData(coach);
                        coachController.circle7.setFill(new ImagePattern(new Image(coach.getPhotoPath())));
                        coachController.description7.setText(coach.getCourse());
                        coachController.name7.setText(coach.getName());
                    }
                    else {
                        coachController.circle7.setVisible(false);
                        coachController.description7.setVisible(false);
                        coachController.name7.setVisible(false);
                        coachController.pane7.setVisible(false);
                    }
                    if ((integer*12+7)<number) {
                        coach=coachArrayList.get(integer*12+7);
                        coachController.pane8.setUserData(coach);
                        coachController.circle8.setFill(new ImagePattern(new Image(coach.getPhotoPath())));
                        coachController.description8.setText(coach.getCourse());
                        coachController.name8.setText(coach.getName());
                    }
                    else {
                        coachController.circle8.setVisible(false);
                        coachController.description8.setVisible(false);
                        coachController.name8.setVisible(false);
                        coachController.pane8.setVisible(false);
                    }
                    if ((integer*12+8)<number) {
                        coach=coachArrayList.get(integer*12+8);
                        coachController.pane9.setUserData(coach);
                        coachController.circle9.setFill(new ImagePattern(new Image(coach.getPhotoPath())));
                        coachController.description9.setText(coach.getCourse());
                        coachController.name9.setText(coach.getName());
                    }
                    else {
                        coachController.circle9.setVisible(false);
                        coachController.description9.setVisible(false);
                        coachController.name9.setVisible(false);
                        coachController.pane9.setVisible(false);
                    }
                    if ((integer*12+9)<number) {
                        coach=coachArrayList.get(integer*12+9);
                        coachController.pane10.setUserData(coach);
                        coachController.circle10.setFill(new ImagePattern(new Image(coach.getPhotoPath())));
                        coachController.description10.setText(coach.getCourse());
                        coachController.name10.setText(coach.getName());
                    }
                    else {
                        coachController.circle10.setVisible(false);
                        coachController.description10.setVisible(false);
                        coachController.name10.setVisible(false);
                        coachController.pane10.setVisible(false);
                    }
                    if ((integer*12+10)<number) {
                        coach=coachArrayList.get(integer*12+10);
                        coachController.pane11.setUserData(coach);
                        coachController.circle11.setFill(new ImagePattern(new Image(coach.getPhotoPath())));
                        coachController.description11.setText(coach.getCourse());
                        coachController.name11.setText(coach.getName());
                    }
                    else {
                        coachController.circle11.setVisible(false);
                        coachController.description11.setVisible(false);
                        coachController.name11.setVisible(false);
                        coachController.pane11.setVisible(false);
                    }
                    if ((integer*12+11)<number) {
                        coach=coachArrayList.get(integer*12+11);
                        coachController.pane12.setUserData(coach);
                        coachController.circle12.setFill(new ImagePattern(new Image(coach.getPhotoPath())));
                        coachController.description12.setText(coach.getCourse());
                        coachController.name12.setText(coach.getName());
                    }
                    else {
                        coachController.circle12.setVisible(false);
                        coachController.description12.setVisible(false);
                        coachController.name12.setVisible(false);
                        coachController.pane12.setVisible(false);
                    }
                    return coaches;
                }
            }
        });
        pages.setVisible(true);
    }
}
