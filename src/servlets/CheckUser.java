package servlets;

import base.com.BaseUser;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class CheckUser extends Dispatcher {
    public String getServletInfo(){
        return "Registration servlet";
    }

    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        ServletContext ctx = getServletContext();
//        BaseUser base = new BaseUser();
//        User user = new User(base.getUser(request.getParameter("user")));
//        ctx.setAttribute("user", user);

        String usert = request.getParameter("login");
        String password = request.getParameter("password");
        User user = new User();

            if (user.checkUser(request.getParameter("login"), request.getParameter("password"))){
                HttpSession sessions = request.getSession();
                sessions.setAttribute("login", request.getParameter("login"));
                sessions.setAttribute("password", request.getParameter("password"));
                this.forward("/successLogin.jsp", request, response);
            } else {
                this.forward("/registration.html", request, response);
            }
    }
}