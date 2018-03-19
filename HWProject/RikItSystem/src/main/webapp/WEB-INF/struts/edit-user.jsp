<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="rik" uri="/rik-tld"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="/WEB-INF/common-jsp/head.jsp"%>
    <title>Edit</title>
    <script>
        var restBaseUrl = "http://" + document.location.host + "/rik-it-system/api/v1/user";
        var departmentsListUrl = restBaseUrl + "/departments";
        var positionsListUrl = restBaseUrl + "/positions";
        var statusTimeout = 300000; // timeout form message

        getDepartments();
        getPositions();

        function editUser() {

        }

        function getDepartments() {
            $.ajax({
                url: departmentsListUrl,
                cache: false,
                dataType: "json",
                timeout: statusTimeout,
                type: "get",
                error: departmentsErrorHandler,
                success: departmentsHandler});
        }

        function getPositions() {
            $.ajax({
                url: positionsListUrl,
                cache: false,
                dataType: "json",
                timeout: statusTimeout,
                type: "get",
                error: positionsErrorHandler,
                success: positionsHandler});
        }

        function departmentsErrorHandler(data) {

        }

        function departmentsHandler(data) {
            var departments = data.departments;
            var selection = document.getElementById('department');
            for(var i = 0; i < departments.length; i++) {
                var opt = document.createElement('option');
                opt.innerHTML = departments[i].location + ", " + departments[i].name;
                opt.value = departments[i].location + "&" + departments[i].name;
                if ("${user.departmentRef.location}" === departments[i].location &&
                    "${user.departmentRef.name}" === departments[i].name
                   ) {
                    opt.selected = true;
                }
                selection.appendChild(opt);
            }
            $("#department").selectpicker('refresh');
        }

        function positionsErrorHandler(data) {

        }

        function positionsHandler(data) {
            var positions = data.positions;
            var selection = document.getElementById('position');
            for(var i = 0; i < positions.length; i++) {
                var opt = document.createElement('option');
                opt.innerHTML = positions[i].title;
                opt.value = positions[i].title;
                if ("${user.positionRef.title}" === positions[i].title) {
                    opt.selected = true;
                }
                selection.appendChild(opt);
            }
            $("#position").selectpicker('refresh');
        }
    </script>
</head>
<body>
<rik:statistics/>
<div class="container">
    <form id="editForm" class="form-signin">
        <div class="form-group ${error ne null ? 'has-error' : ''}">
            <input name="name" type="text" class="form-control" placeholder="Name" disabled="disabled" value="${user.name}"/>
            <input name="email" type="text" class="form-control" placeholder="Email" disabled="disabled" value="${user.email}"/>
            <select id="department" name="department" class="selectpicker" data-width="300px"></select>
            <select id="position" name="position" class="selectpicker" data-width="300px"></select>
            <span id="error" name="error">${error}</span>
        </div>
        <input type="button" class="btn btn-lg btn-primary btn-block" value="Edit" onclick="editUser()">
        <input type="hidden" name="csrf" value="<c:out value='${csrf}'/>"/>
    </form>
</div>
</body>
</html>
