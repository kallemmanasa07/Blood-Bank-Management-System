<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="model.Donor"%>

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

<title>Donor Management</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

<link rel="stylesheet" href="css/style.css">

</head>

<body>

<jsp:include page="navbar.jsp" />

<div class="container mt-4">

<div class="card">

<div class="card-header bg-danger text-white">

<h3>Donor Management</h3>

</div>
<div class="card-body">

<form action="DonorServlet" method="post">

<input type="hidden"
name="donorId"
value="${donor.donorId}">

<div class="row">

<div class="col-md-6 mb-3">

<label>Name</label>

<input type="text"
name="name"
class="form-control"
value="${donor.name}"
required>

</div>

<div class="col-md-6 mb-3">

<label>Gender</label>

<select name="gender" class="form-select" required>

<option value="" selected disabled>Select Gender</option>
<option value="Male" ${donor.gender=='Male'?'selected':''}>Male</option>
<option value="Female" ${donor.gender=='Female'?'selected':''}>Female</option>
<option value="Other" ${donor.gender=='Other'?'selected':''}>Other</option>

</select>

</div>

<div class="col-md-6 mb-3">

<label>Age</label>
<input type="number"
       name="age"
       class="form-control"
       value="${donor.age}"
       min="18"
       max="65"
       required>

</div>

<div class="col-md-6 mb-3">

<label>Blood Group</label>

<select name="bloodGroup" class="form-select" required>

<option value="" selected disabled>Select Blood Group</option>

<option value="A+" ${donor.bloodGroup=='A+'?'selected':''}>A+</option>
<option value="A-" ${donor.bloodGroup=='A-'?'selected':''}>A-</option>
<option value="B+" ${donor.bloodGroup=='B+'?'selected':''}>B+</option>
<option value="B-" ${donor.bloodGroup=='B-'?'selected':''}>B-</option>
<option value="AB+" ${donor.bloodGroup=='AB+'?'selected':''}>AB+</option>
<option value="AB-" ${donor.bloodGroup=='AB-'?'selected':''}>AB-</option>
<option value="O+" ${donor.bloodGroup=='O+'?'selected':''}>O+</option>
<option value="O-" ${donor.bloodGroup=='O-'?'selected':''}>O-</option>

</select>
</div>

<div class="col-md-6 mb-3">

<label>Phone</label>

<input type="tel"
       name="phone"
       class="form-control"
       value="${donor.phone}"
       pattern="[0-9]{10}"
       maxlength="10"
       placeholder="Enter 10-digit phone number"
       required>

</div>

<div class="col-md-6 mb-3">

<label>Last Donation</label>

<input type="date"
name="lastDonation"
class="form-control"
value="${donor.lastDonation}">

</div>

<div class="col-md-12 mb-3">

<label>Address</label>

<textarea
name="address"
class="form-control"
rows="3">${donor.address}</textarea>

</div>

</div>

<button type="submit" class="btn btn-success">
    ${empty donor ? 'Save Donor' : 'Update Donor'}
</button>

<a href="DonorServlet"
class="btn btn-primary">

View Donors

</a>

</form>

</div>

</div>

<br>

<div class="card">

<div class="card-header bg-dark text-white">

<h4>Donor List</h4>

</div>

<div class="card-body">

<form action="DonorServlet" method="get">

<input type="hidden" name="action" value="search">

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

<thead class="table-danger">

<tr>

<th>ID</th>

<th>Name</th>

<th>Gender</th>

<th>Age</th>

<th>Blood Group</th>

<th>Phone</th>

<th>Address</th>

<th>Last Donation</th>
<th>Action</th>

</tr>

</thead>

<tbody>

<%
List<Donor> donorList =
    (List<Donor>) request.getAttribute("donorList");

if (donorList != null && !donorList.isEmpty()) {

    for (Donor d : donorList) {
%>

<tr>

<td><%=d.getDonorId()%></td>

<td><%=d.getName()%></td>

<td><%=d.getGender()%></td>

<td><%=d.getAge()%></td>

<td><%=d.getBloodGroup()%></td>

<td><%=d.getPhone()%></td>

<td><%=d.getAddress()%></td>

<td><%=d.getLastDonation()%></td>

<td>

<a href="DonorServlet?action=edit&id=<%=d.getDonorId()%>"
   class="btn btn-warning btn-sm">
    ✏️Edit
</a>

<%
if(role != null && role.equalsIgnoreCase("Admin")){
%>

<a href="DonorServlet?action=delete&id=<%=d.getDonorId()%>"
   class="btn btn-danger btn-sm"
   onclick="return confirm('Delete this donor?')">
    🗑Delete
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
    <td colspan="9" class="text-center text-muted">
        No donors found.
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