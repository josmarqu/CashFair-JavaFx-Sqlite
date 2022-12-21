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
        btnStyle.setOnAction(event -> changeStyle());
        if (App.style .contains("light_mode")){
            btnStyle.setGraphic(App.DARKIMG);
        } else {
            btnStyle.setGraphic(App.LIGHTIMG);
        }
    }

    private void changeStyle() {
        if (App.style.contains("light_mode")){
            App.style = "dark_mode";
            App.changeStyleSheet();
            btnStyle.setGraphic(App.LIGHTIMG);

        } else {
            App.style = "light_mode";
            App.changeStyleSheet();
            btnStyle.setGraphic(App.DARKIMG);
        }
    }
}
