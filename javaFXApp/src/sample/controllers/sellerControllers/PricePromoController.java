package sample.controllers.sellerControllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import sample.Main;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by miczi on 17.11.16.
 */
public class PricePromoController implements Initializable{

    @FXML
    private TextField newValueOfPrice;
    @FXML
    private ComboBox typeOdPrice;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initialize();
    }

    @FXML
    private Label type1, type2, type3, type4;
    @FXML
    private Label price1, price2, price3, price4;


    public void initialize(){
        Main.bridge = "showPrices";
        String[] priceList;
        while (true) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (!Main.solution.equals("")) {
                priceList = Main.solution.split(",");
                if(priceList.length >= 2) {
                    type1.setText(priceList[0]);
                    price1.setText(priceList[1]);
                    if(priceList.length >= 4) {
                        type2.setText(priceList[2]);
                        price2.setText(priceList[3]);
                        if(priceList.length >= 6) {
                            type3.setText(priceList[4]);
                            price3.setText(priceList[5]);
                            if(priceList.length >= 8) {
                                type4.setText(priceList[6]);
                                price4.setText(priceList[7]);
                            }
                        }
                    }
                }
                break;
            }
        }
        Main.solution = "";

    }

}
