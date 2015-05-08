<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/tags/includes.jsp" %>

<t:template>

    <h1>Store “Luxoftmarket”</h1>


    <h2>Spring MVC 4 + Hibernate 4 + H2 (in memoy) + Spring Sequrity</h2>

    <h3>Admin area login details:</h3>

    <h4>login: “Vladyslav”</h4>
    <h4>password: “bestDeveloper” :)</h4>

    <body>

    Author: Vladyslav Kostiuchenko<br>
    E-mail: sadkoua@gmail.com<br>
    tel: +38 (O97) 95O-82-5б<br>
    <br>
    For implementation:<br>
    <br>
    Back-end:<br>
    Java7 + Spring MVC 4 + Spring Security 3 + Hibernate 4 + H2 (in-memory data base)<br>
    <br>
    Front-end:<br>
    HTML 5 + JavaScript + CSS <br>
    <br>
    Tests:<br>
    Junit4 + Mockito + jBehave<br>
    <br>
    Validation:<br>
    Spring Validation + Hibernate Validation

    </body>

    <br>

    <sec:authorize access="isAnonymous()">
        <a href="addUser">Регистрация</a><br>
    </sec:authorize>

    <sec:authorize access="isAuthenticated()">
        <a href="buy">Приступить к покупкам (админу тоже можно)</a><br>
    </sec:authorize>

    <sec:authorize access="hasRole('administrator')">
        <a href="admin">В кабинет админа</a><br>
    </sec:authorize>


</t:template>