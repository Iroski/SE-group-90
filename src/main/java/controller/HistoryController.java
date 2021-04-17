package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.entity.ReturnEntity;
import model.entity.Video;
import model.service.UserService;

import java.util.ArrayList;

public class HistoryController {
    public AnchorPane historyPane;
    public VBox allHistory;
    public AnchorPane h1Pane;
    public ImageView history1;
    public Label description1;
    public Label author1;

    public AnchorPane h2Pane;
    public ImageView history2;
    public Label description2;
    public Label author2;

    public AnchorPane h3Pane;
    public ImageView history3;
    public Label description3;
    public Label author3;

    public AnchorPane h4Pane;
    public ImageView history4;
    public Label description4;
    public Label author4;

    public AnchorPane h5Pane;
    public ImageView history5;
    public Label description5;
    public Label author5;

    public AnchorPane h6Pane;
    public ImageView history6;
    public Label description6;
    public Label author6;

    @FXML
    public void initialize() {
        ArrayList<Video> historyList=null;
        UserService Service=new UserService();
        ReturnEntity returnEntity=Service.getHistoryByName("hly");
        if (returnEntity.getCode()==5000) {
            System.out.println("Data base error!");
        }
        else if(returnEntity.getObject()==null) {
            historyList=null;
        }
        else {
            historyList= (ArrayList<Video>) returnEntity.getObject();
        }
        try {
            if (historyList.get(0) != null) {
                history1.setImage(new Image(historyList.get(0).getCoverPath()));
                author1.setText(historyList.get(0).getAuthor());
                description1.setText("it's a good video!");
            }
            if (historyList.get(1) != null) {
                history2.setImage(new Image(historyList.get(1).getCoverPath()));
                author2.setText(historyList.get(1).getAuthor());
                description2.setText("it's a good video!");
            }
            if (historyList.get(2) != null) {
                history3.setImage(new Image(historyList.get(2).getCoverPath()));
                author3.setText(historyList.get(2).getAuthor());
                description3.setText("it's a good video!");
            }
            if (historyList.get(3) != null) {
                history4.setImage(new Image(historyList.get(3).getCoverPath()));
                author4.setText(historyList.get(3).getAuthor());
                description4.setText("it's a good video!");
            }
            if (historyList.get(4) != null) {
                history5.setImage(new Image(historyList.get(4).getCoverPath()));
                author5.setText(historyList.get(4).getAuthor());
                description5.setText("it's a good video!");
            }
            if (historyList.get(5) != null) {
                history6.setImage(new Image(historyList.get(5).getCoverPath()));
                author6.setText(historyList.get(5).getAuthor());
                description6.setText("it's a good video!");
            }
        }
        catch (Exception ignored){

        }
    }

    public void closeHistory(MouseEvent mouseEvent) {
        Stage stage = (Stage) historyPane.getScene().getWindow();
        AnchorPane anchorPane= (AnchorPane) stage.getScene().getRoot();
        anchorPane.getChildren().remove(this.historyPane);
    }
}
