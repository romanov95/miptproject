package servlets;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class Login extends Dispatcher {
    public String getServletInfo(){
        return "Registration servlet";
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        if (request.getParameter("cancelButton")!=null){
            HttpSession sessions = request.getSession();
            sessions.invalidate();
            this.forward("/login.jsp", request, response);
        }
        if (request.getParameter("loginButton")!=null){
            User user = new User();
            user.setUser(request.getParameter("login"), request.getParameter("password"));
            if (user.checkUser(user)){
                HttpSession sessions = request.getSession();
                sessions.setAttribute("userSession", user);
                this.forward("/successLogin.jsp", request, response);
            } else {
                this.forward("/errorLogin.jsp", request, response);
            }
        } else if (request.getParameter("registration")!=null) {
            this.forward("/registration.html", request, response);
        }
    }
}