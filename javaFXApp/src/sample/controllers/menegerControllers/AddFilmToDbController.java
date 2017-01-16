package sample.controllers.menegerControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.Main;

/**
 * Created by miczi on 08.01.2017.
 */
public class AddFilmToDbController {

    @FXML
    private TextField titleOfFilm;
    @FXML
    private TextField duration;
    @FXML
    private TextField yearOfProduction;
    @FXML
    private TextField typeOfFilm;
    @FXML
    private TextField ageLimit;
    @FXML
    private Label failLabel;
    @FXML
    private TextField directorLabel;

    @FXML
    private void addFilm(){

        if(!titleOfFilm.getText().equals("") && !duration.getText().equals("")
                && !yearOfProduction.getText().equals("") && !typeOfFilm.getText().equals("")
                && !ageLimit.getText().equals("") && !directorLabel.getText().equals("")){

            //String year = yearOfProduction.getText().substring(0,4) + yearOfProduction.getText().substring(5,7) + yearOfProduction.getText().substring(8,10);

            Main.bridge = "addMovie," + titleOfFilm.getText() +"," + duration.getText() + "," + yearOfProduction.getText() + "," + directorLabel.getText() +
            "," + typeOfFilm.getText() + "," + ageLimit.getText() + "," + 1;

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
                    if (Main.solution.equals("Movie added")) {
                        failLabel.setText("Movie added");
                        titleOfFilm.clear();
                        duration.clear();
                        yearOfProduction.clear();
                        typeOfFilm.clear();
                        ageLimit.clear();
                        directorLabel.clear();
                    }
                    else failLabel.setText("Movie can't be added. Pleace try again.");
                    break;
                }
            }
            Main.solution = "";

        } else {
            failLabel.setText("Please fill all of the fields.");
        }


    }

}
