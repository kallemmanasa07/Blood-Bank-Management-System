<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Login | Blood Bank Management System</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet">

<link rel="stylesheet" href="css/style.css">

</head>

<body>

<jsp:include page="navbar.jsp" />

<div class="container login-container">

    <div class="login-box">

        <h2 class="text-center text-danger mb-4">
            Blood Bank Login
        </h2>

        <%
            String error = (String) request.getAttribute("error");

            if (error != null) {
        %>

        <div class="alert alert-danger">
            <%= error %>
        </div>

        <%
            }

            String success = (String) request.getAttribute("success");

            if (success != null) {
        %>

        <div class="alert alert-success">
            <%= success %>
        </div>

        <%
            }
        %>

        <form action="LoginServlet" method="post">

            <div class="mb-3">

                <label class="form-label">Email</label>

                <input
                    type="email"
                    name="email"
                    class="form-control"
                    placeholder="Enter Email"
                    autocomplete="email"
                    required>

            </div>

            <div class="mb-3">

                <label class="form-label">Password</label>

                <input
                    type="password"
                    id="password"
                    name="password"
                    class="form-control"
                    placeholder="Enter Password"
                    autocomplete="current-password"
                    required>

            </div>

            <div class="form-check mb-3">

                <input
                    class="form-check-input"
                    type="checkbox"
                    id="showPassword">

                <label
                    class="form-check-label"
                    for="showPassword">

                    Show Password

                </label>

            </div>

            <div class="text-center mt-4">

                <button
                    type="submit"
                    class="btn btn-danger login-btn">

                    Login

                </button>

    </div>

            <div class="text-center mt-4">

                <p>

                    Don't have an account?

                    <a href="register.jsp">
                        Register Here
                    </a>

                </p>

            </div>

        </form>

    </div>

</div>

<jsp:include page="footer.jsp" />

<script>

document.getElementById("showPassword").addEventListener("change", function () {

    const password = document.getElementById("password");

    password.type = this.checked ? "text" : "password";

});

</script>


</body>

</html>