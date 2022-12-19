package com.cashfair.controllers;


import com.cashfair.App;
import com.cashfair.dbManager.DbManager;
import com.cashfair.entities.Concept;
import com.cashfair.entities.Contributor;
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

    @FXML
    private Button btnBack;

    @FXML
    private Button btnNext;

    public static ArrayList<Contributor> listContributors;
    Boolean answer;
    String answerTxt;
    Concept concept;
    DbManager dbManager;
    AddContributorController addcont;
    LocalDate currentDate;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeWidgets();
    }

    private void initializeWidgets()  {
        personCell.setCellValueFactory(new PropertyValueFactory<>("name"));
        initColAmount();
        moneyPaymentSpnr.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 1000, 0, 0.5));
        moneyPaymentSpnr.setEditable(true);
        addItemsComboBox();
        btnApply.setOnAction(event -> {
            if (moneyPaymentScrp.getValue() == null) {
                App.showAlert(Alert.AlertType.ERROR, "You must add the type of currency");
            }
            else {
                addTableItems();
                App.showAlert(Alert.AlertType.INFORMATION, "The money has been distributed");
            }
        });
        btnBack.setOnAction(event -> {
            addcont = new AddContributorController();
            addcont.getData(listContributors);
            App.redirectTo("addContributor");
        });
        btnNext.setOnAction(event -> {
            answer = App.showAlert(Alert.AlertType.CONFIRMATION, "Are you sure you want to make the payment?");
            if (answer = true) {
                storeData();
            } else {
                // Go back
            }
        });
    }

    private void initColAmount() {
        amountCell.setCellValueFactory(new PropertyValueFactory<>("money"));
        amountCell.setCellFactory(tc -> new TableCell<Contributor, Double>() {
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
        answerTxt = App.showDialog("Request for concept name", "Please enter the name of the concept");
        currentDate = LocalDate.now();
        concept = new Concept(listContributors, moneyPaymentScrp.getValue(), String.valueOf(currentDate),answerTxt);
        dbManager = new DbManager();
        dbManager.insertData(concept);
        App.redirectTo("home-screen");
        listContributors.clear();
        addcont = new AddContributorController();
        addcont.emptyFields();
    }

    private void addTableItems() {
        for (Contributor contributor : listContributors) {
            contributor.setMoney(moneyPaymentSpnr.getValue() * contributor.getPercentage() / 100);
        }
        moneyPaymentTable.setItems(FXCollections.observableArrayList(listContributors));
    }

    public void getDataPeople(ArrayList<Contributor> contributorsSent) {
        listContributors = contributorsSent;
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
