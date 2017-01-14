package sample.controllers.menegerControllers;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import sample.Main;

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


    @FXML
    private void removeFilmButton(){

        if(checkBox.isSelected() == true && String.valueOf(nameOfFilmComboBox.getValue()) != ""){


            Main.bridge = "removeFilmFromRep";

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            failLabel.setText("Removed in progress"); // nie wyswietla

            while (true) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (!Main.solution.equals("")) {
                    if (Main.solution.equals("Film removed")) {
                        System.out.println("Film removed--------");
                        checkBox.setSelected(false);
                    } else if (Main.solution.equals("Can't remove film from repertoire"))
                        System.out.println("Can't remove film from repertoire---------");
                    break;
                }
            }
            Main.solution = "";

        } else {
            failLabel.setText("Please pick title of film and/or select checkbox.");
        }

    }
}
