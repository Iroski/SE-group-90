package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Callback;
import lombok.SneakyThrows;
import model.entity.Coach;
import model.entity.ReturnEntity;
import model.service.CoachService;
import java.util.ArrayList;


public class CoachPageController {
    public Pagination pages;
    public TextField searchText;
    public Button searchButton;
    public Label noCoachLabel;
    int number;
    int pageNumber;
    ArrayList<Coach> coachArrayList;
    CoachService coachService;

    public void search(MouseEvent mouseEvent) {
        if (searchText.getText()==null|| searchText.getText().equals("")) {
            getCoaches();
            setPage();
            return ;
        }
        ReturnEntity returnEntity=coachService.blurSearchByName(searchText.getText());
        if (returnEntity.getCode()==500) {
            System.out.println("Data base error!");
        }
        else if (returnEntity.getObject()==null) {
            coachArrayList=null;
        }
        else if (returnEntity.getCode()==200) {
            coachArrayList=(ArrayList<Coach>) returnEntity.getObject();
        }
        setPage();
    }

    public void getCoaches() {
        coachService=new CoachService();
        ReturnEntity returnEntity=coachService.getAllCoaches();
        if (returnEntity.getCode()==5000) {
            System.out.println("Data base error!");
        }
        else if(returnEntity.getObject()==null) {
            coachArrayList=null;
        }
        else {
            coachArrayList= (ArrayList<Coach>) returnEntity.getObject();
        }
    }

    public void setPage() {
        if (coachArrayList==null) {
            noCoachLabel.setVisible(true);
            number=0;
        }
        else {
            noCoachLabel.setVisible(false);
            number = coachArrayList.size();
        }
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
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/fxml/Coach.fxml"));
                AnchorPane coaches = (AnchorPane) loader.load();
                CoachController coachController = loader.getController();
                Coach coach=null;
                Circle[] circles={coachController.circle1,coachController.circle2,coachController.circle3,coachController.circle4,coachController.circle5,
                        coachController.circle6,coachController.circle7,coachController.circle8,coachController.circle9,coachController.circle10,coachController.circle11,
                        coachController.circle12};
                AnchorPane[] panes={coachController.pane1,coachController.pane2,coachController.pane3,coachController.pane4,coachController.pane5,coachController.pane6
                        ,coachController.pane7,coachController.pane8,coachController.pane9,coachController.pane10,coachController.pane11,coachController.pane12};
                Label[] descriptions={coachController.description1,coachController.description2,coachController.description3,coachController.description4,
                        coachController.description5,coachController.description6,coachController.description7,coachController.description8,
                        coachController.description9,coachController.description10,coachController.description11,coachController.description12};
                Label[] names={coachController.name1,coachController.name2,coachController.name3,coachController.name4,coachController.name5,coachController.name6,
                        coachController.name7,coachController.name8,coachController.name9,coachController.name10,coachController.name11,coachController.name12};
                for (int i=0;i<12;i++) {
                    if ((integer * 12 + i) < number) {
                        coach = coachArrayList.get(integer * 12 + i);
                        panes[i].setUserData(coach);
                        circles[i].setFill(new ImagePattern(new Image(coach.getPhotoPath())));
                        descriptions[i].setText(coach.getCourse());
                        names[i].setText(coach.getName());
                    } else {
                        circles[i].setVisible(false);
                        descriptions[i].setVisible(false);
                        names[i].setVisible(false);
                        panes[i].setVisible(false);
                    }
                }
                return coaches;
            }
        });
        pages.setVisible(true);
    }

    @FXML
    public void initialize() {
        noCoachLabel.setVisible(false);
        getCoaches();
        setPage();
    }
}
