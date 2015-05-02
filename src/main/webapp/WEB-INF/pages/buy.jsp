<%@ taglib prefix="td" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/tags/includes.jsp" %>


<t:template>
    <script src="<c:url value="/resources/s/validate_amount.js" />"></script>
    <link href="<c:url value="/resources/c/main.css" />" rel="stylesheet">

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
            <table class="good-list-table">
                <tr>
                    <th>Название</th>
                    <th>Цена</th>
                    <th>Остаток на складе</th>
                    <th>Количество купить</th>
                    <th>Купить</th>
                </tr>
                <c:forEach items="${goodList}" var="good">

                    <tr>
                        <form name="select_amount_form" method="POST" action="buy" onsubmit="return validate_amount ('amount_required_${good.id}','amount_have_${good.id}' , 'submit');" >
                            <input type="hidden" name="good" value="${good.id}">
                            <td>${good.name}</td>
                            <td>${good.price}</td>
                            <td>${good.amount}</td>

                            <input type="hidden" value="${good.amount}" id="amount_have_${good.id}">
                            <td><input type="number" name="amount" size="3" id="amount_required_${good.id}" min="1" max="${good.amount}"></td>
                            <%--<td><input type="text" name="amount" size="3" required></td>--%>
                            <td><input type="submit" value="Buy" id="submit" ></td>
                            <%--<td><input type="submit" value="Buy" id="submit" disabled = "1" ></td>--%>
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