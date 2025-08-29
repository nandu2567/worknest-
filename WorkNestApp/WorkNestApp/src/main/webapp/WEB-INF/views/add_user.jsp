<%@ page language="java" contentType="text-html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add New User</title>
    <style> body { font-family: sans-serif; } .form-container { width: 400px; margin: auto; padding: 20px; border: 1px solid #ccc; border-radius: 5px; } </style>
</head>
<body>
    <div class="form-container">
        <h2>Add a New User</h2>
        <form action="saveUser" method="post">
            <p>Full Name:<br/><input type="text" name="fullName" required style="width: 95%;"></p>
            <p>Email:<br/><input type="email" name="email" required style="width: 95%;"></p>
            <p>Password:<br/><input type="password" name="password" required style="width: 95%;"></p>
            <p>Role:<br/>
                <select name="role" required>
                    <option value="ROLE_USER">User</option>
                    <option value="ROLE_ADMIN">Admin</option>
                </select>
            </p>
            <input type="submit" value="Save User">
        </form>
        <p><a href="users">Back to User List</a></p>
    </div>
</body>
</html>