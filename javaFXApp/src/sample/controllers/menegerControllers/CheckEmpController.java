package sample.controllers.menegerControllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.Main;
import sample.controllers.mainControllers.LoadPane;
import sample.controllers.sellerControllers.PlacesInHallController;

/**
 * Created by miczi on 19.11.16.
 */
public class CheckEmpController {

    @FXML
    private TextField lastname;
    @FXML
    private Label failLabel;

    protected String data;

    @FXML
    private void checkEmployeeButton(){
        if(dataValidation()){

            Main.bridge = "checkEmp,"+lastname.getText();

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
                    if (!Main.solution.equals("blad")) {
                        data = Main.solution;

                        lastname.clear();
                        loadWindowWithEmp();
                    }
                    if (Main.solution.equals("blad"))
                        failLabel.setText("Can't find employee");
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


    private void loadWindowWithEmp(){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/sample/fxmlFiles/menegerFXMLs/EmployeeData.fxml"));
        Pane pane = null;
        Stage stage = new Stage();
        try {
            pane = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        EmployeeDataController employeeDataController = loader.getController();
        employeeDataController.setCheckEmpController(this);

        stage.setTitle("Information about employee");
        stage.setScene(new Scene(pane));
        stage.setResizable(false);
        stage.show();
    }
}
