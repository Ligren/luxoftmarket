package com.luxoftmarket.controller;

import com.luxoftmarket.domain.Good;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public interface IGoodController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    String getGoods(Model model);

    @RequestMapping(value = "addGood", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
    String addGood(Model model);

    @RequestMapping(value = "addGood", method = RequestMethod.POST)
    @PreAuthorize("hasRole('admin')")
    String addGood(@ModelAttribute("good") Good good, BindingResult bindingResult);

    @RequestMapping(value = "deleteGood/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('admin')")
    String deleteGood(@PathVariable Integer id);
}
