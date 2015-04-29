package com.luxoftmarket.dao.impl;

import com.luxoftmarket.domain.Good;
import com.luxoftmarket.service.impl.GoodServiceImpl;
import junit.framework.TestCase;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;



@RunWith(MockitoJUnitRunner.class)
public class GoodDaoImplTest extends TestCase {

    @Mock
    private SessionFactory sessionFactory;

    @InjectMocks
    GoodDaoImpl s;


    @Test
    public void testAdd() throws Exception {

        Good testGood = new Good();
        s.add(testGood);
    }

    @Test
    public void testEdit() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {

    }

    @Test
    public void testGetGood() throws Exception {

    }

    @Test
    public void testGetAllGood() throws Exception {

    }

    @Test
    public void testFindGoodByName() throws Exception {

    }
}