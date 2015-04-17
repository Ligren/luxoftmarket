<%@ include file="/WEB-INF/tags/includes.jsp"%>

<t:template>
  <!--проверка на условие, если books is !empty тогда заходим в условие-->
  <c:if test = "${!empty goods}">
    <!--начало таблицы-->
    <table class="good-list-table">
      <!--столбци-->
      <tr>
        <th>Name</th>
        <th>Genre</th>
        <sec:authorize access="hasRole('admin')">
          <th>&nbsp;</th>
        </sec:authorize>

      </tr>
      <!--конец столбци-->
      <!--начало строки, цикл, для каждой строки из books (список из Джавы), каждая строка приписывается внутренней переменной book  -->
      <c:forEach items="${goods}" var="good">
        <!-- выводим строку -->
        <tr>
          <td>${goods.name}</td>
          <td>${goods.genre}</td>
          <sec:authorize access="hasRole('admin')">
            <td><a href="javascript:BookUtil.deleteBook(${goods.id})">Delete</a></td>
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

