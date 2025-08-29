<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Assign New Task</title>
    <style> body { font-family: sans-serif; } .form-container { width: 400px; margin: auto; padding: 20px; border: 1px solid #ccc; border-radius: 5px; } </style>
</head>
<body>
    <div class="form-container">
        <h2>Assign a New Task</h2>
        <form action="saveTask" method="post">
            <p>Title:<br/><input type="text" name="title" required style="width: 95%;"></p>
            <p>Description:<br/><textarea name="description" rows="4" style="width: 95%;"></textarea></p>
            <p>Start Date:<br/><input type="date" name="startDate" required></p>
            <p>Due Date:<br/><input type="date" name="dueDate" required></p>
            <p>Assign To:<br/>
                <select name="assignedToId" required>
                    <option value="">-- Select User --</option>
                    <c:forEach var="user" items="${allUsers}">
                        <c:if test="${user.role eq 'ROLE_USER'}">
                             <option value="${user.id}">${user.fullName}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </p>
            <input type="submit" value="Assign Task">
        </form>
        <p><a href="dashboard">Back to Dashboard</a></p>
    </div>
</body>
</html>