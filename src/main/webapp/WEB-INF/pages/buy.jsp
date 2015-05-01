<%@ taglib prefix="td" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/tags/includes.jsp" %>


<t:template>

    <sec:authorize access="hasRole('user')">
        <h3>Поздравляю Вас, Вы зареигстрировалилсь как User. Теперь Вы можете попасть в кабинет админа,<br>
            Именно там находится заветный CRUD + Search<br>
            login: "Vladyslav" password: "bestDeveloper" :) <br><br>
            А тут Вы пока можете отложить товар в корзину!
        </h3>
    </sec:authorize>

    <c:choose>
        <c:when test="${empty goodList}">
            <h3>
                Необходимо сначала Администратору добавить товары<br>
                login: Vladyslav<br>
                password: bestDeveloper
            </h3>
        </c:when>
        <c:otherwise>
            <table class="stylesheet.good-list-table">
                <tr>
                    <th>Название</th>
                    <th>Цена</th>
                    <th>Остаток на складе</th>
                    <th>Количество купить</th>
                    <th>Купить</th>
                </tr>
                <c:forEach items="${goodList}" var="good">

                    <tr>
                        <form action="buy" method="POST">
                            <input type="hidden" name="good" value="${good.id}">
                            <td>${good.name}</td>
                            <td>${good.price}</td>
                            <td>${good.amount}</td>
                            <td><input type="text" name="amount" size="3" required></td>
                            <td><input type="submit" value="Buy"></td>
                        </form>
                    </tr>
                </c:forEach>
            </table>

            <c:choose>
                <c:when test="${empty goodInBasket}">
                    <h2>Ваша корзина пуста, сначала добавьте товар в корзину<br>
                        предварительно выбравши количество товара, и нажавши<br>
                        на кнопку Buy
                    </h2>
                </c:when>
                <c:otherwise>

                    <h3> Товары, которые уже находятся в корзине</h3>
                    <br border="1">
                    <table class="good-list-table">
                        <tr>
                            <th>ID</th>
                            <th>Товар</th>
                            <th>Цена</th>
                            <th>Количество</th>
                        </tr>
                        <c:forEach items="${goodInBasket}" var="entry">
                            <tr>
                                <td>${entry.key.id}</td>
                                <td>${entry.key.name}</td>
                                <td>${entry.key.price}</td>
                                <td>${entry.value}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:otherwise>
            </c:choose>

        </c:otherwise>
    </c:choose>

    <sec:authorize access="hasRole('administrator')">
        <td><a href="admin">В кабинет админа</a></td>
        <br>
    </sec:authorize>

</t:template>