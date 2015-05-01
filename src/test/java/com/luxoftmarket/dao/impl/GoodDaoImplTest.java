package com.luxoftmarket.dao.impl;

import com.luxoftmarket.domain.Good;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Criterion;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertSame;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class GoodDaoImplTest {

    @Mock
    @Autowired
    private SessionFactory sessionFactory;


    @InjectMocks
    GoodDaoImpl s;


    @Test
    public void testAdd() throws Exception {
        final Good testGood = new Good();
        Session mockSession = mock(Session.class, withSettings().serializable());

        doReturn(mockSession).when(sessionFactory).getCurrentSession();
        doReturn(testGood).when(mockSession).save(any(Good.class));

        s.add(testGood);

        verify(sessionFactory).getCurrentSession();
        verify(mockSession).save(same(testGood));
    }

    @Test
    public void testEdit() throws Exception {
        final Good testGood = new Good();
        Session mockSession = mock(Session.class);

        doReturn(mockSession).when(sessionFactory).getCurrentSession();
        doNothing().when(mockSession).update(any(Good.class));

        s.edit(testGood);

        verify(mockSession).update(testGood);

    }

    @Test
    public void testDelete() throws Exception {
        GoodDaoImpl testS = spy(s);
        Good testGood = new Good();
        Session mockSession = mock(Session.class);

        doReturn(mockSession).when(sessionFactory).getCurrentSession();
        when(testS.getGood(anyInt())).thenReturn(testGood);
        doNothing().when(mockSession).delete(testGood);

        testS.delete(5);

        verify(sessionFactory, times(3)).getCurrentSession();
        verify(testS).getGood(5);
        verify(mockSession).delete(any(Good.class));
    }


    @Test
    public void testGetGood() throws Exception {

        Good toBeReturned = new Good();
        Session mockSession = mock(Session.class);
        doReturn(mockSession).when(sessionFactory).getCurrentSession();
        doReturn(toBeReturned).when(mockSession).get(same(Good.class), anyInt());

        Good testGood = s.getGood(5);

        verify(sessionFactory).getCurrentSession();
        verify(mockSession).get(Good.class, 5);

        assertSame(toBeReturned, testGood);
    }

    @Test
    public void testGetAllGood() throws Exception {
        List toBeReturned = Arrays.asList(new Good());
        Session mockSession = mock(Session.class);
        Query mockQuery = mock(Query.class);

        doReturn(mockSession).when(sessionFactory).getCurrentSession();
        doReturn(mockQuery).when(mockSession).createQuery("from Good");
        doReturn(toBeReturned).when(mockQuery).list();

        List testList = s.getAllGood();

        verify(sessionFactory).getCurrentSession();
        verify(mockSession).createQuery("from Good");
        verify(mockQuery).list();

        assertSame(toBeReturned, testList);
    }


    @Test
    public void testFindGoodByName() throws Exception {
        Good toBeReturned = new Good();
        Session mockSession = mock(Session.class);
        Criteria mockCriteria = mock(Criteria.class);
        Criterion mockCriterion = mock(Criterion.class);

        doReturn(mockSession).when(sessionFactory).getCurrentSession();
        doReturn(mockCriteria).when(mockSession).createCriteria(Good.class);
        doReturn(toBeReturned).when(mockCriteria).uniqueResult();
        doReturn(mockCriteria).when(mockCriteria).add(mockCriterion);

        Good testGood = s.findGoodByName("bobrik");

        verify(sessionFactory).getCurrentSession();
        verify(mockSession).createCriteria(eq(Good.class));
        verify(mockCriteria).uniqueResult();
        verify(mockCriteria).uniqueResult();

        assertSame(toBeReturned, testGood);

    }
}