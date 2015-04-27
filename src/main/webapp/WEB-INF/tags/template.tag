<%@ tag description="Template Tag" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/includes.jsp"%>


<!doctype>
<html>
<head>
    <%--<link rel="icon" type="image/png" href="/favicon.png" />--%>
    <%--someimage.png--%>
    <meta charset="utf-8">
    <link href="<c:url value="/resources/c/main.css" />" rel="stylesheet">
    <script src="<c:url value="/resources/s/goodsUtil.js" />"></script>
        <script src="<c:url value="/resources/s/clock.js" />"></script>

    <title>Luxoft forever !</title>

    <!-- класс "login-link-container" должен присутствовать в main.css -->
    <div class="login-link-container">
        <sec:authorize access="isAnonymous()">
            <p>
                <a href="/spring_security_login">Вход</a>
            </p>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <a href="/j_spring_security_logout">Выход</a><br>
            <%--<spring:message code="user.logged"/>:--%>
            Вошли как: <sec:authentication property="name"/><br>
            Права: <sec:authentication property="authorities"/><br>
        </sec:authorize>
    </div>

</head>
<body>

<div id="clock"></div>

<div class="content">
    <h1>Супер магазин</h1>
    <jsp:doBody/>
</div>
</body>
</html>