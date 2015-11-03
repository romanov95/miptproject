package servlets;
import java.util.HashMap;
import java.util.Map;


public class UserList  {

    private static Map users = new HashMap();

    public static User findUser(String user){
        return (User)users.get(user);
    }

    public static boolean addUser(User user){
        boolean result = false;
        if ((!users.containsKey(user.getUser()))&&(user.getPassword()!=null)&&(!"".equals(user.getPassword()))){
            users.put(user.getUser(),user);
            Base base = new Base();
            base.addUser(user.getUser(),user.getPassword());
            result = true;
        }

        return result;
    }

}