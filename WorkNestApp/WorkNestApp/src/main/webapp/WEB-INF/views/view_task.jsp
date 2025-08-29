<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Task Details</title>
    <style>
        body { font-family: sans-serif; }
        .container { width: 700px; margin: auto; }
        .task-details, .comments-section, .add-comment { border: 1px solid #ccc; padding: 15px; margin-bottom: 20px; border-radius: 5px; }
        .comment { border-bottom: 1px solid #eee; padding-bottom: 10px; margin-bottom: 10px; }
        .comment-meta { font-size: 0.8em; color: #777; }
    </style>
</head>
<body>
    <div class="container">
        <h2>Task Details</h2>
        <div class="task-details">
            <h3>${task.title}</h3>
            <p><strong>Status:</strong> ${task.status}</p>
            <p><strong>Due Date:</strong> <fmt:formatDate value="${task.dueDate}" pattern="yyyy-MM-dd"/></p>
            <p><strong>Description:</strong><br/> ${task.description}</p>
        </div>

        <div class="comments-section">
            <h4>Comments</h4>
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
                <p>No comments yet.</p>
            </c:if>
        </div>

        <div class="add-comment">
            <h4>Add a Comment</h4>
            <form action="addComment" method="post">
                <input type="hidden" name="taskId" value="${task.id}">
                <textarea name="content" rows="4" style="width: 98%;" required></textarea><br/><br/>
                <input type="submit" value="Add Comment">
            </form>
        </div>
        <p><a href="${pageContext.request.contextPath}/user/dashboard">Back to Dashboard</a></p>
    </div>
</body>
</html>