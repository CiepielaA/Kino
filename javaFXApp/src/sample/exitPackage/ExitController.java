package sample.exitPackage;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.controllers.mainControllers.LogController;
import sample.controllers.mainControllers.MainController;

/**
 * Created by miczi on 15.11.16.
 */
public class ExitController {


    private MainController mainController;
    private LogController logController;

    @FXML
    private Button closeWindow;

    @FXML
    private void buttonYesEnd() {
       // logController.hotel.closeDataBaseConnection();
        System.exit(0);
    }

    @FXML
    private void buttonNoEnd() {
        Stage stageExit = (Stage) closeWindow.getScene().getWindow();
        stageExit.close();
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setLogController(LogController logController) {
        this.logController = logController;
    }
}
