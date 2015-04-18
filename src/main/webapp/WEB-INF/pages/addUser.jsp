<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/includes.jsp"%>

<t:template>
  <form:form method="post" action="addUser" commandName="user"> <!-- addBook адрес, который в контроллере замапен -->

    <table>
      <tr>
        <td><form:label path="nick">
          Ваш ник
        </form:label></td>
        <td><form:input path="nick" /></td>
        <td><form:errors cssClass="error" path="nick"></form:errors></td>
      </tr>

      <tr>
        <td><form:label path="password">
          Ваш пароль (мин 6 символов)
        </form:label></td>
        <td><form:input type="password" size="25" path="password" accept-charset="UTF-8" /></td> <!-- form:input type="password" path="genre" /></td> -->
        <td><form:errors cssClass="error" path="password"></form:errors></td>
      </tr>

      <tr>
        <td><form:label path="email">
          Ваш email
        </form:label></td>
        <td><form:input path="email" /></td>
        <td><form:errors cssClass="error" path="email"></form:errors></td>
      </tr>


      <tr>
        <td colspan="2"><input type="submit" value="Регистрация" /></td>
      </tr>
    </table>

  </form:form>
</t:template>
