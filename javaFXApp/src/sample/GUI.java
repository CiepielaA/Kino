package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by miczi on 02.01.17.
 */
public class GUI extends Application implements Runnable{

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("fxmlFiles/mainFXMLs/mainWindow.fxml"));
        primaryStage.setScene(new Scene(root, 900, 700));
        primaryStage.setTitle("Kino: Projektor");
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    public static String[] arguments;

    public static void runGUI(){
            launch();
    }

    public void run() {
        runGUI();
    }
}
