<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="model.Donation"%>

<%
if (session.getAttribute("user") == null) {
    response.sendRedirect("login.jsp");
    return;
}
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Donation Management</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet">

<link rel="stylesheet" href="css/style.css">

</head>

<body>

<jsp:include page="navbar.jsp" />

<div class="container mt-4">

    <!-- Donation Form -->

    <div class="card shadow">

        <div class="card-header bg-danger text-white">

            <h3>Blood Donation</h3>

        </div>

        <div class="card-body">

            <form action="DonationServlet" method="post">

                <input type="hidden"
                       name="donationId"
                       value="${donation.donationId}">

                <div class="row">

                    <div class="col-md-6 mb-3">

                        <label class="form-label">Donor ID</label>

                        <input type="number"
       class="form-control"
       name="donorId"
       min="1"
       value="${donation.donorId}"
       required>

                    </div>

                    <div class="col-md-6 mb-3">

                        <label class="form-label">Blood Group</label>

                        <select name="bloodGroup"
                                class="form-select"
                                required>

                            <option value="" disabled
                                ${empty donation.bloodGroup ? 'selected' : ''}>
                                Select Blood Group
                            </option>

                            <option value="A+" ${donation.bloodGroup=='A+'?'selected':''}>A+</option>
                            <option value="A-" ${donation.bloodGroup=='A-'?'selected':''}>A-</option>
                            <option value="B+" ${donation.bloodGroup=='B+'?'selected':''}>B+</option>
                            <option value="B-" ${donation.bloodGroup=='B-'?'selected':''}>B-</option>
                            <option value="AB+" ${donation.bloodGroup=='AB+'?'selected':''}>AB+</option>
                            <option value="AB-" ${donation.bloodGroup=='AB-'?'selected':''}>AB-</option>
                            <option value="O+" ${donation.bloodGroup=='O+'?'selected':''}>O+</option>
                            <option value="O-" ${donation.bloodGroup=='O-'?'selected':''}>O-</option>

                        </select>

                    </div>

                    <div class="col-md-6 mb-3">

                        <label class="form-label">Units</label>

                        <input type="number"
                               class="form-control"
                               name="units"
                               min="1"
                               value="${donation.units}"
                               required>

                    </div>

                    <div class="col-md-6 mb-3">

                        <label class="form-label">Donation Date</label>

                        <input type="date"
                               class="form-control"
                               name="donationDate"
                               value="${donation.donationDate}"
                               required>

                    </div>

                </div>

                <button type="submit" class="btn btn-success">
                    ${empty donation ? 'Save Donation' : 'Update Donation'}
                </button>

                <a href="DonationServlet"
                   class="btn btn-primary">
                    View Donations
                </a>

            </form>

        </div>

    </div>

    <br>

    <!-- Donation List starts below -->
        <!-- Donation List -->

    <div class="card shadow">

        <div class="card-header bg-dark text-white">

            <h4>Donation List</h4>

        </div>

        <div class="card-body">

            <table class="table table-bordered table-hover">

                <thead class="table-danger">

                    <tr>

                        <th>ID</th>

                        <th>Donor ID</th>

                        <th>Blood Group</th>

                        <th>Units</th>

                        <th>Date</th>

<%
String role = (String) session.getAttribute("role");

if ("ADMIN".equalsIgnoreCase(role)) {
%>

                        <th>Action</th>

<%
}
%>

                    </tr>

                </thead>

                <tbody>

<%
List<Donation> donationList =
        (List<Donation>) request.getAttribute("donationList");

if (donationList != null && !donationList.isEmpty()) {

    for (Donation d : donationList) {
%>

                    <tr>

                        <td><%= d.getDonationId() %></td>

                        <td><%= d.getDonorId() %></td>

                        <td><%= d.getBloodGroup() %></td>

                        <td><%= d.getUnits() %></td>

                        <td><%= d.getDonationDate() %></td>
<%
if ("ADMIN".equalsIgnoreCase(role)) {
%>

                        <td>

                            <a href="DonationServlet?action=edit&id=<%= d.getDonationId() %>"
                               class="btn btn-warning btn-sm me-1">
                                ✏️ Edit
                            </a>

                      <a href="DonationServlet?action=delete&id=<%= d.getDonationId() %>"
   class="btn btn-danger btn-sm"
   onclick="return confirm('Delete this donation?')">
    🗑 Delete
</a>

                        </td>

<%
}
%>

                    </tr>

<%
    }
} else {
%>

                    <tr>

                       <td colspan="<%= "ADMIN".equalsIgnoreCase(role) ? 6 : 5 %>"
                            class="text-center text-muted">

                            No donations found.

                        </td>

                    </tr>

<%
}
%>

                </tbody>

            </table>

        </div>

    </div>

</div>

<%@ include file="footer.jsp" %>

</body>

</html>