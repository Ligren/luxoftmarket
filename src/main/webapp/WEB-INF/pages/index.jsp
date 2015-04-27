<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/tags/includes.jsp" %>

<t:template>

    <h1>
        Информация для проверяющих
    </h1>

    <body>
    Уважаемые проверяющие, я понимаю что всяинформация по пользователям (ник, ФИО, пароли и прочая информация) должна
    хранится в базе данных. Но так как
    у нас база данных in memory, то постоянно хранить в ней информацию возможности нет. Поэтому вся информация по
    пользователям зашита наглухо:
    admin (password: koala)
    user (password: koala)
    Доступ под админом позволяет менять ассортимент, удалять товары в целом.
    Доступ под пользователем позволяет выбирать товары из списка в корзину.
    Создание нового пользователя не имеет полноценного функционала, т.к. база данных in-memory.
    То-же самое касается и данного текста, он тоже должен быть зашит в базу данных, что-бы его легко можно-было менять.
    Мы занимаемся тем что продаем прекрасные тапки для Всех, но наши тапки могут покупать только зарегистрированные
    пользователи. Зарегистрируйтеь пожалуйста.
    Мы занимаемся тем что продаем прекрасные тапки для Всех, но наши тапки могут покупать только зарегистрированные
    пользователи. Зарегистрируйтеь пожалуйста.
    Мы занимаемся тем что продаем прекрасные тапки для Всех, но наши тапки могут покупать только зарегистрированные
    пользователи. Зарегистрируйтеь пожалуйста.
    Мы занимаемся тем что продаем прекрасные тапки для Всех, но наши тапки могут покупать только зарегистрированные
    пользователи. Зарегистрируйтеь пожалуйста.

    </body><br>

    <sec:authorize access="isAnonymous()">
        <a href="addUser">Регистрация</a><br>
    </sec:authorize>

    <%--<sec:authorize access="isAuthenticated()">--%>
    <a href="buy">Приступить к покупкам (админу тоже можно)</a><br>
    <%--</sec:authorize>--%>

    <%--<sec:authorize access="hasRole('admin')">--%>
    <td><a href="admin">В кабинет админа</a></td><br>
    <%--</sec:authorize>--%>


</t:template>