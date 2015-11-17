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
        PreparedStatement preparedStatement = null;
        String insertUser = "INSERT  INTO users(login, password, salt) VALUES (?,?,?)";
        try {
            preparedStatement = Base.getConnection().prepareStatement(insertUser);
            if (!tmpUser.getUser(tmpUser.getLogin())){
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

    public static User getUser(String login) {
        User user = new User();
        PreparedStatement preparedStatement = null;
        String search = "SELECT login, password, salt FROM users WHERE login = ?";
        try {
            preparedStatement = Base.getConnection().prepareStatement(search);
            preparedStatement.setString(1, login);
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()){
                this.setUser(res.getString("login"), res.getString("password"), res.getLong("salt"));
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkUser (User chUser) {
        User tmpUser = new User();
        tmpUser.getUser(chUser.getLogin());
        chUser.setUser(chUser.getLogin(),generatePas(chUser.getPassword(),tmpUser.getSalt()),tmpUser.getSalt());
        return chUser.equals(tmpUser);
    }

    public boolean checkUserSession (User chUser){
        User tmpUser = new User();
        tmpUser.getUser(chUser.getLogin());
        return chUser.equals(tmpUser);
    }
}
