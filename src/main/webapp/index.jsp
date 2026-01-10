<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Application</title>
<style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            margin: 0;
            background-color: #f5f5f5;
        }
        .login-container {
            background: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            width: 100%;
            max-width: 400px;
        }
        h2 {
            text-align: center;
            color: #333;
        }
        input {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }
        button {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<%
    // Error from request attribute (forward)
    String errorMsg = (String) request.getAttribute("error");
    
    // Message from URL parameter (redirect)
    String message = request.getParameter("message");
    
    String success = (String) request.getAttribute("successMsg");
%>

<!-- Error from request attribute -->
<% if(errorMsg != null) { %>
    <p style="color:red;"><%= errorMsg %></p>
<% } %>

<!-- Success from URL parameter -->
<% if("success".equals(message)) { %>
    <p style="color:green;">✅ Password reset successful! Please login.</p>
<% } %>

<!-- Error from URL parameter -->
<% if(success != null) { %>
    <p style="color:green;"><%= success %></p>
<% } %>

   <div class="login-container">
        <h2>Login</h2>
        <form action="login" method="post">
            <input type="email" name="email" placeholder="Email" required>
            <input type="password" name="password" placeholder="Password" required>
            <button type="submit">Login</button>
        </form>
        <p style="text-align: center;">
            <a href="forgotPswd.jsp">Forgot Password?</a> | 
            <a href="register.html">Register</a>
        </p>
    </div>
      

</body>
</html>