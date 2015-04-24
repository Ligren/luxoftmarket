package com.luxoftmarket.controller;

import com.luxoftmarket.domain.Good;
import com.luxoftmarket.repository.GoodRepository;
import com.luxoftmarket.service.IGoodService;
import com.luxoftmarket.validation.GoodValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@Controller
public class GoodSController {


    @Autowired
    private IGoodService goodService;

    @RequestMapping(value = "/", method = RequestMethod.GET)//при заходе на стартовую страницу
    public String index() {
        return "index";
    }
/*
    @RequestMapping(value = "allGoods", method = RequestMethod.GET)//при заходе на стартовую страницу
    @PreAuthorize("isAuthenticated()")
    public String allGoods(Model model) {
        List<Good> goods = goodService.getAllGood();
        model.addAttribute("goods", goods);
        return "allGoods"; //возвращаем страницу index
    }
*/


//    @RequestMapping(value="/admin", method=RequestMethod.GET)
    @RequestMapping(value="/admin")
//    @PreAuthorize("hasRole('admin')")
    public String setupForm(Map<String, Object> map) {
//        testSession();
        Good good = new Good();
        map.put("good", good);
        map.put("goodList", goodService.getAllGood());
//        System.out.println(goodService.getAllGood().toString());
//        System.out.8println("Here must be goods!");
        return "admin";
    }

    @RequestMapping(value="/good.do", method=RequestMethod.POST)
    public String doAction(@ModelAttribute Good good, @RequestParam String action, Map<String, Object> map) { //BindingResult bindingResult
        Good goodResult = new Good();
        switch(action.toLowerCase()){
            case "add":
                goodService.add(good);
                goodResult = good;
                break;
            case "edit":
                goodService.edit(good);
                goodResult = good;
                break;
            case "delete":
                goodService.delete(good.getId());
                goodResult = new Good();
                break;
            case "search":
                Good searchedGood =  goodService.getGood(good.getId());
                goodResult = searchedGood != null ? searchedGood : new Good();
                break;
        }
        map.put("good", goodResult);
        map.put("goodList", goodService.getAllGood());
        return "admin";
    }

    @RequestMapping(value="/buy", method=RequestMethod.GET)
//    @PreAuthorize("isAuthenticated()")
    public String byuStart(Map<String, Object> map) {
//        System.out.println("Блин, мы не в том методе!");
//        testSession();
//        Good good = new Good();
//        map.put("good", good);
        map.put("goodList", goodService.getAllGood());
        return "buy";
    }


    @RequestMapping(value="buytest", method=RequestMethod.POST)
    public String byuGoodTest(@ModelAttribute("mystring") String mystring) {
        System.out.println("here is my string: " + mystring);
        return "buy";
    }

//    @RequestMapping(value="byu", method=RequestMethod.POST)
        @RequestMapping(value="buy", method=RequestMethod.POST)
//    @PreAuthorize("isAuthenticated()")
//    public String byuGood(HttpServletRequest req) { //BindingResult bindingResul
//        public String byuGood(@ModelAttribute("amount") int amount, @ModelAttribute("good") String good, Map<String, Object> map, HttpServletRequest req) { //BindingResult bindingResul @RequestParam String amount @RequestParam String action , @RequestParam String amount
            public String byuGood(@ModelAttribute("good") String good, Map<String, Object> map, HttpServletRequest req) { //BindingResult bindingResul @RequestParam String amount @RequestParam String action , @RequestParam String amount
//            @ModelAttribute Good good, @RequestParam String action @ModelAttribute("amount") String amount
//        if (action.toLowerCase() != "byu"){
//            return "redirect:/byu";
//        }
//        int amountk=546;
//        Good good = new Good();
        int amountOfGood = 0;//new Integer(amount);
//            System.out.println("Here us new amount 1 = " + amount);
            System.out.println("Here is good id = " + good);
//            int amount2 = new Integer(amount);
//            int amount2 = Integer.parseInt(amount);
//        System.out.println("Here us new amount 2 = " + amount2);
//        System.out.println("Here us name good = " + good);

        HttpSession session = req.getSession();
        Map<Good, Integer> purchase = null;
        Boolean createdNewPurchase = true;
        Enumeration e = session.getAttributeNames(); //        session.getAttribute( "theName" );
        while (e.hasMoreElements()) {
            String namef = (String) e.nextElement();
            if (namef.equals("goodInBasket")) {
                createdNewPurchase = false;
                purchase = (Map)session.getAttribute( "goodInBasket" );;
            }
        }

        if (createdNewPurchase = true) {
            Map<Good, Integer> inPurchase = new HashMap<Good, Integer>();
            session.setAttribute("goodInBasket", inPurchase);
            purchase = inPurchase;
        }
//        purchase.put(good, amountOfGood);
//        map.put("goodInBasket", purchase);
        return "buy"; //возвращаем страницу addBook
    }


  /*  @RequestMapping(value="/good.do", method=RequestMethod.POST)
    public String byu(@ModelAttribute Good good, BindingResult bindingResult, @RequestParam String action, Map<String, Object> map) {
        Good goodResult = new Good();
        switch(action.toLowerCase()){
            case "add":
                goodService.add(good);
                goodResult = good;
                break;
            case "edit":
                goodService.edit(good);
                goodResult = good;
                break;
            case "delete":
                goodService.delete(good.getId());
                goodResult = new Good();
                break;
            case "search":
                Good searchedGood =  goodService.getGood(good.getId());
                goodResult = searchedGood != null ? searchedGood : new Good();
                break;
        }
        map.put("good", goodResult);
        map.put("goodInBasket", goodService.getAllGood());
        return "admin";
    }
*/


}
