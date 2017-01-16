package sample.controllers.menegerControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Created by miczi on 15.01.2017.
 */
public class EmployeeDataController {

    @FXML
    private Button closeB;
    @FXML
    private Label information;

    private String empData;
    private CheckEmpController checkEmpController;

    @FXML
    public void mouseEnter(MouseEvent e) {
        if(!empData.equals("")){
            String[] temp = empData.split(",");

            information.setText(
                    "Pesel: " + temp[0]+"\n"+
                            "Branch name: " + temp[1]+"\n"+
                            "Firstname: " + temp[2]+"\n"+
                            "Lastname: " + temp[3]+"\n"+
                            "Position: " + temp[4]+"\n"+
                            "E-mail address: " + temp[5]+"\n"+
                            "Address: " + temp[6]+"\n"+
                            "Salary: " + temp[7]+"\n"+
                            "Start date: " + temp[8]
            );

        } else {
            information.setText("Fail in display information.\nPlease try again.");
        }
    }

    @FXML
    private void closeButton(){
        Stage stageExit = (Stage) closeB.getScene().getWindow();
        stageExit.close();
    }


    public void setCheckEmpController(CheckEmpController checkEmpController){
        this.checkEmpController=checkEmpController;
        empData = this.checkEmpController.data;
    }
}
