package servlets;


import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;
import java.util.Random;


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
        PreparedStatement preparedStatement = null;
        String insertUser = "INSERT  INTO users(login, password, salt) VALUES (?,?,?)";
        Random random = new Random();
        Long longSalt = random.nextLong();
        try {
            preparedStatement = connection.prepareStatement(insertUser);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, DigestUtils.sha256Hex(password + longSalt));
            preparedStatement.setLong(3, longSalt);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkUser (String user, String password) {
        PreparedStatement preparedStatement = null;
        String search = "SELECT login, password, salt FROM users WHERE login = ?";
        try {
            preparedStatement = connection.prepareStatement(search);
            preparedStatement.setString(1, user);
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()){
                if (res.getString("password").equals(DigestUtils.sha256Hex(password+res.getLong("salt")))) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User getUser(String user) {
        PreparedStatement preparedStatement = null;
        String search = "SELECT login, password FROM users WHERE login = ?";
        try {
            preparedStatement = connection.prepareStatement(search);
            preparedStatement.setString(1, user);
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()){
                User tmpUser = new User();
                tmpUser.setUser(res.getString("login"));
                tmpUser.setPassword(res.getString("password"));
                return tmpUser;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
