package com.controllers;


import com.App;
import com.dbManager.DbManager;
import com.entities.Concept;
import com.entities.Contributor;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MoneyPaymentController implements Initializable {
    @FXML
    private ComboBox<String> cmbCurrency;
    @FXML
    private Spinner<Double> spnMoney;
    @FXML
    private TableView<Contributor> tblPayment;
    @FXML
    private TableColumn<Contributor, String> personCol;
    @FXML
    private TableColumn<Contributor, Double> amountCol;
    @FXML
    private Button btnApply;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnNext;
    private  static ArrayList<Contributor> listContributors = AddContributorController.listContributors;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeWidgets();
    }

    private void initializeWidgets()  {
        initColumns();
        initSpinner();
        initComboBox();
        initButtons();
    }

    private void initButtons() {
        btnApply.setOnAction(event -> splitMoney());
        btnBack.setOnAction(event -> App.redirectTo("addContributor"));
        btnNext.setOnAction(event -> makePayment());
    }

    private void splitMoney() {
        if (cmbCurrency.getValue() == null) {
            App.showAlert(Alert.AlertType.ERROR, "You must add the type of currency");
        }
        else {
            addTableItems();
            App.showAlert(Alert.AlertType.INFORMATION, "The money has been distributed");
        }
    }

    private void makePayment() {
        if (tblPayment.getItems().isEmpty()) {
            App.showAlert(Alert.AlertType.ERROR, "You must add the money to distribute");
        }
        else {
            Boolean answer;
            answer = App.showAlert(Alert.AlertType.CONFIRMATION, "Are you sure you want to make the payment?");
            if (answer == true) {
                storeData();
            } 
        }
    }

    private void initSpinner() {
        spnMoney.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 1000, 0, 0.5));

        TextFormatter<Double> formatter = new TextFormatter<>(spnMoney.getValueFactory().getConverter(), spnMoney.getValueFactory().getValue(),
                c -> {
                    if (c.getControlNewText().isEmpty()) {
                        return c;
                    }
                    try {
                        Double.parseDouble(c.getControlNewText());
                        return c;
                    } catch (NumberFormatException e) {
                        return null;
                    }
                });
        spnMoney.getEditor().setTextFormatter(formatter);
    }

    private void initColumns() {
        personCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("money"));
        amountCol.setCellFactory(tc -> new TableCell<Contributor, Double>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(App.CONVERTER.toString(item));
                }
            }
        });
    }

    private void storeData()  {
        String conceptName = App.showDialog("Request for concept name", "Please enter the name of the concept", "Concept name cant be empty");
        if (conceptName != null) {
            LocalDate currentDate = LocalDate.now();
            Concept concept = new Concept(listContributors, cmbCurrency.getValue(), String.valueOf(currentDate), conceptName);
            DbManager dbManager = new DbManager();
            dbManager.insertData(concept);
            App.showAlert(Alert.AlertType.INFORMATION, "Your operation was completed successfully!");
            App.redirectTo("home-screen");
            listContributors.clear();
        }
    }

    private void addTableItems() {
        for (Contributor contributor : listContributors) {
            contributor.setMoney(spnMoney.getValue() * contributor.getPercentage() / 100);
        }
        tblPayment.setItems(FXCollections.observableArrayList(listContributors));
    }

    private void initComboBox() {
        cmbCurrency.setItems(FXCollections.observableArrayList(
                "$",
                "€",
                "¥",
                "£"
        ));
    }
}
