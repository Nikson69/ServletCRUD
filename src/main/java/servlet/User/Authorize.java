package servlet.User;

import dao.UserDAO;
import models.User;
import services.UserDaoFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class Authorize extends HttpServlet {
    UserDAO userDAO = UserDaoFactory.getUserDAO();
    User user;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");

        if(userDAO.findByLogin(login)){
            user = userDAO.authorize(login);
            if(user.getLogin().equals(login)&&user.getPassword().equals(request.getParameter("password"))){
                switch (user.getRole()){
                    case "user" :
                        HttpSession sessionUser = request.getSession();
                        sessionUser.setAttribute("userRole", user.getRole());
                        //setting session to expiry in 30 mins
                        sessionUser.setMaxInactiveInterval(15*60);
                        Cookie userName = new Cookie("userName", user.getName());
                        userName.setMaxAge(15*60);
                        response.addCookie(userName);
                        response.sendRedirect("/user");
                        break;
                    case "admin" :
                        HttpSession sessionAdmin = request.getSession();
                        sessionAdmin.setAttribute("userRole", user.getRole());
                        //setting session to expiry in 30 mins
                        sessionAdmin.setMaxInactiveInterval(15*60);
                        Cookie adminName = new Cookie("userName", user.getName());
                        adminName.setMaxAge(15*60);
                        response.addCookie(adminName);
                        response.sendRedirect("/admin");
                }
            } else {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
                PrintWriter out= response.getWriter();
                out.println("<font color=red>Either user name or password is wrong.</font>");
                rd.include(request, response);
            }
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
            PrintWriter out= response.getWriter();
            out.println("<font color=red>Either user name or password is wrong.</font>");
            rd.include(request, response);
        }




//        if(userID.equals(user) && password.equals(pwd)){
//            HttpSession session = request.getSession();
//            session.setAttribute("user", "Pankaj");
//            //setting session to expiry in 30 mins
//            session.setMaxInactiveInterval(30*60);
//            Cookie userName = new Cookie("user", user);
//            userName.setMaxAge(30*60);
//            response.addCookie(userName);
//            response.sendRedirect("LoginSuccess.jsp");
//        }else{
//            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
//            PrintWriter out= response.getWriter();
//            out.println("<font color=red>Either user name or password is wrong.</font>");
//            rd.include(request, response);
//        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setAttribute("user", user);
//        request.getRequestDispatcher("/user/index.jsp").forward(request, response);
    }
}
