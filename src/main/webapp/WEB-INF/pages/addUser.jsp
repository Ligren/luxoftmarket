<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/tags/includes.jsp" %>

<t:template>

    <script src="<c:url value="/resources/s/password.js" />"></script>


    <form:form method="post" action="addUser" commandName="user">

        <h3>После регистрации, необходимо будет войти в систему<br>
            Воспользовавшись ссылкой "Вход" в правом верхнем углу!
        </h3>
        <table>
            <tbody>
            <tr>

                <td><label for="nick">
                    Ваш ник
                </label></td>
                <td><input id="nick" name="nick" type="text" value="" required></td>
                <%--<td><form:errors path="nick" /></td>--%>
                <td></td>
            </tr>

            <tr>
                <td><label for="password">
                    Ваш пароль (мин 6 символов)
                </label></td>
                <td><input id="password" name="password" type="password" accept-charset="UTF-8" value="" size="25"
                           onKeyUp="passValid('registration','password','pass12','submit'),isEqual('registration','password','password2','pass22','submit')" min="6" required>
                </td>
                <td><span id="pass11"><span id="pass12">&nbsp;</span></span></td>
            </tr>

            <tr>
                <td><label for="password2">
                    Повторите пароль
                </label></td>
                <td><input id="password2" name="password2" type="password" accept-charset="UTF-8" value="" size="25"
                           onKeyUp="isEqual('form','password','password2','pass22','submit')" min="6" required></td>

                <td><span id="pass22"></span></td>
            </tr>

            <tr>
                <td><label for="email">
                    Ваш email
                </label></td>
                <td><input id="email" name="email" type="email" value=""></td>
                <td></td>
            </tr>

            <tr>
                <td colspan="2"><input type="submit" value="Регистрация" id="submit" disabled="1"></td>
            </tr>

            </tbody>
        </table>


    </form:form>
    <form:errors path="nick"></form:errors>
</t:template>