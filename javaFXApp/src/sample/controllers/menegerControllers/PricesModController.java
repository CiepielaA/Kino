package sample.controllers.menegerControllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.Main;

/**
 * Created by miczi on 17.11.16.
 */
public class PricesModController {

    @FXML
    private ComboBox typeOfPrice;
    @FXML
    private TextField newValueOfPrice;
    @FXML
    private Label failLabel;

    @FXML
    private void changePrice(){

        if(dataValidation()){
            if(String.valueOf(typeOfPrice.getValue()) != ""){

                Main.bridge = "priceMod";

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                failLabel.setText("Modification in progress"); // nie wyswietla

                while (true) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (!Main.solution.equals("")) {
                        if (Main.solution.equals("Prices modificated")) {
                            System.out.println("Prices modificated--------");
                        }
                        else if (Main.solution.equals("Can't modificate prices"))
                            System.out.println("Can't modificate prices---------");
                        break;
                    }
                }
                Main.solution = "";
            }
        } else {
            failLabel.setText("Please fill all of the fields.");
        }

    }

    private boolean dataValidation(){
        if(newValueOfPrice.getText().length() != 0){
            return true;
        } else {
            failLabel.setText("You have to fill fileds with lastname.");
            return false;
        }
    }
}
