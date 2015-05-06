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

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class AddDataInDatabase {

    @Autowired
    IGoodDao goodDao;
    @Autowired
    IUserDao userDao;
    @Autowired
    IRoleDao roleDao;

    @PostConstruct
    public void init() throws ServletException {
        if (userDao.findUser(1) == null) {
            List<Role> rolesForAdmin = new ArrayList<Role>(3);
            String[] roles = {"administrator", "user", "tester"};
            for (String roleName : roles) {
                Role roleTemp = new Role(roleName);
                roleDao.addRole(roleTemp);
                rolesForAdmin.add(roleTemp);
            }
            userDao.addUser(new User("Vladyslav", new BCryptPasswordEncoder().encode("bestDeveloper"), "sadkoua@gmail.com", rolesForAdmin, UserStatus.ACTIVE));
            Random rand = new Random();
            for (int i = 1; i < 9; i++) {
                goodDao.add(new Good(i, "Товар №" + (rand.nextInt(99) + 1), rand.nextInt(99) + 1, rand.nextInt(99) + 1));
            }
        }
    }
}

