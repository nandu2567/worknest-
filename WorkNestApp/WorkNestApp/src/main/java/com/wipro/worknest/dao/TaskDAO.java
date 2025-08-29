package com.wipro.worknest.dao;

import com.wipro.worknest.model.Task;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public class TaskDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void saveTask(Task task) {
        sessionFactory.getCurrentSession().saveOrUpdate(task);
    }
    @Transactional(readOnly = true)
    public Task getTaskById(int id) {
        Task task = sessionFactory.getCurrentSession().get(Task.class, id);
        
        // ---- ADD THIS LINE ----
        // This tells Hibernate to fetch the comments while the session is still open.
        if (task != null) {
            Hibernate.initialize(task.getComments());
        }
        
        return task;
    }

     
    
    @Transactional(readOnly = true)
    public List<Task> getAllTasks() {
        return sessionFactory.getCurrentSession().createQuery("from Task", Task.class).list();
    }
 // Add this method inside your TaskDAO.java class

    @Transactional(readOnly = true)
    public List<Task> getTasksByUserId(int userId) {
        String hql = "from Task where assignedTo.id = :userId";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, Task.class)
                .setParameter("userId", userId)
                .list();
    }
    
}