package com.wipro.worknest.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wipro.worknest.dao.TaskDAO;
import com.wipro.worknest.dao.UserDAO;
import com.wipro.worknest.model.Task;
import com.wipro.worknest.model.User;

@Controller
public class AdminController {

    @Autowired
    private TaskDAO taskDAO;

    @Autowired
    private UserDAO userDAO;

    // --- Dashboard & Task Tracking Methods ---

    @GetMapping("/admin/dashboard")
    public String showAdminDashboard(Model model) {
        List<Task> allTasks = taskDAO.getAllTasks();

        model.addAttribute("pendingTasks", filterTasksByStatus(allTasks, "Pending"));
        model.addAttribute("inProgressTasks", filterTasksByStatus(allTasks, "In Progress"));
        model.addAttribute("completedTasks", filterTasksByStatus(allTasks, "Completed"));
        model.addAttribute("delayedTasks", allTasks.stream().filter(this::isDelayed).collect(Collectors.toList()));

        return "admin_dashboard";
    }

    @GetMapping("/admin/assignTask")
    public String showAssignTaskForm(Model model) {
        List<User> users = userDAO.getAllUsers();
        model.addAttribute("task", new Task());
        model.addAttribute("allUsers", users);
        return "assign_task";
    }

    @PostMapping("/admin/saveTask")
    public String saveTask(@ModelAttribute("task") Task task, @RequestParam("assignedToId") int userId) {
        User assignedUser = userDAO.getUserById(userId);
        task.setAssignedTo(assignedUser);
        task.setStatus("Pending"); 
        taskDAO.saveTask(task);
        return "redirect:/admin/dashboard";
    }

    // --- User Management Methods ---

    @GetMapping("/admin/users")
    public String showUserList(Model model) {
        List<User> userList = userDAO.getAllUsers();
        model.addAttribute("users", userList);
        return "user_list";
    }

    @GetMapping("/admin/addUser")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "add_user";
    }

    @PostMapping("/admin/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        // All new users will be ROLE_USER by default unless changed in form
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("ROLE_USER");
        }
        userDAO.saveUser(user);
        return "redirect:/admin/users";
    }

    // --- Helper Methods ---

    private List<Task> filterTasksByStatus(List<Task> tasks, String status) {
        return tasks.stream()
                .filter(task -> status.equals(task.getStatus()) && !isDelayed(task))
                .collect(Collectors.toList());
    }

    private boolean isDelayed(Task task) {
        return new Date().after(task.getDueDate()) && !"Completed".equals(task.getStatus());
    }
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
 // Add this method inside your AdminController.java

    @GetMapping("/admin/viewTask")
    public String viewTaskDetails(@RequestParam int taskId, Model model) {
        // The getTaskById method already fetches comments, so we can reuse it
        Task task = taskDAO.getTaskById(taskId);
        model.addAttribute("task", task);
        return "admin_view_task"; // This will be a new JSP page for the admin
    }
}