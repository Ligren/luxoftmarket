package com.luxoftmarket.dao.impl;

import com.luxoftmarket.dao.IUserDao;
import com.luxoftmarket.domain.User;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserDaoImpl implements IUserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @Transactional
    public void editUser(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    @Transactional
    public void deleteUser(int userId) {
        sessionFactory.getCurrentSession().delete(findUser(userId));
    }

    @Override
    @Transactional
    public User findUser(int userId) {
        return (User) sessionFactory.getCurrentSession().get(User.class, userId);
    }

    @Override
    @Transactional
    public User findUserByName(String username) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("nick", username));
        return (User) criteria.uniqueResult();
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return sessionFactory.getCurrentSession().createQuery("from User").list();
    }
}
