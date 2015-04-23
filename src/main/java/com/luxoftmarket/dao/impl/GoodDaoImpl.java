package com.luxoftmarket.dao.impl;

import com.luxoftmarket.domain.Good;
import com.luxoftmarket.dao.IGoodDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GoodDaoImpl implements IGoodDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Good good) {
        sessionFactory.getCurrentSession().save(good);
    }

    @Override
    public void edit(Good good) {
        sessionFactory.getCurrentSession().update(good);
    }

    @Override
    public void delete(int id) {
        sessionFactory.getCurrentSession().delete(getGood(id));
    }

    @Override
    public Good getGood(int id) {
        return (Good)sessionFactory.getCurrentSession().get(Good.class, id);
    }

    @Override
    public List getAllGood() {
        return sessionFactory.getCurrentSession().createQuery("from Good").list();
    }
}
