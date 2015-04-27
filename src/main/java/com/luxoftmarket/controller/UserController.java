package com.luxoftmarket.controller;

import com.luxoftmarket.domain.User;
import com.luxoftmarket.service.IUserService;
import com.luxoftmarket.validation.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    private UserValidator userValidator;
//    private UserRepository userRepository;
    private Logger log = LoggerFactory.getLogger( UserController.class ); // 1. Объявляем переменную логгера

    @Autowired
    IUserService userService;


//    @Autowired
//    public UserController(UserRepository userRepository, UserValidator userValidator){
//        this.userRepository = userRepository;

//        this.userValidator = userValidator;
//    }

    @RequestMapping(value = "addUser", method = RequestMethod.GET)
    @PreAuthorize("isAnonymous()")
    public String addUser(Model model) {
        log.info( "-----------------------Some object: {we are in the method add User, requestMethod GET}");
        model.addAttribute("user", new User());
        return "addUser";
    }


    @RequestMapping(value = "addUser", method = RequestMethod.POST)
//    @PreAuthorize("hasRole('admin')")
    public String addUser(@ModelAttribute("user") User user, BindingResult bindingResult) {
//        public String addUser() {
//        this.userValidator.validate(user, bindingResult);
//        if (bindingResult.hasErrors()) {
//           return "addUser";
//        }
//        this.userRepository.addUser(user);
//        userService.add(user);
        userService.add(user);
        return "redirect:/";
    }
}