package com;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.converter.NumberStringConverter;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Optional;

public class App extends Application {
    private static Scene scene;
    private static boolean answer;
    private static String answerTxt;
    public static String style = "light_mode";
    private static double x;
    private static double y;
    public static final NumberStringConverter CONVERTER = new NumberStringConverter("#.##");
    private static final ImageView SUCCESSIMG = new ImageView(String.valueOf(App.class.getResource("/images/success.png")));
    private static final ImageView ERRORIMG = new ImageView(String.valueOf(App.class.getResource("/images/error.png")));
    private static final ImageView QUESTIONIMG= new ImageView(String.valueOf(App.class.getResource("/images/question.png")));
    public static final ImageView DARKIMG = new ImageView(String.valueOf(App.class.getResource("/images/dark_mode.png")));
    public static final ImageView LIGHTIMG = new ImageView(String.valueOf(App.class.getResource("/images/light_mode.png")));

    @Override
    public void start(Stage stage) throws IOException {
        initStage(stage);
    }

    public static void main(String[] args) {
        launch();
    }

    private void initStage(Stage stage) throws IOException {
        initScene();
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
        x = stage.getX();
        y = stageGetY();
        addStageListeners(stage);
    }

    private static void initScene() throws IOException {
        scene = new Scene(loadFXML("home-screen"), 1366, 768);
        scene.getStylesheets().add(String.valueOf(App.class.getResource("/styles/" + style + ".css")));
    }

    private static void addStageListeners(Stage stage) {
        stage.xProperty().addListener((observable, oldValue, newValue) -> {
            x = newValue.doubleValue();
        });
        stage.yProperty().addListener((observable, oldValue, newValue) -> {
            y = newValue.doubleValue();
        });
    }

    public static Double stageGetX() {
        return x;
    }

    public static Double stageGetY() {
        return y;
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
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/views/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }


    public static boolean showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        centerAlerts(alert);
        alert.getDialogPane().getStylesheets().add(String.valueOf(App.class.getResource("/styles/" + style + ".css")));
        alert.setTitle("");
        if (alert.getAlertType() == Alert.AlertType.ERROR) {
            alert.setHeaderText("Error");
            alert.setContentText(message);
            alert.setGraphic(ERRORIMG);
            alert.showAndWait();
        } else if (alert.getAlertType() == Alert.AlertType.INFORMATION) {
            alert.setHeaderText("Success");
            alert.setContentText(message);
            alert.setGraphic(SUCCESSIMG);
            alert.showAndWait();
        } else if (alert.getAlertType() == Alert.AlertType.CONFIRMATION) {
            ButtonType buttonYes = new ButtonType("Yes");
            ButtonType buttonNo= new ButtonType("No");
            alert.setHeaderText(message);
            alert.setGraphic(QUESTIONIMG);
            alert.getButtonTypes().setAll(buttonYes, buttonNo);
            Optional<ButtonType> resultBtn = alert.showAndWait();
            if (resultBtn.get() == buttonYes) {
                answer = true;
            } else {
                answer = false;
            }
        }
        return (answer);
    }

    public static String showDialog(String tittle, String message, String messageError) {
        Dialog dialog = new TextInputDialog();
        centerDialogs(dialog);
        dialog.getDialogPane().getStylesheets().add(String.valueOf(App.class.getResource("/styles/" + style + ".css")));
        dialog.setTitle(tittle);
        dialog.setHeaderText(message);
        dialog.setGraphic(QUESTIONIMG);
        Optional<String> resultStr = dialog.showAndWait();

        if (resultStr.isPresent()) {
            answerTxt = resultStr.get().trim();
            if (answerTxt.isEmpty()){
                showAlert(Alert.AlertType.ERROR, messageError);
                return showDialog(tittle, message, messageError);
            } else {
                return answerTxt;
            }
        } else {
            return null;
        }
    }

    private static void centerDialogs(Dialog dialog){
        dialog.getDialogPane().setPrefWidth(350);
        dialog.getDialogPane().setPrefHeight(100);
        dialog.setX(((stageGetX() + (scene.getWidth() / 2)) - (dialog.getDialogPane().getWidth() / 2)));
        dialog.setY((stageGetY() + (scene.getHeight() / 2)) - (dialog.getDialogPane().getPrefHeight() / 2));
    }

    private static void centerAlerts(Alert alert){
        alert.getDialogPane().setPrefWidth(350);
        alert.getDialogPane().setPrefHeight(100);
        alert.setX((stageGetX() + (scene.getWidth() / 2)) - (alert.getDialogPane().getWidth() / 2));
        alert.setY((stageGetY() + (scene.getHeight() / 2)) - (alert.getDialogPane().getPrefHeight() / 2));
    }

    public static void changeStyleSheet() {
        scene.getStylesheets().clear();
        scene.getStylesheets().add(String.valueOf(App.class.getResource("/styles/" + style + ".css")));
    }
}