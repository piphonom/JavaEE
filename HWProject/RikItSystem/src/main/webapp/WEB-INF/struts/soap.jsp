<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <%@include file="/WEB-INF/common-jsp/head.jsp"%>
    <title>Credit calculator</title>
    <script>
        var restBaseUrl = "http://" + document.location.host + "/rik-it-system/api/v1";
        var sunrisesetUrl = restBaseUrl + "/sunriseset";
        var ipUrl = restBaseUrl + "/iplocation";
        var statusTimeout = 300000; // timeout form message

        function querySunRiseSet() {
            var latitude = document.getElementById('latitude');
            var longitude = document.getElementById('longitude');

            var request = jQuery.param([
                {name: "latitude", value: latitude.value},
                {name: "longitude", value: longitude.value}
            ]);

            $.ajax({
                url: sunrisesetUrl,
                cache: false,
                data: request,
                dataType: "json",
                timeout: statusTimeout,
                type: "get",
                error: uiHandlerSun,
                success: uiHandlerSun});
        }

        function uiHandlerSun(data) {
            var sunrise = document.getElementById("sunrise");
            sunrise.innerHTML = "Sunrise: " + data.rise;
            var sunset = document.getElementById("sunset");
            sunset.innerHTML = "Sunset: " + data.set;
        }

        function queryIP() {
            var ip = document.getElementById('ip');

            var request = jQuery.param([
                {name: "ip", value: ip.value}
            ]);

            $.ajax({
                url: ipUrl,
                cache: false,
                data: request,
                dataType: "json",
                timeout: statusTimeout,
                type: "get",
                error: uiHandlerIP,
                success: uiHandlerIP});
        }

        function uiHandlerIP(data) {
            var location = document.getElementById("location");
            location.innerHTML = "IP is located in " + data.location;
        }
    </script>
</head>
<body>
<div class="container">
    <form>
        <table  class="table table-striped">
            <thead>
            <tr>
                <td><input name="latitude" id="latitude" type="text" class="form-control" placeholder="Latitude" autofocus="true"/></td>
                <td><input name="longitude" id="longitude" type="text" class="form-control" placeholder="Longitude"/></td>
                <td><input type="button" class="btn btn-lg btn-primary btn-block" value="Get sunrise time" onclick="querySunRiseSet()"></td>
            </tr>
            </thead>
        </table>
        <span id="sunrise" name="sunrise" class="alert"></span>
        <br>
        <span id="sunset" name="sunset" class="alert"></span>
    </form>
</div>
<div class="container">
    <form>
        <table  class="table table-striped">
            <thead>
            <tr>
                <td><input name="ip" id="ip" type="text" class="form-control" placeholder="IP v4 address" autofocus="true"/></td>
                <td><input type="button" class="btn btn-lg btn-primary btn-block" value="Get location" onclick="queryIP()"></td>
            </tr>
            </thead>
        </table>
        <span id="location" name="location" class="alert"></span>
    </form>
</div>
</body>
</html>