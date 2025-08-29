package com.wipro.worknest.dao;

import com.wipro.worknest.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository // Marks this as a Spring bean for database operations
public class UserDAO {

    @Autowired // Injects the SessionFactory bean we created in AppConfig
    private SessionFactory sessionFactory;

    @Transactional // Manages the database transaction automatically
    public void saveUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(user);
    }

    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("from User where email = :email", User.class);
        query.setParameter("email", email);
        return query.uniqueResult(); // Returns one user or null
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from User", User.class).list();
    }
 // Add this method inside your UserDAO.java class

    @Transactional(readOnly = true)
    public User getUserById(int id) {
        return sessionFactory.getCurrentSession().get(User.class, id);
    }
    
    
    // You would add methods for deleteUser, getUserById, etc. here
}