package servlet;

import models.User;

import java.io.IOException;
import java.net.URL;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/user/*","/admin/*"})
public class AuthenticationFilter implements javax.servlet.Filter {
    private ServletContext context;
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;



        HttpSession session = req.getSession(false);
        String role = (String) session.getAttribute("userRole");

        String loginURI = req.getContextPath() + "/index.jsp";

        boolean loggedIn = session != null && role != null;
        boolean loginRequest = req.getRequestURI().equals(loginURI);
        System.out.println(req.getRequestURI());



        if (loggedIn || loginRequest) {
            switch (role){
                case "user" :
                    if (req.getRequestURI().equals("/user/")){
                        chain.doFilter(req, res);
                    } else {
                        //chain.doFilter(req, res);
                        res.sendRedirect("/user/");
                    }
                break;
                case "admin" : chain.doFilter(req, res);
                break;
            }
        } else {
            res.sendRedirect(loginURI);
        }
    }

    public void init(FilterConfig config) throws ServletException {
//        this.context = config.getServletContext();
//        this.context.log("AuthenticationFilter initialized");

    }

}
