package sample.controllers.mainControllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import sample.exitPackage.ExitClass;

import java.io.IOException;

/**
 * Created by miczi on 15.11.16.
 */
public class LogController {

    private MainController mainController;

    @FXML
    private TextField loginText;
    @FXML
    private TextField passwordText;
    @FXML
    private Label bladLabel;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    public void checkLogin(){
        if(loginText.getText().equals("admin")){
            loadMenegerWindow();
        }else if(loginText.getText().equals("seller")){
            loadSellerWindow();
        }else bladLabel.setText("Bad username or password.");
    }

    public void loadMenegerWindow() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/sample/fxmlFiles/mainFXMLs/MenegerWindow.fxml"));
        Pane pane = null;
        try{
            pane = loader.load();
        }catch(IOException e){
            e.printStackTrace();
        }

        //wyciagamy referencje do kontrolera
        MenegerController menegerController = loader.getController();
        menegerController.setUserName(loginText.getText());
        menegerController.setMainController(mainController);
        menegerController.setLogController(this);
        mainController.setScreen(pane);
    }

    public void loadSellerWindow() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/sample/fxmlFiles/mainFXMLs/SellererWindow.fxml"));
        Pane pane = null;
        try{
            pane = loader.load();
        }catch(IOException e){
            e.printStackTrace();
        }

        //wyci¹gamy referencje do kontrolera
        SellerController sellerController = loader.getController();
        sellerController.setUserName(loginText.getText());
        sellerController.setMainController(mainController);
        sellerController.setLogController(this);
        mainController.setScreen(pane);
    }

    @FXML
    private void isEnter(){
        loginText.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.ENTER)) checkLogin();
            }
        });
    }

    @FXML
    private void exitButton() {
        System.exit(0); // to trzeba usunąć zeby okienko zamykania sie pojawialo
        ExitClass exit = new ExitClass();
        exit.exit();
    }

}
