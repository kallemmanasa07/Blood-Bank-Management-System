package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import dao.DonorDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Donor;
import model.User;

public class DonorServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private DonorDAO donorDAO;

    @Override
    public void init() {
        donorDAO = new DonorDAO();
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

            response.sendRedirect("DonorServlet");
            return;
        }

        switch (action) {

        case "edit":
            editDonor(request, response);
            break;

        case "delete":
            deleteDonor(request, response);
            break;

        case "search":
            searchDonor(request, response);
            break;

        default:
            listDonors(request, response);
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

        // Only ADMIN can Add/Update Donor
        if (!"ADMIN".equalsIgnoreCase(user.getRole())) {
            response.sendRedirect("DonorServlet");
            return;
        }

        String donorId = request.getParameter("donorId");

        Donor donor = new Donor();

        donor.setName(request.getParameter("name"));
        donor.setGender(request.getParameter("gender"));
        donor.setAge(Integer.parseInt(request.getParameter("age")));
        donor.setBloodGroup(request.getParameter("bloodGroup"));
        donor.setPhone(request.getParameter("phone"));
        donor.setAddress(request.getParameter("address"));
        donor.setLastDonation(
                Date.valueOf(request.getParameter("lastDonation")));

        if (donorId == null || donorId.isEmpty()) {

            donorDAO.addDonor(donor);

        } else {

            donor.setDonorId(Integer.parseInt(donorId));

            donorDAO.updateDonor(donor);
        }

        response.sendRedirect("DonorServlet");
    }

    // Display Donors
    private void listDonors(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        List<Donor> donorList = donorDAO.getAllDonors();

        request.setAttribute("donorList", donorList);

        request.getRequestDispatcher("donor.jsp")
                .forward(request, response);
    }

    // Edit Donor
    private void editDonor(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int donorId = Integer.parseInt(request.getParameter("id"));

        Donor donor = donorDAO.getDonorById(donorId);

        List<Donor> donorList = donorDAO.getAllDonors();

        request.setAttribute("donor", donor);
        request.setAttribute("donorList", donorList);

        request.getRequestDispatcher("donor.jsp")
                .forward(request, response);
    }

    // Delete Donor
    private void deleteDonor(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        int donorId = Integer.parseInt(request.getParameter("id"));

        donorDAO.deleteDonor(donorId);

        response.sendRedirect("DonorServlet");
    }

    // Search Donor
    private void searchDonor(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String bloodGroup = request.getParameter("bloodGroup");

        List<Donor> donorList;

        if (bloodGroup == null || bloodGroup.trim().isEmpty()) {

            donorList = donorDAO.getAllDonors();

        } else {

            donorList = donorDAO.searchByBloodGroup(bloodGroup);
        }

        request.setAttribute("donorList", donorList);

        request.getRequestDispatcher("donor.jsp")
                .forward(request, response);
    }
}