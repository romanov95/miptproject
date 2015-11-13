package servlets;
public class User  {

    private String user;
    private String password;

    public User(){
    }

    public User(User user){
        if (user != null) {
            setUser(user.getUser());
            setPassword(user.getPassword());
        }
    }

    public String getUser(){
        return user;
    }
    public void setUser(String user){
        this.user = user;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
}