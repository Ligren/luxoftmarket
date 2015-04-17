package com.luxoftmarket.controller;

import com.luxoftmarket.domain.Good;
import com.luxoftmarket.domain.User;
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
    /*
 http://devcolibri.com/2890 ����������� js � css
 https://www.youtube.com/watch?v=rdYQOqxq9F0 CRUD Spring MVC, Hibarnate, Eclipse
 https://www.youtube.com/watch?v=vR6jYVEMJS0 Spring security
 https://www.youtube.com/watch?v=dO883S85d_k IDEA GitHub key
 https://spring.io/blog/2012/04/06/migrating-to-spring-3-1-and-hibernate-4-1
 https://www.youtube.com/watch?v=4DDuIjJBj-o start in web on Russian
 http://stackoverflow.com/questions/2237537/which-maven-dependencies-to-include-for-spring-3-0 all Spring dependency
 https://www.youtube.com/watch?v=9GdtWiovvIQ ������ ��������� ��� ������������


 */

    private GoodValidator goodValidator; //������� ����������� �� ���������
    private GoodRepository goodRepository;

    @Autowired
    public GoodController(GoodRepository goodRepository, GoodValidator goodValidator){ // ��������� ���������� ��������� � ������������
//            this.userRepository = userRepository;
        this.goodRepository = goodRepository;
        this.goodValidator = goodValidator;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)//��� ������ �� ��������� ��������
    public String getBooks(Model model) {
//        System.out.println("-----we are on controller in the index page");
          List<Good> goods = this.goodRepository.listAll();
//        model.addAttribute("message", "new my massage");
        return "index"; //���������� �������� index
    }

    @RequestMapping(value = "addGood", method = RequestMethod.GET)// ��� ������ ������ c URL addBook c requestMethod GET --- ������ �� ������
    @PreAuthorize("isAuthenticated()")
    public String addGood(Model model) {                        //���������� �������� addBook.jsp � ������� ����� ����� ��� ���������� ����� ����� � ������� add book
//            model.addAttribute("book", new Book());
        return "addGood"; //���������� �������� addBook
    }

    @RequestMapping(value = "addGood", method = RequestMethod.POST) // ����� c URL addBook c requestMethod POST ---- ������ �� ������
    @PreAuthorize("isAuthenticated()")
    public String addGood(@ModelAttribute("good") User user, BindingResult bindingResult) { //����� ��������� ����� ����� � ���� ������, �������� Binding Result ������� ����� ������� � ���� ������
//            this.bookValidator.validate(book, bindingResult);                                   //���������
//            if (bindingResult.hasErrors()) { //����� ����������� ����� � ����, ���������, � ������� ���������� ����������, ���� ������ �� ������� ������ ? � ��� ������ ������������ � Binding Result
        return "addGood"; // ���� ���� ������, ���������� ��-�� ����� �����
    }
//        System.out.println("����: " + book.getGenre() + ", ����� " + book.getName());
//            this.bookRepository.addBook(book);
//            return "redirect:/"; //��������� �� �������� ������� /
//        }

    @RequestMapping(value = "deleteGood/{id}", method = RequestMethod.GET) // --- ������ �� ������
    @PreAuthorize("hasRole('admin')")
    public String deleteGood(@PathVariable Integer id) {
//            this.bookRepository.removeBook(id);
        return "redirect:/";
    }

//    @RequestMapping(value="/logout", method = RequestMethod.GET)
//    public String logout(ModelMap model) {
//        System.out.println("logout string");
//        return "index";
//
//    }
}
