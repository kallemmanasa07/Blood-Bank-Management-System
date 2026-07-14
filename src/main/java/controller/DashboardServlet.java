package controller;

import java.io.IOException;

import dao.BloodRequestDAO;
import dao.BloodStockDAO;
import dao.DonationDAO;
import dao.DonorDAO;
import dao.PatientDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

public class DashboardServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Check Login
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");

        // Only ADMIN and STAFF can access this dashboard
        if ("USER".equalsIgnoreCase(user.getRole())) {
            response.sendRedirect("UserDashboardServlet");
            return;
        }

        DonorDAO donorDAO = new DonorDAO();
        PatientDAO patientDAO = new PatientDAO();
        BloodStockDAO stockDAO = new BloodStockDAO();
        BloodRequestDAO requestDAO = new BloodRequestDAO();
        DonationDAO donationDAO = new DonationDAO();

        // Dashboard Statistics
        request.setAttribute("totalDonors", donorDAO.getDonorCount());
        request.setAttribute("totalPatients", patientDAO.getPatientCount());
        request.setAttribute("totalBloodUnits", stockDAO.getTotalBloodUnits());
        request.setAttribute("totalRequests", requestDAO.getRequestCount());
        request.setAttribute("totalDonations", donationDAO.getDonationCount());

        // Low Stock Alert
        request.setAttribute("lowStock", stockDAO.getLowStock());

        // Blood Group Chart
        request.setAttribute("stockList", stockDAO.getAllBloodStock());

        // Recent Activities
        request.setAttribute("recentDonations",
                donationDAO.getRecentDonations());

        request.setAttribute("recentRequests",
                requestDAO.getRecentRequests());

        request.getRequestDispatcher("dashboard.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}