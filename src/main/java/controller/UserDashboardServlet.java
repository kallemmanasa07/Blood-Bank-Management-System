package controller;

import java.io.IOException;


import dao.BloodRequestDAO;
import dao.BloodStockDAO;
import dao.PatientDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

public class UserDashboardServlet extends HttpServlet {

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

        // Only USER can access this dashboard
        if (!"USER".equalsIgnoreCase(user.getRole())) {
            response.sendRedirect(request.getContextPath() + "/DashboardServlet");
            return;
        }

        PatientDAO patientDAO = new PatientDAO();
        BloodRequestDAO requestDAO = new BloodRequestDAO();
        BloodStockDAO stockDAO = new BloodStockDAO();

        // Get patient_id using logged-in user's user_id
        int patientId = patientDAO.getPatientIdByUserId(user.getId());

        if (patientId == 0) {

            request.setAttribute("error",
                    "Your patient profile has not been created yet. Please contact the administrator.");

            request.setAttribute("myRequests", 0);
            request.setAttribute("approved", 0);
            request.setAttribute("pending", 0);
            request.setAttribute("rejected", 0);
            request.setAttribute("stockList", stockDAO.getAllBloodStock());

            request.getRequestDispatcher("/userDashboard.jsp")
                   .forward(request, response);
            return;
        }

        request.setAttribute("myRequests",
                requestDAO.getMyRequestCount(patientId));

        request.setAttribute("approved",
                requestDAO.getApprovedCount(patientId));

        request.setAttribute("pending",
                requestDAO.getPendingCount(patientId));

        request.setAttribute("rejected",
                requestDAO.getRejectedCount(patientId));

        request.setAttribute("stockList",
                stockDAO.getAllBloodStock());

        request.setAttribute("myRequestsList",
                requestDAO.getRecentRequestsByPatient(patientId));

        request.getRequestDispatcher("/userDashboard.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}