package controller;

import java.io.IOException;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

public class DeleteAccountServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");

        UserDAO dao = new UserDAO();

        // Use getUserId() if your User model has userId
        boolean deleted = dao.deleteUser(user.getId());

        if (deleted) {
            session.invalidate();
            response.sendRedirect("login.jsp?msg=AccountDeleted");
        } else {
            response.sendRedirect("dashboard.jsp?msg=DeleteFailed");
        }
    }
}