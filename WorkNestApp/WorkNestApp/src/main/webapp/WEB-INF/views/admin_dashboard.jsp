 <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <style>
        body { font-family: sans-serif; }
        .dashboard-container { display: flex; justify-content: space-around; }
        .task-column { width: 23%; padding: 10px; margin: 5px; border: 1px solid #ccc; background-color: #f9f9f9; border-radius: 5px; }
        .task-card { background-color: white; border: 1px solid #ddd; padding: 8px; margin-bottom: 8px; border-radius: 4px; }
        h1, h3 { text-align: center; }
        h4 { margin-top: 0; }
        .delayed { background-color: #ffdddd; border-left: 5px solid red; }
        .completed { background-color: #d4edda; border-left: 5px solid green; }
        .pending { border-left: 5px solid orange; }
        .task-link {
    text-decoration: none;
    color: black;
}
        .in-progress { border-left: 5px solid #007bff; }
    </style>
</head>
<body>
    <h1>Admin Dashboard</h1>
    <%-- In admin_dashboard.jsp --%>
<h3>
    Welcome, ${sessionScope.loggedInUser.fullName} | 
    <a href="assignTask">Assign New Task</a> | 
    <a href="users">Manage Users</a>
</h3>
    <hr/>
     <%-- In admin_dashboard.jsp --%>
    <div class="dashboard-container">
    <div class="task-column">
        <h4>Pending</h4>
        <c:forEach var="task" items="${pendingTasks}">
            <a href="viewTask?taskId=${task.id}" class="task-link">
                <div class="task-card pending"><b>${task.title}</b><br/>To: ${task.assignedTo.fullName}</div>
            </a>
        </c:forEach>
    </div>
    <div class="task-column">
        <h4>In Progress</h4>
        <c:forEach var="task" items="${inProgressTasks}">
            <a href="viewTask?taskId=${task.id}" class="task-link">
                <div class="task-card in-progress"><b>${task.title}</b><br/>To: ${task.assignedTo.fullName}</div>
            </a>
        </c:forEach>
    </div>
    <div class="task-column">
        <h4>Completed</h4>
        <c:forEach var="task" items="${completedTasks}">
            <a href="viewTask?taskId=${task.id}" class="task-link">
                <div class="task-card completed"><b>${task.title}</b><br/>To: ${task.assignedTo.fullName}</div>
            </a>
        </c:forEach>
    </div>
    <div class="task-column">
        <h4>Delayed</h4>
        <c:forEach var="task" items="${delayedTasks}">
            <a href="viewTask?taskId=${task.id}" class="task-link">
                <div class="task-card delayed"><b>${task.title}</b><br/>To: ${task.assignedTo.fullName}</div>
            </a>
        </c:forEach>
    </div>
</div>
</body>
</html>