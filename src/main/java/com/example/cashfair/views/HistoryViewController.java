package com.example.cashfair.views;

import com.example.cashfair.App;
import com.example.cashfair.entities.Concept;
import com.example.cashfair.entities.Contributor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.io.IOException;
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

    public  static Concept concept;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        detailsBtn.setOnAction((ActionEvent a) -> showConceptDetails());
        historyLstVw.setCellFactory(stringListView -> new CenteredListViewCell());
        backBtn.setOnAction((ActionEvent a) -> App.redirectTo("home-screen"));
        if (concept != null) {
            historyLstVw.getItems().add(concept.getConceptName());
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

    public void getDataConcept(Concept sentConcept) {
        concept = sentConcept;
    }

    private void showConceptDetails()  {
        if (historyLstVw.getSelectionModel().getSelectedItem() != null) {
            ArrayList<Contributor> contributors = new ArrayList<>();
            contributors = concept.getListContributor();
            ConceptViewController concviewcont = new ConceptViewController();
            concviewcont.getDataPeople(contributors, concept);
            App.redirectTo("concept-screen");
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("You must select a concept");
            alert.showAndWait();
        }

    }
}
