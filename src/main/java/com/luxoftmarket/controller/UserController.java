package com.luxoftmarket.controller;

import com.luxoftmarket.dao.IRoleDao;
import com.luxoftmarket.dao.IUserDao;
import com.luxoftmarket.domain.Role;
import com.luxoftmarket.domain.User;
import com.luxoftmarket.domain.UserStatus;
import com.luxoftmarket.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private IUserDao userDao;
    @Autowired
    private IRoleDao roleDao;
    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "addUser", method = RequestMethod.GET)
    @PreAuthorize("isAnonymous()")
    public String addUser() {
        return "addUser";
    }

    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    @PreAuthorize("isAnonymous()")
    public String addUser(@ModelAttribute("user") User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "addUser";

        } else {

            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            List<Role> role = new ArrayList<>(1);
            role.add(roleDao.findRole(2)); //set "user" role for user

            user.setRoles(role);
            user.setStatus(UserStatus.ACTIVE);
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            userDao.addUser(user);

            return "redirect:/";
        }
    }
}