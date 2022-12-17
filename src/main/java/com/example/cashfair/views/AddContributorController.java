package com.example.cashfair.views;

import com.example.cashfair.App;
import com.example.cashfair.entities.Contributor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AddContributorController {
    @FXML
    private Spinner<Double> spnPor;
    @FXML
    private Button btnAdd;
    @FXML
    private TextField txtName;
    @FXML
    private TableView<Contributor> tblContributor;
    @FXML
    private TableColumn<Contributor, String> colName;
    @FXML
    private TableColumn<Contributor, Double> colPor;
    @FXML
    private Button btnRem;
    @FXML
    private Button btnNex;
    @FXML
    private Button btnBac;
    @FXML
    private Button btnSplit;
    private static Contributor contributor;
    private MoneyPaymentController moneyPayCont;
    private static ArrayList<Contributor> listContributors = new ArrayList<>();
    @FXML
    public void initialize() {
        initializeWidgets();
    }

    private void initializeWidgets() {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        initColPer();
        spnPor.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 100, 1));
        btnBac.setOnAction(event3 -> {
            App.redirectTo("home-screen");
            emptyFields();
        });
        btnAdd.setOnAction(event -> addDataTable());
        btnNex.setOnAction(event3 -> transferDataToPayment());
        btnRem.setOnAction(event2 -> {
            contributor = tblContributor.getSelectionModel().getSelectedItem();
            if (contributor != null)
            {
                tblContributor.getItems().remove(contributor);
            } else {
                App.showAlert(Alert.AlertType.ERROR, "You must select a contributor");
            }
        });
        btnSplit.setOnAction(event -> {
            double size = tblContributor.getItems().size();
            if (size >= 2) {
                tblContributor.getItems().forEach(item -> item.setPercentage(100 / size));
                tblContributor.refresh();
            } else {
                App.showAlert(Alert.AlertType.ERROR, "You must add at least two contributors");
            }
        });
        if (!listContributors.isEmpty()) {
            tblContributor.setItems(FXCollections.observableArrayList(listContributors));
        }
    }

    private void initColPer() {
        colPor.setCellValueFactory(new PropertyValueFactory<>("percentage"));
        colPor.setCellFactory(tc -> new TableCell<Contributor, Double>() {
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

    private void transferDataToPayment() {
        if (tblContributor.getItems().isEmpty()) {
            App.showAlert(Alert.AlertType.ERROR, "You must add at least one contributor");
        }
        else if (sumPor() != 100) {
            App.showAlert(Alert.AlertType.ERROR, "The percentage must be 100%");
        }
        else {
            App.showAlert(Alert.AlertType.INFORMATION, "Contributors added");
            listContributors.addAll(tblContributor.getItems());
            moneyPayCont = new MoneyPaymentController();
            moneyPayCont.getDataPeople(listContributors);
            App.redirectTo("money-payment");
        }
    }

    private void addDataTable() {
        if(txtName.getText().isBlank()) {
            App.showAlert(Alert.AlertType.ERROR, "You must enter the name of the contributor");
        }
        else if (spnPor.getValue() == null) {
            App.showAlert(Alert.AlertType.ERROR, "You must enter the contributor's percentage payable");
        }
        else if (spnPor.getValue() + sumPor() > 100) {
            App.showAlert(Alert.AlertType.ERROR, "The percentage cannot be more than 100%");
        }
        else {
            App.showAlert(Alert.AlertType.INFORMATION, "Contributor added");
            contributor = new Contributor(txtName.getText(), 0.0, spnPor.getValue());
            fillTable();
            emptyFields();
        }
    }

    public void fillTable() {
        tblContributor.getItems().add(contributor);
    }

    private Double sumPor() {
        Double sum = 0.0;
        for (Contributor contributor : tblContributor.getItems()) {
            sum += contributor.getPercentage();
        }
        return sum;
    }

    public void getData(ArrayList<Contributor> listContributorsSent) {
        listContributors = listContributorsSent;
    }

    public void emptyFields() {
        listContributors.clear();
    }
}
