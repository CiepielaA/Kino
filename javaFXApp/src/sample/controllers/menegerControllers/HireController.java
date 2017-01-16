package sample.controllers.menegerControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import sample.Main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
    private TextField branchName;
    @FXML
    private Label failLabel;



    @FXML
    private void addEmployee(){
        failLabel.textProperty().unbind();
        if(dataValidation()) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate start = LocalDate.now();
            String startDate = formatter.format(start);
            startDate = startDate.substring(0,4) + startDate.substring(5,7) +startDate.substring(8,10);

                    Main.bridge = "addEmployee," +
                            idNumber.getText()+","+
                            branchName.getText()+","+
                            firstName.getText()+","+
                            lastname.getText()+","+
                            position.getText()+","+
                            emailAddress.getText()+","+
                            employeeAddress.getText()+","+
                            salary.getText()+","+
                            startDate;
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
                    if (Main.solution.equals("Employee added")) {
                        firstName.clear();
                        lastname.clear();
                        emailAddress.clear();
                        idNumber.clear();
                        employeeAddress.clear();
                        position.clear();
                        salary.clear();
                        branchName.clear();
                        failLabel.setText("Adding complete.");

                    } else {
                        failLabel.setText("Added error");
                    }
                    break;
                }
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

