package com.example.cashfair.dbManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbManager {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");

        String url = "jdbc:h2:/home/jose/Trabajo/2añodam/Desarrollo de interfaces/Workspace/CashFair/CashFair/src/main/java/com/example/cashfair/db/cashFairDb";
        String usuario = "admin";
        Connection conexion = DriverManager.getConnection(url, usuario, "");

    }
}
