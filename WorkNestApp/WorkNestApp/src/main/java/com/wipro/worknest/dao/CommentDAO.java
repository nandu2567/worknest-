package com.wipro.worknest.dao;

import com.wipro.worknest.model.Comment;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CommentDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void saveComment(Comment comment) {
        sessionFactory.getCurrentSession().save(comment);
    }
}