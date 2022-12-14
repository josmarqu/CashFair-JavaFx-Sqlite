package com.example.cashfair.views;

import com.example.cashfair.App;
import com.example.cashfair.entities.Person;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class AddPersonController {
    @FXML
    private Spinner<Double> spnPor;
    @FXML
    private Button btnAdd;
    @FXML
    private TextField txtName;
    @FXML
    private TableView<Person> tblPerson;
    @FXML
    private TableColumn<Person, String> colName;
    @FXML
    private TableColumn<Person, Double> colPor;
    @FXML
    private Button btnRem;
    @FXML
    private Button btnNex;
    @FXML
    public void initialize() {
        spnPor.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 100, 1));

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
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Information");
                alert.setContentText("Person added");
                alert.showAndWait();

                Person person = new Person(txtName.getText(), spnPor.getValue());

                colName.setCellValueFactory(new PropertyValueFactory<>("name"));
                colPor.setCellValueFactory(new PropertyValueFactory<>("porc"));
                tblPerson.getItems().add(person);

                clean();
            }

            btnRem.setOnAction(event2 -> {
                Person person = tblPerson.getSelectionModel().getSelectedItem();
                tblPerson.getItems().remove(person);
            });

            btnNex.setOnAction(event3 -> {
                if (tblPerson.getItems().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error");
                    alert.setContentText("You must add at least one person");
                    alert.showAndWait();
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText("Information");
                    alert.setContentText("People added");
                    alert.showAndWait();

                    ArrayList<Person> people = new ArrayList<>();
                    people.addAll(tblPerson.getItems());

                    App.redirectTo("money-payment");
                }
            });
        });
    }

    private void clean() {
        txtName.setText("");
        spnPor.getValueFactory().setValue(1.0);
    }
}
