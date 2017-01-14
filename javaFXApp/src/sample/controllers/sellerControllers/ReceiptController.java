package sample.controllers.sellerControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by miczi on 17.11.16.
 */
public class ReceiptController {

    @FXML
    private Label billToPay; // label to show how much to pay
    @FXML
    private Label changeLabel;
    @FXML
    private TextField howMuchPaid;
    @FXML
    private Button acceptButtonId;

    @FXML
    public void acceptButton() {
        Stage stageExit = (Stage) acceptButtonId.getScene().getWindow();
        stageExit.close();
    }

    @FXML
    private void calculateChange(){

    }
}
