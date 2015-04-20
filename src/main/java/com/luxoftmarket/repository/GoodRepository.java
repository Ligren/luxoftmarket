package com.luxoftmarket.repository;

import com.luxoftmarket.domain.Good;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Repository
@Transactional // включает возможность транзакции в этом классе (все методы в это классе будут выполнятся как транзакция)
public class GoodRepository implements IGoodRepository { //для этого нам нужно добавить необходимые зависимости на Спринговые библиотеки для работы с транзакциями



//sessionFactory класс находится в Hibernate, для его использования необходимо добавить в poom.xml зависимости на Hibernate
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

