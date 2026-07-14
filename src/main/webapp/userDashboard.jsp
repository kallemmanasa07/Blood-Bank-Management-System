<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%@ page import="java.util.List"%>
<%@ page import="model.BloodStock"%>
<%@ page import="model.BloodRequest"%>

<%
User user = (User) session.getAttribute("user");

if (user == null) {
    response.sendRedirect("login.jsp");
    return;
}

if (!"USER".equalsIgnoreCase(user.getRole())) {
    response.sendRedirect("dashboard.jsp");
    return;
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Dashboard</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet"
href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<link rel="stylesheet" href="css/style.css">
</head>

<body>

<jsp:include page="navbar.jsp"/>
<div class="container mt-5">

    <!-- Welcome Banner -->

    <div class="welcome-banner shadow mb-4">

        <h2>🩸 Welcome, <%= user.getName() %></h2>

        <h5>User Dashboard</h5>

        <p id="dateTime"></p>

    </div>

    <!-- Statistics -->

    <div class="row mb-4">

        <div class="col-lg-3 col-md-6 mb-3">
            <div class="card bg-primary text-white">
                <div class="card-body text-center">
                    <i class="bi bi-file-earmark-medical fs-1"></i>
                    <h2>${myRequests}</h2>
                    <h6>My Requests</h6>
                </div>
            </div>
        </div>

        <div class="col-lg-3 col-md-6 mb-3">
            <div class="card bg-success text-white">
                <div class="card-body text-center">
                    <i class="bi bi-check-circle-fill fs-1"></i>
                    <h2>${approved}</h2>
                    <h6>Approved</h6>
                </div>
            </div>
        </div>

        <div class="col-lg-3 col-md-6 mb-3">
            <div class="card bg-warning">
                <div class="card-body text-center">
                    <i class="bi bi-hourglass-split fs-1"></i>
                    <h2>${pending}</h2>
                    <h6>Pending</h6>
                </div>
            </div>
        </div>

        <div class="col-lg-3 col-md-6 mb-3">
            <div class="card bg-danger text-white">
                <div class="card-body text-center">
                    <i class="bi bi-x-circle-fill fs-1"></i>
                    <h2>${rejected}</h2>
                    <h6>Rejected</h6>
                </div>
            </div>
        </div>

    </div>

    <!-- Quick Actions -->

    <div class="row mb-4">

        <div class="col-md-4 mb-3">
            <a href="BloodRequestServlet" class="btn btn-danger w-100 p-3">
                <i class="bi bi-droplet-fill"></i> Request Blood
            </a>
        </div>

        <div class="col-md-4 mb-3">
            <a href="BloodRequestServlet?action=myrequests"
               class="btn btn-primary w-100 p-3">
                <i class="bi bi-list-check"></i> My Requests
            </a>
        </div>

        <div class="col-md-4 mb-3">
            <a href="LogoutServlet"
               class="btn btn-secondary w-100 p-3">
                <i class="bi bi-box-arrow-right"></i> Logout
            </a>
        </div>

    </div>

</div>
<script>

function updateDateTime(){

    const now = new Date();

    document.getElementById("dateTime").innerHTML =
        now.toLocaleDateString() + " | " +
        now.toLocaleTimeString();

}

updateDateTime();

setInterval(updateDateTime,1000);

</script>
<jsp:include page="footer.jsp"/>

</body>
</html>