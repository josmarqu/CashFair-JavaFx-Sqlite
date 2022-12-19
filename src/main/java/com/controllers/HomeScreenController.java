package com.controllers;

import com.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomeScreenController {
    @FXML
    private Button btnPay;

    @FXML
    private Button btnHis;

    @FXML
    public void initialize() {
        btnPay.setOnAction(event -> {
            App.redirectTo("addContributor");
        });
        btnHis.setOnAction(event -> {
            App.redirectTo("history-screen");
        });
    }
}
