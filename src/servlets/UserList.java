package servlets;
import java.util.HashMap;
import java.util.Map;


public class UserList  {

    public static boolean addUser(User user){
        boolean result = false;
        if ((user.getPassword()!=null)&&(!"".equals(user.getPassword())))  {
            Base base = new Base();
            base.addUser(user.getUser(),user.getPassword());
            result = true;
        }
        return result;
    }

}