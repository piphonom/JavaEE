<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="head.jsp"%>
    <title>Supported browsers</title>
</head>
<body>
<div class="container">
    <h2 class="form-heading">Update your browser</h2>
    <table class="help-block">
        <c:forEach var="browser" items="${browsersList}">
            <c:set var="classSucess" value="info"/>
            <tr class="${classSucess}">
                <td><a href="${browser.link}" class="mix__link" style="background-image: url(${browser.imageSource});"></a> version ${browser.minVersion} and higher</td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>