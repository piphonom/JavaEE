<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/common.css" rel="stylesheet">
    <link href="/css/style.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/css/bootstrap-select.min.css" rel="stylesheet">

    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <script src="/js/jquery-2.1.4.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/js/bootstrap-select.min.js"></script>
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
            </tr>
        </thead>
        <#list statistics as record>
            <tr class="text-info">
                <td>${record.idStat!""}</td>
                <td>${record.pageName!""}</td>
                <td>${record.clientIP!""}</td>
                <td>${record.clientName!""}</td>
                <td>${record.clientTime!""}</td>
                <td>${record.serverTime!""}</td>
                <td>${record.prevIdStat!""}</td>
            </tr>
        </#list>
    </table>
</div>
</body>
</html>