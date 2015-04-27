/*
http://devcolibri.com/2890 подключение js и css
https://www.youtube.com/watch?v=rdYQOqxq9F0 CRUD Spring MVC, Hibarnate, Eclipse
https://www.youtube.com/watch?v=vR6jYVEMJS0 Spring security
https://www.youtube.com/watch?v=dO883S85d_k IDEA GitHub key
https://spring.io/blog/2012/04/06/migrating-to-spring-3-1-and-hibernate-4-1
https://www.youtube.com/watch?v=4DDuIjJBj-o start in web on Russian
http://stackoverflow.com/questions/2237537/which-maven-dependencies-to-include-for-spring-3-0 all Spring dependency
https://www.youtube.com/watch?v=9GdtWiovvIQ хорошо объяснено про инкапсуляцию
http://www.site-do.ru/db/sql1.php sql statement info
validation and loging
http://www.journaldev.com/2668/spring-mvc-form-validation-example-using-annotation-and-custom-validator-implementation
http://www.h2database.com/html/cheatSheet.html h2
http://trails.codehaus.org/DatabaseConfigurations info about db
http://www.developermemo.com/1073224/ databases in memory
http://habrahabr.ru/post/203318/ spring security
https://www.jetbrains.com/idea/features/unit_testing_and_coverage.html junit test with idea
http://habrahabr.ru/post/111102/ spring security
http://www.mkyong.com/spring-mvc/spring-mvc-log4j-integration-example/ log4j
https://vimeo.com/58008858 unit test (JUnit, Mockito)
http://mrbool.com/how-to-create-a-login-application-using-spring-mvc-in-java/27113 login application
http://habrahabr.ru/post/243155/ Unit test Mockito
session http://stackoverflow.com/questions/4931323/whats-the-difference-between-getrequesturi-and-getpathinfo-methods-in-httpservl
session http://docs.oracle.com/javaee/6/api/javax/servlet/http/HttpServletRequest.html
session http://www.java2s.com/Code/JavaAPI/javax.servlet.http/HttpSessionsetAttributeStringkeyObjectvalue.htm
*/

package com.luxoftmarket.controller;

import com.luxoftmarket.domain.Good;
import com.luxoftmarket.service.IGoodService;
import com.luxoftmarket.service.IUserService;
import com.luxoftmarket.validation.GoodValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class GoodController {



    @Autowired
    private IGoodService goodService;
    @Autowired
    private IUserService userService;
    private GoodValidator goodValidator;

    @RequestMapping(value = "/", method = RequestMethod.GET)//при заходе на стартовую страницу
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/admin")
//    @PreAuthorize("hasRole('admin')")
    public String setupForm(Map<String, Object> map) {
        Good good = new Good();
        map.put("good", good);
        map.put("goodList", goodService.getAllGood());
        map.put("userList", userService.getAllUser());
        return "admin";
    }

/*    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
//    @PreAuthorize("hasRole('admin')")
    public String addUser() {
        return "addUser";
    }
    */

    @RequestMapping(value = "/good.do", method = RequestMethod.POST)
    public String doAction(@ModelAttribute Good good, @RequestParam String action, Map<String, Object> map) { //BindingResult bindingResult
//        Good goodResult = new Good();
        switch (action.toLowerCase()) {
            case "add":
                goodService.add(good);
//                goodResult = good;
                break;
            case "edit":
                goodService.edit(good);
//                goodResult = good;
                break;
            case "delete":
                goodService.delete(good.getId());
//                goodResult = new Good();
                break;
            case "search by id":
                Good searchedGood = goodService.getGood(good.getId());
                map.put("good", searchedGood != null ? searchedGood : new Good());

                break;
        }
//        map.put("good", goodResult);
        map.put("goodList", goodService.getAllGood());
        return "admin";
    }

    @RequestMapping(value = "/buy", method = RequestMethod.GET)
//    @PreAuthorize("isAuthenticated()")
    public String byuStart(Map<String, Object> map) {
        map.put("goodList", goodService.getAllGood());
        return "buy";
    }


    @RequestMapping(value = "buy", method = RequestMethod.POST)
//    @PreAuthorize("isAuthenticated()")
    public String byuGood(Map<String, Object> map, HttpServletRequest req) { //BindingResult bindingResul @RequestParam String amount @RequestParam String action , @RequestParam String amount

        int amount = Integer.parseInt(req.getParameter("amount"));
        int goodId = Integer.parseInt(req.getParameter("good"));

        HttpSession session = req.getSession();
        Map<Good, Integer> purchase =  new LinkedHashMap<Good, Integer>();
        Boolean createdNewPurchase = true;
        Enumeration e = session.getAttributeNames();
        while (e.hasMoreElements()) {
            String nameSessionAtribute = (String) e.nextElement();
            if (nameSessionAtribute.equals("goodInBasket")) {
                createdNewPurchase = false;
                purchase = (Map) session.getAttribute("goodInBasket");
            }
        }
        if (createdNewPurchase) {
            session.setAttribute("goodInBasket", purchase);
        }

        int oldAmount = -1;
        Good key = null;
        for (Map.Entry<Good, Integer> entry : purchase.entrySet()) {
            if(entry.getKey().getId().equals(goodId)) {
                oldAmount = entry.getValue();
                key = entry.getKey();

            }
        }
        if(oldAmount != -1) {
            purchase.put(key, oldAmount + amount);

        } else {
            purchase.put(goodService.getGood(goodId), amount);
        }

        Good goodInStore = goodService.getGood(goodId);
        goodInStore.setAmount(goodInStore.getAmount() - amount);
        goodService.edit(goodInStore);

        map.put("goodList", goodService.getAllGood());
        req.setAttribute("goodInBasket", purchase);

        return "buy";
    }
}