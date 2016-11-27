package sample.controllers.mainControllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class MainController {

    @FXML
    private StackPane mainPane;

    @FXML
    public void initialize(){
        loadLogScreen();
    }

    public void loadLogScreen() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/sample/fxmlFiles/mainFXMLs/LogWindow.fxml"));
        Pane pane = null;
        try{
              pane = loader.load();
        } catch (IOException e){
               e.printStackTrace();
        }
        //wyci¹gamy referencje do kontrolera
        LogController logController = loader.getController();
        logController.setMainController(this);
        this.setScreen(pane);
    }


    public void setScreen(Pane pane) {
        mainPane.getChildren().clear();	// czyszczenie przed za³adowaniem innego okna
        mainPane.getChildren().add(pane);
    }

}
