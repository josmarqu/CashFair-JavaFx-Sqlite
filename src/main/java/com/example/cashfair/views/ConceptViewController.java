package com.example.cashfair.views;

import com.example.cashfair.App;
import com.example.cashfair.entities.Contributor;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ConceptViewController implements Initializable {
    @FXML
    private Label conceptLbl;
    @FXML
    private TableView conceptTbl;
    @FXML
    private TableColumn<Contributor, String> cptNameCol;
    @FXML
    private TableColumn<Contributor, Double> cptAmountCol;
    @FXML
    private TableColumn<Contributor, Integer> cptPerCol;
    @FXML
    private Button backBtn;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backBtn.setOnAction((ActionEvent a) -> App.redirectTo("history-screen"));

        // TableView adding text test TODO: add text using xml data
        cptNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        cptAmountCol.setCellValueFactory(new PropertyValueFactory<>("money"));
        cptPerCol.setCellValueFactory(new PropertyValueFactory<>("percentage"));
        conceptTbl.getItems().add(
                new Contributor("Juan", 50.0, 100));
    }


}
