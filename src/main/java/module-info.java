module com.example.cashfair {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.cashfair to javafx.fxml;
    exports com.example.cashfair;

    opens com.example.cashfair.views to javafx.fxml;
    exports com.example.cashfair.views;

    opens com.example.cashfair.entities to javafx.fxml;
    exports com.example.cashfair.entities;
}