package sample.controllers.mainControllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sample.controllers.sellerControllers.ReceiptController;

import java.io.IOException;
/**
 * Thanks to this class we can only make instance of this class and
 * load our pane in class MenegerController and SellerController
 */

/**
 * Created by miczi on 18.11.16.
 */
public class LoadPane {

    private FXMLLoader loader;
    private Pane paneForWindows;

    public LoadPane(FXMLLoader loader, Pane paneForWindows){
        this.loader = loader;
        this.paneForWindows = paneForWindows;
    }

    public LoadPane(FXMLLoader loader){
        this.loader = loader;
    }

    public void loadMyPane(){
        if(loader != null) {
            Pane pane = null;
            try {
                pane = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //creating refference to controller
            this.setScreen(pane);
        } else {
            System.out.println("Nie zaladowano loadera!");
            System.exit(-1);
        }
    }

    private void setScreen(Pane pane) {
        paneForWindows.getChildren().clear();	// czyszczenie przed za³adowaniem innego okna
        paneForWindows.getChildren().add(pane);
    }



    public void loadMyGridPane(String nameOfWindow){
        GridPane group = null;
        Stage stage = new Stage();

        try {
            group = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ReceiptController controller = loader.getController();

        stage.setTitle(nameOfWindow);
        stage.setScene(new Scene(group));
        stage.setResizable(false);
        stage.show();
    }

    public void loadMyWindowPane(String nameOfWindow){
        StackPane group = null;
        Stage stage = new Stage();

        try {
            group = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        ReceiptController controller = loader.getController();

        stage.setTitle(nameOfWindow);
        stage.setScene(new Scene(group));
        stage.setResizable(false);
        stage.show();
    }



}
