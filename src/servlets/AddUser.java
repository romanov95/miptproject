package servlets;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class AddUser extends Dispatcher {
    public String getServletInfo(){
        return "Add user servlet";
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext ctx = getServletContext();
        if (request.getParameter("save")!=null){
//            String user = request.getParameter("user");
//            String password = request.getParameter("password");
//            User newUser = new User();
//            newUser.setLogin(user);
//            newUser.setPassword(password);

            User newUser = new User(request.getParameter("user"),request.getParameter("password"));
            ctx.setAttribute("user", newUser);

            boolean res = newUser.addUser(newUser);
                if (res) {
                this.forward("/successRegistration.jsp", request, response);
            } else {
                this.forward("/errorRegistration.html", request, response);
            }
        } else if (request.getParameter("cancel")!=null){
            this.forward("/login.html", request, response);
        }
    }
}