package com.luxoftmarket.controller;

import com.luxoftmarket.dao.IRoleDao;
import com.luxoftmarket.dao.IUserDao;
import com.luxoftmarket.domain.User;
import com.luxoftmarket.domain.UserStatus;
import com.luxoftmarket.validation.UserValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    private IUserDao userDao;
    @Mock
    private IRoleDao roleDao;
    @Mock
    private UserValidator userValidator;

    @InjectMocks
    UserController s;


    @Test
    public void testAddUserGet() throws Exception {

        String toBeReturned = s.addUser();

        assertEquals("addUser", toBeReturned);

    }

    @Test
    public void testAddUserPostHasError() throws Exception {

        BindingResult mockBinding = mock(BindingResult.class);
        User testUser = new User();

        doNothing().when(userValidator).validate(eq(User.class), any(BindingResult.class));
        doReturn(true).when(mockBinding).hasErrors();

        String toBeReturned = s.addUser(testUser, mockBinding);

        assertEquals("addUser", toBeReturned);

        verify(userValidator).validate(any(User.class), any(BindingResult.class));
        verify(mockBinding).hasErrors();
    }

    @Test
    public void testAddUserPostNoError() throws Exception {
        BindingResult mockBinding = mock(BindingResult.class);
        User testUser = mock(User.class);

        doNothing().when(userValidator).validate(eq(User.class), any(BindingResult.class));
        doReturn(false).when(mockBinding).hasErrors();
        doNothing().when(userDao).addUser(testUser);
        doNothing().when(testUser).setRoles(any(List.class));
        doNothing().when(testUser).setStatus(UserStatus.ACTIVE);
        doNothing().when(testUser).setPassword(anyString());
        doReturn("krakozyabra!").when(testUser).getPassword();

        String toBeReturned = s.addUser(testUser, mockBinding);

        verify(userValidator).validate(any(User.class), any(BindingResult.class));
        verify(mockBinding).hasErrors();
        verify(userDao).addUser(any(User.class));
        verify(testUser).setRoles(any(List.class));
        verify(testUser).setStatus(UserStatus.ACTIVE);
        verify(testUser).setPassword(anyString());
        verify(testUser).getPassword();

        assertEquals("redirect:/", toBeReturned);

    }
}