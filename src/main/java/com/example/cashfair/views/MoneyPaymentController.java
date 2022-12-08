package com.example.cashfair.views;


import com.example.cashfair.entities.Contributor;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


import java.net.URL;
import java.util.ResourceBundle;

public class MoneyPaymentController implements Initializable {

    @FXML
    private ComboBox<String> moneyPaymentScrp;

    @FXML
    private Spinner<Double> moneyPaymentSpnr;

    @FXML
    private TableView moneyPaymentTable;


    @FXML
    private Button btnApply;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        moneyPaymentSpnr.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 1000, 0, 0.5));
        moneyPaymentSpnr.setEditable(true);
        addTableItem();

        addItemsComboBox();

    }

    private void addTableItem() {
        //TODO wait for other view to create the Persons to show

    }

    private void addItemsComboBox() {
        moneyPaymentScrp.setItems(FXCollections.observableArrayList(
                "$",
                "€",
                "¥",
                "£"
        ));
    }
}
