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
*/
package com.luxoftmarket.controller;

import com.luxoftmarket.domain.Good;
import com.luxoftmarket.repository.GoodRepository;
import com.luxoftmarket.validation.GoodValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class GoodController {

    private GoodValidator goodValidator; //создаем зависимость на валидатор
    private GoodRepository goodRepository;

    @Autowired
    public GoodController(GoodRepository goodRepository, GoodValidator goodValidator){ // валидатор необходимо прописать в конструкторе
//            this.userRepository = userRepository;
        this.goodRepository = goodRepository;
        this.goodValidator = goodValidator;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)//при заходе на стартовую страницу
    public String getBooks(Model model) {
//        System.out.println("-----we are on controller in the index page");
          List<Good> goods = this.goodRepository.listAll();
        model.addAttribute("goods", goods);
//        model.addAttribute("message", "new my massage");
        return "index"; //возвращаем страницу index
    }

    @RequestMapping(value = "addGood", method = RequestMethod.GET)// при вызове метода c URL addBook c requestMethod GET --- нажали на ссылку
    @PreAuthorize("isAuthenticated()")
    public String addGood(Model model) {                        //возвращает страницу addBook.jsp в которой будет форма для добавления новой книги с кнопкой add book
        model.addAttribute("good", new Good());
       return "addGood"; //возвращаем страницу addBook
    }

    @RequestMapping(value = "addGood", method = RequestMethod.POST) // метод c URL addBook c requestMethod POST ---- нажали на кнопку
//    @PreAuthorize("isAuthenticated()")
    @PreAuthorize("hasRole('admin')")
    public String addGood(@ModelAttribute("good") Good good, BindingResult bindingResult) { //будет добавлять новую книгу в базу данных, добавили Binding Result который будет хранить в себе ошибки
            this.goodValidator.validate(good, bindingResult);                                   //валидации
            if (bindingResult.hasErrors()) { //перед сохранением книги в базу, проверяем, с помощью созданного валидатора, нашу модель на предмет ошибок ? и все ошибки записываются в Binding Result
        return "addGood"; // если есть ошибки, возвращаем ту-же самую вьюху
    }
        this.goodRepository.addGood(good);
            return "redirect:/"; //переходим на страницу главную /
        }

    @RequestMapping(value = "deleteGood/{id}", method = RequestMethod.GET) // --- нажали на ссылку
    @PreAuthorize("hasRole('admin')")
    public String deleteGood(@PathVariable Integer id) {
            this.goodRepository.removeGood(id);
        return "redirect:/";
    }

//    @RequestMapping(value="/logout", method = RequestMethod.GET)
//    public String logout(ModelMap model) {
//        System.out.println("logout string");
//        return "index";
//
//    }
}
