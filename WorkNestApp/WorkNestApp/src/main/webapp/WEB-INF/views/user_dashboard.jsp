<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>My Dashboard</title>
    <style>
        body { font-family: sans-serif; }
        .container { width: 900px; margin: auto; }
        table { width: 100%; border-collapse: collapse; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
    </style>
</head>
<body>
    <div class="container">
        <h2>My Tasks</h2>
        <h3>Welcome, ${sessionScope.loggedInUser.fullName}</h3>
        <hr/>
        <table>
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Due Date</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="task" items="${tasks}">
                    <tr>
                        <td>${task.title}</td>
                        <td><fmt:formatDate value="${task.dueDate}" pattern="yyyy-MM-dd"/></td>
                        <td>${task.status}</td>
                        <td>
                            <a href="viewTask?taskId=${task.id}">View Details</a>
                            <c:if test="${task.status == 'Pending'}">
                                | <a href="updateStatus?taskId=${task.id}&status=In Progress">Start Work</a>
                            </c:if>
                            <c:if test="${task.status == 'In Progress'}">
                                | <a href="updateStatus?taskId=${task.id}&status=Completed">Mark as Complete</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>