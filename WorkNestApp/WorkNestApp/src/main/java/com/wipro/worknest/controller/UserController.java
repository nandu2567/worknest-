package com.wipro.worknest.controller;

import com.wipro.worknest.dao.CommentDAO;
import com.wipro.worknest.dao.TaskDAO;
import com.wipro.worknest.model.Comment; // <-- Added this import
import com.wipro.worknest.model.Task;
import com.wipro.worknest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping; // <-- Added this import
import org.springframework.web.bind.annotation.RequestParam; // <-- Added this import

import javax.servlet.http.HttpSession;
import java.util.Date; // <-- Added this import
import java.util.List;

@Controller
public class UserController {

    // --- DAO Dependencies ---
    @Autowired
    private TaskDAO taskDAO;

    @Autowired
    private CommentDAO commentDAO;

    // --- Controller Methods ---

    @GetMapping("/user/dashboard")
    public String showUserDashboard(HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/";
        }
        List<Task> myTasks = taskDAO.getTasksByUserId(loggedInUser.getId());
        model.addAttribute("tasks", myTasks);
        return "user_dashboard";
    }

    @GetMapping("/user/updateStatus")
    public String updateTaskStatus(@RequestParam int taskId, @RequestParam String status) {
        Task task = taskDAO.getTaskById(taskId);
        if (task != null) {
            task.setStatus(status);
            taskDAO.saveTask(task);
        }
        return "redirect:/user/dashboard";
    }

    @GetMapping("/user/viewTask")
    public String viewTask(@RequestParam int taskId, Model model) {
        Task task = taskDAO.getTaskById(taskId);
        model.addAttribute("task", task);
        return "view_task";
    }

    @PostMapping("/user/addComment")
    public String addComment(@RequestParam int taskId, @RequestParam String content, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/";
        }
        Task task = taskDAO.getTaskById(taskId);
        
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setTask(task);
        comment.setUser(loggedInUser);
        comment.setCreatedAt(new Date());
        
        commentDAO.saveComment(comment);
        
        return "redirect:/user/viewTask?taskId=" + taskId;
    }
}