package com.luxoftmarket.service.impl;

import com.luxoftmarket.dao.IGoodDao;
import com.luxoftmarket.dao.IRoleDao;
import com.luxoftmarket.dao.IUserDao;
import com.luxoftmarket.domain.Good;
import com.luxoftmarket.domain.Role;
import com.luxoftmarket.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AddDataInDatabaseTest {
    @Mock
    IGoodDao goodDao;
    @Mock
    IUserDao userDao;
    @Mock
    IRoleDao roleDao;

    @InjectMocks
    AddDataInDatabase s;

    @Test
    public void testInitIsEmpty() throws Exception {

        when(userDao.findUser(anyInt())).thenReturn(null);
        doNothing().when(roleDao).addRole(any(Role.class));
        doNothing().when(userDao).addUser(any(User.class));
        doNothing().when(goodDao).add(any(Good.class));

        s.init();

        verify(userDao).findUser(1);
        verify(roleDao, times(3)).addRole(any(Role.class));
        verify(userDao, times(1)).addUser(any(User.class));
        verify(goodDao, times(8)).add(any(Good.class));
    }

    @Test
    public void testInitIsNotEmpty() throws Exception {

        when(userDao.findUser(anyInt())).thenReturn(any(User.class));

        s.init();

        verify(userDao).findUser(1);
    }
}