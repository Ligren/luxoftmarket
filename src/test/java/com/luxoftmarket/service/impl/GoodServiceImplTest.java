package com.luxoftmarket.service.impl;

import com.luxoftmarket.dao.IGoodDao;
import com.luxoftmarket.domain.Good;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GoodServiceImplTest {

    @Mock
    private IGoodDao goodDao;

    @InjectMocks
    GoodServiceImpl s;
    //вот он - mock-объект (mock-объект будет запоминать любые вызовы его методов, чтобы после Вы могли проверить какие методы вызывал ваш тестируемый код у mock-объекта)

    @Test
    public void testAdd() throws Exception {
//        public void add(Good good) {goodDao.add(good);}
        doNothing().when(goodDao).add(any(Good.class));

        Good g = new Good();
        s.add(g); //используем mock-объект (вызываем на нем метод, что-бы проверить потом, был ли этот метод вызван на mock объекте)
        //передаем как параметр g (new Good())

        verify(goodDao).add(same(g)); // проверяем, был ли вызван метод add (с тем-же объектом g) у mock Объекта
    }

    @Test
    public void testEdit() throws Exception {
        //ничего не делать когда у goodDao вызываем метод edit с любым экземпляром класса Good
        doNothing().when(goodDao).edit(any(Good.class));

        //создаем экземпляр класса Good
        Good g = new Good();
        //вызываем у mock Объекта (созданного ранее с помощью аннотации @InjectMocks) метод edit с параметром g
        s.edit(g);

        //Проверяем, был ли вызванный метод edit у mock объекта. Передавался ли параметр g.
        verify(goodDao).edit(same(g));
    }

    @Test
    public void testDelete() throws Exception {
        //ничего не делаем когда у mock объекта вызываем метод delete с любым int
        doNothing().when(goodDao).delete(anyInt());

        //вызываем у mock объекта метод delete с параметром 5
        s.delete(5);

        //проверяем, был ли вызван методв delete у mock объекта и была ли передана цифра 5 как параметр
        verify(goodDao).delete(eq(5));
    }

    //public Good getGood(int id) { return goodDao.getGood(id);}
    @Test
    public void testGetGood() throws Exception {
        //тестируем метод getGood, он должен вернуть объект типа Good
        //создаем объект, типа Good, который нам должен вернуть метод
        Good toBeReturned = new Good();
        //метод goodDao.getGood(anyInt()) должен вернуть toBeReturned когда заглушка goodDao вызывает метод getGood с любым интом,
        doReturn(toBeReturned).when(goodDao).getGood(anyInt());

        //Создаем переменную good и присваеваем ей значение, которое вернул нам вызов метода getGood на mock-объекте s
        //в качестве параметра передаем 2
        Good good = s.getGood(2);

        //проверяем, был ли вызван метод getGood с параметром 2 у объекта goodDao
        verify(goodDao).getGood(eq(2));
        //сравниваем значение, котрое вернул вызванный метод getGood(2) вызванный у mock Объекта s с тем значение, которое должен был вернуть метод goodDao.getGood()
        assertSame(toBeReturned, good);
    }

    //public List getAllGood() { return goodDao.getAllGood(); }
    @Test
    public void testGetAllGood() throws Exception {
        //создаем ссылку типа List которая ссылается на ArrayList с одним объектом Good.
        List<Good> toBeReturned = Arrays.asList(new Good());
        //вернуть созданный ранее List когда у mock объекта будет вызван метод getAllGood()
        doReturn(toBeReturned).when(goodDao).getAllGood();

        //Создать ссылку типа List с названием allGood, и присвоить ей значение, которое вернет вызов метода getAllGood() на mock Объекте s
        List allGood = s.getAllGood();

        //проверить, вызывался ли метод getAllGood на mock объекте goodDao
        verify(goodDao).getAllGood();
        //проверить, то что вернул вызов метода s.getAllGood() равняется ли тому Listу который мы приготовили заранее
        assertSame(toBeReturned, allGood);
    }

    @Test
    public void testFindGoodByName() throws Exception {

        //    public Good findGoodByName(String goodname) { return goodDao.findGoodByName(goodname); }

        Good testGood = new Good();
        doReturn(testGood).when(goodDao).findGoodByName(anyString());

        Good good = s.findGoodByName("testString");

        verify(goodDao).findGoodByName(eq("testString"));
        assertSame(testGood, good);

//        verify(goodDao).findGoodByName(eq("testString")); //т.е. тут я ему говорю еще проверить этот ли стринг был передан в качестве параметра?

//        verify(goodDao).findGoodByName("testString"); //а тут ему говорю что стринг можно не проверять?
    }

}