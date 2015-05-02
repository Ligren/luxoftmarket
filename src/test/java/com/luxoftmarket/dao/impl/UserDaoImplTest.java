package com.luxoftmarket.dao.impl;

import com.luxoftmarket.domain.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Criterion;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserDaoImplTest {

    @Mock
    @Autowired
    SessionFactory sessionFactory;

    @Mock
    private Session mockSession;

    @InjectMocks
    UserDaoImpl s;

    private User testUser;

    @Before
    public void createUser() { User testUser = new User(); this.testUser = testUser; }

    @Before
    public void doReturnSessionFactory() { doReturn(mockSession).when(sessionFactory).getCurrentSession(); }

    @Test
    public void testAddUser() throws Exception {
        doReturn(testUser).when(mockSession).save(any(User.class));

        s.addUser(testUser);

        verify(sessionFactory).getCurrentSession();
        verify(mockSession).save(testUser);
    }

    @Test
    public void testEditUser() throws Exception {
        doNothing().when(mockSession).update(any(User.class));

        s.editUser(testUser);

        verify(sessionFactory).getCurrentSession();
        verify(mockSession).update(testUser);
    }

    @Test
    public void testDeleteUser() throws Exception {
        UserDaoImpl testS = spy(s);

        doReturn(testUser).when(testS).findUser(anyInt());
        doNothing().when(mockSession).delete(any(User.class));

        testS.deleteUser(7);

        verify(sessionFactory).getCurrentSession();
        verify(testS).findUser(7);
        verify(mockSession).delete(testUser);
    }

    @Test
    public void testFindUser() throws Exception {
        doReturn(testUser).when(mockSession).get(same(User.class), anyInt());

        User returnedUser = s.findUser(7);

        verify(sessionFactory).getCurrentSession();
        verify(mockSession).get(User.class, 7);
        assertEquals(testUser, returnedUser);
    }

    @Test
    public void testFindUserByName() throws Exception {
        Criteria mockCriteria = mock(Criteria.class);
        Criterion mockCriterion = mock(Criterion.class);

        doReturn(mockCriteria).when(mockSession).createCriteria(same(User.class));
        doReturn(mockCriteria).when(mockCriteria).add(mockCriterion);
        doReturn(testUser).when(mockCriteria).uniqueResult();

        User testUser1 = s.findUserByName("");

        verify(sessionFactory).getCurrentSession();
        verify(mockSession).createCriteria(same(User.class));
        verify(mockCriteria).uniqueResult();

        assertEquals(testUser, testUser1);
    }

    @Test
    public void testGetAllUsers() throws Exception {
        List<User> toBeReturned = new ArrayList();
        toBeReturned.add(new User());
        Query mockQuery = mock(Query.class);

        doReturn(mockQuery).when(mockSession).createQuery(same("from User"));
        doReturn(toBeReturned).when(mockQuery).list();

        List<User> testList = s.getAllUsers();

        verify(sessionFactory).getCurrentSession();
        verify(mockSession).createQuery(same("from User"));
        verify(mockQuery).list();

        assertEquals(toBeReturned, testList);
    }
}