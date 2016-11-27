package sample.controllers.mainControllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import sample.exitPackage.ExitClass;

/**
 * Created by miczi on 15.11.16.
 */
public class MenegerController {

    private MainController mainController;
    private LogController logController;
    @FXML
    private Label userName;
    @FXML
    private AnchorPane paneForWindows;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setLogController(LogController logController) {
        this.logController = logController;
    }

    public void setUserName(String txt){
        userName.setText(txt);
    }

    @FXML
    public void logOutButton(){
        mainController.loadLogScreen();
    }

    @FXML
    private void exitButton() {
        ExitClass exit = new ExitClass();
        exit.exit();
    }

    private void loadWindow(String window){
        FXMLLoader loader = null;
        if(window.equals("fireButton"))
            loader = new FXMLLoader(this.getClass().getResource("/sample/fxmlFiles/menegerFXMLs/FireWindow.fxml"));
        if(window.equals("addFilmButton"))
            loader = new FXMLLoader(this.getClass().getResource("/sample/fxmlFiles/menegerFXMLs/AddFilmToRepertoireWindow.fxml"));
        if(window.equals("removeFilmButton"))
            loader = new FXMLLoader(this.getClass().getResource("/sample/fxmlFiles/menegerFXMLs/RemoveFilmFromRepertoireWindow.fxml"));
        if(window.equals("viewershipButton"))
            loader = new FXMLLoader(this.getClass().getResource("/sample/fxmlFiles/menegerFXMLs/ViewershipWindow.fxml"));
        if(window.equals("timetableOfHallButton"))
            loader = new FXMLLoader(this.getClass().getResource("/sample/fxmlFiles/menegerFXMLs/TimetableOfHallWindow.fxml"));
        if(window.equals("hireButton"))
            loader = new FXMLLoader(this.getClass().getResource("/sample/fxmlFiles/menegerFXMLs/HireWindow.fxml"));
        if(window.equals("pricesModButton"))
            loader = new FXMLLoader(this.getClass().getResource("/sample/fxmlFiles/menegerFXMLs/PricesModWindow.fxml"));
        if(window.equals("checkEmpButton"))
            loader = new FXMLLoader(this.getClass().getResource("/sample/fxmlFiles/menegerFXMLs/CheckEmployeeWindow.fxml"));

        LoadPane loadPane = new LoadPane(loader, paneForWindows);
        loadPane.loadMyPane();
    }

    @FXML
    private void hireButton(){
        loadWindow("hireButton");
    }

    @FXML
    private void fireButton(){
        loadWindow("fireButton");
    }

    @FXML
    private void checkEmpButton(){ loadWindow("checkEmpButton");}

    @FXML
    private void addFilmButton(){
        loadWindow("addFilmButton");
    }

    @FXML
    private void removeFilmButton(){
        loadWindow("removeFilmButton");
    }

    @FXML
    private void viewershipButton(){
        loadWindow("viewershipButton");
    }

    @FXML
    private void timetableOfHallButton(){
        loadWindow("timetableOfHallButton");
    }

    @FXML
    private void pricesModButton(){
        loadWindow("pricesModButton");
    }
}
