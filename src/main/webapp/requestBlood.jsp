<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="model.BloodRequest"%>

<%
if(session.getAttribute("user")==null){
    response.sendRedirect("login.jsp");
    return;
}
%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>Blood Request Management</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
rel="stylesheet">

<link rel="stylesheet" href="css/style.css">

</head>

<body>

<%@ include file="navbar.jsp" %>

<div class="container mt-4">

<div class="card">

<div class="card-header bg-danger text-white">

<h3>Blood Request</h3>

</div>

<div class="card-body">

<%
String error=(String)request.getAttribute("error");

if(error!=null){
%>

<div class="alert alert-danger">

<%=error%>

</div>

<%
}
%>

<form action="BloodRequestServlet" method="post">

<div class="row">

<div class="col-md-4 mb-3">

<label>Patient ID</label>

<input type="number"
class="form-control"
name="patientId"
required>

</div>

<div class="col-md-4 mb-3">

<label>Blood Group</label>

<select name="bloodGroup"
class="form-select"
required>

<option value="">Select</option>

<option value="A+">A+</option>
<option value="A-">A-</option>
<option value="B+">B+</option>
<option value="B-">B-</option>
<option value="AB+">AB+</option>
<option value="AB-">AB-</option>
<option value="O+">O+</option>
<option value="O-">O-</option>

</select>

</div>

<div class="col-md-4 mb-3">

<label>Units Required</label>

<input type="number"
class="form-control"
name="units"
min="1"
required>

</div>

</div>

<button type="submit"
class="btn btn-danger">

Request Blood

</button>

<a href="BloodRequestServlet"
class="btn btn-primary">

Refresh

</a>

</form>

</div>

</div>

<br>

<div class="card">

<div class="card-header bg-dark text-white">

<h4>Blood Request List</h4>

</div>

<div class="card-body">

<table class="table table-bordered table-hover">

<thead class="table-danger">

<tr>

<th>Request ID</th>

<th>Patient ID</th>

<th>Blood Group</th>

<th>Units</th>

<th>Status</th>

<%
String role = (String)session.getAttribute("role");

if("ADMIN".equalsIgnoreCase(role)) {
%>
<th>Actions</th>
<%
}
%>

</tr>


</thead>
<tbody>

<%
List<BloodRequest> requestList =
    (List<BloodRequest>) request.getAttribute("requestList");

if (requestList != null && !requestList.isEmpty()) {

    for (BloodRequest br : requestList) {
%>

<tr>

    <td><%= br.getRequestId() %></td>

    <td><%= br.getPatientId() %></td>

    <td><%= br.getBloodGroup() %></td>

    <td><%= br.getUnits() %></td>

    <td>

    <%
    if ("Approved".equals(br.getStatus())) {
    %>

        <span class="badge bg-success">Approved</span>

    <%
    } else if ("Rejected".equals(br.getStatus())) {
    %>

        <span class="badge bg-danger">Rejected</span>

    <%
    } else {
    %>

        <span class="badge bg-warning text-dark">Pending</span>

    <%
    }
    %>

    </td>

    <%
    if ("ADMIN".equalsIgnoreCase(role)) {
    %>

    <td>

        <a href="BloodRequestServlet?action=approve&id=<%= br.getRequestId() %>"
           class="btn btn-success btn-sm">
            Approve
        </a>

        <a href="BloodRequestServlet?action=reject&id=<%= br.getRequestId() %>"
           class="btn btn-warning btn-sm">
            Reject
        </a>

        <a href="BloodRequestServlet?action=delete&id=<%= br.getRequestId() %>"
           class="btn btn-danger btn-sm"
           onclick="return confirm('Delete this request?')">
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
        No blood requests found.
    </td>
</tr>

<%
}
%>

</tbody>
</table>

</div>

</div>


<%@ include file="footer.jsp" %>

</body>
</html>