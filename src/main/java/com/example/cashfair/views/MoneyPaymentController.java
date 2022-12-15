package com.example.cashfair.views;


import com.example.cashfair.App;
import com.example.cashfair.entities.Concept;
import com.example.cashfair.entities.Contributor;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
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
    Concept concept;
    LocalDate currentDate;
    String conceptName;

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
                alert.setContentText("You must add the type of currency");
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

        btnBack.setOnAction(event -> {
            App.redirectTo("addContributor");
        });

        btnNext.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Are you sure you want to make the payment?");

            ButtonType buttonYes = new ButtonType("Yes");
            ButtonType buttonNo = new ButtonType("No");

            alert.getButtonTypes().setAll(buttonYes, buttonNo);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonYes) {
                storeData();
            } else {
                // Go back
            }
        });
    }

    private void storeData() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Request for concept name");
        dialog.setHeaderText("Please enter the name of the concept:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(concept -> conceptName = concept);
        currentDate = LocalDate.now();
        concept = new Concept(listContributors, moneyPaymentScrp.getValue(), String.valueOf(currentDate),conceptName);
        HistoryViewController histviewcont = new HistoryViewController();
        histviewcont.getDataConcept(concept);
        App.redirectTo("home-screen");
    }

    private void addTableItem() {
        for (Contributor contributor : listContributors) {
            contributor.setMoney(moneyPaymentSpnr.getValue() * contributor.getPercentage() / 100);
        }

        personCell.setCellValueFactory(new PropertyValueFactory<>("name"));
        amountCell.setCellValueFactory(new PropertyValueFactory<>("money"));
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
