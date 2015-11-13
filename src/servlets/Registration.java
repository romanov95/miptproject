package servlets;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class Registration extends Dispatcher {
    public String getServletInfo(){
        return "Registration servlet";
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        ServletContext ctx = getServletContext();
        if (request.getParameter("loginButton")!=null){
            this.forward("/CheckUser", request, response);
        } else if (request.getParameter("registration")!=null) {
            this.forward("/registration.html", request, response);
        }
    }
}