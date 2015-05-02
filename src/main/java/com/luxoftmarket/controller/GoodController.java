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

import com.luxoftmarket.dao.IRoleDao;
import com.luxoftmarket.dao.IUserDao;
import com.luxoftmarket.domain.Good;
import com.luxoftmarket.domain.Role;
import com.luxoftmarket.domain.User;
import com.luxoftmarket.domain.UserStatus;
import com.luxoftmarket.service.IGoodService;
import com.luxoftmarket.validation.GoodValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;

@Controller
public class GoodController {


    @Autowired
    private IGoodService goodService;
    @Autowired
    private GoodValidator goodValidator;
    @Autowired
    IUserDao userDao;
    @Autowired
    IRoleDao roleDao;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
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
                goodService.add(new Good(i, "Товар №" + (rand.nextInt(99) + 1), rand.nextInt(99) + 1, rand.nextInt(99) + 1));
            }
        }
        return "index";
    }


    @RequestMapping(value = {"/admin", "/good.do"}, method = RequestMethod.GET)
//    @PreAuthorize("hasRole('administrator')")
    public String setupForm(Map<String, Object> map, @ModelAttribute Good good) {
        map.put("goodList", goodService.getAllGood());
        map.put("userList", userDao.getAllUsers());
        return "admin";
    }

    @RequestMapping(value = "/good.do", method = RequestMethod.POST)
    public String doAction(@Valid @ModelAttribute Good good, @RequestParam String action, Map<String, Object> map, BindingResult bindingResult) { //BindingResult bindingResult
        goodValidator.validate(good, bindingResult);
        if (bindingResult.hasErrors()) {System.out.println("has errors"); List listError = bindingResult.getAllErrors(); System.out.println("our errors: " + listError.toString()); }
        switch (action.toLowerCase()) {
            case "add":
                if(!bindingResult.hasErrors()){ goodService.add(good); }
                break;
            case "edit":
                if(!bindingResult.hasErrors()) { goodService.edit(good); }
                break;
            case "delete":
                if(good.getId() != null & goodService.getGood(good.getId()) != null) goodService.delete(good.getId());
                break;
            case "search (id or name)":
                Good searchedGood = null;

                if(good.getId() != null) searchedGood = goodService.getGood(good.getId());

                if (searchedGood == null & good.getName() != null) searchedGood = goodService.findGoodByName(good.getName());

                if (searchedGood != null) map.put("good", searchedGood);

                break;
        }
        map.put("goodList", goodService.getAllGood());
        map.put("userList", userDao.getAllUsers());
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
        Map<Good, Integer> purchase = new LinkedHashMap<Good, Integer>();
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
            if (entry.getKey().getId().equals(goodId)) {
                oldAmount = entry.getValue();
                key = entry.getKey();

            }
        }
        if (oldAmount != -1) {
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