/*
In Service layer must be business logic, But, I have not any time to implements her there.
 */

package com.luxoftmarket.service.impl;

import com.luxoftmarket.dao.IGoodDao;
import com.luxoftmarket.domain.Good;
import com.luxoftmarket.service.IGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GoodServiceImpl implements IGoodService {

    @Autowired
    private IGoodDao goodDao;

    @Transactional
    public void add(Good good) {
        goodDao.add(good);
    }

    @Transactional
    public void edit(Good good) {
        goodDao.edit(good);
    }

    @Transactional
    public void delete(int id) {
        goodDao.delete(id);
    }

    @Transactional
    public Good getGood(int id) {
        return goodDao.getGood(id);
    }

    @Transactional
    public List getAllGood() {
        return goodDao.getAllGood();
    }
}
