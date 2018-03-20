<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="rik" uri="/rik-tld"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="session" value="${pageContext.request.getSession(false)}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="/WEB-INF/common-jsp/head.jsp"%>
    <title>Users</title>
    <script>
        var catalogBaseUrl = "http://" + document.location.host + "/rik-it-system/api/v1/catalog";
        var userListUrl = catalogBaseUrl + "/users";
        var statusTimeout = 300000; // timeout form message

        getUsers();

        function removeUser (email) {

        }

        function getUsers() {
            $.ajax({
                url: userListUrl,
                cache: false,
                dataType: "json",
                timeout: statusTimeout,
                type: "get",
                error: errorHandler,
                success: listHandler});
        }

        function errorHandler(data) {

        }

        function listHandler(data) {
            var users = data.users;
            var table = document.getElementById("usersList");
            for (var i = 0; i < users.length; i++) {
                var row = table.insertRow();
                row.classList.add("info");
                var cell = row.insertCell(0);
                cell.innerHTML = "<a href=\"${contextPath}/edit-user.action?email=" + users[i].email + "\">" + users[i].name + "</a>";

                cell = row.insertCell(1);
                cell.innerHTML = users[i].email;

                cell = row.insertCell(2);
                cell.innerHTML = users[i].departmentRef.location;

                cell = row.insertCell(3);
                cell.innerHTML = users[i].departmentRef.name;

                cell = row.insertCell(4);
                cell.innerHTML = users[i].positionRef.title;

                cell = row.insertCell(5);
                cell.innerHTML = users[i].positionRef.salary;

                cell = row.insertCell(6);
                cell.innerHTML = "<a href=\"#\" onclick=\"removeUser(" + users[i].email + ")\"><span class=\"glyphicon glyphicon-trash\" aria-hidden=\"true\"></span></a>"
            }
        }
    </script>
</head>
<body>
<rik:statistics/>
<div class="container">
        <%--<s:if test="%{#user != null}">--%>
            <form id="searchForm" method="POST" action="${contextPath}/search">
                <table  class="table table-striped">
                    <thead>
                    <tr>
                        <td><input name="name" type="text" class="form-control" placeholder="Name" autofocus="true"/></td>
                        <td><input name="location" type="text" class="form-control" placeholder="City"/></td>
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

            <h2>List of users for ${user.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a></h2>

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
            <form action="/edit" method="POST" id="usersForm" role="form" >
                <input type="hidden" id="email" name="email">
                <input type="hidden" id="action" name="action">
                <%--${pageContext.request.getSession(false).setAttribute("usersList", usersList)}--%>
                <table  class="table table-striped" id="usersList">
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
                </table>
            </form>

            <c:if test="${not empty session.getAttribute(\"role-hr\")}">
                <form action ="${contextPath}/new-user">
                    <input type="hidden" name="csrf" value="<c:out value='${csrf}'/>"/>
                    <br>
                    <button type="submit" class="btn btn-primary  btn-md">New user</button>
                </form>
            </c:if>

            <a href="${contextPath}/statistics.action">Statistics</a>
            <a href="${contextPath}/chat.action">Chat</a>
            <a href="${contextPath}/credit.action">Credit</a>
        <%--</s:if>--%>
        <%--<s:else>--%>
            <%--<jsp:forward page="${contextPath}/login.action"/>--%>
        <%--</s:else>--%>
</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/js/bootstrap.min.js"></script>
</body>
</html>
