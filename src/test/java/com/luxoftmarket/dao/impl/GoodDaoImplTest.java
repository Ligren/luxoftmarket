package com.luxoftmarket.dao.impl;

import com.luxoftmarket.domain.Good;
import org.hibernate.Criteria;
//import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class GoodDaoImplTest {

    @Mock
@Autowired
    private SessionFactory sessionFactory;


    @InjectMocks
    GoodDaoImpl s;


    @Test//(expected = Exception.class)
    public void testAdd() throws Exception {
/*public void add(Good good) {sessionFactory.getCurrentSession().save(good); } */
        final Good testGood = new Good();
//        Session mockSession = mock(Session.class, withSettings().serializable());
        Session mockSession = mock(Session.class, withSettings().serializable());

        doReturn(mockSession).when(sessionFactory).getCurrentSession();
        doAnswer(new Answer<Object>() {
            public Object answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                if (args[0].equals(testGood)) {
                    return "testGood";
                } else {
                    return "else";
                }
            }
        }).when(mockSession).save(any(Good.class));

        s.add(testGood);

        verify(sessionFactory).getCurrentSession();
        verify(mockSession).save(eq(testGood));

        assertEquals("testGood", mockSession.save(testGood)); // Serializable
    }

    @Test(expected = RuntimeException.class)
    public void testEdit() throws Exception {
//        public void edit(Good good) { sessionFactory.getCurrentSession().update(good);}
        /*//        public void edit(Good good) {
         sessionFactory.getCurrentSession().update(good);}
          */
        final Good testGood = new Good();
        Session mockSession = mock(Session.class);
        doReturn(mockSession).when(sessionFactory).getCurrentSession();
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                    throw new RuntimeException();
            }
        }).when(mockSession).update(any(Good.class));
//        doThrow(new Exception()).when(sessionFactory).getCurrentSession().update(any(Session.class));
//        doNothing().when(sessionFactory).getCurrentSession().update(any(Good.class));

//        Good testGood = new Good();
        s.edit(testGood);

        verify(sessionFactory).getCurrentSession().update(same(testGood));
//        assertEquals("testGood", mockSession.update(testGood)); //void type not allowed here
//        assertEquals("testGood", mockSession.update(testGood));
    }




    /*        public void delete(int id) {
                Good delGood = getGood(id);
                sessionFactory.getCurrentSession().delete(delGood);}
 */
    @Test//(expected = Exception.class)
    public void testDelete() throws Exception {

/*        public void delete(int id) {
Session tempSession = sessionFactory.getCurrentSession();
Good tempGood = getGood(id);
tempSession.delete(getGood(id));
 */
        GoodDaoImpl ses = mock(GoodDaoImpl.class);
        Good mockGood = mock(Good.class);
        Good testGood = new Good();
        Session mockSession = mock(Session.class);
        doReturn(mockSession).when(sessionFactory).getCurrentSession();
//        doReturn(testGood).when(ses).getGood(anyInt());
        doNothing().when(mockSession).delete(testGood);

//        doThrow(new Exception()).when(sessionFactory).getCurrentSession().delete(any(Good.class));

        s.delete(5);

//        verify(s).getGood(eq(5));
        verify(sessionFactory, times(2)).getCurrentSession();
//        verify(s).getGood(eq(5));
//        verify(mockSession).delete(testGood);
//        verify(sessionFactory).getCurrentSession().delete(eq(5));

    }

    /*        public Good getGood(int id) {
                    Session session = sessionFactory.getCurrentSession();
                    return (Good) session.get(Good.class, id);
     }

     */
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


/*        public List getAllGood() {
            return sessionFactory.getCurrentSession().createQuery("from Good").list(); } */
    @Test
    public void testGetAllGood() throws Exception {
/*
    public List getAllGood() {
     Session tempSession = sessionFactory.getCurrentSession();
     Query tempQuery = tempSession.createQuery("from Good");
     List returnedList = tempQuery.list();
        return returnedList;
    }
 */

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
/*        public Good findGoodByName(String goodname) {
            Session tempSession = sessionFactrory.getCurrentSession();
            Criteria criteria = tempSession.createCriteria(Good.class);
            criteria.add(Restrictions.eq("name", goodname));
            return (Good) criteria.uniqueResult();
        }*/
        Good toBeReturned = new Good();
        Session mockSession = mock(Session.class);
        Criteria mockCriteria = mock(Criteria.class);
//        SimpleExpression mockSimpleExpression = mock(SimpleExpression.class);
//        Restrictions mockRestrictions = mock(Restrictions.class);
//        Restrictions mockRestrictions = mock(Restrictions.class);

        doReturn(mockSession).when(sessionFactory).getCurrentSession();
        doReturn(mockCriteria).when(mockSession).createCriteria(Good.class);
        doReturn(toBeReturned).when(mockCriteria).uniqueResult();
//        doNothing().when(mockRestrictions).eq(eq("name"), anyString()).thenReturn(mockCriteria);
//        when(mockRestrictions.eq(anyString(), anyString())).thenReturn(mockSimpleExpression);

        Good testGood = s.findGoodByName("bobrik");

        verify(sessionFactory).getCurrentSession();
        verify(mockSession).createCriteria(eq(Good.class));
        verify(mockCriteria).add(Restrictions.eq("name", "bobrik"));

/*      ВСЕ ЗАЕБЛО, ЗАЕБЛО, ЗАЕБЛО !!!

Argument(s) are different! Wanted:
criteria.add(name=bobrik);
-> at com.luxoftmarket.dao.impl.GoodDaoImplTest.testFindGoodByName(GoodDaoImplTest.java:173)
Actual invocation has different arguments:
criteria.add(name=bobrik);
-> at com.luxoftmarket.dao.impl.GoodDaoImpl.findGoodByName(GoodDaoImpl.java:49)

Expected :criteria.add(name=bobrik);
Actual   :criteria.add(name=bobrik);
*/

        verify(mockCriteria).uniqueResult();

        assertSame(toBeReturned, testGood);

    }
}