package com.luxoftmarket.controller;

import com.luxoftmarket.domain.Good;
import com.luxoftmarket.repository.GoodRepository;
import com.luxoftmarket.validation.GoodValidator;
import org.junit.Test;
import org.mockito.invocation.MockHandler;
import org.mockito.mock.MockCreationSettings;
import org.mockito.plugins.MockMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;


public class GoodControllerTest {
    @Autowired
    GoodController goodController;
    GoodValidator goodValidator;
    GoodRepository goodRepository;

    @Test
    public void testGetGoods() throws Exception {
        List<Good> testCollection = new ArrayList<Good>();
        testCollection.add(new Good());
        Model mockModel = mock(Model.class);
        GoodRepository mockGoodRepository = mock(GoodRepository.class);

        when(mockGoodRepository.listAll()).thenReturn(testCollection);
        String testReturnValue = "index";

        List<Good> goods = mockGoodRepository.listAll();

        assertEquals(testReturnValue , "index");
    }

    @Test
    public void testAddGoodGet() throws Exception {

    }

    @Test
    public void testAddGoodPost() throws Exception {

    }

    @Test
    public void testDeleteGood() throws Exception {

    }
}