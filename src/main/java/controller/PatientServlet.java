package controller;

import java.io.IOException;
import java.util.List;

import dao.PatientDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Patient;
import model.User;

public class PatientServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private PatientDAO patientDAO;

    @Override
    public void init() {
        patientDAO = new PatientDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {

            case "edit":
                editPatient(request, response);
                break;

            case "delete":
                deletePatient(request, response);
                break;

            case "search":
                searchPatient(request, response);
                break;

            default:
                listPatients(request, response);
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

        String patientId = request.getParameter("patientId");

        Patient patient = new Patient();

        // Link patient with logged-in user
        patient.setUserId(user.getId());
        patient.setName(request.getParameter("name"));
        patient.setBloodGroup(request.getParameter("bloodGroup"));
        patient.setUnits(Integer.parseInt(request.getParameter("units")));
        patient.setDoctor(request.getParameter("doctor"));
        patient.setHospital(request.getParameter("hospital"));
        patient.setPhone(request.getParameter("phone"));

        if (patientId == null || patientId.isEmpty()) {
            patientDAO.addPatient(patient);
        } else {
            patient.setPatientId(Integer.parseInt(patientId));
            patientDAO.updatePatient(patient);
        }

        response.sendRedirect("PatientServlet");
    }

    private void listPatients(HttpServletRequest request,
                              HttpServletResponse response)
            throws ServletException, IOException {

        List<Patient> patientList = patientDAO.getAllPatients();

        request.setAttribute("patientList", patientList);

        request.getRequestDispatcher("patient.jsp")
               .forward(request, response);
    }

    private void editPatient(HttpServletRequest request,
                             HttpServletResponse response)
            throws ServletException, IOException {

        int patientId = Integer.parseInt(request.getParameter("id"));

        Patient patient = patientDAO.getPatientById(patientId);

        List<Patient> patientList = patientDAO.getAllPatients();

        request.setAttribute("patient", patient);
        request.setAttribute("patientList", patientList);

        request.getRequestDispatcher("patient.jsp")
               .forward(request, response);
    }

    private void deletePatient(HttpServletRequest request,
                               HttpServletResponse response)
            throws IOException {

        int patientId = Integer.parseInt(request.getParameter("id"));

        patientDAO.deletePatient(patientId);

        response.sendRedirect("PatientServlet");
    }

    private void searchPatient(HttpServletRequest request,
                               HttpServletResponse response)
            throws ServletException, IOException {

        String bloodGroup = request.getParameter("bloodGroup");

        List<Patient> patientList = patientDAO.searchPatients(bloodGroup);

        request.setAttribute("patientList", patientList);

        request.getRequestDispatcher("patient.jsp")
               .forward(request, response);
    }
}