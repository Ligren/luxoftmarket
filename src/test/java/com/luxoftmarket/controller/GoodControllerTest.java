package com.luxoftmarket.controller;

import com.luxoftmarket.domain.Good;
import com.luxoftmarket.repository.IGoodRepository;
import com.luxoftmarket.validation.GoodValidator;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class GoodControllerTest {

    GoodValidator mockGoodValidator;
    IGoodController mockGoodController;
    IGoodRepository mockGoodRepository;
    Model mockModel;
    String testReturnValue;
    Good mockGood;
    BindingResult mockBindingResult;

    @Before
    public void setUpMethod() throws Exception {
        mockModel = mock(Model.class);
        mockGoodRepository = mock(IGoodRepository.class);
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
//        testReturnValue = "index";

        when(mockGoodRepository.listAll()).thenReturn(new ArrayList<Good>(5));

        String url = new GoodController(mockGoodRepository, mockGoodValidator).getGoods(mockModel);

        mockModel.addAttribute("goods", mockGood);

        verify(mockGoodRepository).listAll(); //должны что-либо получить от метода
        verify(mockModel).addAttribute("goods", mockGood); //должны установить значение методу

        assertEquals("index", url);
/*
when(mockGoodRepository.listAll()).thenReturn();
потом controller (не МОК а реальный контроллер).getGoods(mockModel)
а потом verify(mockGoodRepository).listAll();
        verify(mockModel).addAttribute("goods", mockGood);
 */
    }

    @Test
    public void testAddGoodGet() throws Exception {
        /*
            public String addGood(Model model) {
            model.addAttribute("good", new Good());
            return "addGood";
            }
         */

        mockModel.addAttribute("goods", mockModel);
        verify(mockModel).addAttribute("goods", mockModel);

        String url = new GoodController(mockGoodRepository, mockGoodValidator).addGood(mockModel);

        assertEquals("addGood", url);
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

        mockGoodValidator.validate(mockGood, mockBindingResult);
        verify(mockGoodValidator).validate(mockGood, mockBindingResult);

        mockBindingResult.hasErrors();
        verify(mockBindingResult).hasErrors();

        when(mockBindingResult.hasErrors()).thenReturn(true).thenReturn(false);


        String urlHasError = new GoodController(mockGoodRepository, mockGoodValidator).addGood(new Good(), mockBindingResult);
        assertEquals("addGood", urlHasError);

        String urlNoError = new GoodController(mockGoodRepository, mockGoodValidator).addGood(new Good(), mockBindingResult);
        assertEquals("redirect:/", urlNoError);
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
        int id = 5;


        String url = new GoodController(mockGoodRepository, mockGoodValidator).deleteGood(id);

//        mockGoodRepository.removeGood(id);
        verify(mockGoodRepository).removeGood(id);
//       assertEquals(5, mockGoodRepository.);
        verifyZeroInteractions(mockGoodRepository);

        assertEquals("redirect:/", url);
    }
}