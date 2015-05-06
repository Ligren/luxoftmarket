package com.luxoftmarket.controller;

import com.luxoftmarket.dao.IGoodDao;
import com.luxoftmarket.dao.IRoleDao;
import com.luxoftmarket.dao.IUserDao;
import com.luxoftmarket.domain.Good;
import com.luxoftmarket.domain.Role;
import com.luxoftmarket.domain.User;
import com.luxoftmarket.validation.GoodValidator;
import org.apache.catalina.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GoodControllerTest {

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session mockSession;

    @Mock
    private IRoleDao roleDao;

    @Mock
    private IUserDao userDao;

    @Mock
    private IGoodDao goodDao;

    @Mock
    private GoodValidator goodValidator;

    @InjectMocks
    GoodController s;


    @Test
    public void testIndex() throws Exception {

        doNothing().when(roleDao).addRole(any(Role.class));
        doNothing().when(goodDao).add(any(Good.class));
        doNothing().when(userDao).addUser(any(User.class));
        doReturn(new Role()).when(roleDao).findRole(1);
        doReturn(new Role()).when(roleDao).findRole(2);
        doReturn(new Role()).when(roleDao).findRole(3);

        s.index();

        verify(roleDao, times(3)).addRole(any(Role.class));
        verify(goodDao, times(8)).add(any(Good.class));
        verify(roleDao).findRole(1);
        verify(roleDao).findRole(2);
        verify(roleDao).findRole(3);
        verify(userDao).addUser(any(User.class));
    }

    @Test
    public void testSetupForm() throws Exception {
        /*
        map.put("goodList", goodDao.getAllGood());
        map.put("userList", userDao.getAllUsers());
        return "admin";
         */

        List listGood = new ArrayList();
        doReturn(listGood).when(goodDao).getAllGood();

        List listUser = new ArrayList();
        doReturn(listUser).when(userDao).getAllUsers();

        String toBeReturned = s.setupForm(new HashMap<String, Object>(), new Good());

        verify(userDao).getAllUsers();
        verify(goodDao).getAllGood();

        assertEquals("admin", toBeReturned);
    }

    @Test
    public void testDoActionAddHasError() throws Exception {

        BindingResult mockBinding = mock(BindingResult.class);
        Map testMap = new HashMap<String, Object>();

        doNothing().when(goodValidator).validate(anyObject(), any(Errors.class));
        doReturn(true).when(mockBinding).hasErrors();
        doReturn(new ArrayList()).when(goodDao).getAllGood();
        doReturn(new ArrayList()).when(userDao).getAllUsers();

        String toBeReturned = s.doAction(new Good(), "add", testMap, mockBinding);

        verify(goodValidator).validate(any(Good.class), any(Errors.class));
        verify(mockBinding).hasErrors();
        verify(goodDao).getAllGood();
        verify(userDao).getAllUsers();

        assertEquals("admin", toBeReturned);
    }

    @Test
    public void testDoActionAddNoError() throws Exception {

//        goodValidator.validate(good, bindingResult);
/*        switch (action.toLowerCase()) {
            case "add":
                if (!bindingResult.hasErrors()) {
                    goodDao.add(good);
                }
                break;
            case "edit":
                if (!bindingResult.hasErrors()) {
                    goodDao.edit(good);
                }
                break;
            case "delete":
                if (good.getId() != null & goodDao.getGood(good.getId()) != null) goodDao.delete(good.getId());
                break;
            case "search (id or name)":
                Good searchedGood = null;

                if (good.getId() != null) searchedGood = goodDao.getGood(good.getId());

                if (searchedGood == null & good.getName() != null)
                    searchedGood = goodDao.findGoodByName(good.getName());

                if (searchedGood != null) map.put("good", searchedGood);

                break;
        }
        map.put("goodList", goodDao.getAllGood());
        map.put("userList", userDao.getAllUsers());
        return "admin";
*/
        BindingResult mockBinding = mock(BindingResult.class);
        Map testMap = new HashMap<String, Object>();

//        doNothing().when(goodValidator).validate(anyObject(), mock(Errors.class));
        when((mockBinding).hasErrors()).thenReturn(false);
        doReturn(new ArrayList()).when(goodDao).getAllGood();
        doReturn(new ArrayList()).when(userDao).getAllUsers();
        doNothing().when(goodDao).add(any(Good.class));

        String toBeReturned = s.doAction(new Good(), "add", testMap, mockBinding);
//        verify(goodValidator).validate(any(Good.class), mockBinding);
        verify(mockBinding).hasErrors();
        verify(goodDao).getAllGood();
        verify(goodDao).add(any(Good.class));
        verify(userDao).getAllUsers();

        assertEquals("admin", toBeReturned);
    }

    @Test
    public void testDoActionEditNoError() throws Exception {
        BindingResult mockBinding = mock(BindingResult.class);
        Map testMap = new HashMap<>();

        doNothing().when(goodValidator).validate(anyObject(), any(Errors.class));
        when((mockBinding).hasErrors()).thenReturn(false);
        doReturn(new ArrayList()).when(goodDao).getAllGood();
        doReturn(new ArrayList()).when(userDao).getAllUsers();
        doNothing().when(goodDao).edit(any(Good.class));

        String toBeReturned = s.doAction(new Good(), "edit", testMap, mockBinding);
        verify(goodValidator).validate(any(Good.class), any(Errors.class));
        verify(mockBinding).hasErrors();
        verify(goodDao).edit(any(Good.class));
        verify(userDao).getAllUsers();
        verify(goodDao).getAllGood();

        assertEquals("admin", toBeReturned);
    }

    @Test
    public void testDoActionEditHasError() throws Exception {

        BindingResult mockBinding = mock(BindingResult.class);
        Map testMap = new HashMap<String, Object>();

        doNothing().when(goodValidator).validate(anyObject(), any(Errors.class));
        when((mockBinding).hasErrors()).thenReturn(true);
        doReturn(new ArrayList()).when(goodDao).getAllGood();
        doReturn(new ArrayList()).when(userDao).getAllUsers();
        doNothing().when(goodDao).edit(any(Good.class));

        String toBeReturned = s.doAction(new Good(), "edit", testMap, mockBinding);

        verify(goodValidator).validate(any(Good.class), any(BindingResult.class));
        verify(mockBinding).hasErrors();
        verify(userDao).getAllUsers();
        verify(goodDao).getAllGood();

        assertEquals("admin", toBeReturned);
    }

    @Test
    public void testDoActionDelete() throws Exception {

        BindingResult mockBinding = mock(BindingResult.class);
        Map testMap = new HashMap<String, Object>();

        when((mockBinding).hasErrors()).thenReturn(false);
        doReturn(new ArrayList()).when(goodDao).getAllGood();
        doReturn(new ArrayList()).when(userDao).getAllUsers();
        doNothing().when(goodDao).delete(anyInt());

        String toBeReturned = s.doAction(new Good(), "edit", testMap, mockBinding);
        verify(mockBinding).hasErrors();
        verify(userDao).getAllUsers();
        verify(goodDao).getAllGood();

        assertEquals("admin", toBeReturned);
    }

    @Test
    public void testByuStart() throws Exception {
        String toBeReturned = s.byuStart(new HashMap<String, Object>());
        assertEquals("buy", toBeReturned);
    }

    @Test
    public void testByuGood() throws Exception {
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        HttpSession mockSession = mock(HttpSession.class);
        Enumeration e = mock(Enumeration.class);
        Good mockGood = mock(Good.class);

        doReturn("5").when(mockRequest).getParameter("amount");
        doReturn("5").when(mockRequest).getParameter("good");
        doReturn(mockSession).when(mockRequest).getSession();
        doReturn(e).when(mockSession).getAttributeNames();
        when(e.hasMoreElements()).thenReturn(false);
        doNothing().when(mockSession).setAttribute("goodInBasket", eq(anyObject()));
        doReturn("goodInBasket").when(e).nextElement();
        doReturn(mockGood).when(goodDao).getGood(anyInt());
        doNothing().when(mockGood).setAmount(anyInt());
        doNothing().when(goodDao).edit(mockGood);

        String toBeReturned = s.buyGood(new HashMap<String, Object>(), mockRequest);

        verify(mockRequest).getParameter("amount");
        verify(mockRequest).getParameter("good");
        verify(mockRequest).getSession();
        verify(mockSession).getAttributeNames();
        verify(e).hasMoreElements();
        verify(goodDao, times(2)).getGood(anyInt());
        verify(mockGood).setAmount(anyInt());
        verify(goodDao).edit(mockGood);

        assertEquals("buy", toBeReturned);
    }
}