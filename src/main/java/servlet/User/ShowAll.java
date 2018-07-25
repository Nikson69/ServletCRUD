package servlet.User;

import dao.UserDAO;
import models.User;
import services.UserDaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/admin/")
public class ShowAll extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        UserDAO userDAO = UserDaoFactory.getUserDAO();
        HttpSession session = request.getSession();
        request.setAttribute("user",session.getAttribute("user"));

        request.setAttribute("clients", userDAO.getUsers());
        request.getServletContext().getRequestDispatcher("/admin/index.jsp").forward(request, response);


    }
}
