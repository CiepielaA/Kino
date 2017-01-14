package sample.controllers.sellerControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import sample.Main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.lang.String.valueOf;

/**
 * Created by miczi on 16.11.16.
 */
public class ReserveTicketController {

    @FXML
    private ComboBox filmComboBox;
    @FXML
    private ComboBox choseDate;
    @FXML
    private ComboBox choseHour;
    @FXML
    private TextField rowNumber;
    @FXML
    private TextField seatNumebers;
    @FXML
    private TextField emailTextField;
    @FXML
    private Label failLabel;

    public void initialize() {

//        tu bedziemy wysylac zapytanie o to jakie filmy sa obecnie dostepne
//        i wsadzac je do movies
        ObservableList<String> movies = FXCollections.observableArrayList(
                " ",
                "pitbul",
                "zielonaMila",
                "8mila");
        filmComboBox.getItems().addAll(movies);
    }

    @FXML
    private void reserveTicketButton(){
        //dodajemy purchase z isPaid = 0,
        //...
        //jesli dodany email to wysylamy na niego maila
        if(dataValidation()){
            String title = valueOf(filmComboBox.getValue());
            String date = valueOf(choseDate.getValue());
            String hour = valueOf(choseHour.getValue());
            String email = emailTextField.getText() == "" ? "" : emailTextField.getText();

            Main.bridge = "reserveTicket,"+
                    title+","+
                    date+","+
                    hour+","+
                    rowNumber.getText()+
                    ","+seatNumebers.getText()+
                    ","+0+  // isPaid
                    ","+0;  // idClient

            while (true) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (!Main.solution.equals("")) {
                    if (Main.solution.equals("reserveAdded")) {
                        filmComboBox.getItems().clear();
                        choseDate.getItems().clear();
                        choseHour.getItems().clear();
                        emailTextField.clear();
                        seatNumebers.clear();
                        rowNumber.clear();

                        failLabel.setText("Added...");
                    } else if (Main.solution.equals("reservation doesn't added"))
                        System.out.println("doctor doesn't removed---------");
                    break;
                }
            }
            Main.solution = "";
        }









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
        if(valueOf(filmComboBox.getValue()).equals("pitbul")) {
            dates.add("2017-09-23");
            dates.add("2017-12-13");
            dates.add("2017-04-23");
        } else
        if(valueOf(filmComboBox.getValue()).equals("zielonaMila")) {
            dates.add("2017-01-21");
            dates.add("2017-02-22");
            dates.add("2017-03-23");
        } else
        if(valueOf(filmComboBox.getValue()).equals("8mila")) {
            dates.add("2017-05-13");
            dates.add("2017-06-14");
            dates.add("2017-07-15");
        }
        choseDate.getItems().addAll(dates);
    }

    private boolean dataValidation(){
        if(seatNumebers.getText().length() != 0 &&
                rowNumber.getText().length() != 0 &&
                String.valueOf(filmComboBox.getValue()) != null &&
                String.valueOf(choseDate.getValue()) != null &&
                String.valueOf(choseHour.getValue()) != null){
            return true;
        } else {
            failLabel.setText("You have to fill all fields.");
            return false;
        }
    }

}
