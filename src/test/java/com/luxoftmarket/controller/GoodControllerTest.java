package com.luxoftmarket.controller;

import com.luxoftmarket.domain.Good;
import com.luxoftmarket.repository.GoodRepository;
import com.luxoftmarket.validation.GoodValidator;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class GoodControllerTest {

    GoodValidator mockGoodValidator;
    IGoodController mockGoodController;
    GoodRepository mockGoodRepository;
    Model mockModel;
    String testReturnValue;
    Good mockGood;
    BindingResult mockBindingResult;

    @Before
    public void setUpMethod() throws Exception {
        mockModel = mock(Model.class);
        mockGoodRepository = mock(GoodRepository.class);
        mockGoodValidator = mock(GoodValidator.class);
//        mockGoodController = mock(GoodController.class);
        mockGoodController = new GoodController(mockGoodRepository, mockGoodValidator);
        mockGood = mock(Good.class);
        mockBindingResult = mock(BindingResult.class);
        when(mockBindingResult.hasErrors()).thenReturn(true).thenReturn(false);

    }

    @Test
    public void testGetGoods() throws Exception {
        /*
            public String getGoods(Model model) {
            List<Good> goods = this.goodRepository.listAll();
            model.addAttribute("goods", goods);
            return "index";
         */
        testReturnValue = "index";
        List<Good> testCollection = new ArrayList<Good>();
        testCollection.add(mockGood);
        testCollection.add(mockGood);

        when(mockGoodRepository.listAll()).thenReturn(testCollection);

        mockGoodRepository.listAll();

        assertEquals(testReturnValue, mockGoodController.getGoods(mockModel));
    }

    @Test
    public void testAddGoodGet() throws Exception {
        /*
            public String addGood(Model model) {
            model.addAttribute("good", new Good());
            return "addGood";
            }
         */
        testReturnValue = "addGood";
        mockModel.addAttribute("good", mockGood);

        assertEquals(testReturnValue, mockGoodController.addGood(mockModel));
    }

    @Test
    public void testAddGoodPostHasError() throws Exception {
        /*
            public String addGood(@ModelAttribute("good") Good good, BindingResult bindingResult) {
            this.goodValidator.validate(good, bindingResult);
            if (bindingResult.hasErrors()) {
            return "addGood";
        }
            this.goodRepository.addGood(good);
            return "redirect:/";
        }
         */
        testReturnValue = "addGood";

        mockGoodController.addGood(mockGood, mockBindingResult);
        if (mockBindingResult.hasErrors()) {
            assertEquals(testReturnValue, mockGoodController.addGood(mockGood, mockBindingResult));
        }
        }

    @Test
    public void testAddGoodPostNotHasError() throws Exception {

        testReturnValue = "addGood";

        mockGoodController.addGood(mockGood, mockBindingResult);

        testReturnValue = "redirect:/";
        if (mockBindingResult.hasErrors()) {
            mockGoodRepository.addGood(mockGood);
            assertEquals(testReturnValue, mockGoodController.addGood(mockGood, mockBindingResult));
        }
    }

    @Test
    public void testDeleteGood() throws Exception {
        /*
            @RequestMapping(value = "deleteGood/{id}", method = RequestMethod.GET)
            public String deleteGood(@PathVariable Integer id) {
            this.goodRepository.removeGood(id);
            return "redirect:/";
    }
         */
        testReturnValue = "redirect:/";
        int testId = new Random().nextInt();
        mockGoodRepository.removeGood(testId);
        assertEquals(testReturnValue, mockGoodController.deleteGood(testId));
    }
}