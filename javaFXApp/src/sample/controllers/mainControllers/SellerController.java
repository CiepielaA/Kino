package sample.controllers.mainControllers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import sample.exitPackage.ExitClass;

/**
 * Created by miczi on 15.11.16.
 */
public class SellerController {


    private LogController logController;
    private MainController mainController;
    @FXML
    private Label userName;


    public void setUserName(String txt){
        userName.setText(txt);
    }

    public void setLogController(LogController logController) {
        this.logController = logController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
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

    @FXML
    private AnchorPane paneForWindows;

    private void loadWindow(String window){
        FXMLLoader loader = null;
        if(window.equals("setSellTicketWindow"))
            loader = new FXMLLoader(this.getClass().getResource("/sample/fxmlFiles/sellerFXMLs/SellTicketWindow.fxml"));
        if(window.equals("setReserveWindow"))
            loader = new FXMLLoader(this.getClass().getResource("/sample/fxmlFiles/sellerFXMLs/ReserveTicketWindow.fxml"));
        if(window.equals("ticketFormReserveButton"))
            loader = new FXMLLoader(this.getClass().getResource("/sample/fxmlFiles/sellerFXMLs/TicketFromReservationWindow.fxml"));
        if(window.equals("ticketExchangeButton"))
            loader = new FXMLLoader(this.getClass().getResource("/sample/fxmlFiles/sellerFXMLs/TicketExchangeWindow.fxml"));
        if(window.equals("addClientButton"))
            loader = new FXMLLoader(this.getClass().getResource("/sample/fxmlFiles/sellerFXMLs/AddClientWindow.fxml"));
        if(window.equals("priceAndPromoButton"))
            loader = new FXMLLoader(this.getClass().getResource("/sample/fxmlFiles/sellerFXMLs/PriceAndPromoWindow.fxml"));
        if(window.equals("reportireButton"))
            loader = new FXMLLoader(this.getClass().getResource("/sample/fxmlFiles/sellerFXMLs/ShowRepertoireWindow.fxml"));
        if(window.equals("cancelReservationButton"))
            loader = new FXMLLoader(this.getClass().getResource("/sample/fxmlFiles/sellerFXMLs/CancelReservationWindow.fxml"));

        LoadPane loadPane = new LoadPane(loader, paneForWindows);
        loadPane.loadMyPane();
    }


//    public void setScreen(Pane pane) {
//        paneForWindows.getChildren().clear();	// czyszczenie przed za³adowaniem innego okna
//        paneForWindows.getChildren().add(pane);
//    }


    @FXML
    private void setSellTicketWindow(){
        loadWindow("setSellTicketWindow");
    }

    @FXML
    private void setReserveWindow(){
        loadWindow("setReserveWindow");
    }


    @FXML
    private void ticketFormReserveButton(){
        loadWindow("ticketFormReserveButton");
    }


    @FXML
    private void ticketExchangeButton(){
        loadWindow("ticketExchangeButton");
    }

    @FXML
    private void loyalityCardButton(){
        loadWindow("addClientButton");
    }


    @FXML
    private void priceAndPromoButton(){
        loadWindow("priceAndPromoButton");
    }

    @FXML
    private void reportireButton(){
        loadWindow("reportireButton");
    }

    @FXML
    private void cancelReservationButton(){ loadWindow("cancelReservationButton");}

}
