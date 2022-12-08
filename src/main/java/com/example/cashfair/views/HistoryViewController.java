package com.example.cashfair.views;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.net.URL;
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        historyLstVw.setCellFactory(stringListView -> new CenteredListViewCell());
        historyLstVw.getItems().add("Uber Eats");
        historyLstVw.getItems().add("Bono bus");
        historyLstVw.getItems().add("Port Aventura");
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
