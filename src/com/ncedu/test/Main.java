package com.ncedu.test;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Created by Sergey on 11/3/2015.
 */
public class Main {

    private static final String URL = "jbdc:mysql://localhost:4000/data_logins";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
        Connection connection;

        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            System.err.println("Don't load class of the driver!");
        }

        try {
            connection = DriverManager.getConnection(URL,USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT  INTO users(login, password) VALUES ('test','test')");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
