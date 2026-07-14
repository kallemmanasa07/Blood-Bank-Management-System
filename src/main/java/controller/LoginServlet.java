package controller;

import java.io.IOException;


import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDAO dao = new UserDAO();
        User user = dao.login(email, password);

        if (user != null) {

            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("username", user.getName());
            session.setAttribute("role", user.getRole());

            if ("ADMIN".equalsIgnoreCase(user.getRole())
                    || "STAFF".equalsIgnoreCase(user.getRole())) {

                response.sendRedirect(request.getContextPath() + "/DashboardServlet");

            } else {

                response.sendRedirect(request.getContextPath() + "/UserDashboardServlet");

            }

        } else {

            request.setAttribute("error", "Invalid Email or Password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect("login.jsp");
    }
}