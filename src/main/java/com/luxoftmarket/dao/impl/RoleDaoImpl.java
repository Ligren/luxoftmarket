package com.luxoftmarket.dao.impl;

import com.luxoftmarket.dao.IRoleDao;
import org.hibernate.SessionFactory;
import com.luxoftmarket.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class RoleDaoImpl implements IRoleDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    @Transactional
    public com.luxoftmarket.domain.Role findRole(int roleId) {
        return (Role) sessionFactory.getCurrentSession().get(Role.class, roleId);
    }

    @Override
    @Transactional
    public void addRole(Role role) {
        sessionFactory.getCurrentSession().save(role);
    }
}
