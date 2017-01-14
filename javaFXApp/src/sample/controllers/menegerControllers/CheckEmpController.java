package sample.controllers.menegerControllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.Main;
import sample.controllers.mainControllers.LoadPane;

/**
 * Created by miczi on 19.11.16.
 */
public class CheckEmpController {

    @FXML
    private TextField lastname;
    @FXML
    private Label failLabel;

    @FXML
    private void checkEmployeeButton(){
        if(dataValidation()){

            Main.bridge = "checkEmp";

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            failLabel.setText("Checking in progress"); // nie wyswietla

            while (true) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (!Main.solution.equals("")) {
                    if (Main.solution.equals("Emp checked")) {
                        System.out.println("doctor checked--------");
                        lastname.clear();
                        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/sample/fxmlFiles/menegerFXMLs/ShowEmployeeWindow.fxml"));
                        LoadPane loadPane = new LoadPane(loader);
                        loadPane.loadMyWindowPane("Employee");

                    }
                    else if (Main.solution.equals("Can't check emp"))
                        System.out.println("Can't check emp---------");
                    break;
                }
            }
            Main.solution = "";
        }
    }


    private boolean dataValidation(){
        if(lastname.getText().length() != 0){
            return true;
        } else {
            failLabel.setText("You have to fill fileds with lastname.");
            return false;
        }
    }

}
