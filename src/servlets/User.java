package servlets;

import base.com.Base;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServlet;
import java.security.SecureRandom;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class User extends HttpServlet{

    private String login =  "";
    private String password = "";
    private Long salt = (long) 0;

    public User(){};

    public User(String login, String password){
        this.login = login;
        this.salt = this.generateSalt();
        this.password = generatePas(password, this.salt);
    }


    public void setUser(String login, String password){
        this.login = login;
        this.password = password;
    }

    public void setUser(User user){
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.salt = user.getSalt();
    }

    public void setUser(String login, String password, Long salt){
        this.login = login;
        this.password = password;
        this.salt = salt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(salt, user.salt);
    }

    private String generatePas(String password, Long salt){
        return DigestUtils.sha256Hex(password + salt);
    }
    private Long generateSalt(){
        SecureRandom secureRandom = new SecureRandom();
        return secureRandom.nextLong();
    }

    public String getLogin(){
        return login;
    }

    public String getPassword(){
        return password;
    }

    public long getSalt(){
        return this.salt;
    }


    public boolean addUser (User tmpUser) {
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

    public boolean getUser(String login) {
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