package com.example.cashfair;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class App extends Application {
    private static Scene scene;
    static Alert alert;
    static TextInputDialog dialog;
    static ButtonType buttonYes = new ButtonType("Yes");
    static ButtonType buttonNo= new ButtonType("No");
    static Optional<ButtonType> resultBtn;
    static Optional<String> resultStr;
    static boolean answer;
    static String answerTxt;
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("home-screen"), 1366, 768);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }

    private static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static void redirectTo(String to) {
        try {
            App.setRoot(to);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    public static void main(String[] args) {
        launch();
    }

    public static boolean showAlert(Alert.AlertType type, String message) {
        alert = new Alert(type);
        if (alert.getAlertType() == Alert.AlertType.ERROR) {
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText(message);
            alert.showAndWait();
        } else if (alert.getAlertType() == Alert.AlertType.INFORMATION) {
            alert.setTitle("Information");
            alert.setHeaderText("Information");
            alert.setContentText(message);
            alert.showAndWait();
        } else if (alert.getAlertType() == Alert.AlertType.CONFIRMATION) {
            alert.setTitle("Confirmation");
            alert.setHeaderText(message);
            alert.getButtonTypes().setAll(buttonYes, buttonNo);
            resultBtn = alert.showAndWait();
            if (resultBtn.get() == buttonYes) {
                answer = true;
            } else {
                answer = false;
            }
        }
        return (answer);
    }

    public static String showDialog(String tittle, String message) {
        dialog = new TextInputDialog();
        dialog.setTitle(tittle);
        dialog.setHeaderText(message);
        resultStr = dialog.showAndWait();
        resultStr.ifPresent(result -> answerTxt = result);
        return answerTxt;
    }
}