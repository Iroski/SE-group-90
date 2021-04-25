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
    private Image image=new Image("view/images/1.png");
    @FXML
    public void initialize() {
        ArrayList<Video> historyList=null;
        UserService Service=new UserService();
        //ReturnEntity returnEntity=Service.getHistoryByName("hly");
        ReturnEntity returnEntity=Service.getHistoryByName(LoginController.userName);
        if (returnEntity.getCode()==5000) {
            System.out.println("Data base error!");
        }
        else if(returnEntity.getObject()==null) {
            historyList=null;
        }
        else {
            historyList= (ArrayList<Video>) returnEntity.getObject();
            //System.out.println(historyList);
        }
        ImageView[] historys={history1,history2,history3,history4,history5,history6};
        Label[] authors={author1,author2,author3,author4,author5,author6};
        Label[] descriptions={description1,description2,description3,description4,description5,description6};
        try {
            for (int i=0;i<6;i++) {
                if (historyList.get(i) != null) {
                    if (historyList.get(i).getStaticVideo().getCoverPath()==null) {
                        historys[i].setImage(image);
                    }
                    else
                        historys[i].setImage(new Image(historyList.get(i).getStaticVideo().getCoverPath()));
                    authors[i].setText(historyList.get(i).getStaticVideo().getAuthor());
                    descriptions[i].setText("it's a good video!");
                }
                else
                    break;
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
