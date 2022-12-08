package com.example.cashfair.views;

import com.example.cashfair.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ConceptViewController implements Initializable {
    @FXML
    private Label conceptLbl;
    @FXML
    private TableView conceptTbl;
    @FXML
    private TableColumn cptNameCol;
    @FXML
    private TableColumn cptAmountCol;
    @FXML
    private TableColumn cptPerCol;
    @FXML
    private Button backBtn;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backBtn.setOnAction((ActionEvent a) -> App.redirectTo("history-screen"));
    }


}
