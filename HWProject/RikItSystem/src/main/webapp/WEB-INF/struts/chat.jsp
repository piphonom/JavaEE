<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <%@include file="/WEB-INF/common-jsp/head.jsp"%>
    <title>Chat</title>
    <script>
        var webSocket = new WebSocket("ws://" + document.location.host + "/chat");

        webSocket.onmessage = function(evt) {
            var table = document.getElementById("messages");
            var data = JSON.parse(evt.data);
            for (var i = 0; i < data.length; i++) {
                var row = table.insertRow();
                row.classList.add("text-error");
                var recordId = row.insertCell(0);
                recordId.innerHTML = data[i].user;
                var pageName = row.insertCell(1);
                pageName.innerHTML = data[i].message;
            }
        };

        function sendMessage() {
            var messageField = document.getElementById('newMessage');
            webSocket.send(messageField.value);
            messageField.value = "";
        }
    </script>
</head>
<body>
<div class="container">
<h2>Chat</h2>
<table id="messages" class="table table-striped">
    <thead>
    <tr>
        <td>User</td>
        <td>Message</td>
    </tr>
    </thead>
    <s:iterator value="messages">
        <tr class="text-success">
            <td><s:property value="user"/></td>
            <td><s:property value="message"/></td>
        </tr>
    </s:iterator>
</table>
</div>
<div class="container">
    <div class="form-group">
        <input name="newMessage" id="newMessage" type="text" class="form-control" placeholder="Type message"
               autofocus="true"/>
        <button class="btn btn-lg btn-primary btn-block" type="submit" onclick="sendMessage()">Send</button>
    </div>
</div>
</body>
</html>
