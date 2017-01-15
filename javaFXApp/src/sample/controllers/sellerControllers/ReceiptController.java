package sample.controllers.sellerControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Created by miczi on 17.11.16.
 */
public class ReceiptController {

    @FXML
    private Label billToPay; // label to show how much to pay
    @FXML
    private Label changeLabel;
    @FXML
    private TextField howMuchPaid;
    @FXML
    private Button acceptButtonId;

    private SellTicketController sellTicketController;
    private TicketExchangeController ticketExchangeController;

    private int[] tabWithPrice = new int[3];
    private String priceType = "";


    @FXML
    public void acceptButton() {
        Stage stageExit = (Stage) acceptButtonId.getScene().getWindow();
        stageExit.close();
    }



    @FXML
    private void mouseEnter(MouseEvent e){
        if(billToPay.getText().equals("")) {
            System.out.println("najechalem na paragon");
            String text = "";
            if (priceType.equals("normalny")) {
                tabWithPrice[0] = 20;
                text = String.valueOf(tabWithPrice[0]);
            } else if (priceType.equals("ulgowy")) {
                tabWithPrice[1] = 15;
                text = String.valueOf(tabWithPrice[1]);
            } else if (priceType.equals("rodzinny")) {
                tabWithPrice[2] = 30;
                text = String.valueOf(tabWithPrice[2]);
            }

            billToPay.setText(String.valueOf(text));
        }
    }

    @FXML
    private void calculateChange(){
        int paid = Integer.valueOf(howMuchPaid.getText());
        int changeTemp = paid - Integer.valueOf(billToPay.getText());
        changeLabel.setText(String.valueOf(changeTemp));
    }

    public void setSellTicketController(SellTicketController sellTicketController){
        this.sellTicketController = sellTicketController;
    }

    public void setTicketExchangeController(TicketExchangeController ticketExchangeController) {
        this.ticketExchangeController = ticketExchangeController;
    }


    public void setPriceType(String priceType){
        this.priceType = priceType;
    }


}
