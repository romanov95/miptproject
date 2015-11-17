package servlets;
import base.com.BaseUser;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class Registration extends Dispatcher {
    public String getServletInfo(){
        return "Add user servlet";
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("save")!=null){
            User newUser = new User(request.getParameter("login"),request.getParameter("password"));
                if (BaseUser.addUser(newUser)) {
                    HttpSession sessions = request.getSession();
                    sessions.setAttribute("userSession", newUser);
                    this.forward("/successRegistration.jsp", request, response);
                } else {
                this.forward("/errorRegistration.html", request, response);
            }
        } else if (request.getParameter("cancel")!=null){
            this.forward("/login.jsp", request, response);
        }
    }
}