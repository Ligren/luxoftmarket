<%@ taglib prefix="td" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/includes.jsp"%>

<t:template>
  <%--<form:form method="post" action="addGood" commandName="good"> <!-- addBook адрес, который в контроллере замапен -->--%>

    <!--проверка на условие, если books is !empty тогда заходим в условие-->
    <%--<c:if test = "${!empty goodList}">--%>
      <%--<form:form method="post" commandName="byu" >--%>
      <%--<form:form method="post" action="addUser" commandName="user">--%>
      <%--<form:form method="POST" action="byuGood" commandName="good"/>--%>
      <%--<form:form action="buy.do" method="POST" commandName="good">--%>

      <%--<table class="good-list-table">--%>
        <!--столбци-->
        <tr>
          <th>Название</th>
          <th>Цена</th>
          <th>Остаток на складе</th>
          <th>Количество купить</th>
          <th>Купить</th>
        </tr>
        <!--конец столбци-->
        <!--начало строки, цикл, для каждой строки из books (список из Джавы), каждая строка приписывается внутренней переменной book  -->
        <%--<c:forEach items="${goodList}" var="good">--%>
          <!-- выводим строку -->
          <%--<tr>--%>

  <c:forEach items="${goodList}" var="good">
    <form action="buy" method="POST">
        <%--<input type="hidden" name="good" value="${good.id}">--%>
      <input type="hidden" name="good" value="its my string">
      <input type="text" name="amount">
      <input type="submit" value="Buy">
    </form>
  </c:forEach>

  <form action="buytest" method="POST" commandName="mystring">
      <input type="text" name="mystring" value="its my string" path="mystring">
    <input type="submit" value="buytest">
  </form>



              <%--<c:forEach items="${goodInBasket}" var="good">--%>
          <%--<tr>--%>
            <%--<td><form:label path="email">${good.name}</form:label></td>--%>
            <%--<td><form:input path="email" /></td>--%>
            <%--<td><form:errors cssClass="error" path="email"></form:errors></td>--%>
          <%--</tr>--%>


            <%--<td>${good.name}</td>--%>
            <%--<td>${good.price}</td>--%>
            <%--<td>${good.amount}</td>--%>
<%----%>
            <%--<td><input type="text" size="5" name="namereturn"  /></td>--%>
<%----%>
            <%--<td><input type="submit" name="action" value="byu"/></td>--%>
            <%--<input type="submit" name="action" value="Add" />--%>

            <%--value="bYu" - имя на дисплее--%>
            <%--name="byu" --%>

            <%--<input type="submit" name="action" value="Byu" />--%>
            <%--<td><form:label path="email">Ваш email</form:label></td>--%>
            <%--<td><form:input path="email" /></td>--%>
            <%--<td><form:errors cssClass="error" path="email"></form:errors></td>--%>
          <%--</tr>--%>
        <%--</c:forEach>--%>
      <%--</table>--%>
      <%--</form:form>--%>



      <%--<tr>--%>
        <!--Предназначен для создания одной ячейки таблицы. Тег <td> должен размещаться внутри контейнера <tr>, который в свою очередь располагается внутри тега <table>. -->
        <!-- кнопка с надписью Add Book типа submit (представлять)-->
        <!-- colspan="2" объединение 2х ячеек в одну-->
        <!-- атрибуеы тега <input>
        "text" - текстовое поле
        "password" поле с паролем
        "radio" переключатель,
        "checkbox" флажок
        "hidden" скрытое поле
        "button" кнопка
        "submit" кнопка для отправки формы
        "reset" кнопка для очистки формы
        "file" поле для отправки файла
        "image" кнопка с изображением-->
        <%--<td colspan="2"><input type="submit" value="Add Book" /></td>--%>
      <%--</tr>--%>


    <h3> Товары, которые уже находятся в корзине</h3>
    <br border = "1">
    <table>
      <tr>
        <th>ID</th>
        <th>Товар</th>
        <th>Цена</th>
        <th>Количество</th>
      </tr>

      <%--<c:forEach items="${goodInBasket}" var="good">--%>
        <%--<tr>--%>
          <%--<td>${good.id}</td>--%>
          <%--<td>${good.name}</td>--%>
          <%--<td>${good.price}</td>--%>
          <%--<td>${good.amount}</td>--%>
        <%--</tr>--%>

      <%--</c:forEach>--%>
    </table>
    <%--</c:if>--%>
  <c:if test = "${empty goodList}">
    <h2>Необходимо сначала Администратору (admin password: koala) добавить товары</h2>
  </c:if>

  <%--<sec:authorize access="hasRole('admin')">--%>
  <td><a href="admin">В кабинет админа</a></td>
  <%--</sec:authorize>--%>


  <%--</form:form>--%>
</t:template>
