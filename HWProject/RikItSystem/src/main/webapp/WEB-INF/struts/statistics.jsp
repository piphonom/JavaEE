<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <%@include file="/WEB-INF/common-jsp/head.jsp"%>
    <title>Statistics</title>
    <script>
        var webSocket = new WebSocket("ws://" + document.location.host + "/statistics");
        webSocket.onmessage = function(evt) {
            var table = document.getElementById("statistics");
            var data = JSON.parse(evt.data);
            for (var i = 0; i < data.length; i++) {
                var row = table.insertRow();
                row.classList.add("text-info");
                var recordId = row.insertCell(0);
                recordId.innerHTML = data[i].idStat;
                var pageName = row.insertCell(1);
                pageName.innerHTML = data[i].pageName;
                var clientIP = row.insertCell(2);
                clientIP.innerHTML = data[i].clientIP;
                var clientAgent = row.insertCell(3);
                clientAgent.innerHTML = data[i].clientName;
                var clientTime = row.insertCell(4);
                clientTime.innerHTML = data[i].clientTime;
                var serverTime = row.insertCell(5);
                serverTime.innerHTML = data[i].serverTime;
                var prevRecordId = row.insertCell(6);
                prevRecordId.innerHTML = data[i].prevIdStat;
                var origin = row.insertCell(7);
                origin.innerHTML = data[i].origin;
            }
        };
    </script>
</head>
<body>
<div class="container">
    <h2>Statistics</h2>
    <table id="statistics" class="table table-striped">
        <thead>
        <tr>
            <td>Record ID</td>
            <td>Page Name</td>
            <td>Client IP</td>
            <td>Client Agent</td>
            <td>Client Time</td>
            <td>Server Time</td>
            <td>Prev Record ID</td>
            <td>Origin (CORS)</td>
        </tr>
        </thead>
        <s:iterator value="statistics">
            <tr class="text-info">
                <td><s:property value="idStat"/></td>
                <td><s:property value="pageName"/></td>
                <td><s:property value="clientIP"/></td>
                <td><s:property value="clientName"/></td>
                <td><s:property value="clientTime"/></td>
                <td><s:property value="serverTime"/></td>
                <td><s:property value="prevIdStat"/></td>
                <td><s:property value="origin"/></td>
            </tr>
        </s:iterator>
    </table>
</div>
</body>
</html>
