package controller;

import java.io.IOException;

import java.util.List;

import dao.BloodStockDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.BloodStock;
import model.User;

public class BloodStockServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private BloodStockDAO stockDAO;

    @Override
    public void init() {
        stockDAO = new BloodStockDAO();
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

        String role = "";

        if (user != null) {
            role = user.getRole();
        }

        // Only ADMIN can Edit/Delete
        if (("edit".equalsIgnoreCase(action)
                || "delete".equalsIgnoreCase(action))
                && !"ADMIN".equalsIgnoreCase(role)) {

            response.sendRedirect("BloodStockServlet");
            return;
        }

        switch (action) {

        case "edit":
            editStock(request, response);
            break;

        case "delete":
            deleteStock(request, response);
            break;

        case "search":
            searchStock(request, response);
            break;

        default:
            listStock(request, response);
            break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        User user = null;

        if (session != null) {
            user = (User) session.getAttribute("user");
        }

        // Only ADMIN can Add/Update Blood Stock
        if (user == null ||
                !"ADMIN".equalsIgnoreCase(user.getRole())) {

            response.sendRedirect("BloodStockServlet");
            return;
        }

        String stockId = request.getParameter("stockId");

        BloodStock stock = new BloodStock();

        stock.setBloodGroup(request.getParameter("bloodGroup"));
        stock.setUnits(Integer.parseInt(request.getParameter("units")));

        if (stockId == null || stockId.isEmpty()) {

            stockDAO.addBloodStock(stock);

        } else {

            stock.setStockId(Integer.parseInt(stockId));
            stockDAO.updateBloodStock(stock);
        }

        response.sendRedirect("BloodStockServlet");
    }

    // Display All Blood Stock
    private void listStock(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        List<BloodStock> stockList = stockDAO.getAllBloodStock();

        request.setAttribute("stockList", stockList);

        request.getRequestDispatcher("bloodStock.jsp")
               .forward(request, response);
    }

    // Search Blood Stock
    private void searchStock(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String bloodGroup = request.getParameter("bloodGroup");

        List<BloodStock> stockList;

        if (bloodGroup == null || bloodGroup.trim().isEmpty()) {

            stockList = stockDAO.getAllBloodStock();

        } else {

            stockList = stockDAO.searchBloodStock(bloodGroup);
        }

        request.setAttribute("stockList", stockList);

        request.getRequestDispatcher("bloodStock.jsp")
               .forward(request, response);
    }

    // Edit Blood Stock
    private void editStock(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int stockId = Integer.parseInt(request.getParameter("id"));

        BloodStock stock = stockDAO.getBloodStockById(stockId);

        List<BloodStock> stockList = stockDAO.getAllBloodStock();

        request.setAttribute("stock", stock);
        request.setAttribute("stockList", stockList);

        request.getRequestDispatcher("bloodStock.jsp")
               .forward(request, response);
    }

    // Delete Blood Stock
    private void deleteStock(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        int stockId = Integer.parseInt(request.getParameter("id"));

        stockDAO.deleteBloodStock(stockId);

        response.sendRedirect("BloodStockServlet");
    }
}