<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="head.jsp"%>
    <title>Users</title>
</head>
<body>

<div class="container">

    <c:choose>
        <%--<c:when test="<c:out value='${user}' scope=session/>" != null}">--%>
        <c:when test="${pageContext.request.getSession(false).getAttribute(\"user\") != null}">
        <%--<c:when test="${session.getAttribute(\"user\") != null}">--%>

            <form id="searchForm" method="POST" action="${contextPath}/search">
                <table  class="table table-striped">
                    <thead>
                    <tr>
                        <td><input name="name" type="text" class="form-control" placeholder="User name" autofocus="true"/></td>
                        <td><input name="location" type="text" class="form-control" placeholder="Location"/></td>
                        <td><input name="department" type="text" class="form-control" placeholder="Department"/></td>
                        <td><button type="submit" class="btn btn-primary  btn-md">Search</button></td>
                    </tr>
                    </thead>
                </table>
                <input type="hidden" name="csrf" value="<c:out value='${csrf}'/>"/>
            </form>

            <form id="logoutForm" method="POST" action="${contextPath}/logout">
                <input type="hidden" name="csrf" value="<c:out value='${csrf}'/>"/>
            </form>

            <h2>List of users for ${pageContext.request.getSession(false).getAttribute("user")} | <a onclick="document.forms['logoutForm'].submit()">Logout</a></h2>

            <c:if test="${not empty message}">
                            <div class="alert alert-success">
                                ${message}
                            </div>
            </c:if>
            <c:if test="${not empty error}">
                            <div class="alert alert-warning">
                                ${error}
                            </div>
            </c:if>
            <form action="/users" method="POST" id="usersForm" role="form" >
                <input type="hidden" id="userName" name="userName">
                <input type="hidden" id="action" name="action">
                <input type="hidden" name="csrf" value="<c:out value='${csrf}'/>"/>
                <c:choose>
                    <c:when test="${not empty usersList}">
                        <table  class="table table-striped">
                            <thead>
                                <tr>
                                    <td>Name</td>
                                    <td>Email</td>
                                    <td>City</td>
                                    <td>Department</td>
                                    <td>Position</td>
                                    <td>Salary</td>
                                </tr>
                            </thead>
                            <c:forEach var="user" items="${usersList}">
                                <c:set var="classSucess" value="info"/>
                                <tr class="${classSucess}">
                                    <td><a href="${contextPath}/edit-user?userName=${user.name}">${user.name}</a></td>
                                    <td>${user.email}</td>
                                    <td>${user.departmentRef.location}</td>
                                    <td>${user.departmentRef.name}</td>
                                    <td>${user.positionRef.title}</td>
                                    <td>${user.positionRef.salary}</td>
                                    <td><a href="#" id="remove"
                                           onclick="document.getElementById('action').value = 'remove';document.getElementById('groupName').value = '${user.name}';
                                                document.getElementById('usersForm').submit();">
                                            <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <br>
                        <div class="alert alert-info">
                            No users
                        </div>
                    </c:otherwise>
                </c:choose>
            </form>

            <c:if test="${pageContext.request.getSession(false).getAttribute(\"role-hr\") != null}">
                <form action ="${contextPath}/new-user">
                    <input type="hidden" name="csrf" value="<c:out value='${csrf}'/>"/>
                    <br></br>
                    <button type="submit" class="btn btn-primary  btn-md">New user</button>
                </form>
            </c:if>
        </c:when>
        <c:otherwise>
            <jsp:forward page="${contextPath}/login.jsp"/>
        </c:otherwise>
    </c:choose>
</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/js/bootstrap.min.js"></script>
</body>
</html>
