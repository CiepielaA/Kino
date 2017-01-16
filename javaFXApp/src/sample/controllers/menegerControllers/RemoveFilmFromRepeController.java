package sample.controllers.menegerControllers;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import sample.Main;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by miczi on 17.11.16.
 */
public class RemoveFilmFromRepeController {

    @FXML
    private CheckBox checkBox;
    @FXML
    private ComboBox nameOfFilmComboBox;
    @FXML
    private Label failLabel;


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
        nameOfFilmComboBox.getItems().addAll(movies);
    }

    @FXML
    private void removeFilmButton(){

        if(checkBox.isSelected() == true && !String.valueOf(nameOfFilmComboBox.getValue()).equals("")){

            Main.bridge = "removeFilmFromRepertoire," + String.valueOf(nameOfFilmComboBox.getValue());

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            while (true) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (!Main.solution.equals("")) {
                    if (Main.solution.equals("Movie deleted")) {
                        failLabel.setText("Movie deleted");
                        checkBox.setSelected(false);
                        nameOfFilmComboBox.getItems().clear();
                    } else
                        failLabel.setText("Movie didn't deleted");
                    break;
                }
            }
            Main.solution = "";

        } else {
            failLabel.setText("Please pick title of film and/or select checkbox.");
        }

    }
}
