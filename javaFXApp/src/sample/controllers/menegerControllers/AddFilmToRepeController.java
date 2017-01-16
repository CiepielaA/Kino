package sample.controllers.menegerControllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.Main;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by miczi on 17.11.16.
 */
@SuppressWarnings("Duplicates")
public class AddFilmToRepeController {

    @FXML
    private TextField hallId;
    @FXML
    private TextField dateTextField;
    @FXML
    private TextField hourTextField;
    @FXML
    private Label failLabel;
    @FXML
    private ComboBox titleOfFilm;


    public void initialize() {

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
        titleOfFilm.getItems().addAll(movies);
    }

    @FXML
    private void addFilmToRep(){
        String date = dateTextField.getText();
        String hour = hourTextField.getText();

        if(!date.equals("") && !hour.equals("") && !String.valueOf(titleOfFilm.getValue()).equals("") && !hallId.getText().equals("")){

            date = dateTextField.getText().substring(0,4) + dateTextField.getText().substring(5,7) + dateTextField.getText().substring(8,10);
            hour = hourTextField.getText().substring(0,2) + hourTextField.getText().substring(3,5);

            Main.bridge = "addSeance," + String.valueOf(titleOfFilm.getValue()) + "," + hallId.getText() + "," + date + "," + hour;

            while (true) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (!Main.solution.equals("")) {
                    if(Main.solution.equals("Seance added")) {
                        failLabel.setText("Film added");
                        dateTextField.clear();
                        hourTextField.clear();
                        hallId.clear();
                        titleOfFilm.getItems().clear();
                    }
                    else failLabel.setText("Film didn't add.");
                    break;
                }
            }
            Main.solution = "";
        }else {
            failLabel.setText("You have to fill all fileds");
        }
    }
}
