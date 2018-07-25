package servlet.User;

import dao.UserDAO;
import services.UserDaoFactory;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/edit")
public class Edit extends HttpServlet {
    UserDAO userDAO = UserDaoFactory.getUserDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String id = request.getParameter("id");
        String role = request.getParameter("role");


        if (name == null || name.equals("")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else if (login == null||login.equals("")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else if (password == null||password.equals("")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
            userDAO.update(new User(Long.valueOf(id),name,login,password,role));
        }

        response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/admin/"));

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");

        int idEdit = Integer.parseInt(request.getParameter("idEdit"));

        if (idEdit == 0) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
            User user = userDAO.findById(idEdit);

            response.setContentType("text/html");

            request.setAttribute("user", user);
            request.getRequestDispatcher("/admin/list/edit/edit.jsp").forward(request, response);


        }
    }
}
