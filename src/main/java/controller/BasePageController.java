package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import model.entity.ReturnEntity;
import model.entity.User;
import model.service.AccountService;
import model.service.UserService;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;


import static javafx.scene.paint.Color.*;

public class BasePageController {
    public Label vipLabel;
    public Button history;
    public static ImageView user_image;
    public Button b_log_out;
    public Button favorite;
    public Button b_account;
    public Button b_video;
    public Button b_coach;
    public Button b_lesson;
    public Button userName;
    public AnchorPane pane;
    public ImageView FIT;
    public AtomicBoolean checkPremium;
    public Button b_home;

    @FXML
    public void initialize() {
        String userName = LoginController.userName;
        this.userName.setText(userName);
        UserService userService=new UserService();
        ReturnEntity returnEntity=userService.getUser(userName);
        User user=null;
        if (returnEntity.getCode()==200) {
            user = (User) returnEntity.getObject();
        }
        if(user.getProfilePhotoPath() == null||user.getProfilePhotoPath().equals("")){
            user.setProfilePhotoPath("/view/images/default/profilephoto/11.jpg");
        }
        Image image = new Image(user.getProfilePhotoPath());
        user_image = new ImageView(image);
        user_image.setFitWidth(50);
        user_image.setPreserveRatio(true);
        user_image.setLayoutX(710);
        user_image.setLayoutY(13);
        user_image.setCursor(Cursor.HAND);
        user_image.setOnMouseClicked(mouseEvent1 -> {
            try {
                goToProfile(mouseEvent1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        pane.getChildren().add(user_image);
    }

    public void init(){
        AccountService accountService = new AccountService();
        ReturnEntity returnEntity=accountService.isPremium(LoginController.userName);
        checkPremium = new AtomicBoolean(false);
        if(returnEntity.getCode() == 200){
            // successful
            checkPremium = (AtomicBoolean)returnEntity.getObject();
            if(checkPremium.get()){
                vipLabel.setTextFill(Color.rgb(255,223,169));
            }
        }
        else if(returnEntity.getCode() == 4042){
            // account not exist
        }
        else if(returnEntity.getCode() == 5000){
            // database error
        }
        FIT.setImage(new Image("/view/images/FIT.png"));
    }

    public void showHistory(MouseEvent me) throws IOException{
            //vipPane.setVisible(true);
        Stage stage = (Stage) b_home.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/fxml/HistoryPage.fxml"));
        AnchorPane historyPane = (AnchorPane) loader.load();;
        AnchorPane anchorPane= (AnchorPane) stage.getScene().getRoot();
        anchorPane.getChildren().add(historyPane);
        historyPane.setLayoutY(75);
        historyPane.setLayoutX(history.getLayoutX()+0.1*historyPane.getPrefWidth());
        historyPane.setVisible(true);
        if(me.getEventType().equals(MouseEvent.MOUSE_ENTERED))
            history.setTextFill(PINK);
        else if(me.getEventType().equals(MouseEvent.MOUSE_EXITED))
            history.setTextFill(BLACK);
    }

    public void showVip(MouseEvent event) throws IOException{
        Label l = (Label) event.getSource();
        if(l.equals(vipLabel) && checkPremium.get()){ // is premium
            Stage stage = (Stage) b_home.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/fxml/VipPageForVip.fxml"));
            AnchorPane vip = (AnchorPane) loader.load();
            AnchorPane anchorPane= (AnchorPane) stage.getScene().getRoot();
            anchorPane.getChildren().add(vip);
            VipController vipController = loader.getController();
            vipController.vipCard.setUserData(this);
            vipController.init();
            vip.setLayoutY(75);
            vip.setLayoutX(vipLabel.getLayoutX()-0.5*vip.getPrefWidth()+ 0.5 * l.getPrefWidth());
            vip.setVisible(true);
        }
        else if(l.equals(vipLabel) && !checkPremium.get()){ // is not premium
            Stage stage = (Stage) b_home.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/fxml/VipPageForOrdinary.fxml"));
            AnchorPane vip = (AnchorPane) loader.load();
            AnchorPane anchorPane= (AnchorPane) stage.getScene().getRoot();
            anchorPane.getChildren().add(vip);
            VipPageForOrdinaryController vipPageForOrdinaryController = loader.getController();
            vipPageForOrdinaryController.vipCard.setUserData(this);
            vipPageForOrdinaryController.init();
            vip.setLayoutY(75);
            vip.setLayoutX(vipLabel.getLayoutX()-0.5 * vip.getPrefWidth() + 0.5 * l.getPrefWidth());
            vip.setVisible(true);
        }
    }


    public void buttonColorChange1(MouseEvent me){
        Button b = (Button) me.getSource();
        if(me.getEventType().equals(MouseEvent.MOUSE_ENTERED))
            b.setTextFill(PINK);
        else if(me.getEventType().equals(MouseEvent.MOUSE_EXITED))
            b.setTextFill(BLACK);
    }
    public void buttonColorChange2(MouseEvent me){
        Button b = (Button) me.getSource();
        if(me.getEventType().equals(MouseEvent.MOUSE_ENTERED))
            b.setTextFill(PINK);
        else if(me.getEventType().equals(MouseEvent.MOUSE_EXITED))
            b.setTextFill(WHITE);
    }

    public void goToCoach(MouseEvent mouseEvent) throws IOException {

        Stage stage = (Stage) b_home.getScene().getWindow();
        stage.setTitle("Coaches");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/fxml/CoachPage.fxml"));
        AnchorPane coaches = (AnchorPane) loader.load();
        // Set person overview into the center of root layout.
        AnchorPane anchorPane= (AnchorPane) stage.getScene().getRoot();
        anchorPane.getChildren().remove(2);
        anchorPane.getChildren().add(2, coaches);

        coaches.setLayoutX(200);
        coaches.setLayoutY(75);
    }
    public void goToVideo(MouseEvent mouseEvent) throws IOException {

        Stage stage = (Stage) b_home.getScene().getWindow();
        stage.setTitle("Videos");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/fxml/VideoPage.fxml"));
        AnchorPane video = (AnchorPane) loader.load();
        // Set person overview into the center of root layout.
        AnchorPane anchorPane= (AnchorPane) stage.getScene().getRoot();
        anchorPane.getChildren().remove(2);
        anchorPane.getChildren().add(2, video);

        video.setLayoutX(200);
        video.setLayoutY(75);
    }
    public void goToMyLesson(MouseEvent mouseEvent) throws IOException {

        Stage stage = (Stage) b_home.getScene().getWindow();
        stage.setTitle("My Lessons");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/fxml/LessonPage.fxml"));
        AnchorPane lessons = (AnchorPane) loader.load();
        // Set person overview into the center of root layout.
        AnchorPane anchorPane= (AnchorPane) stage.getScene().getRoot();
        anchorPane.getChildren().remove(2);
        anchorPane.getChildren().add(2, lessons);

        lessons.setLayoutX(200);
        lessons.setLayoutY(75);
        LessonPageController lessonPageController = loader.getController();
        lessonPageController.init();
    }
    public void goToHome(MouseEvent mouseEvent) throws IOException {

        Stage stage = (Stage) b_home.getScene().getWindow();
        stage.setTitle("Home");
        FXMLLoader loader = new FXMLLoader();
        this.init();
        loader.setLocation(getClass().getResource("/view/fxml/MainPage.fxml"));
        AnchorPane home = (AnchorPane) loader.load();
        // Set person overview into the center of root layout.
        AnchorPane anchorPane= (AnchorPane) stage.getScene().getRoot();
        anchorPane.getChildren().remove(2);
        anchorPane.getChildren().add(2, home);

        home.setLayoutX(200);
        home.setLayoutY(75);
    }
    public void goToFavorite(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) b_home.getScene().getWindow();
        stage.setTitle("Favorite");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/fxml/FavoritePage.fxml"));
        AnchorPane account = (AnchorPane) loader.load();
        // Set person overview into the center of root layout.
        AnchorPane anchorPane= (AnchorPane) stage.getScene().getRoot();
        anchorPane.getChildren().remove(2);
        anchorPane.getChildren().add(2, account);

        account.setLayoutX(200);
        account.setLayoutY(75);
    }
    public void goToAccount(MouseEvent mouseEvent) throws IOException {

        Stage stage = (Stage) b_home.getScene().getWindow();
        stage.setTitle("Account");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/fxml/Account.fxml"));
        AnchorPane account = (AnchorPane) loader.load();
        // Set person overview into the center of root layout.
        AnchorPane anchorPane= (AnchorPane) stage.getScene().getRoot();
        anchorPane.getChildren().remove(2);
        anchorPane.getChildren().add(2, account);

        account.setLayoutX(200);
        account.setLayoutY(75);
    }
    public void goToProfile(MouseEvent mouseEvent) throws IOException {

        Stage stage = (Stage) b_home.getScene().getWindow();
        stage.setTitle("Profile");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/fxml/Profile.fxml"));
        AnchorPane profile = (AnchorPane) loader.load();
        // Set person overview into the center of root layout.
        AnchorPane anchorPane= (AnchorPane) stage.getScene().getRoot();
        anchorPane.getChildren().remove(2);
        anchorPane.getChildren().add(2, profile);

        profile.setLayoutX(200);
        profile.setLayoutY(75);
    }

    public void goToLogin(MouseEvent mouseEvent) throws IOException{
        Stage stage = (Stage) b_home.getScene().getWindow();
        stage.close();
        stage=new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/fxml/LoginPage.fxml"));
        AnchorPane rootLayout=(AnchorPane) loader.load();
        //BasePageController basePageController=loader.getController();
        //basePageController.userName.setUserData();
        Scene scene=new Scene(rootLayout);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
    }

    public static void changeImage(String path){
        Image image = new Image(path);
        user_image.setImage(image);
    }
}
