package com.wipro.worknest.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wipro.worknest.model.User;
import com.wipro.worknest.service.UserService;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    // This method shows the login page when the user visits the root URL
    @GetMapping("/")
    public String showLoginPage() {
        return "login"; // Resolves to /WEB-INF/views/login.jsp
    }

    // This method handles the form submission from the login page
    @PostMapping("/login")
    public String handleLogin(@RequestParam String email, 
                              @RequestParam String password, 
                              HttpSession session, 
                              Model model) {
        
        User user = userService.checkLogin(email, password);

        if (user != null) {
            // Login successful, store user info in the session
            session.setAttribute("loggedInUser", user);
            
            // Redirect to the correct dashboard based on role
            if ("ROLE_ADMIN".equals(user.getRole())) {
                return "redirect:/admin/dashboard";
            } else {
                return "redirect:/user/dashboard";
            }
        } else {
            // Login failed, show an error message on the login page
            model.addAttribute("error", "Invalid email or password.");
            return "login";
        }
    }
    
    
    

     
}