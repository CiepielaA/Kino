package sample.controllers.menegerControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Created by miczi on 19.11.16.
 */
public class ShowEmployeeController {

    @FXML
    private Label firstnameL;
    @FXML
    private Label lastnameL;
    @FXML
    private Label positionL;
    @FXML
    private Label slaryL;
    @FXML
    private Label phoneL;
    @FXML
    private Label emailL;
    @FXML
    private Label addressL;
    @FXML
    private Label idNumberL;
    @FXML
    private Label startDateL;
    @FXML
    private Button okExitButton;



    public void okExitButton(ActionEvent actionEvent) {
        Stage stageExit = (Stage) okExitButton.getScene().getWindow();
        stageExit.close();
    }
}
