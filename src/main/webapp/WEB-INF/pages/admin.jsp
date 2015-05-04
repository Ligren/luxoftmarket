<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/tags/includes.jsp" %>


<t:template>


    <h1>Товары</h1>
    <%--<form:form action="good.do" method="POST">--%>
    <%--<form:form action="good.do" method="POST" >--%>
    <form:form action="good.do" method="POST" commandName="good">
        <table class="good-list-table">
            <tr>
                <td>Id (autoincrement)</td>
                <%--<input type="text" name="amount" size="3" required>--%>
                <td><form:input path="id" type="number"/></td>
            </tr>
            <tr>
                <td>Название</td>
                <td><form:input path="name" type="text"/></td>
            </tr>
            <tr>
                <td>Цена</td>
                <td><form:input path="price" type="number" /></td>
            </tr>

            <tr>
                <td>Количество</td>
                <td><form:input path="Amount" type="number" /></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" name="action" value="Add"/>
                    <input type="submit" name="action" value="Edit"/>
                    <input type="submit" name="action" value="Delete"/>
                    <input type="submit" name="action" value="Search (id or name)"/>
                </td>
            </tr>
        </table>

    </form:form>

    <c:choose>
    <c:when test="${empty goodList}">
        <h2>Товаров в магазине нет, их необходимо добавить.</h2>
    </c:when>
    <c:otherwise>

    <br border="1">
    <table class="good-list-table">
        <tr>
            <th>ID</th>
            <th>Good</th>
            <th>Price</th>
            <th>Amount</th>
        </tr>

        <c:forEach items="${goodList}" var="good">
            <tr>
                <td>${good.id}</td>
                <td>${good.name}</td>
                <td>${good.price}</td>
                <td>${good.amount}</td>
            </tr>

        </c:forEach>

    </table>
    </c:otherwise>
    </c:choose>

    <c:choose>
    <c:when test="${empty userList}">
        <h2>Нет зарегистрированных пользователей.</h2>
    </c:when>
    <c:otherwise>
        <h2>Зарегистрированные пользователи:</h2>
    <table class="good-list-table">
        <tr>
            <th>ID</th>
            <th>Nick</th>
            <th>Email</th>
            <th>Password</th>
        </tr>

        <c:forEach items="${userList}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.nick}</td>
                <td>${user.email}</td>
                <td>${user.password}</td>
            </tr>

        </c:forEach>

    </table>
    </c:otherwise>
    </c:choose>



    <a href="buy">Приступить к покупкам</a>
</t:template>