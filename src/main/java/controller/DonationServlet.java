package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import dao.DonationDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Donation;
import model.User;

public class DonationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private DonationDAO donationDAO;

    @Override
    public void init() {
        donationDAO = new DonationDAO();
    }

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

        String role = user.getRole();

        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        // Only ADMIN can Edit/Delete
        if (("edit".equalsIgnoreCase(action)
                || "delete".equalsIgnoreCase(action))
                && !"ADMIN".equalsIgnoreCase(role)) {

            response.sendRedirect("DonationServlet");
            return;
        }

        switch (action) {

        case "edit":
            editDonation(request, response);
            break;

        case "delete":
            deleteDonation(request, response);
            break;

        default:
            listDonations(request, response);
            break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");

        // Only ADMIN can Add/Update Donation
        if (!"ADMIN".equalsIgnoreCase(user.getRole())) {
            response.sendRedirect("DonationServlet");
            return;
        }

        String donationId = request.getParameter("donationId");

        Donation donation = new Donation();

        donation.setDonorId(
                Integer.parseInt(request.getParameter("donorId")));

        donation.setBloodGroup(
                request.getParameter("bloodGroup"));

        donation.setUnits(
                Integer.parseInt(request.getParameter("units")));

        donation.setDonationDate(
                Date.valueOf(request.getParameter("donationDate")));

        if (donationId == null || donationId.isEmpty()) {

            donationDAO.addDonation(donation);

        } else {

            donation.setDonationId(
                    Integer.parseInt(donationId));

            donationDAO.updateDonation(donation);
        }

        response.sendRedirect("DonationServlet");
    }

    // Display Donations
    private void listDonations(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        List<Donation> donationList = donationDAO.getAllDonations();

        request.setAttribute("donationList", donationList);

        request.getRequestDispatcher("donation.jsp")
                .forward(request, response);
    }

    // Edit Donation
    private void editDonation(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int donationId =
                Integer.parseInt(request.getParameter("id"));

        Donation donation =
                donationDAO.getDonationById(donationId);

        List<Donation> donationList =
                donationDAO.getAllDonations();

        request.setAttribute("donation", donation);
        request.setAttribute("donationList", donationList);

        request.getRequestDispatcher("donation.jsp")
                .forward(request, response);
    }

    // Delete Donation
    private void deleteDonation(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        int donationId =
                Integer.parseInt(request.getParameter("id"));

        donationDAO.deleteDonation(donationId);

        response.sendRedirect("DonationServlet");
    }
}