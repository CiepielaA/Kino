package sample.controllers.menegerControllers;

import javafx.beans.DefaultProperty;
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
public class PricesModController {

    @FXML
    private ComboBox typeOfPrice;
    @FXML
    private TextField newValueOfPrice;
    @FXML
    private Label failLabel;

    public void initialize() {
        String prices;
        Main.bridge = "showTypesOfPrice";

        while (true) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (!Main.solution.equals("")) {
                prices = Main.solution;
                break;
            }
        }
        Main.solution = "";

        ArrayList<String> movies = new ArrayList<>();
        String[] temp = prices.split(",");
        movies.addAll(Arrays.asList(temp));
        typeOfPrice.getItems().addAll(movies);
    }


    @FXML
    private void changePrice(){

        if(!String.valueOf(typeOfPrice.getValue()).equals("") && !newValueOfPrice.getText().equals("")){

                Main.bridge = "updatePriceCost," + String.valueOf(typeOfPrice.getValue()) +"," + newValueOfPrice.getText();

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
                        if (Main.solution.equals("Price updated")) {
                            failLabel.setText("Price updated");
                        }
                        else failLabel.setText("Price can't be update.");
                        break;
                    }
                }
                Main.solution = "";

        } else {
            failLabel.setText("Please fill all of the fields.");
        }

    }
}
