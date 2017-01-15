package sample.controllers.sellerControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.Main;

/**
 * Created by miczi on 17.11.16.
 */
public class CancelReservationController {

    @FXML
    private TextField numberOfReservationTextField;
    @FXML
    private Label failLabel;


    @FXML
    private void cancelResButton(){
        if(!numberOfReservationTextField.getText().equals("")){

            Main.bridge ="deletePurchase," + numberOfReservationTextField.getText();
            while (true) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (!Main.solution.equals("")) {

                    failLabel.setText(Main.solution);

                    break;
                }
            }
        }
    }
}
