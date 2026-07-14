package controller;


import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/GoogleLoginServlet")
public class GoogleLoginServlet extends HttpServlet {


private static final long serialVersionUID = 1L;


@Override
protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response)

throws ServletException, IOException {


String clientId =
"YOUR_GOOGLE_CLIENT_ID";


String redirect =
"http://localhost:8081/BloodBankManagementSystem/GoogleCallbackServlet";


String url =
"https://accounts.google.com/o/oauth2/v2/auth?"
+"client_id="+clientId
+"&redirect_uri="+redirect
+"&response_type=code"
+"&scope=email%20profile";


response.sendRedirect(url);


}


}