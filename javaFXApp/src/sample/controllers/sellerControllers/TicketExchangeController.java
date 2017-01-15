package sample.controllers.sellerControllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.Main;
import sample.controllers.mainControllers.LoadPane;

import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.String.valueOf;

/**
 * Created by miczi on 17.11.16.
 */
@SuppressWarnings("Duplicates")
public class TicketExchangeController {

    @FXML
    private TextField ticketNumberToRemove;
    @FXML
    private ComboBox filmComboBox;
    @FXML
    private ComboBox choseDate;
    @FXML
    private ComboBox typeOfTicketCB;
    @FXML
    private ComboBox choseHour;
    @FXML
    private TextField rowNumber;
    @FXML
    private TextField seatNumber;
    @FXML
    private Label failLabel;



    public void initialize() {
        typeOfTicketCB.getItems().addAll("normalny","ulgowy","rodzinny");

        String allFilms;
        Main.bridge = "getFilms";

        while (true) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (!Main.solution.equals("")) {
                allFilms = Main.solution;
                break;
            }
        }
        Main.solution = "";

        ArrayList<String> movies = new ArrayList<>();
        String[] temp = allFilms.split(",");
        movies.addAll(Arrays.asList(temp));
        filmComboBox.getItems().addAll(movies);
    }

    @FXML
    private void sellTicketButton(){

        if(!ticketNumberToRemove.getText().equals("")){

            Main.bridge ="deleteTicket," + ticketNumberToRemove.getText();
            while (true) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (!Main.solution.equals("")) {

                    failLabel.setText("Ticket removed");

                    break;
                }
            }
        }


        if(dataValidation()){
            String title = valueOf(filmComboBox.getValue());
            String date = valueOf(choseDate.getValue());
            String hour = valueOf(choseHour.getValue());
            String ticketType =  valueOf(typeOfTicketCB.getValue());

            String subDate = date.substring(0,4)+date.substring(5,7)+date.substring(8,10);
            String subHour = hour.substring(0,2)+hour.substring(3,5);

            Main.bridge = "addPurchase,"+
                    title+","+
                    subDate+","+
                    subHour+","+
                    ticketType+","+
                    rowNumber.getText()+","+
                    seatNumber.getText()+","+
                    0+","+  // isPaid
                    0+","+  //idClient
                    1;      //buy ticket?

            while (true) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (!Main.solution.equals("")) {


                    FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/sample/fxmlFiles/sellerFXMLs/Receipt.fxml"));
                    LoadPane loadPane = new LoadPane(loader);
                    loadPane.loadMyGridPane("Receipt");
                    ReceiptController receiptController = loader.getController();
                    receiptController.setTicketExchangeController(this);
                    receiptController.setPriceType(ticketType);

                    filmComboBox.getItems().clear();
                    choseDate.getItems().clear();
                    choseHour.getItems().clear();
                    typeOfTicketCB.getItems().clear();
                    seatNumber.clear();
                    rowNumber.clear();

                    failLabel.setText("TicketId: " + Main.solution);

                    break;
                }
            }
            Main.solution = "";

        }else {
            failLabel.setText("You have to fill all fileds");
        }
    }









    public String[] tempPlaces;
    @FXML
    private void showPlacesInHall(){

        if(!String.valueOf(filmComboBox.getValue()).equals("") ||  !String.valueOf(choseDate.getValue()).equals("") || !String.valueOf(choseHour.getValue()).equals("")) {
            //change from YYYY-MM-DD to YYYYMMDD
            String date = String.valueOf(choseDate.getValue());
            String dateTemp = date.substring(0,4)+date.substring(5,7)+date.substring(8,10);
            //change from HH:MM to HHMM
            String hour = String.valueOf(choseHour.getValue());
            String hourTemp = hour.substring(0,2)+hour.substring(3,5);

            Main.bridge = "showReservedPlaces," + String.valueOf(filmComboBox.getValue()) + "," + dateTemp + "," + hourTemp;
            String reservedPlaces = "";

            while (true) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (!Main.solution.equals("")) {
                    reservedPlaces = Main.solution;
                    break;
                }
            }
            Main.solution = "";

            tempPlaces = reservedPlaces.split(",");

            showWindowWithPlaces(); // wyswietla okno

        }else {
         //   failLabel.setText("You have to choose title, date and hour");
        }
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
        if(String.valueOf(filmComboBox.getValue()) != "" || String.valueOf(choseDate.getValue()) != "") {
            choseHour.getItems().clear();
            //change from YYYY-MM-DD to YYYYMMDD
            String date = String.valueOf(choseDate.getValue());
            String dateTemp = date.substring(0,4)+date.substring(5,7)+date.substring(8,10);
            Main.bridge = "getHours," + String.valueOf(filmComboBox.getValue()) + "," + dateTemp;
            String allHours = "";
            while (true) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("sol:" + Main.solution);
                if (!Main.solution.equals("")) {
                    allHours = Main.solution;
                    break;
                }
            }
            Main.solution = "";

            ArrayList<String> hours = new ArrayList<>();
            String[] temp = allHours.split(",");
            for(int i = 0; i < temp.length; i++){
                temp[i] = temp[i].substring(0,2)+":"+temp[i].substring(2,4);
            }
            hours.addAll(Arrays.asList(temp));
            choseHour.getItems().addAll(hours);
        }
    }

    private void checkDates() {
        if(String.valueOf(filmComboBox.getValue()) != ""){
            choseDate.getItems().clear();
            Main.bridge = "getDates," + String.valueOf(filmComboBox.getValue());
            String allDates = "";

            while (true) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (!Main.solution.equals("")) {
                    allDates = Main.solution;
                    break;
                }
            }

            Main.solution = "";
            ArrayList<String> dates = new ArrayList<>();
            String[] temp = allDates.split(",");
            for(int i = 0; i < temp.length; i++){
                temp[i] = temp[i].substring(0,4)+"-"+temp[i].substring(4,6)+"-"+temp[i].substring(6,8);
            }
            dates.addAll(Arrays.asList(temp));
            choseDate.getItems().addAll(dates);
        }
    }

    private boolean dataValidation(){
        if(seatNumber.getText().length() != 0 &&
                rowNumber.getText().length() != 0 &&
                String.valueOf(filmComboBox.getValue()) != null &&
                String.valueOf(choseDate.getValue()) != null &&
                String.valueOf(choseHour.getValue()) != null){
            return true;
        } else {
         //   failLabel.setText("You have to fill all fields.");
            return false;
        }
    }

    private void showWindowWithPlaces() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/sample/fxmlFiles/sellerFXMLs/PlacesInHallWindow.fxml"));
        Pane pane = null;
        Stage stage = new Stage();
        try {
            pane = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        PlacesInHallController placesInHallController = loader.getController();
        placesInHallController.setTicketExchangeController(this);
        stage.setTitle("Places");
        stage.setScene(new Scene(pane));
        stage.setResizable(false);
        stage.show();
    }
}
