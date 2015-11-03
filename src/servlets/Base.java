package servlets;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;


/**
 * Created by Sergey on 11/3/2015.
 */
public class Base {

    private final String URL = "jdbc:mysql://localhost:4000/data_logins";
    private final String USERNAME = "root";
    private final String PASSWORD = "root";
    private Connection connection;
    ResultSet rs = null;

    public Base () {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL,USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addUser (String user, String password) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("INSERT  INTO users(login, password) VALUES ('" + user + "','" + password + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}
