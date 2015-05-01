package com.luxoftmarket.dao.impl;

import com.luxoftmarket.domain.Good;
import com.luxoftmarket.dao.IGoodDao;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional
    public Good findGoodByName(String goodname) {
/*        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Good.class);
        criteria.add(Restrictions.eq("name", goodname));
        return (Good) criteria.uniqueResult();*/
        Session tempSession = sessionFactory.getCurrentSession();
        Criteria criteria = tempSession.createCriteria(Good.class);
        SimpleExpression tempExpression = Restrictions.eq("name", goodname);
        criteria.add(tempExpression);
        return (Good) criteria.uniqueResult();
    }
}
