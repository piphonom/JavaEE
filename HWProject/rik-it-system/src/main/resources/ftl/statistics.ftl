<html lang="en">
<head>
    <#include "header.ftl">
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
        <#list statistics as record>
            <tr class="text-info">
                <td>${record.idStat!}</td>
                <td>${record.pageName!}</td>
                <td>${record.clientIP!}</td>
                <td>${record.clientName!}</td>
                <td>${record.clientTime!}</td>
                <td>${record.serverTime!}</td>
                <td>${record.prevIdStat!}</td>
                <td>${record.origin!}</td>
            </tr>
        </#list>
    </table>
</div>
</body>
</html>