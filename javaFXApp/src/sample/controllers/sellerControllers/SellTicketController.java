package sample.controllers.sellerControllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import sample.controllers.mainControllers.LoadPane;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by miczi on 16.11.16.
 */
public class SellTicketController {

    @FXML
    private ComboBox filmComboBox;
    @FXML
    private ComboBox choseDate;
    @FXML
    private ComboBox choseHour;
    @FXML
    private TextField rowNumber;
    @FXML
    private TextField seatNumber;
    @FXML
    private Label failLabel;

    @FXML
    private void sellTicketButton(){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/sample/fxmlFiles/sellerFXMLs/Receipt.fxml"));
        LoadPane loadPane = new LoadPane(loader);
        loadPane.loadMyGridPane("Receipt");
    }


    @FXML
    private void showPlacesInHall(){

    }


    @FXML
    private void isDatesCBClicked(MouseEvent e){
        checkDates();
    }

    @FXML
    private void isHoursCBClicked(MouseEvent e){
        checkHours();
    }

    private void checkHours() {
//        tu bedziemy sprawdzac w jakich godzinach grany jest dany film w danym dniu

        List<String> dates = new ArrayList<>();


    }

    private void checkDates() {
        List<String> dates = new ArrayList<>();

//        tu bedziemy sprawdzac w jakich dniach grany jest dany film

        choseDate.getItems().clear();
        if(String.valueOf(filmComboBox.getValue()).equals("pitbul")) {
            dates.add("2017-09-23");
            dates.add("2017-12-13");
            dates.add("2017-04-23");
        } else
        if(String.valueOf(filmComboBox.getValue()).equals("zielonaMila")) {
            dates.add("2017-01-21");
            dates.add("2017-02-22");
            dates.add("2017-03-23");
        } else
        if(String.valueOf(filmComboBox.getValue()).equals("8mila")) {
            dates.add("2017-05-13");
            dates.add("2017-06-14");
            dates.add("2017-07-15");
        }
        choseDate.getItems().addAll(dates);
    }

}
