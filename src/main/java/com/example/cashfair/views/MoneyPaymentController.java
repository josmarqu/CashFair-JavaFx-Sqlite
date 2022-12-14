package com.example.cashfair.views;


import com.example.cashfair.entities.Contributor;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MoneyPaymentController implements Initializable {

    @FXML
    private ComboBox<String> moneyPaymentScrp;

    @FXML
    private Spinner<Double> moneyPaymentSpnr;

    @FXML
    private TableView<Contributor> moneyPaymentTable;

    @FXML
    private TableColumn<Contributor, String> personCell;

    @FXML
    private TableColumn<Contributor, Double> amountCell;

    @FXML
    private Button btnApply;

    public static ArrayList<Contributor> people;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        moneyPaymentSpnr.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 1000, 0, 0.5));
        moneyPaymentSpnr.setEditable(true);;

        addItemsComboBox();

        btnApply.setOnAction(event -> {
            if (moneyPaymentScrp.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("You must add the money to distribute");
                alert.showAndWait();
            }
            else {
                addTableItem();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Information");
                alert.setContentText("The money has been distributed");
                alert.showAndWait();
            }
        });
    }

    private void addTableItem() {
        for (Contributor contributor : people) {
            contributor.setMoney(moneyPaymentSpnr.getValue() * contributor.getPercentage() / 100);
        }

        personCell.setCellValueFactory(new PropertyValueFactory<>("name"));
        amountCell.setCellValueFactory(new PropertyValueFactory<>("money"));
        moneyPaymentTable.setItems(FXCollections.observableArrayList(people));

    }

    public void getDataPeople(ArrayList<Contributor> peopleSent) {
        people = peopleSent;
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
