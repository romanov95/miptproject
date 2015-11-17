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

    public User(){}

    public User(String login, String password){
        this.login = login;
        this.salt = this.generateSalt();
        this.password = generatePas(password, this.salt);
    }
    public User(User user){
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.salt = user.getSalt();
    }

    public User(String login, String password, Long salt){
        this.login = login;
        this.password = password;
        this.salt = salt;
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

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSalt(Long salt) {
        this.salt = salt;
    }

    public void setUser(String login, String password){
        this.login = login;
        this.password = password;
    }

    public void setUser(String login, String password, Long salt){
        this.login = login;
        this.password = generatePas(password, salt);
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



}