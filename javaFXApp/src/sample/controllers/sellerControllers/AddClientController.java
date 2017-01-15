package sample.controllers.sellerControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.Main;

/**
 * Created by miczi on 17.11.16.
 */
public class AddClientController {

    @FXML
    private TextField firstName;
    @FXML
    private TextField lastname;
    @FXML
    private Label failLabel;

    @FXML
    private void addClientCard(){

        if(!firstName.getText().equals("") && !lastname.getText().equals("")) {

            Main.bridge = "addClient," +firstName.getText()+","+lastname.getText();

            while (true) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (!Main.solution.equals("")) {
                    failLabel.setText("Added");
                    firstName.clear();
                    lastname.clear();
                    break;
                }
            }
            Main.solution = "";

        }else {
            failLabel.setText("You have to choose title, date and hour");
        }

    }
}
