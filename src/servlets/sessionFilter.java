package servlets;


import base.com.Base;
import base.com.BaseUser;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Sergey on 11/17/2015.
 */
public class sessionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        HttpSession session = request.getSession(false);
        if ((session != null) && (session.getAttribute("userSession") != null)) {
            User user = new User((User) session.getAttribute("userSession"));
            if (BaseUser.checkUserSession(user)) {
                chain.doFilter(req, res);
            } else {
                response.sendRedirect("errorAuthorization.jsp");
            }
        } else {
            response.sendRedirect("errorAuthorization.jsp");
        }
    }

    @Override
    public void destroy() {

    }
}
