<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <%@include file="/WEB-INF/common-jsp/head.jsp"%>
    <title>Statistics</title>
</head>
<body>
<div class="container">
    <h2>Statistics</h2>
    <table class="table table-striped">
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
