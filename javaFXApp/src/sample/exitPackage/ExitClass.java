package sample.exitPackage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by miczi on 17.11.16.
 */
public class ExitClass {

    public void exit() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/sample/exitPackage/ExitWindow.fxml"));
        Group group = null;
        Stage stage = new Stage();
        try {
            group = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ExitController exitController = loader.getController();

        stage.setTitle("Exit?");
        stage.setScene(new Scene(group));
        stage.setResizable(false);
        stage.show();
    }
}
