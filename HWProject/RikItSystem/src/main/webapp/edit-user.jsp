<%@ page import="ru.otus.rik.service.persistence.PersistenceService" %>
<%@ page import="ru.otus.rik.service.persistence.JpaPersistenceService" %>
<%@ page import="ru.otus.rik.domain.UserEntity" %>
<%@ page import="ru.otus.rik.domain.DepartmentEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.otus.rik.domain.PositionEntity" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<%!
    private static final PersistenceService persistenceService = new JpaPersistenceService();
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
            user = persistenceService.findUserByEmail(email);;
        }
        request.setAttribute("user", user);
    %>
    <c:choose>
        <c:when test="${pageContext.request.getAttribute('user') != null}">
            <%
                List<DepartmentEntity> departments = persistenceService.findAllDepartments();
                request.setAttribute("departmentsList", departments);
                List<PositionEntity> positions = persistenceService.findAllPositions();
                request.setAttribute("positionsList", positions);
            %>
            <form id="editForm" method="POST" action="${contextPath}/edit" class="form-signin">
                <div class="form-group ${error != null ? 'has-error' : ''}">
                    <input name="name" type="text" class="form-control" placeholder="Name" disabled="disabled" value="${user.name}"/>
                    <input name="email" type="text" class="form-control" placeholder="Email" disabled="disabled" value="${user.email}"/>
                    <select id="department" class="selectpicker" data-width="300px">
                        <c:forEach var="department" items="${departmentsList}">
                            <option value="${department.location}&${department.name}"
                                    ${user.departmentRef.equals(department) ? 'selected' : ''}>${department.location}, ${department.name}</option>
                        </c:forEach>
                    </select>
                    <select id="role" class="selectpicker" data-width="300px">
                        <c:forEach var="position" items="${positionsList}">
                            <option value="${position.title}"
                                    ${user.positionRef.equals(position) ? 'selected' : ''}>${position.title}</option>
                        </c:forEach>
                    </select>
                    <span id="error" name="error">${error}</span>
                </div>
                <button type="submit" class="btn btn-primary  btn-md">Edit</button>
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
