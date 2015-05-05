package com.luxoftmarket.dao.impl;

import com.luxoftmarket.dao.IGoodDao;
import com.luxoftmarket.domain.Good;
import com.luxoftmarket.validation.GoodValidator;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class GoodDaoImpl implements IGoodDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private GoodValidator goodValidator;

    @Override
    @Transactional
    public void add(Good good) {
        sessionFactory.getCurrentSession().save(good);
    }

    @Override
    @Transactional
    public void edit(Good good) {
        sessionFactory.getCurrentSession().update(good);
    }

    @Override
    @Transactional
    public void delete(int id) {
        sessionFactory.getCurrentSession().delete(getGood(id));
    }

    @Override
    @Transactional
    public Good getGood(int id) {
        return (Good)sessionFactory.getCurrentSession().get(Good.class, id);
    }

    @Override
    @Transactional
    public List getAllGood() {
        return sessionFactory.getCurrentSession().createQuery("from Good").list();
    }

    @Override
    @Transactional
    public Good findGoodByName(String goodname) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Good.class);
        criteria.add(Restrictions.eq("name", goodname));
        return (Good) criteria.uniqueResult();
    }
}
