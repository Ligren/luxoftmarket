package com.luxoftmarket.repository;
//DAO component
import com.luxoftmarket.domain.Good;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Repository
@Transactional // �������� ����������� ���������� � ���� ������ (��� ������ � ��� ������ ����� ���������� ��� ����������)
public class GoodRepository { //��� ����� ��� ����� �������� ����������� ����������� �� ���������� ���������� ��� ������ � ������������



//sessionFactory ����� ��������� � Hibernate, ��� ��� ������������� ���������� �������� � poom.xml ����������� �� Hibernate
        @Autowired
        private SessionFactory sessionFactory;

       public void addGood(Good good) {
            this.sessionFactory.getCurrentSession().save(good);
        }

    public void editGood(Good good) { }

       public List<Good> listAll() {
            return this.sessionFactory.getCurrentSession().createQuery("from Good").list();
        }


       public void removeGood(Integer id) {
            Good contact = (Good) this.sessionFactory.getCurrentSession().load(
            Good.class, id);
            if (null != contact) {
                this.sessionFactory.getCurrentSession().delete(contact);
            }
        }

    }

