package sample.controllers.sellerControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Created by miczi on 14.01.2017.
 */
public class PlacesInHallController {

    @FXML
    private Label placesLabel;
    @FXML
    private Button closeButton;

   // public String[] tempPlaces;
    private ReserveTicketController reserveTicketController;
    private SellTicketController sellTicketController;

    private void initialize(){

    }

    @FXML
    private void closeWindow(){
        Stage stageExit = (Stage) closeButton.getScene().getWindow();
        stageExit.close();
    }

    public void setReserveTicketController(ReserveTicketController reserveTicketController) {
        this.reserveTicketController = reserveTicketController;
    }
    public void setSellTicketController(SellTicketController sellTicketController) {
        this.sellTicketController = sellTicketController;
    }


    @FXML
    private void mouseOn(MouseEvent e){
        String[] temporaryPlaces = null;

        if(reserveTicketController == null){
            placesLabel.setText(sellTicketController.tempPlaces[0]);
            temporaryPlaces = sellTicketController.tempPlaces;
        } else if(sellTicketController == null){
            placesLabel.setText(reserveTicketController.tempPlaces[0]);
            temporaryPlaces = reserveTicketController.tempPlaces;
        }




        String[][] coordinate = new String[10][10];
        for(int i = 0; i < 10; i++)
            for(int j = 0; j < 10; j++){
                coordinate[i][j] = "0";
            }

        if(!temporaryPlaces[0].equals("-1")) {
            for (int i = 0; i < temporaryPlaces.length; i++) {
                coordinate[Integer.valueOf(temporaryPlaces[i]) - 1][Integer.valueOf(temporaryPlaces[i + 1]) - 1] = "1";
                i++;
            }
        }

        String text = "";
        for(int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                text += coordinate[i][j] + "\t\t";
            }
            text += "\n\n";
        }

        placesLabel.setText(text);
    }
}
