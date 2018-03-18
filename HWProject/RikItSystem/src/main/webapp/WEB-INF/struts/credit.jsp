<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <%@include file="/WEB-INF/common-jsp/head.jsp"%>
    <title>Credit calculator</title>
    <script>
        var restBaseUrl = "http://" + document.location.host + "/rik-it-system/api/v1/credit/calculator";
        var annuityUrl = restBaseUrl + "/annuity";
        var differentialUrl = restBaseUrl + "/differential";
        var statusTimeout = 300000; // timeout form message

        function query(url, uiHandler) {
            var total = document.getElementById('total');
            var percent = document.getElementById('percent');
            var period = document.getElementById('period');
            var request = jQuery.param([
                {name: "total", value: total.value},
                {name: "percent", value: percent.value},
                {name: "period", value: period.value}
            ]);

            $.ajax({
                url: url,
                cache: false,
                data: request,
                dataType: "json",
                timeout: statusTimeout,
                type: "get",
                error: uiHandler,
                success: uiHandler});
        }

        function annuityUiHandler(data) {
            var table = document.getElementById("annuitySchedule");
            table.innerHTML = "";
            var errorField = document.getElementById('annuityPaymentError');
            var payment = data.payment;
            if (payment !== null) {
                var row = table.insertRow();
                row.classList.add("text-info");
                var cell = row.insertCell(0);
                cell.innerHTML = payment;
            } else {
                errorField.classList.add("alert-warning");
                errorField.innerText="Calculation error";
            }
        }

        function differentialUiHandler(data) {
            var table = document.getElementById("differentialSchedule");
            table.innerHTML = "";
            var errorField = document.getElementById('differentialPaymentError');
            var schedule = data.schedule;
            for (var i = 0; i < schedule.length; i++) {
                var row = table.insertRow();
                row.classList.add("text-info");
                var month = row.insertCell(0);
                month.innerHTML = i + 1;
                var payment = row.insertCell(1);
                payment.innerHTML = schedule[i];
            }
        }

        function calculate() {
            query(annuityUrl, annuityUiHandler);
            query(differentialUrl, differentialUiHandler);
        }
    </script>
</head>
<body>
<div class="container">
    <form>
        <table  class="table table-striped">
            <thead>
            <tr>
                <td><input name="total" id="total" type="text" class="form-control" placeholder="Total" autofocus="true"/></td>
                <td><input name="percent" id="percent" type="text" class="form-control" placeholder="Percent"/></td>
                <td><input name="period" type="text" id="period" class="form-control" placeholder="Period"/><td>
                <td><input type="button" class="btn btn-lg btn-primary btn-block" value="Calculate" onclick="calculate()"></td>
            </tr>
            </thead>
        </table>
    </form>
    <div class="container">
        <h4>Annuity payment per month</h4>
        <table id="annuitySchedule" class="table table-striped"></table>
        <div id="annuityPaymentError" class="alert"></div>
    </div>
    <div class="container">
        <h4>Differential payments schedule</h4>
        <table id="differentialSchedule" class="table table-striped"></table>
        <div id="differentialPaymentError" class="alert"></div>
    </div>
</div>
</body>
</html>