package servlets;

import base.com.BaseUser;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Random;

public class User  {

    private String login =  "";
    private String password = "";
    private Long salt = (long) 0;

    public User(){};

    public User(String login, String password){
        this.login = login;
        this.salt = this.generateSalt();
        this.password = generatePas(password, this.salt);
    }

    public boolean setUser(String login, String password, Long salt){
        this.login = login;
        this.password = password;
        this.salt = salt;
        return true;
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
        Random random = new Random();
        return random.nextLong();
    }

//    public User(User login){
//        if (login != null) {
//            setLogin(login.getLogin());
//            setPassword(login.getPassword());
//        }
//    }




    public String getLogin(){
        return login;
    }
//    public void setLogin(String login){
//        this.login = login;
//    }
    public String getPassword(){
        return password;
    }
//    public void setPassword(String password){
//        this.password = password;
//    }
//    public void setSalt(){
//        Random random = new Random();
//        this.salt = random.nextLong();
//    }
    public long getSalt(){
        return this.salt;
    }


    public boolean addUser (User tmpUser) {
        PreparedStatement preparedStatement = null;
        String insertUser = "INSERT  INTO users(login, password, salt) VALUES (?,?,?)";
        try {
            preparedStatement = BaseUser.getConnection().prepareStatement(insertUser);
            preparedStatement.setString(1, tmpUser.getLogin());
            preparedStatement.setString(2, tmpUser.getPassword());
            preparedStatement.setLong(3, tmpUser.getSalt());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  true;
    }

    public boolean getUser(String login) {
        PreparedStatement preparedStatement = null;
        String search = "SELECT login, password, salt FROM users WHERE login = ?";
        try {
            preparedStatement = BaseUser.getConnection().prepareStatement(search);
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

    public boolean checkUser (String login, String password) {
        User tmpUser = new User();
        tmpUser.getUser(login);
        User chUser = new User();
        chUser.setUser(login,generatePas(password,tmpUser.getSalt()),tmpUser.getSalt());
        return chUser.equals(tmpUser);
    }



}