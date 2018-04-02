<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="rik" uri="/rik-tld"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="/WEB-INF/common-jsp/head.jsp"%>
    <title>Edit</title>
</head>
<body>
<rik:statistics/>
<div class="container">
        <rik:user email="${param.email}"/>
    <c:choose>
        <c:when test="${not empty pageContext.getAttribute('user')}">
            <rik:departments/>
            <rik:positions/>
            <form id="editForm" method="POST" action="${contextPath}/edit" class="form-signin">
                <div class="form-group ${error ne null ? 'has-error' : ''}">
                    <input name="name" type="text" class="form-control" placeholder="Name" disabled="disabled" value="${user.name}"/>
                    <input name="email" type="text" class="form-control" placeholder="Email" disabled="disabled" value="${user.email}"/>
                    <select id="department" name="department" class="selectpicker" data-width="300px">
                        <c:forEach var="department" items="${pageContext.getAttribute('departmentsList')}">
                            <option value="${department.location}&${department.name}"
                                    ${user.departmentRef.equals(department) ? 'selected' : ''}>${department.location}, ${department.name}</option>
                        </c:forEach>
                    </select>
                    <select id="position" name="position" class="selectpicker" data-width="300px">
                        <c:forEach var="position" items="${pageContext.getAttribute('positionsList')}">
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
            <jsp:forward page="${contextPath}/search-result.jsp"/>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
