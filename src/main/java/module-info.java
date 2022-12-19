module com.example.cashfair {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.json;


    opens com to javafx.fxml;
    exports com;

    opens com.controllers to javafx.fxml;
    exports com.controllers;

    opens com.entities to javafx.fxml;
    exports com.entities;
}