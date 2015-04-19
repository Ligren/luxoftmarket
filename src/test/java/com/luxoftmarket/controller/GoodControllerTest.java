package com.luxoftmarket.controller;

import com.luxoftmarket.domain.Good;
import com.luxoftmarket.repository.GoodRepository;
import com.luxoftmarket.validation.GoodValidator;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.invocation.MockHandler;
import org.mockito.mock.MockCreationSettings;
import org.mockito.plugins.MockMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;


public class GoodControllerTest {
    @Autowired
    GoodValidator mockGoodValidator;
    GoodRepository mockGoodRepository;
    Model mockModel;
    String testReturnValue;
    IGoodController mockGoodController;
    Good mockGood;

    @Before
    public  void setUpMethod() throws Exception {
        mockModel = mock(Model.class);
        mockGoodRepository = mock(GoodRepository.class);
        mockGoodValidator = mock(GoodValidator.class);
        mockGoodController = mock(GoodController.class);
        mockGood = mock(Good.class);

    }

    @Test
    public void testGetGoods() throws Exception {
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
        testReturnValue = "addGood";
        mockModel.addAttribute("good", mockGood);

        assertEquals(testReturnValue, mockGoodController.addGood(mockModel));
    }

    @Test
    public void testAddGoodPost() throws Exception {
        /*
            public String addGood(@ModelAttribute("good") Good good, BindingResult bindingResult) {
            this.goodValidator.validate(good, bindingResult);
            if (bindingResult.hasErrors()) {
        return "addGood";
        this.goodRepository.addGood(good);
            return "redirect:/";
         */
        testReturnValue = "addGood";
        BindingResult mockBindingResult = mock(BindingResult.class);
        when(mockBindingResult.hasErrors()).thenReturn(true).thenReturn(false);

        assertEquals(testReturnValue, mockGoodController.addGood(mockGood, mockBindingResult));
    }

    @Test
    public void testDeleteGood() throws Exception {

    }
}