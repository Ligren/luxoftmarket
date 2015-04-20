package com.luxoftmarket.repository;

import com.luxoftmarket.domain.Good;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Repository
@Transactional // �������� ����������� ���������� � ���� ������ (��� ������ � ��� ������ ����� ���������� ��� ����������)
public class GoodRepository implements IGoodRepository { //��� ����� ��� ����� �������� ����������� ����������� �� ���������� ���������� ��� ������ � ������������



//sessionFactory ����� ��������� � Hibernate, ��� ��� ������������� ���������� �������� � poom.xml ����������� �� Hibernate
        @Autowired
        private SessionFactory sessionFactory;

       @Override
       public void addGood(Good good) {
            this.sessionFactory.getCurrentSession().save(good);
        }

        @Override
        public List<Good> listAll() {
            return this.sessionFactory.getCurrentSession().createQuery("from Good").list();
        }

        @Override
        public void removeGood(Integer id) {
            Good contact = (Good) this.sessionFactory.getCurrentSession().load(
            Good.class, id);
            if (null != contact) {
                this.sessionFactory.getCurrentSession().delete(contact);
            }
        }

    }

