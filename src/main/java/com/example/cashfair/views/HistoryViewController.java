package com.example.cashfair.views;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;

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
        historyLstVw.getItems().add("Item 1");
        historyLstVw.getItems().add("Item 2");
        historyLstVw.getItems().add("Item 3");
    }
}
