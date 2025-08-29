package com.wipro.worknest.service;

import com.wipro.worknest.dao.UserDAO;
import com.wipro.worknest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // Marks this as a Spring bean for business logic
public class UserService {

    @Autowired
    private UserDAO userDAO;

    /**
     * Checks if the provided email and password are valid.
     * @param email The user's email.
     * @param password The user's password.
     * @return The User object if login is successful, otherwise null.
     */
    public User checkLogin(String email, String password) {
        User user = userDAO.findByEmail(email);
        
        // In a real app, passwords should be hashed and securely compared.
        // For this project, we are doing a simple string comparison.
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        
        return null;
    }
}