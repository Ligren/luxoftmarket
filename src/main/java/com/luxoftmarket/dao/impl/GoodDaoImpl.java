package com.luxoftmarket.dao.impl;

import com.luxoftmarket.domain.Good;
import com.luxoftmarket.dao.IGoodDao;
import com.luxoftmarket.validation.GoodValidator;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Component
@Repository
public class GoodDaoImpl implements IGoodDao {

//    @Qualifier("sessionFactory")
    @Autowired
    private SessionFactory sessionFactory;

//    @Qualifier("goodValidator")
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
