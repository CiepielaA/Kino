package sample.controllers.sellerControllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.Main;
import sample.controllers.mainControllers.LoadPane;

import static java.lang.String.valueOf;

/**
 * Created by miczi on 17.11.16.
 */
public class TicketFromReserveController {

    @FXML
    private Label failReservationLabel;
    @FXML
    private TextField numberOfReservationTextField;
    @FXML
    private Label failLabel;

    @FXML
    private void acceptButton(){

        if(!numberOfReservationTextField.getText().equals("")){

            Main.bridge = "ticketFromRes," + numberOfReservationTextField.getText();

            while (true) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (!Main.solution.equals("")) {

//                    FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/sample/fxmlFiles/sellerFXMLs/Receipt.fxml"));
//                    LoadPane loadPane = new LoadPane(loader);
//                    loadPane.loadMyGridPane("Receipt");
//                    ReceiptController receiptController = loader.getController();
//                    receiptController.setSellTicketController(this);
//                    receiptController.setPriceType(ticketType);


                    numberOfReservationTextField.clear();

                    failLabel.setText("TicketId: " + Main.solution);

                    break;
                }
            }
            Main.solution = "";

        }else {
            failLabel.setText("You have to fill all fileds");
        }

    }
}
