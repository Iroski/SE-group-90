package controller;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class SignUpController {
    private Stage singUpStage;

    /**
     * Sets the stage of this dialog.
     *
     * @param SignUpStage
     */
    public void setSignUpStage(Stage SignUpStage) {
        this.singUpStage = SignUpStage;
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        singUpStage.close();
    }
}
