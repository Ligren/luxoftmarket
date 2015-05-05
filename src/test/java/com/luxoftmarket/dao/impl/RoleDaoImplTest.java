package com.luxoftmarket.dao.impl;

import com.luxoftmarket.domain.Role;
import org.hibernate.SessionFactory;
import org.hibernate.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class RoleDaoImplTest {

    @Mock
    @Autowired
    SessionFactory sessionFactory;

    @Mock
    private Session mockSession;

    @InjectMocks
    RoleDaoImpl s;

    private Role testRole;

    @Before
    public void createTestRole() {
        this.testRole = new Role();
    }

    @Test
    public void testFindRole() throws Exception {

/*Session tempSession = sessionFactory.getCurrentSession();
Role tempRole = tempSession.get(Role.class, roleId);
return tempRole;
 */

        doReturn(mockSession).when(sessionFactory).getCurrentSession();
        doReturn(testRole).when(mockSession).get(Role.class, 5);

        Role returnedRole = s.findRole(5);

        verify(sessionFactory).getCurrentSession();
        verify(mockSession).get(Role.class, 5);
        assertEquals(testRole, returnedRole);
    }

    @Test
    public void testAddRole() throws Exception {
//            sessionFactory.getCurrentSession().save(role);

        doReturn(mockSession).when(sessionFactory).getCurrentSession();
        doReturn(testRole).when(mockSession).save(any(Role.class));

        s.addRole(testRole);

        verify(sessionFactory).getCurrentSession();
        verify(mockSession).save(testRole);

    }
}