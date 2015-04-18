<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/includes.jsp"%>

<t:template>
  <!--проверка на условие, если books is !empty тогда заходим в условие-->
  <c:if test = "${!empty goods}">
    <!--начало таблицы-->
    <table class="good-list-table">
      <!--столбци-->
      <tr>
        <th>Название</th>
        <th>Цена</th>
        <th>Остаток на складе</th>
        <sec:authorize access="hasRole('admin')">
          <th>&nbsp;</th>
        </sec:authorize>

      </tr>
      <!--конец столбци-->
      <!--начало строки, цикл, для каждой строки из books (список из Джавы), каждая строка приписывается внутренней переменной book  -->
      <c:forEach items="${goods}" var="good">
        <!-- выводим строку -->
        <tr>
          <td>${good.name}</td>
          <td>${good.price}</td>
          <td>${good.amount}</td>
          <sec:authorize access="hasRole('admin')">
            <td><a href="javascript:GoodsUtil.deleteGood(${good.id})">Delete</a></td>
          </sec:authorize>
        </tr>
      </c:forEach>
    </table>
  </c:if>
  <sec:authorize access="isAuthenticated()">
    <!-- ссылка на addBook с надписью Add book -->
    <!--@RequestMapping(value = "addBook", method = RequestMethod.GET) -->
    <a href="addGood">Add goods</a>
  </sec:authorize>


</t:template>

