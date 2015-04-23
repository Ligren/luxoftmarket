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

    @RequestMapping(value = "/", method = RequestMethod.GET)//��� ������ �� ��������� ��������
    public String index() {
        return "index";
    }
/*
    @RequestMapping(value = "allGoods", method = RequestMethod.GET)//��� ������ �� ��������� ��������
    @PreAuthorize("isAuthenticated()")
    public String allGoods(Model model) {
        List<Good> goods = goodService.getAllGood();
        model.addAttribute("goods", goods);
        return "allGoods"; //���������� �������� index
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

    @RequestMapping(value="buy", method=RequestMethod.GET)
//    @PreAuthorize("isAuthenticated()")
    public String byuStart(Map<String, Object> map) {
        System.out.println("����, �� �� � ��� ������!");
//        testSession();
//        Good good = new Good();
//        map.put("good", good);
        map.put("goodList", goodService.getAllGood());
//        System.out.println(goodService.getAllGood().toString());
//        System.out.println("Here must be goods!");
        return "buy";
    }


//    @RequestMapping(value="byu", method=RequestMethod.POST)
        @RequestMapping(value="/buy.do", method=RequestMethod.POST)
//    @PreAuthorize("isAuthenticated()")
//    public String byuGood(HttpServletRequest req) { //BindingResult bindingResul
        public String byuGood(@ModelAttribute Good good, @RequestParam String namereturn, Map<String, Object> map, HttpServletRequest req) { //BindingResult bindingResul @RequestParam String amount @RequestParam String action , @RequestParam String amount
//        if (action.toLowerCase() != "byu"){
//            return "redirect:/byu";
//        }
//        int amountk=546;
//        Good good = new Good();
        int amountOfGood = 0;//new Integer(amount);
        System.out.println("Here us new amount = " + namereturn);
        System.out.println("Here us name good = " + good.getName());

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
        purchase.put(good, amountOfGood);
//        map.put("goodInBasket", purchase);
        return "buy"; //���������� �������� addBook
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