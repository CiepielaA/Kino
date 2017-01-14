package sample.controllers.sellerControllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.controllers.mainControllers.LoadPane;

/**
 * Created by miczi on 17.11.16.
 */
public class TicketFromReserveController {

    @FXML
    private Label failReservationLabel;
    @FXML
    private TextField numberOfReservationTextField;

    @FXML
    private void acceptButton(){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/sample/fxmlFiles/sellerFXMLs/Receipt.fxml"));
        LoadPane loadPane = new LoadPane(loader);
        loadPane.loadMyGridPane("Receipt");
    }
}
