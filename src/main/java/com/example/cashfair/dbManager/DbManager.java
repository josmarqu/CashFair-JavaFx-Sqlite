package com.example.cashfair.dbManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbManager {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");

        String url = "jdbc:h2:./com/example/cashfair/db/cashFairDb.mv.db";
        String usuario = "admin";
        Connection conexion = DriverManager.getConnection(url, usuario, "");

    }

    public static void insertData() {

    }

}
