<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="/WEB-INF/common-jsp/head.jsp"%>
    <title>Log in with your account</title>
</head>

<body>
<div class="container">
    <form method="POST" action="j_security_check" class="form-signin">
        <h2 class="form-heading">Log in</h2>

        <div class="form-group ${error != null ? 'has-error' : ''}">
            <input name="j_username" type="text" class="form-control" placeholder="Email" value="${email}"
                   autofocus="true"/>
            <input name="j_password" type="password" class="form-control" placeholder="Password"/>
            <span id="error" name="error">${error}</span>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
        </div>
    </form>
</div>
<!-- /container -->
</body>
</html>
