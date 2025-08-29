<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin - Task Details</title>
    <style>
        body { font-family: sans-serif; }
        .container { width: 700px; margin: auto; }
        .task-details, .comments-section { border: 1px solid #ccc; padding: 15px; margin-bottom: 20px; border-radius: 5px; }
        .comment { border-bottom: 1px solid #eee; padding-bottom: 10px; margin-bottom: 10px; }
        .comment-meta { font-size: 0.8em; color: #777; }
    </style>
</head>
<body>
    <div class="container">
        <h2>Task Details (Admin View)</h2>
        <div class="task-details">
            <h3>${task.title}</h3>
            <p><strong>Assigned To:</strong> ${task.assignedTo.fullName}</p>
            <p><strong>Status:</strong> ${task.status}</p>
            <p><strong>Due Date:</strong> <fmt:formatDate value="${task.dueDate}" pattern="yyyy-MM-dd"/></p>
            <p><strong>Description:</strong><br/> ${task.description}</p>
        </div>

        <div class="comments-section">
            <h4>User Comments</h4>
            <c:forEach var="comment" items="${task.comments}">
                <div class="comment">
                    <p>${comment.content}</p>
                    <p class="comment-meta">
                        By: ${comment.user.fullName} on 
                        <fmt:formatDate value="${comment.createdAt}" pattern="yyyy-MM-dd HH:mm"/>
                    </p>
                </div>
            </c:forEach>
            <c:if test="${empty task.comments}">
                <p>No comments have been added by the user yet.</p>
            </c:if>
        </div>
        <p><a href="${pageContext.request.contextPath}/admin/dashboard">Back to Dashboard</a></p>
    </div>
</body>
</html>