<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="session" value="${pageContext.request.getSession(false)}"/>

<html>
<head>
    <%@include file="/WEB-INF/common-jsp/head.jsp"%>
    <title>Chat</title>
    <script>
        var webSocket = new WebSocket("ws://" + document.location.host + "/chat");

        webSocket.onmessage = function(evt) {
            var table = document.getElementById("messages");
            var data = JSON.parse(evt.data);

            var row = table.insertRow(1);
            row.classList.add("text-success");
            var user = row.insertCell(0);
            user.innerHTML = data.user;
            var message = row.insertCell(1);
            message.innerHTML = data.message;
        };

        function sendMessage() {
            var messageField = document.getElementById('newMessage');
            var dataToSend = JSON.stringify({
                user: "${session.getAttribute('user')}",
                message: messageField.value
            });
            webSocket.send(dataToSend);
            messageField.value = "";
        }
    </script>
</head>
<body>
<div class="container">
    <form class="form-signin">
        <div class="form-group">
            <textarea name="newMessage" id="newMessage" type="text" class="inputWidth form-control" placeholder="Type message"
                    autofocus="true"></textarea>
            <input type="button" class="btn btn-lg btn-primary btn-block" value="Send" onclick="sendMessage()">
        </div>
    </form>
</div>
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
</body>
</html>
