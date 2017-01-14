package sample.controllers.sellerControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Created by miczi on 14.01.2017.
 */
public class PlacesInHallController {

    @FXML
    private Label placesLabel;
    @FXML
    private Button closeButton;

    @FXML
    private void closeWindow(){
        Stage stageExit = (Stage) closeButton.getScene().getWindow();
        stageExit.close();
    }

}
