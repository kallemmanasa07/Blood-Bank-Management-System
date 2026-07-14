<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Register | Blood Bank Management System</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
rel="stylesheet">

<link rel="stylesheet" href="css/style.css">

</head>


<body>


<jsp:include page="navbar.jsp" />


<div class="container login-container">


<div class="login-box">


<h2 class="text-center text-danger mb-4">

User Registration

</h2>


<%

String error = (String)request.getAttribute("error");

if(error != null){

%>


<div class="alert alert-danger">

<%=error%>

</div>


<%

}

%>


<form action="RegisterServlet" method="post">



<div class="mb-3">

<label class="form-label">

Full Name

</label>


<input
type="text"
name="name"
class="form-control"
placeholder="Enter Full Name"
required>


</div>




<div class="mb-3">

<label class="form-label">

Email

</label>


<input
type="email"
name="email"
class="form-control"
placeholder="Enter Email"
required>


</div>





<div class="mb-3">

<label class="form-label">

Password

</label>


<input
type="password"
name="password"
class="form-control"
placeholder="Enter Password"
required>


</div>





<div class="mb-3">

<label class="form-label">

Confirm Password

</label>


<input
type="password"
name="confirmPassword"
class="form-control"
placeholder="Confirm Password"
required>


</div>





<div class="mb-3">


<label class="form-label">

Role

</label>


<select
name="role"
class="form-select"
required>


<option value="" selected disabled>

Select a Role

</option>


<option value="ADMIN">

ADMIN

</option>


<option value="USER">

USER

</option>


</select>


</div>





<div class="text-center mt-4">


<button
type="submit"
class="btn btn-danger login-btn">

Register

</button>


</div>





<div class="text-center mt-4">


<p>

Already have an account?


<a href="login.jsp">

Login Here

</a>


</p>


</div>




</form>


</div>


</div>



<jsp:include page="footer.jsp" />



<script>


document.querySelector("form").addEventListener("submit",function(e){


let password =
document.getElementsByName("password")[0].value;


let confirmPassword =
document.getElementsByName("confirmPassword")[0].value;



if(password !== confirmPassword){


alert("Password and Confirm Password must match.");


e.preventDefault();


}


});


</script>



</body>

</html>