package com.controllers;

import com.App;
import com.dbManager.DbManager;
import com.entities.Concept;
import com.entities.Contributor;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private TableColumn<Contributor, Double> cptPerCol;
    @FXML
    private Button backBtn;
    public static Concept concept;
    DbManager dbManager;
    public static String conceptName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backBtn.setOnAction((ActionEvent a) -> App.redirectTo("history-screen"));
        cptNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        initCptAmountCol();
        initCptPerCol();
        setData();
    }

    private void initCptPerCol() {
        cptPerCol.setCellValueFactory(new PropertyValueFactory<>("percentage"));
        cptPerCol.setCellFactory(tc -> new TableCell<Contributor, Double>() {
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

    private void initCptAmountCol() {
        cptAmountCol.setCellValueFactory(new PropertyValueFactory<>("money"));
        cptAmountCol.setCellFactory(tc -> new TableCell<Contributor, Double>() {
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



    private void setData() {
        dbManager = new DbManager();
        concept = dbManager.getConcept(conceptName);
        for (Contributor contributor : concept.getListContributor()) {
            conceptTbl.setItems(FXCollections.observableArrayList(concept.getListContributor()));
        }
        cptAmountCol.setText(concept.getCurrency());
        conceptLbl.setText(concept.getConceptName() + " (" + concept.getDate() + ")");
    }

    public void getConceptName(String sentConceptName) {
        conceptName = sentConceptName;
    }
}
