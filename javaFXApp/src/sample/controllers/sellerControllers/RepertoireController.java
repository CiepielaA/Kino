package sample.controllers.sellerControllers;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import sample.Main;

/**
 * Created by miczi on 17.11.16.
 */
public class RepertoireController {

    @FXML
    private DatePicker datePicker;
    @FXML
    private Label repertoireLabel;
    private TextField editor;

//    @FXML
//    public void mouseIn(MouseEvent event){
//        //datePicker.setEditable(true);
//    }

    @FXML
    public void something(){

        editor = datePicker.getEditor();
        String date = "";
        date = editor.getText();
        date = date.substring(6,10) + date.substring(3,5) + date.substring(0,2);
       // date = "18142015" ; // do prob jadrawych
        String result = "";

        if(!date.equals("")) {

            Main.bridge = "showRepertoire," + date;

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
                    repertoireLabel.setText(temp);

                    System.out.println(result);
                    break;
                }
            }
            Main.solution = "";

        }else {
           // failLabel.setText("You have to choose title, date and hour");
        }

    }
}
