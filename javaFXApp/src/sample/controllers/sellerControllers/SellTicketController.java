package sample.controllers.sellerControllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.Main;
import sample.controllers.mainControllers.LoadPane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.String.valueOf;

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

    public void initialize() {
//        String allFilms;
//        Main.bridge = "getFilms";
//
//        while (true) {
//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            if (!Main.solution.equals("")) {
//                allFilms = Main.solution;
//                break;
//            }
//        }
//        Main.solution = "";
//
//        ArrayList<String> movies = new ArrayList<>();
//        String[] temp = allFilms.split(",");
//        movies.addAll(Arrays.asList(temp));
//        filmComboBox.getItems().addAll(movies);
    }


    @FXML
    private void sellTicketButton(){

        if(dataValidation()){
            String title = valueOf(filmComboBox.getValue());
            String date = valueOf(choseDate.getValue());
            String hour = valueOf(choseHour.getValue());


            String subDate = date.substring(0,3)+date.substring(5,6)+date.substring(8,9);
            String subHour = date.substring(0,1)+date.substring(3,4);

            Main.bridge = "addPurchase,"+
                    title+","+
                    subDate+","+
                    subHour+","+
                    rowNumber.getText()+","+
                    seatNumber.getText()+","+
                    0+","+  // isPaid
                    0;  // idClient

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
                        seatNumber.clear();
                        rowNumber.clear();

                        failLabel.setText("Added...");
                    } else if (Main.solution.equals("reservation doesn't added"))
                        System.out.println("doctor doesn't removed---------");
                    break;
                }
            }
            Main.solution = "";
        }else {
            failLabel.setText("You have to fill all fileds");
        }




        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/sample/fxmlFiles/sellerFXMLs/Receipt.fxml"));
        LoadPane loadPane = new LoadPane(loader);
        loadPane.loadMyGridPane("Receipt");
    }


    @FXML
    private void showPlacesInHall(){

        showWindowWithPlaces();
        if(String.valueOf(filmComboBox.getValue()) != "" &&  String.valueOf(choseDate.getValue()) != "" && String.valueOf(choseHour.getValue()) != "") {
            Main.bridge = "getDates," + String.valueOf(filmComboBox.getValue()) + "," + String.valueOf(choseDate.getValue()) + "," + String.valueOf(choseHour.getValue());
            String reservedPlaces = "";

            while (true) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (!Main.solution.equals("")) {
                    if (Main.solution.equals("getPlaces")) {
                        reservedPlaces = Main.solution;
                    }
                    break;
                }
            }
            Main.solution = "";

            String[] temp = reservedPlaces.split(",");


        }else {
            failLabel.setText("You have to choose title, date and hour");
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
        if(String.valueOf(filmComboBox.getValue()) != "" && String.valueOf(choseDate.getValue()) != "") {
            Main.bridge = "getDates," + String.valueOf(filmComboBox.getValue()) + String.valueOf(choseDate.getValue());
            String allHours = "";
            while (true) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (!Main.solution.equals("")) {
                    if (Main.solution.equals("getHours")) {
                        allHours = Main.solution;
                    }
                    break;
                }
            }
            Main.solution = "";

            ArrayList<String> hours = new ArrayList<>();
            String[] temp = allHours.split(",");
            hours.addAll(Arrays.asList(temp));
            choseDate.getItems().addAll(hours);
        }


    }

    private void checkDates() {
        if(String.valueOf(filmComboBox.getValue()) != ""){
            Main.bridge = "getDates," + String.valueOf(filmComboBox.getValue());
            String allDates = "";

            while (true) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (!Main.solution.equals("")) {
                    if (Main.solution.equals("getDates")) {
                        allDates = Main.solution;
                    }
                    break;
                }
            }
            Main.solution = "";

            ArrayList<String> dates = new ArrayList<>();
            String[] temp = allDates.split(",");
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
            failLabel.setText("You have to fill all fields.");
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

        stage.setTitle("Places");
        stage.setScene(new Scene(pane));
        stage.setResizable(false);
        stage.show();
    }

}
