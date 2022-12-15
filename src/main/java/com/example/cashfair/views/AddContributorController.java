package com.example.cashfair.views;

import com.example.cashfair.App;
import com.example.cashfair.entities.Contributor;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

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

    public static ArrayList<Contributor> contributors = new ArrayList<>();
    @FXML
    public void initialize() {
        spnPor.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 100, 1));

        btnBac.setOnAction(event3 -> App.redirectTo("home-screen"));
        btnAdd.setOnAction(event -> {
            if(txtName.getText().isBlank()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("You must enter the name");
                alert.showAndWait();
            }
            else if (spnPor.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("You must enter the distribution");
                alert.showAndWait();
            }
            else if (spnPor.getValue() + sumPor() > 100) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("The porcentage cannot be more than 100%");
                alert.showAndWait();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Information");
                alert.setContentText("Contributor added");
                alert.showAndWait();

                Contributor contributor = new Contributor(txtName.getText(), 0.0, spnPor.getValue());

                colName.setCellValueFactory(new PropertyValueFactory<>("name"));
                colPor.setCellValueFactory(new PropertyValueFactory<>("percentage"));
                tblContributor.getItems().add(contributor);

                clean();
            }

            btnRem.setOnAction(event2 -> {
                Contributor contributor = tblContributor.getSelectionModel().getSelectedItem();
                tblContributor.getItems().remove(contributor);
            });

            btnNex.setOnAction(event4 -> {
                if (tblContributor.getItems().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error");
                    alert.setContentText("You must add at least one contributor");
                    alert.showAndWait();
                }
                else if (sumPor() != 100) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error");
                    alert.setContentText("The porcentage must be 100%");
                    alert.showAndWait();
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText("Information");
                    alert.setContentText("Contributors added");
                    alert.showAndWait();

                    contributors.addAll(tblContributor.getItems());

                    MoneyPaymentController moneyPayCont = new MoneyPaymentController();
                    moneyPayCont.getDataPeople(contributors);

                    App.redirectTo("money-payment");
                }
            });
        });
    }

    private Double sumPor() {
        Double sum = 0.0;
        for (Contributor person : tblContributor.getItems()) {
            sum += person.getPercentage();
        }
        return sum;
    }

    private void clean() {
        txtName.setText("");
        spnPor.getValueFactory().setValue(1.0);
    }
}
