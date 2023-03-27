<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reset Password</title>
</head>
<body>
    <h1>Reset Password</h1>
    <% 
        String email = request.getParameter("email");
        // validate email and check if it's associated with an account
        
        // generate reset token
        
        // send email with reset token
        
    %>
    <p>An email has been sent to <%= email %> with instructions on how to reset your password.</p>
</body>
</html>
