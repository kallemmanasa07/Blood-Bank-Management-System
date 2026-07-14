<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Blood Bank Management System</title>

<link rel="stylesheet" 
href="${pageContext.request.contextPath}/css/style.css">

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style>

body{
    margin:0;
    padding:0;
    background:#f4f8fb;
}

.hero{
    height:100vh;
    display:flex;
    justify-content:center;
    align-items:center;
}

.card-box{
    max-width:700px;
    width:100%;
    padding:40px;
    border-radius:15px;
    background:white;
    box-shadow:0px 0px 20px rgba(0,0,0,0.2);
    text-align:center;
}

h1{
    color:#c62828;
    font-weight:bold;
}

p{
    color:#555;
    font-size:18px;
}

.btn-custom{
    min-width:180px;
    margin:10px;
}

.footer{
    margin-top:30px;
    color:gray;
}

</style>

</head>
<body>

<div class="container hero">

<div class="card-box">

<h1>🩸 Blood Bank Management System</h1>

<hr>

<p class="lead">
Welcome to the Blood Bank Management System
</p>

<p>
Connecting blood donors with patients quickly, safely, and efficiently.
</p>

<p class="text-danger fw-bold">
🩸 Donate Blood... Save Lives...
</p>

<div class="home-buttons">

    <a href="login.jsp" class="home-btn login-home-btn">
        Login
    </a>

    <a href="register.jsp" class="home-btn register-home-btn">
        Register
    </a>

</div>

<div class="footer">

<hr>
<h6 class="text-secondary">
Developed Using JSP | Servlets | JDBC | MySQL | Bootstrap 5
</h6>

<p class="small">
© 2026 Blood Bank Management System. All Rights Reserved.
</p>

</div>

</div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>