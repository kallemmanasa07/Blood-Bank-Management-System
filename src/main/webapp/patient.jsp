<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="model.Patient"%>

<%
if(session.getAttribute("user")==null){
    response.sendRedirect("login.jsp");
    return;
}

String role = (String) session.getAttribute("role");
%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>Patient Management</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

<link rel="stylesheet" href="css/style.css">

</head>

<body>

<jsp:include page="navbar.jsp" />
<div class="container mt-4">

<div class="card">

<div class="card-header bg-primary text-white">

<h3>Patient Management</h3>

</div>

<div class="card-body">

<form action="PatientServlet" method="post">

<input type="hidden"
name="patientId"
value="${patient.patientId}">

<div class="row">

<div class="col-md-6 mb-3">
<label>Patient Name</label>

<input type="text"
       name="name"
       class="form-control"
       value="${patient.name}"
       required>

</div>

<div class="col-md-6 mb-3">

<label>Blood Group</label>

<select name="bloodGroup" class="form-select" required>

<option value="" selected disabled>Select Blood Group</option>
<option value="A+" ${patient.bloodGroup=='A+'?'selected':''}>A+</option>
<option value="A-" ${patient.bloodGroup=='A-'?'selected':''}>A-</option>
<option value="B+" ${patient.bloodGroup=='B+'?'selected':''}>B+</option>
<option value="B-" ${patient.bloodGroup=='B-'?'selected':''}>B-</option>
<option value="AB+" ${patient.bloodGroup=='AB+'?'selected':''}>AB+</option>
<option value="AB-" ${patient.bloodGroup=='AB-'?'selected':''}>AB-</option>
<option value="O+" ${patient.bloodGroup=='O+'?'selected':''}>O+</option>
<option value="O-" ${patient.bloodGroup=='O-'?'selected':''}>O-</option>

</select>

</div>

<div class="col-md-6 mb-3">
<label>Units Required</label>

<input type="number"
       name="units"
       class="form-control"
       value="${patient.units}"
       min="1"
       required>

</div>

<div class="col-md-6 mb-3">

<label>Doctor Name</label>

<input type="text"
name="doctor"
class="form-control"
value="${patient.doctor}"
required>

</div>

<div class="col-md-6 mb-3">

<label>Hospital</label>

<input type="text"
name="hospital"
class="form-control"
value="${patient.hospital}"
required>

</div>

<div class="col-md-6 mb-3">

<label>Phone Number</label>

<input type="tel"
       name="phone"
       class="form-control"
       value="${patient.phone}"
       pattern="[0-9]{10}"
       maxlength="10"
       placeholder="Enter 10-digit phone number"
       required>

</div>

</div>

<button type="submit" class="btn btn-success">
    ${empty patient ? 'Save Patient' : 'Update Patient'}
</button>

<a href="PatientServlet"
class="btn btn-primary">

View Patients

</a>

</form>

</div>

</div>
<br>

<div class="card">

<div class="card-header bg-dark text-white">

<h4>Patient List</h4>

</div>

<div class="card-body">

<form action="PatientServlet" method="get">

<input type="hidden"
name="action"
value="search">

<div class="row">

<div class="col-md-4">

<select class="form-select"
name="bloodGroup">
  <option value="" selected>-- Select Blood Group --</option>

<option>A+</option>
<option>A-</option>
<option>B+</option>
<option>B-</option>
<option>AB+</option>
<option>AB-</option>
<option>O+</option>
<option>O-</option>

</select>

</div>

<div class="col-md-2">

<button class="btn btn-primary">

Search

</button>

</div>

</div>

</form>

<br>

<table class="table table-bordered table-hover">

<thead class="table-primary">

<tr>

<th>ID</th>
<th>Name</th>
<th>Blood Group</th>
<th>Units</th>
<th>Doctor</th>
<th>Hospital</th>
<th>Phone</th>
<th>Action</th>

</tr>

</thead>
<tbody>

<%
List<Patient> patientList =
    (List<Patient>) request.getAttribute("patientList");

if (patientList != null && !patientList.isEmpty()) {

    for (Patient p : patientList) {
%>

<tr>

    <td><%= p.getPatientId() %></td>
    <td><%= p.getName() %></td>
    <td><%= p.getBloodGroup() %></td>
    <td><%= p.getUnits() %></td>
    <td><%= p.getDoctor() %></td>
    <td><%= p.getHospital() %></td>
    <td><%= p.getPhone() %></td>

    <td>

    <%
    if ("ADMIN".equalsIgnoreCase(role)) {
    %>

        <a href="PatientServlet?action=edit&id=<%= p.getPatientId() %>"
           class="btn btn-warning btn-sm me-1">
            ✏️ Edit
        </a>

        <a href="PatientServlet?action=delete&id=<%= p.getPatientId() %>"
           class="btn btn-danger btn-sm"
           onclick="return confirm('Delete this patient?')">
            🗑 Delete
        </a>

    <%
    } else {
    %>

        <a href="PatientServlet?action=edit&id=<%= p.getPatientId() %>"
           class="btn btn-warning btn-sm">
            ✏️ Edit
        </a>

    <%
    }
    %>

    </td>

</tr>

<%
    }

} else {
%>

<tr>

    <td colspan="8" class="text-center text-muted">
        No patients found.
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