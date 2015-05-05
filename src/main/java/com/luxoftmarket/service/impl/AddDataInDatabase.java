package com.luxoftmarket.service.impl;

import com.luxoftmarket.dao.IGoodDao;
import com.luxoftmarket.dao.IRoleDao;
import com.luxoftmarket.dao.IUserDao;
import com.luxoftmarket.domain.Good;
import com.luxoftmarket.domain.Role;
import com.luxoftmarket.domain.User;
import com.luxoftmarket.domain.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class AddDataInDatabase extends HttpServlet {

    @Autowired
    IGoodDao goodDao;
    @Autowired
    IUserDao userDao;
    @Autowired
    IRoleDao roleDao;

    public void init() throws ServletException {
        if (userDao.findUser(1) == null) {
            roleDao.addRole(new Role("administrator"));
            roleDao.addRole(new Role("user"));
            roleDao.addRole(new Role("tester"));
            List<Role> role = new ArrayList<Role>(3);
            role.add(roleDao.findRole(1));
            role.add(roleDao.findRole(2));
            role.add(roleDao.findRole(3));
            userDao.addUser(new User("Vladyslav", new BCryptPasswordEncoder().encode("bestDeveloper"), "sadkoua@gmail.com", role, UserStatus.ACTIVE));
            Random rand = new Random();
            for (int i = 1; i < 9; i++) {
                goodDao.add(new Good(i, "Товар №" + (rand.nextInt(99) + 1), rand.nextInt(99) + 1, rand.nextInt(99) + 1));
            }
        }
    }
}
