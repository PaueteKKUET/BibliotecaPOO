package com.fadedbytes.bibliotecapoo.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseManager {

    private static Connection DATABASE = null;

    public static boolean connect() {
        try {


            String url = "jdbc:postgresql://localhost:5432/biblioteca";

            Properties props = new Properties();
            props.setProperty("user", "postgres");
            props.setProperty("password", "alcachofa");
            props.setProperty("ssl", "false");

            DATABASE = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static Statement getStatement() {
        try {
            return DATABASE.createStatement();
        } catch (SQLException e) {
            System.out.println("Error al crear el Statement");
            e.printStackTrace();
            return null;
        }
    }

}
