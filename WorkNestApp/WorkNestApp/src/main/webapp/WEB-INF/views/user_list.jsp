<%@ page language="java" contentType="text-html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Management</title>
    <style>
        body { font-family: sans-serif; }
        .container { width: 800px; margin: auto; }
        table { width: 100%; border-collapse: collapse; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
    </style>
</head>
<body>
    <div class="container">
        <h2>User Management</h2>
        <p>
            <a href="addUser">Add New User</a> | 
            <a href="dashboard">Back to Dashboard</a>
        </p>
        <hr/>
        <table>
            <tr>
                <th>Full Name</th>
                <th>Email</th>
                <th>Role</th>
            </tr>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.fullName}</td>
                    <td>${user.email}</td>
                    <td>${user.role}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>