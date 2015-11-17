package base.com;

import servlets.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Sergey on 11/17/2015.
 */
public class BaseUser {

    public static boolean addUser (User tmpUser) {

        try {
            String insertUser = "INSERT  INTO users(login, password, salt) VALUES (?,?,?)";
            PreparedStatement preparedStatement = Base.getConnection().prepareStatement(insertUser);
            if (!BaseUser.checkUserInBase(tmpUser.getLogin())){
                preparedStatement.setString(1, tmpUser.getLogin());
                preparedStatement.setString(2, tmpUser.getPassword());
                preparedStatement.setLong(3, tmpUser.getSalt());
                preparedStatement.execute();
                return true;
            } else { return false;}
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean checkUserInBase(String login){
        try {
            String search = "SELECT login, password, salt FROM users WHERE login = ?";
            PreparedStatement preparedStatement = Base.getConnection().prepareStatement(search);
            preparedStatement.setString(1, login);
            ResultSet res = preparedStatement.executeQuery();
            if (res.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static User getUser(String login) {
        try {
            String search = "SELECT login, password, salt FROM users WHERE login = ?";
            PreparedStatement preparedStatement = Base.getConnection().prepareStatement(search);
            preparedStatement.setString(1, login);
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()){
                User user = new User(res.getString("login"), res.getString("password"), res.getLong("salt"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        User user = new User();
        return user;
    }

    public static boolean checkUser (User chUser) {
        User tmpUser = new User(BaseUser.getUser(chUser.getLogin()));
        chUser.setUser(chUser.getLogin(),chUser.getPassword(),tmpUser.getSalt());
        return chUser.equals(tmpUser);
    }

    public static boolean checkUserSession (User chUser){
        User tmpUser = new User(BaseUser.getUser(chUser.getLogin()));
        return chUser.equals(tmpUser);
    }
}
