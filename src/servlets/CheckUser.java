package servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class CheckUser extends Dispatcher {
    public String getServletInfo(){
        return "Registration servlet";
    }

    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext ctx = getServletContext();
        Base base = new Base();
        User user = new User(base.getUser(request.getParameter("user")));
        ctx.setAttribute("user", user);
            if (base.checkUser(request.getParameter("user"), request.getParameter("password"))){
                this.forward("/successLogin.jsp", request, response);
            } else {
                this.forward("/registration.html", request, response);
            }
    }
}