package base.com;


import java.sql.*;


/**
 * Created by Sergey on 11/3/2015.
 */
public class Base {
    private static final String URL = "jdbc:mysql://localhost:4000/data_logins";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static Connection connection;


    private static Base base = new Base();

    public Base() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL,USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        return connection;
    }
}
