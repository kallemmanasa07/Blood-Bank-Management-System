<%@ page import="model.User" %>

<%
User navUser = (User) session.getAttribute("user");
String dashboardLink = "DashboardServlet";

if (navUser != null && "USER".equalsIgnoreCase(navUser.getRole())) {
    dashboardLink = "UserDashboardServlet";
}
%>

<nav class="navbar navbar-expand-lg navbar-dark bg-black">

    <div class="container">

        <a class="navbar-brand fw-bold" href="<%= dashboardLink %>">
            &#129656; Blood Bank
        </a>

        <button class="navbar-toggler"
                type="button"
                data-bs-toggle="collapse"
                data-bs-target="#navbarNav"
                aria-controls="navbarNav"
                aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarNav">

            <ul class="navbar-nav ms-auto">

                <li class="nav-item">
                    <a class="nav-link" href="<%= dashboardLink %>">Dashboard</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="DonorServlet">Donors</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="PatientServlet">Patients</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="BloodStockServlet">Blood Stock</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="DonationServlet">Donations</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="BloodRequestServlet">Blood Requests</a>
                </li>

<%
if (navUser != null) {
%>

                <li class="nav-item dropdown">

                    <a class="nav-link dropdown-toggle"
                       href="#"
                       id="navbarDropdown"
                       role="button"
                       data-bs-toggle="dropdown"
                       aria-expanded="false">

                        Welcome, <%= navUser.getName() %>

                    </a>

                    <ul class="dropdown-menu dropdown-menu-end">

                        <li>
                            <a class="dropdown-item" href="<%= dashboardLink %>">
                                Dashboard
                            </a>
                        </li>

                        <li>
                            <a class="dropdown-item text-danger"
                               href="DeleteAccountServlet"
                               onclick="return confirm('Are you sure you want to delete your account?')">
                                Delete Account
                            </a>
                        </li>

                        <li><hr class="dropdown-divider"></li>

                        <li>
                            <a class="dropdown-item text-danger"
                               href="LogoutServlet">
                                Logout
                            </a>
                        </li>

                    </ul>

                </li>

<%
} else {
%>

                <li class="nav-item">
                    <a class="nav-link" href="login.jsp">Login</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="register.jsp">Register</a>
                </li>

<%
}
%>

            </ul>

        </div>

    </div>

</nav>