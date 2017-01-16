package sample.controllers.menegerControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.Main;

/**
 * Created by miczi on 17.11.16.
 */
public class ViewershipController {

    @FXML
    private TextField titleOfFilm;
    @FXML
    private Label numberOfViwers;


    
    @FXML
    private void showAudience(){
        if(!titleOfFilm.getText().equals("")){

            Main.bridge = "viewership,"+titleOfFilm.getText();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            while (true) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (!Main.solution.equals("")) {
                    if (!Main.solution.equals("blad")) {
                        numberOfViwers.setText(Main.solution);

                        titleOfFilm.clear();
                    } else {
                        numberOfViwers.setText("No one");
                    }
                    break;
                }
            }
            Main.solution = "";
        }
    }
}
