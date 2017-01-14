package sample.controllers.menegerControllers;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.Main;

/**
 * Created by miczi on 17.11.16.
 */
public class FireController {

    @FXML
    private TextField firstName;
    @FXML
    private TextField lastname;
    @FXML
    private TextField position;
    @FXML
    private CheckBox fireCheckBox;
    @FXML
    private Label failLabel;



    @FXML
    private void failEmployee(){

        System.out.println();

        if(dataValidation() && fireCheckBox.isSelected() == true) {
            Main.bridge = "removeDoctor";

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            failLabel.setText("Adding in progress..."); // nie wyswietla

            while (true) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (!Main.solution.equals("")) {
                    if (Main.solution.equals("doctor removed")) {
                        System.out.println("doctor removed--------");
                        firstName.clear();
                        lastname.clear();
                        position.clear();
                        fireCheckBox.setSelected(false);
                        failLabel.setText("Fired.");

                    } else if (Main.solution.equals("doctor doesn't removed"))
                        System.out.println("doctor doesn't removed---------");
                    break;
                }
            }
            Main.solution = "";

        } else if(fireCheckBox.isSelected() == false){
            failLabel.setText("If you want to fire employee, you have to select 'FIRE'");
        }
    }


    private boolean dataValidation(){
        if(firstName.getText().length() != 0 &&
                lastname.getText().length() != 0 &&
                position.getText().length() != 0){
            failLabel.setText("all fill.");
            return true;
        } else {
            failLabel.setText("You have to fill all fields.");
            return false;
        }
    }

}
