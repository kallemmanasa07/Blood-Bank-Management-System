<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.BloodStock"%>
<%@ page import="model.Donation"%>
<%@ page import="model.BloodRequest"%>
<%@ page import="model.User"%>

<%
if(session.getAttribute("user")==null){
    response.sendRedirect("login.jsp");
    return;
}


User user = (User) session.getAttribute("user");
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Dashboard</title>

<link rel="stylesheet" href="css/style.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet"
href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

</head>

<body>
<jsp:include page="navbar.jsp" />

<div class="container mt-5">

<div class="welcome-banner shadow mb-5 text-center">

    <h2>🩸 Blood Bank Management System</h2>

    <h5>
        Welcome,
        <strong><%= user.getName() %></strong> 👋
    </h5>

    <p id="dateTime"></p>

</div>
<!-- Dashboard Statistics -->
<div class="row mb-4">

    <div class="col-lg-3 col-md-6 mb-3">
        <div class="card bg-primary text-white h-100">
            <div class="card-body text-center">
                <i class="bi bi-people-fill fs-1"></i>
                <h2>${totalDonors}</h2>
                <h6>Total Donors</h6>
            </div>
        </div>
    </div>

    <div class="col-lg-3 col-md-6 mb-3">
        <div class="card bg-success text-white h-100">
            <div class="card-body text-center">
                <i class="bi bi-hospital-fill fs-1"></i>
                <h2>${totalPatients}</h2>
                <h6>Total Patients</h6>
            </div>
        </div>
    </div>

    <div class="col-lg-3 col-md-6 mb-3">
        <div class="card bg-danger text-white h-100">
            <div class="card-body text-center">
                <i class="bi bi-droplet-fill fs-1"></i>
                <h2>${totalBloodUnits}</h2>
                <h6>Blood Units</h6>
            </div>
        </div>
    </div>

    <div class="col-lg-3 col-md-6 mb-3">
        <div class="card bg-warning text-dark h-100">
            <div class="card-body text-center">
                <i class="bi bi-clipboard2-pulse-fill fs-1"></i>
                <h2>${totalRequests}</h2>
                <h6>Blood Requests</h6>
            </div>
        </div>
    </div>

    <div class="col-lg-3 col-md-6 mb-3">
        <div class="card bg-info text-white h-100">
            <div class="card-body text-center">
                <i class="bi bi-heart-pulse-fill fs-1"></i>
                <h2>${totalDonations}</h2>
                <h6>Total Donations</h6>
            </div>
        </div>
    </div>

</div>

<%
List<BloodStock> lowStock =
(List<BloodStock>) request.getAttribute("lowStock");

if (lowStock != null && !lowStock.isEmpty()) {
%>
<div class="alert alert-danger shadow mb-5 low-stock-card">

    <h4>
        <i class="bi bi-exclamation-triangle-fill"></i>
        Low Blood Stock Alert
    </h4>

    <table class="table table-bordered table-hover bg-white mt-3">

        <thead class="table-danger">
            <tr>
                <th>Blood Group</th>
                <th>Units Available</th>
            </tr>
        </thead>

        <tbody>

        <%
        for(BloodStock stock : lowStock){
        %>

        <tr>
            <td><%= stock.getBloodGroup() %></td>
            <td>
                <span class="badge bg-danger">
                    <%= stock.getUnits() %> Units
                </span>
            </td>
        </tr>

        <%
        }
        %>

        </tbody>

    </table>

</div>

<%
}
%>


<div class="card shadow mb-5 chart-card">

    <div class="card-header bg-primary text-white">
        <h4>
            <i class="bi bi-bar-chart-fill"></i>
            Blood Group Availability
        </h4>
    </div>

    <div class="card-body">

        <canvas id="bloodChart"></canvas>
    </div>

</div>
<!-- Recent Activities -->

<div class="row mb-5">

    <!-- Recent Donations -->
    <div class="col-md-6">

        <div class="card shadow">

            <div class="card-header bg-success text-white">
                <h5>
                    <i class="bi bi-heart-pulse-fill"></i>
                    Recent Donations
                </h5>
            </div>

            <div class="card-body">

                <table class="table table-striped">

                    <thead>

                        <tr>
                            <th>Donor ID</th>
                            <th>Blood Group</th>
                            <th>Units</th>
                        </tr>

                    </thead>

                   <tbody>

<%
List<Donation> recentDonations =
(List<Donation>)request.getAttribute("recentDonations");

if(recentDonations != null && !recentDonations.isEmpty()){

    for(Donation d : recentDonations){
%>

<tr>
    <td><%=d.getDonorId()%></td>
    <td><%=d.getBloodGroup()%></td>
    <td><%=d.getUnits()%></td>
</tr>

<%
    }
}else{
%>

<tr>
    <td colspan="3" class="text-center text-muted">
        No recent donations found.
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

    <!-- Recent Requests -->

    <div class="col-md-6">

        <div class="card shadow">

            <div class="card-header bg-warning">
                <h5>
                    <i class="bi bi-clipboard2-pulse-fill"></i>
                    Recent Blood Requests
                </h5>
            </div>

            <div class="card-body">

                <table class="table table-striped">

                    <thead>

                        <tr>
                            <th>Patient ID</th>
                            <th>Blood Group</th>
                            <th>Status</th>
                        </tr>

                    </thead>

                  <tbody>

<%
List<BloodRequest> recentRequests =
(List<BloodRequest>)request.getAttribute("recentRequests");

if(recentRequests != null && !recentRequests.isEmpty()){

    for(BloodRequest r : recentRequests){
%>

<tr>
    <td><%=r.getPatientId()%></td>
    <td><%=r.getBloodGroup()%></td>
    <td>
        <span class="badge
        <%= "Approved".equals(r.getStatus()) ? "bg-success"
            : "Rejected".equals(r.getStatus()) ? "bg-danger"
            : "bg-warning text-dark" %>">
            <%=r.getStatus()%>
        </span>
    </td>
</tr>

<%
    }
}else{
%>

<tr>
    <td colspan="3" class="text-center text-muted">
        No recent blood requests found.
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

</div>
<div class="row">
<div class="col-lg-2 col-md-4 col-sm-6 mb-3">

<div class="dashboard-card bg-primary">

<h3>Donor</h3>

<p>Manage Donor Details</p>

<a href="DonorServlet"
class="btn btn-light">

Open

</a>

</div>

</div>
<div class="col-lg-2 col-md-4 col-sm-6 mb-3">

<div class="dashboard-card bg-success">

<h3>Patient</h3>

<p>Manage Patient Details</p>

<a href="PatientServlet"
class="btn btn-light">

Open

</a>

</div>

</div>
<div class="col-lg-2 col-md-4 col-sm-6 mb-3">

<div class="dashboard-card bg-danger">

<h3>Blood Stock</h3>

<p>Manage Blood Units</p>

<a href="BloodStockServlet"
class="btn btn-light">

Open

</a>

</div>

</div>
<div class="col-lg-2 col-md-4 col-sm-6 mb-3">

<div class="dashboard-card bg-warning text-dark">

<h3>Donation</h3>

<p>Manage Donations</p>

<a href="DonationServlet"
class="btn btn-dark">

Open

</a>

</div>

</div>
<div class="col-lg-2 col-md-4 col-sm-6 mb-3">

<div class="dashboard-card bg-info">

<h3>Blood Request</h3>

<p>Manage Requests</p>

<a href="BloodRequestServlet"
class="btn btn-light">

Open

</a>

</div>

</div>

<div class="col-lg-2 col-md-4 col-sm-6 mb-3">

<div class="dashboard-card bg-secondary">

<h3>Logout</h3>

<p>Exit Application</p>

<a href="LogoutServlet"
   class="btn btn-light"
   onclick="return confirm('Are you sure you want to logout?')">
    Logout
</a>

</div>

</div>

</div>

</div>
<script>

const labels = [
<%
List<BloodStock> stockList =
(List<BloodStock>)request.getAttribute("stockList");

if(stockList != null){

    for(int i=0;i<stockList.size();i++){
%>
"<%=stockList.get(i).getBloodGroup()%>"<%= (i<stockList.size()-1)?",":"" %>
<%
    }
}
%>
];

const units = [
<%
if(stockList != null){

    for(int i=0;i<stockList.size();i++){
%>
<%=stockList.get(i).getUnits()%><%= (i<stockList.size()-1)?",":"" %>
<%
    }
}
%>
];

new Chart(document.getElementById("bloodChart"),{

    type:"bar",

    data:{

        labels:labels,

        datasets:[{

            label:"Available Units",

            data:units,

            backgroundColor:[
                "#dc3545",
                "#0d6efd",
                "#198754",
                "#ffc107",
                "#6f42c1",
                "#20c997",
                "#fd7e14",
                "#6610f2"
            ],

            borderWidth:1

        }]

    },

    options:{

    	responsive: true,
    	maintainAspectRatio: false,
        animation:{
            duration:1500
        },

        scales:{
            y:{
                beginAtZero:true
            }
        },

        plugins:{
            legend:{
                display:false
            }
        }

    }

});

</script>
<script>
function updateDateTime() {

    const now = new Date();

    document.getElementById("dateTime").innerHTML =
        now.toLocaleDateString() + " | " +
        now.toLocaleTimeString();
}

updateDateTime();
setInterval(updateDateTime, 1000);
</script>
<%@ include file="footer.jsp" %>
</body>

</html>
