package servlet.User;

import dao.UserDAO;
import models.User;
import services.UserDaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/reg")
public class Create extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");

        UserDAO userDAO = UserDaoFactory.getUserDAO();


        String name = request.getParameter("name");
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        userDAO.save(new User(name,login,password));

        response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/admin"));
    }

}
