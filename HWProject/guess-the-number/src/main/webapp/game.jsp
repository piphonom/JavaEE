<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <%@include file="head.jsp"%>
    <title>Guess the number</title>
    <script>
        var restBaseUrl = "http://" + document.location.host + "/guess-the-number/api/v1/guess/number";
        var statusTimeout = 300000; // timeout form message

        function query() {
            var name = document.getElementById('name');
            var number = document.getElementById('number');
            var request = jQuery.param([
                {name: "name", value: name.value},
                {name: "number", value: number.value}
            ]);

            $.ajax({
                url: restBaseUrl,
                cache: false,
                data: request,
                dataType: "json",
                timeout: statusTimeout,
                type: "get",
                error: uiHandler,
                success: uiHandler});
        }

        function uiHandler(data) {
            var table = document.getElementById("result");
            table.innerHTML = "";
            var errorField = document.getElementById('resultError');

            var row = table.insertRow();
            row.classList.add("text-info");
            var cell = row.insertCell(0);

            if (data.status !== undefined) {
                cell.innerHTML = "Error: " + data.description;
            } else if (data.guessed == true)
                cell.innerHTML = "Bingo! You win!!";
            else
                cell.innerHTML = "Sorry but you loose!!";
        }
    </script>
</head>
<body>
<div class="container">
    <form>
        <table  class="table table-striped">
            <thead>
            <tr>
                <td><input name="name" id="name" type="text" class="form-control" placeholder="Name" autofocus="true"/></td>
                <td><input name="number" id="number" type="text" class="form-control" placeholder="Number"/></td>
                <td><input type="button" class="btn btn-lg btn-primary btn-block" value="I'm lucky" onclick="query()"></td>
            </tr>
            </thead>
        </table>
    </form>
    <div class="container">
        <table id="result" class="table table-striped"></table>
        <div id="resultError" class="alert"></div>
    </div>
</div>
</body>
</html>