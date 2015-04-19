package com.luxoftmarket.controller;

import com.luxoftmarket.domain.User;
import com.luxoftmarket.repository.UserRepository;
import com.luxoftmarket.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Controller
public class UserController {

    private UserValidator userValidator;
    private UserRepository userRepository;
    private Logger log = LoggerFactory.getLogger( UserController.class ); // 1. Объявляем переменную логгера

    @Autowired
    public UserController(UserRepository userRepository, UserValidator userValidator){
        this.userRepository = userRepository;
        this.userValidator = userValidator;
    }

/*
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getBooks(Model model) {
        List<User> goods = this.userRepository.listAll();
        model.addAttribute("goods", goods);
        return "index";
    }

*/
    @RequestMapping(value = "addUser", method = RequestMethod.GET)
    @PreAuthorize("isAnonymous()")
    public String addUser(Model model) {
//        log.info( "Some object: {}", object );
        log.info( "-----------------------Some object: {we are in the method add User, requestMethod GET}");
        System.out.println("Here must be log!");

        model.addAttribute("user", new User());
        return "addUser";
    }


    @RequestMapping(value = "addUser", method = RequestMethod.POST)
//    @PreAuthorize("hasRole('admin')")
    public String addUser(@ModelAttribute("user") User user, BindingResult bindingResult) {
        this.userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
           return "addUser";
        }
        System.out.println("Добавляем в контроллере");
        this.userRepository.addUser(user);
        return "redirect:/";
    }
/*
    @RequestMapping(value = "deleteUser/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('admin')")
    public String deleteUser(@PathVariable Integer id) {
        this.userRepository.removeUser(id);
        return "redirect:/";
    }
    */
}