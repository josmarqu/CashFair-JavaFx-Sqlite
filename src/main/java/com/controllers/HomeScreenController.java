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
    private Button btnStyle;

    @FXML
    public void initialize() {
        btnPay.setOnAction(event -> App.redirectTo("addContributor"));
        btnHis.setOnAction(event -> App.redirectTo("history-screen"));
        btnStyle.setGraphic(App.DARKIMG);
        btnStyle.setOnAction(event -> changeStyle());
    }

    private void changeStyle() {
        if (App.style.contains("light_mode")){
            App.style = "dark_mode";
            btnStyle.setGraphic(App.LIGHTIMG);
            App.changeStyleSheet();
        } else {
            App.style = "light_mode";
            btnStyle.setGraphic(App.DARKIMG);
            App.changeStyleSheet();
        }
    }
}
