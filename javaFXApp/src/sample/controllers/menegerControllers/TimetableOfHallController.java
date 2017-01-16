package sample.controllers.menegerControllers;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.Main;

/**
 * Created by miczi on 17.11.16.
 */
public class TimetableOfHallController {


    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField numberOfHall;
    @FXML
    private Label resultLabel;
    private TextField editor;

    @FXML
    private void showTimetable(){

        editor = datePicker.getEditor();
        String date = "";
        date = editor.getText();
        date = date.substring(6,10) + date.substring(3,5) + date.substring(0,2);

        if(!date.equals("") && !numberOfHall.getText().equals("")) {

            Main.bridge = "showRepertoireInHall," + date + "," + numberOfHall.getText();
            String result = "";

            while (true) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (!Main.solution.equals("")) {
                    result = Main.solution;

                    String[] repe = result.split(";");
                    String temp = "";
                    for(int i = 0; i < repe.length; i++){
                        temp += repe[i] + "\n";
                    }
                    resultLabel.setText(temp);

                    break;
                }
            }
            Main.solution = "";

        }else {
            // failLabel.setText("You have to choose title, date and hour");
        }
    }
}
