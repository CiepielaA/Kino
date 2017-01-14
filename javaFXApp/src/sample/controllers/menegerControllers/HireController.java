package sample.controllers.menegerControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import sample.Main;

/**
 * Created by miczi on 17.11.16.
 */
public class HireController {

    @FXML
    private TextField firstName;
    @FXML
    private TextField lastname;
    @FXML
    private TextField emailAddress;
    @FXML
    private TextField idNumber;
    @FXML
    private TextField employeeAddress;
    @FXML
    private TextField position;
    @FXML
    private TextField salary;
    @FXML
    private Label failLabel;


    @FXML
    private void addEmployee(){
        failLabel.textProperty().unbind();
        if(dataValidation()) {

            Main.bridge = "addDoctor";
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
                    if (Main.solution.equals("doctor added")) {
                        System.out.println("doctor added--------");
                        firstName.clear();
                        lastname.clear();
                        emailAddress.clear();
                        idNumber.clear();
                        employeeAddress.clear();
                        position.clear();
                        salary.clear();
                        failLabel.setText("Adding complete.");

                    } else if (Main.solution.equals("doctor doesn't added"))
                        System.out.println("doctor doesn't added---------");
                    break;
                }
                failLabel.setText("Adding in progress..."); // nie wyswietla

            }

            Main.solution = "";
        }
    }



    private boolean dataValidation(){
        if(firstName.getText().length() != 0 &&
                lastname.getText().length() != 0 &&
                emailAddress.getText().length() != 0 &&
                idNumber.getText().length() != 0 &&
                employeeAddress.getText().length() != 0 &&
                position.getText().length() != 0 &&
                salary.getText().length() != 0) {
            return true;
        } else {
            failLabel.setText("You have to fill all fields.");
            return false;
        }
    }

}

