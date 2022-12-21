package com.controllers;

import com.App;
import com.entities.Contributor;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class AddContributorController {
    @FXML
    private Spinner<Double> spnPer;
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
    public static ArrayList<Contributor> listContributors = new ArrayList<>();
    @FXML
    public void initialize() {
        initializeWidgets();
        if (!listContributors.isEmpty()) {
            tblContributor.setItems(FXCollections.observableArrayList(listContributors));
            listContributors.clear();
        }
    }

    private void initializeWidgets() {
        initColumns();
        initSpinner();
        initButtons();
    }

    private void initButtons() {
        btnBac.setOnAction(event3 -> {
            App.redirectTo("home-screen");
            listContributors.clear();
        });
        btnAdd.setOnAction(event -> addDataTable());
        btnNex.setOnAction(event3 -> transferDataToPayment());
        btnRem.setOnAction(event2 -> removeContributor());
        btnSplit.setOnAction(event -> splitMoney());
    }

    private void splitMoney() {
        double size = tblContributor.getItems().size();
        if (size >= 2) {
            tblContributor.getItems().forEach(item -> item.setPercentage(100 / size));
            tblContributor.refresh();
        } else {
            App.showAlert(Alert.AlertType.ERROR, "You must add at least two contributors");
        }
    }

    private void removeContributor() {
        contributor = tblContributor.getSelectionModel().getSelectedItem();
        if (contributor != null)
        {
            tblContributor.getItems().remove(contributor);
        } else {
            App.showAlert(Alert.AlertType.ERROR, "You must select a contributor");
        }
    }

    private void initSpinner() {
        spnPer.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 100, 1));
        TextFormatter<Double> formatter = new TextFormatter<>(spnPer.getValueFactory().getConverter(), spnPer.getValueFactory().getValue(),
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
        spnPer.getEditor().setTextFormatter(formatter);
    }

    private void initColumns() {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
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
            App.redirectTo("money-payment");
        }
    }

    private void addDataTable() {
        if(txtName.getText().isBlank()) {
            App.showAlert(Alert.AlertType.ERROR, "You must enter the contributor's name");
        }
        else if (spnPer.getValue() == null) {
            App.showAlert(Alert.AlertType.ERROR, "You must enter the contributor's percentage payable");
        }
        else if (spnPer.getValue() + sumPor() > 100) {
            App.showAlert(Alert.AlertType.ERROR, "The percentage cannot be more than 100%");
        }
        else {
            contributor = new Contributor(txtName.getText(), 0.0, spnPer.getValue());
            fillTable();
            resetFields();
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

    private void resetFields(){
        txtName.setText("");
        spnPer.getValueFactory().setValue(1.0);
    }
}
