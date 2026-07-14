package controller;

import java.io.IOException;

import java.util.List;

import dao.BloodRequestDAO;
import dao.PatientDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.BloodRequest;
import model.User;

public class BloodRequestServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private BloodRequestDAO requestDAO;

    @Override
    public void init() {
        requestDAO = new BloodRequestDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        HttpSession session = request.getSession(false);

        User user = null;

        if (session != null) {
            user = (User) session.getAttribute("user");
        }

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String role = user.getRole();

        if (("approve".equalsIgnoreCase(action)
                || "reject".equalsIgnoreCase(action)
                || "delete".equalsIgnoreCase(action))
                && !"ADMIN".equalsIgnoreCase(role)) {

            response.sendRedirect("BloodRequestServlet");
            return;
        }

        switch (action) {

        case "approve":
            approveRequest(request, response);
            break;

        case "reject":
            rejectRequest(request, response);
            break;

        case "delete":
            deleteRequest(request, response);
            break;

        default:
            listRequests(request, response);
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

        PatientDAO patientDAO = new PatientDAO();

        int patientId = patientDAO.getPatientIdByUserId(user.getId());

        if (patientId == 0) {
            request.setAttribute("error",
                    "Patient record not found. Please create a patient profile first.");

            listRequests(request, response);
            return;
        }

        BloodRequest bloodRequest = new BloodRequest();

        bloodRequest.setPatientId(patientId);
        bloodRequest.setBloodGroup(request.getParameter("bloodGroup"));
        bloodRequest.setUnits(
                Integer.parseInt(request.getParameter("units")));

        boolean success = requestDAO.addRequest(bloodRequest);

        if (success) {
            response.sendRedirect("BloodRequestServlet");
        } else {
            request.setAttribute("error",
                    "Unable to submit blood request.");

            listRequests(request, response);
        }
    }

    private void listRequests(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        List<BloodRequest> requestList = requestDAO.getAllRequests();

        request.setAttribute("requestList", requestList);

        request.getRequestDispatcher("requestBlood.jsp")
               .forward(request, response);
    }

    private void approveRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        requestDAO.approveRequest(id);

        response.sendRedirect("BloodRequestServlet");
    }

    private void rejectRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        requestDAO.rejectRequest(id);

        response.sendRedirect("BloodRequestServlet");
    }

    private void deleteRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        requestDAO.deleteRequest(id);

        response.sendRedirect("BloodRequestServlet");
    }
}