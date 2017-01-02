package sample.controllers.menegerControllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.controllers.mainControllers.LoadPane;

/**
 * Created by miczi on 19.11.16.
 */
public class CheckEmpController {

    @FXML
    private TextField lastname;
    @FXML
    private TextField position;
    @FXML
    private Label failLabel;

    @FXML
    private void checkEmployeeButton(){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/sample/fxmlFiles/menegerFXMLs/ShowEmployeeWindow.fxml"));
        LoadPane loadPane = new LoadPane(loader);
        loadPane.loadMyWindowPane("Employee");
    }


}
