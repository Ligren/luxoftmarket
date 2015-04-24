<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/includes.jsp"%>

<t:template>
  <h1>Товары</h1>
  <form:form action="good.do" method="POST" commandName="good">
    <table>
      <tr>
        <td>Артикул</td>
        <td><form:input path="id" /></td>
      </tr>
      <tr>
        <td>Название</td>
        <td><form:input path="name" /></td>
    </tr>
      <tr>
        <td>Цена</td>
        <td><form:input path="price" /></td>
      </tr>

      <tr>
        <td>Количество</td>
        <td><form:input path="Amount" /></td>
      </tr>
      <tr>
        <td colspan="2">
          <input type="submit" name="action" value="Add" />
          <input type="submit" name="action" value="Edit" />
          <input type="submit" name="action" value="Delete" />
          <input type="submit" name="action" value="Search" />
        </td>
      </tr>
    </table>

  </form:form>
  <br border = "1">
  <table>
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
  <a href="buy">Приступить к покупкам</a>
</t:template>