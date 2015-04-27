<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/includes.jsp"%>

<t:template>
  <script src="<c:url value="/resources/s/clock.js" />"></script>
  <%--<script src="./Luxoft forever !_files/goodsUtil.js"></script>--%>

  <form:form method="post" action="addUser" commandName="user"> <!-- addBook адрес, который в контроллере замапен -->
    <%--<form id="user" name="registration" action="./Luxoft forever !_files/Luxoft forever !.html" method="post">--%>


    <table>
      <tr>
        <td><form:label path="nick">
          Ваш ник
        </form:label></td>
        <td><form:input path="nick" type="text"/></td>
        <%--<td><input id="nick" name="nick" type="text" value=""></td>--%>
        <td><form:errors cssClass="error" path="nick"></form:errors></td>
      </tr>

      <tr>
        <td><form:label path="password">
          Ваш пароль (мин 6 символов)
        </form:label></td>
        <td><form:input type="password" size="25" path="password" accept-charset="UTF-8"/></td>
        <%--<td><input id="password" name="password" type="password" accept-charset="UTF-8" value="" size="25" onKeyUp="passValid('registration','password','pass12','submit'),isEqual('registration','password','password2','pass22','submit')"></td>--%>
        <%--<td><span id="pass11"><span id="pass12">&nbsp;</span></span></td>--%>
        <td><form:errors cssClass="error" path="password"></form:errors></td>
      </tr>

      <tr>
        <td><form:label path="email">
          Ваш email
        </form:label></td>
        <td><form:input type="email" path="email"/></td>
        <td><form:errors cssClass="error" path="email"></form:errors></td>
      </tr>

      <tr>
        <td colspan="2"><input type="submit" value="Регистрация" /></td>
      </tr>
    </table>

  </form:form>
</t:template>
