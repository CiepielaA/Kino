package sample.controllers.sellerControllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import sample.controllers.mainControllers.LoadPane;

/**
 * Created by miczi on 16.11.16.
 */
public class SellTicketController {

    @FXML
    private TextField numberOfTicketsLabel;
    @FXML
    private ComboBox<Object> filmCheckBox;
    @FXML
    private ComboBox<Object> typeOfFilm;
    @FXML
    private DatePicker dateCheck;
    @FXML
    private ComboBox<Object> seanceCheckBox;
    @FXML
    private TextField rowTextField;
    @FXML
    private TextField seatTextField;
    @FXML
    private Label failLabel;

    @FXML
    private void sellTicketButton(){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/sample/fxmlFiles/sellerFXMLs/Receipt.fxml"));
        LoadPane loadPane = new LoadPane(loader);
        loadPane.loadMyWindowPane("Receipt");
    }
}
