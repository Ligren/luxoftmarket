<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/includes.jsp"%>

<t:template>
  <form:form method="post" action="addGood" commandName="good"> <!-- addBook адрес, который в контроллере замапен -->

    <table>
      <tr>
        <td><form:label path="name">
          Название товара
        </form:label></td>
        <td><form:input path="name" /></td>
        <td><form:errors cssClass="error" path="name"></form:errors></td>
      </tr>

      <tr>
        <td><form:label path="price">
          Цена товара
        </form:label></td>
        <td><form:input path="price" accept-charset="UTF-8" /></td> <!-- form:input type="password" path="genre" /></td> -->
        <td><form:errors cssClass="error" path="price"></form:errors></td>
      </tr>

      <tr>
        <td><form:label path="amount">
          Количество на складе
        </form:label></td>
        <td><form:input path="amount" accept-charset="UTF-8" /></td> <!-- form:input type="password" path="genre" /></td> -->
        <td><form:errors cssClass="error" path="amount"></form:errors></td>
      </tr>

      <tr>
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
        <td colspan="2"><input type="submit" value="Add Book" /></td>
      </tr>
    </table>
  </form:form>
</t:template>
