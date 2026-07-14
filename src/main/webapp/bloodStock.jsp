<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="model.BloodStock"%>
<%
if (session.getAttribute("user") == null) {
    response.sendRedirect("login.jsp");
    return;
}

String role = (String) session.getAttribute("role");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Blood Stock Management</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/style.css">

</head>
<body>

<jsp:include page="navbar.jsp" />

<div class="container mt-4">

    <div class="card">

        <div class="card-header bg-danger text-white">
            <h3>Blood Stock Management</h3>
        </div>

        <div class="card-body">

<% if(role != null && role.equalsIgnoreCase("ADMIN")) { %>

<form action="BloodStockServlet" method="post">

    <input type="hidden" name="stockId" value="${stock.stockId}">

    <div class="row">

        <div class="col-md-6">

            <label class="form-label">Blood Group</label>

            <select name="bloodGroup" class="form-select" required>

                <option value="" disabled
                    ${empty stock ? 'selected' : ''}>
                    Select Blood Group
                <option value="">-- Select Blood Group --</option>
<option value="A+" ${param.bloodGroup=='A+'?'selected':''}>A+</option>
<option value="A-" ${param.bloodGroup=='A-'?'selected':''}>A-</option>
<option value="B+" ${param.bloodGroup=='B+'?'selected':''}>B+</option>
<option value="B-" ${param.bloodGroup=='B-'?'selected':''}>B-</option>
<option value="AB+" ${param.bloodGroup=='AB+'?'selected':''}>AB+</option>
<option value="AB-" ${param.bloodGroup=='AB-'?'selected':''}>AB-</option>
<option value="O+" ${param.bloodGroup=='O+'?'selected':''}>O+</option>
<option value="O-" ${param.bloodGroup=='O-'?'selected':''}>O-</option>

            </select>

        </div>

        <div class="col-md-6">

            <label class="form-label">Units</label>

            <input
                type="number"
                name="units"
                class="form-control"
                min="1"
                value="${stock.units}"
                required>

        </div>

    </div>

    <br>
<button type="submit" class="btn btn-success">
    ${empty stock ? 'Save Blood Stock' : 'Update Blood Stock'}
</button>

    <a href="BloodStockServlet" class="btn btn-primary">
        View Stock
    </a>

</form>

<% } else { %>

<form action="BloodStockServlet" method="get">

    <input type="hidden" name="action" value="search">

    <div class="row">

        <div class="col-md-6">

            <label class="form-label">Search Blood Group</label>

            <select name="bloodGroup" class="form-select">

                <option value="">-- Select Blood Group --</option>

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

            <br>

            <button type="submit" class="btn btn-primary">
                Search
            </button>

        </div>

    </div>

</form>

<% } %>

        </div>

    </div>

    <br>

    <div class="card">

        <div class="card-header bg-dark text-white">
            <h4>Available Blood Stock</h4>
        </div>

        <div class="card-body">

            <table class="table table-bordered table-hover table-striped">

                <thead>

                <tr>

                    <th>Stock ID</th>
                    <th>Blood Group</th>
                    <th>Units</th>

<%
if(role != null && role.equalsIgnoreCase("ADMIN")){
%>

                    <th>Action</th>

<%
}
%>

                </tr>

                </thead>

                <tbody>

<%

List<BloodStock> stockList =
(List<BloodStock>)request.getAttribute("stockList");

if(stockList != null && !stockList.isEmpty()){

    for(BloodStock s : stockList){

%>

<tr>

    <td><%= s.getStockId() %></td>
    <td><%= s.getBloodGroup() %></td>
    <td><%= s.getUnits() %></td>

<%

if(role != null && role.equalsIgnoreCase("ADMIN")){

%>

<td>

<a href="BloodStockServlet?action=edit&id=<%= s.getStockId() %>"
   class="btn btn-warning btn-sm">
    ✏️ Edit
</a>

<a href="BloodStockServlet?action=delete&id=<%= s.getStockId() %>"
   class="btn btn-danger btn-sm"
   onclick="return confirm('Delete this record?');">
    🗑 Delete
</a>

</td>

<%
}
%>

</tr>

<%
    }

}else{
%>

<tr>

<td colspan="<%= (role != null && role.equalsIgnoreCase("ADMIN")) ? 4 : 3 %>"
    class="text-center text-muted">

No blood stock available.

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