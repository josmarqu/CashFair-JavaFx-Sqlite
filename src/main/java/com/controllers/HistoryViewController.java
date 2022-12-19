package com.controllers;

import com.App;
import com.dbManager.DbManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HistoryViewController implements Initializable {

    @FXML
    private ScrollPane historyScrp;
    @FXML
    private ListView historyLstVw;
    @FXML
    private Button backBtn;
    @FXML
    private Button detailsBtn;
    ConceptViewController concViewCont;
    private static ArrayList<String> listConcepts;
    DbManager dbManager;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeWidgets();
    }

    private void initializeWidgets() {
        detailsBtn.setOnAction((ActionEvent a) -> showConceptDetails());
        historyLstVw.setCellFactory(stringListView -> new CenteredListViewCell());
        backBtn.setOnAction((ActionEvent a) -> App.redirectTo("home-screen"));
        dbManager = new DbManager();
        listConcepts = dbManager.getConceptNames();
        if (!listConcepts.isEmpty()) {
            for (String concept_name: listConcepts) {
                historyLstVw.getItems().add(concept_name);
            }
        }
    }

    private void showConceptDetails() {
        concViewCont = new ConceptViewController();
        if (historyLstVw.getSelectionModel().isEmpty()){
            App.showAlert(Alert.AlertType.ERROR, "You must select a concept");
        }
        else {
            String conceptName = historyLstVw.getSelectionModel().getSelectedItem().toString();
            concViewCont.getConceptName(conceptName);
            App.redirectTo("concept-screen");
        }
    }

    final class CenteredListViewCell extends ListCell<String> {
        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
            } else {
                // Create the HBox
                HBox hBox = new HBox();
                hBox.setAlignment(Pos.CENTER);

                // Create centered Label
                Label label = new Label(item);
                label.setAlignment(Pos.CENTER);

                hBox.getChildren().add(label);
                setGraphic(hBox);
            }
        }
    }
}
