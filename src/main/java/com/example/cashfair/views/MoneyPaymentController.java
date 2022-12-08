package com.example.cashfair.views;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


import java.net.URL;
import java.util.ResourceBundle;

public class MoneyPaymentController implements Initializable {

    @FXML
    private ComboBox moneyPaymentScrp;

    @FXML
    private Spinner<Integer> moneyPaymentSpnr;

    @FXML
    private TableView moneyPaymentLstVw;

    @FXML
    private Button btnApply;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        moneyPaymentSpnr.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 0));
        moneyPaymentSpnr.setEditable(true);


    }
}
