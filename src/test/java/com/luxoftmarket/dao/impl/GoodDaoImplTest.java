package com.luxoftmarket.dao.impl;

import com.luxoftmarket.domain.Good;
import junit.framework.TestCase;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class GoodDaoImplTest {

    @Mock
//@Autowired
    private SessionFactory sessionFactory;


    @InjectMocks
    GoodDaoImpl s;

    //        public void add(Good good) { sessionFactory.getCurrentSession().save(good); }
    @Test
    public void testAdd() throws Exception {


//        SessionFactory sessionFactory = mock(SessionFactory.class);

//        SessionFactory ses = mock(SessionFactory.class);
//        Session ses = mock(sessionFactory.getCurrentSession().getClass());

//        doNothing().when(ses).save(any(Good.class));
        doNothing().when(sessionFactory.getCurrentSession()).save(any(Good.class));
//        doNothing().when(goodDao).add(any(Good.class));

//        Serializable serializable = null;
//        doReturn(serializable).when(sessionFactory).getCurrentSession().save(any(Good.class));
//        when(ses.get(anyInt())).thenReturn(null);

        Good testGood = new Good();
        s.add(testGood);

        verify(sessionFactory.getCurrentSession()).save(same(testGood));
    }

    @Test
    public void testEdit() throws Exception {
//        public void edit(Good good) { sessionFactory.getCurrentSession().update(good);}


        doNothing().when(sessionFactory).getCurrentSession().update(any(Good.class));

        Good testGood = new Good();
        s.edit(testGood);

        verify(sessionFactory).getCurrentSession().update(same(testGood));
    }


    /*        public void delete(int id) {
                Good delGood = getGood(id);
                sessionFactory.getCurrentSession().delete(delGood);}
 */
    @Test
    public void testDelete() throws Exception {

        Good testGood = new Good();
        //doReturn(testGood).when(s).getGood(5);
        doNothing().when(sessionFactory).getCurrentSession().delete(Good.class);

        s.delete(5);

        verify(s).getGood(eq(5));
        verify(sessionFactory).getCurrentSession().delete(eq(5));

    }

    //        public Good getGood(int id) { return (Good)sessionFactory.getCurrentSession().get(Good.class, id); }
    @Test
    public void testGetGood() throws Exception {

        Good toBeReturned = new Good();
        doReturn(toBeReturned).when(sessionFactory).getCurrentSession().get(Good.class, anyInt());

        Good testGood = s.getGood(5);
        verify(sessionFactory).getCurrentSession().get(Good.class, eq(5));

        assertSame(toBeReturned, testGood);
    }


/*        public List getAllGood() {
            return sessionFactory.getCurrentSession().createQuery("from Good").list(); } */
    @Test
    public void testGetAllGood() throws Exception {



        List toBeReturned = Arrays.asList(new Good());
        doReturn(toBeReturned).when(sessionFactory).getCurrentSession().createQuery("from Good").list();

        List testList = s.getAllGood();

        verify(sessionFactory).getCurrentSession().createQuery("from Good").list();
        assertSame(toBeReturned, testList);
    }

    @Test
    public void testFindGoodByName() throws Exception {
/*        public Good findGoodByName(String goodname) {
            Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Good.class);
            criteria.add(Restrictions.eq("name", goodname));
            return (Good) criteria.uniqueResult();
        }*/
        Good toBeReturned = new Good();
        Criteria testCriteria = mock(Criteria.class);
        Criterion testCriterion = mock(Criterion.class);

        doReturn(testCriteria).when(sessionFactory).getCurrentSession().createCriteria(Good.class);
        doReturn(toBeReturned).when(testCriteria).uniqueResult();

        Good testGood = s.findGoodByName("bobrik");


        verify(sessionFactory).getCurrentSession().createCriteria(Good.class);
        verify(testCriteria).add(testCriterion);
        verify(testCriteria).uniqueResult();

        assertSame(toBeReturned, testGood);

    }
}