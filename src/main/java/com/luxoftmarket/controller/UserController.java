package com.luxoftmarket.controller;

import com.luxoftmarket.dao.IRoleDao;
import com.luxoftmarket.dao.IUserDao;
import com.luxoftmarket.domain.Role;
import com.luxoftmarket.domain.User;
import com.luxoftmarket.domain.UserStatus;
import com.luxoftmarket.validation.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private UserValidator userValidator;
    private Logger log = LoggerFactory.getLogger(UserController.class); // 1. Объявляем переменную логгера

    @Autowired
    IUserDao userDao;
    @Autowired
    IRoleDao roleDao;

    @RequestMapping(value = "addUser", method = RequestMethod.GET)
//    @PreAuthorize("isAnonymous()")
    public String addUser() {
        log.info("-----------------------Some object: {we are in the method add User, requestMethod GET}");
        return "addUser";
    }


    @RequestMapping(value = "addUser", method = RequestMethod.POST)
//    @PreAuthorize("hasRole('admin')")
    public String addUser(@ModelAttribute("user") User user, BindingResult bindingResult, HttpServletRequest req) {
        HttpSession session = req.getSession();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        List<Role> role = new ArrayList<Role>(1);
        role.add(roleDao.findRole(2));

        user.setRoles(role);
        user.setStatus(UserStatus.ACTIVE);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userDao.addUser(user);

        return "redirect:/";
    }
}