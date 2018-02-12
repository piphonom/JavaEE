<%@ page import="ru.otus.rik.service.persistence.PersistenceService" %>
<%@ page import="ru.otus.rik.service.persistence.JpaPersistenceService" %>
<%@ page import="ru.otus.rik.domain.UserEntity" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<%!
    private static final PersistenceService persistenceService = new JpaPersistenceService();

    UserEntity getUser(String email) {
        return persistenceService.findUserByEmail(email);
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="head.jsp"%>
    <title>Edit</title>
</head>
<body>
<div class="container">
    <%
        UserEntity user = null;
        String email = request.getParameter("email");
        if (email != null) {
            user = getUser(email);
        }
        request.setAttribute("user", user);
    %>
    <c:choose>
        <c:when test="${pageContext.request.getAttribute('user') != null}">
            <form id="editForm" method="POST" action="${contextPath}/edit" class="form-signin">
                <div class="form-group ${error != null ? 'has-error' : ''}">
                    <input name="name" type="text" class="form-control" placeholder="Name" disabled="disabled" value="${user.name}"/>
                    <input name="email" type="text" class="form-control" placeholder="Email" disabled="disabled" value="${user.email}"/>
                    <input name="location" type="text" class="form-control" placeholder="Location" value="${user.departmentRef.location}"/>
                    <input name="department" type="text" class="form-control" placeholder="Department" value="${user.departmentRef.name}"/>
                    <input name="position" type="text" class="form-control" placeholder="Position" value="${user.positionRef.title}"/>
                    <input name="salary" type="text" class="form-control" placeholder="Salary" value="${user.positionRef.salary}"/>
                    <span id="error" name="error">${error}</span>
                    <button type="submit" class="btn btn-primary  btn-md">Edit</button>
                </div>
                <input type="hidden" id="email" name="email" value="${user.email}">
                <input type="hidden" id="action" name="action" value="edit">
                <input type="hidden" name="csrf" value="<c:out value='${csrf}'/>"/>
            </form>
        </c:when>
        <c:otherwise>
            ${pageContext.request.setAttribute("error", "User not found")}
            ${pageContext.request.setAttribute("usersList", pageContext.request.getSession(false).getAttribute("usersList"))}
            <jsp:forward page="${contextPath}/users.jsp"/>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
