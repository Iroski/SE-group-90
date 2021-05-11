package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import javax.xml.transform.Source;
import java.io.IOException;

public class LiveController {

    private static final String ENTER = "Enter";
    public Button backButton;
    public Button sendButton;
    public TextArea chat;
    public ImageView chatImage;
    public TextArea test;
    private String chats="";
    @FXML
    public void initialize() {
        chatImage.setImage(new Image("view/images/liveChat.png"));
        test.setEditable(false);
        String style = "BORDER-BOTTOM-STYLE: none; BORDER-LEFT-STYLE: none; BORDER-RIGHT-STYLE: none; BORDER-TOP-STYLE: none";
        test.setStyle(style);
    }
    public void goToMyLesson(MouseEvent mouseEvent) throws IOException {

        Stage stage =(Stage) backButton.getScene().getWindow();
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

    public void sendText(MouseEvent mouseEvent) {
        String text=chat.getText();
        String temp="hly: " +text + "\n";
        this.chats=this.chats+temp;
        test.setText(this.chats);
        test.setScrollTop(Double.MAX_VALUE);
        test.setWrapText(true);
        chat.setText("");
    }

    public void send(KeyEvent keyEvent) {
        if (keyEvent.getCode().getName().equals(ENTER)) {
            String temp="hly: " +chat.getText() + "\n";
            this.chats=this.chats+temp;
            test.setText(this.chats);
            test.setScrollTop(Double.MAX_VALUE);
            test.setWrapText(true);
            chat.setText("");
        }
    }
}
