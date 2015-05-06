package com.luxoftmarket.service.impl;

import com.luxoftmarket.dao.IUserDao;
import com.luxoftmarket.domain.Role;
import com.luxoftmarket.domain.User;
import com.luxoftmarket.domain.UserStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserDetailsServiceImplTest {

    @Mock
    @Autowired
    private IUserDao userDao;

    @InjectMocks
    UserDetailsServiceImpl s;

    @Test(expected = UsernameNotFoundException.class)
    public void testLoadUserByUsernameIfUserNull() throws Exception {
        when(userDao.findUserByName(anyString())).thenReturn(isNull(User.class));

        s.loadUserByUsername("sameName");

        verify(userDao).findUserByName(anyString());
    }

    @Test
    public void testLoadUserByUsernameIfUserNotNull() throws Exception {
        List<Role> listRole = new ArrayList<Role>();
        listRole.add(new Role("admin", null));

        User userTest = mock(User.class);

        doReturn(userTest).when(userDao).findUserByName(anyString());
        doReturn("isPassword").when(userTest).getPassword();
        doReturn(UserStatus.ACTIVE).when(userTest).getStatus();
        doReturn(listRole).when(userTest).getRoles();

        s.loadUserByUsername("sameName");

        verify(userDao).findUserByName(eq("sameName"));
        verify(userTest).getPassword();
        verify(userTest, times(4)).getStatus();
        verify(userTest).getRoles();
    }
}