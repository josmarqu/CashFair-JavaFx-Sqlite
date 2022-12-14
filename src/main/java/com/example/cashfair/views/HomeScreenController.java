package com.example.cashfair.views;

import com.example.cashfair.App;
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
            App.redirectTo("addPerson");
        });
        btnHis.setOnAction(event -> {
            App.redirectTo("history-screen");
        });
    }
}
