<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="../../resources/img/favicon.ico" type="image/x-icon">
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script type="text/javascript" src="../../resources/js/preload.js"></script>
    <script type="text/javascript" src="../../resources/js/index_warning.js"></script>
    <style><%@include file="../../resources/css/preload.css"%></style>
    <style><%@include file="../../resources/css/select.css"%></style>
    <style><%@include file="../../resources/css/button.css"%></style>

    <title>Schedule</title>
</head>
<body>
<div id="p_prldr"><div class="contpre"><span class="svg_anm"></span><br>Wait For<br><small>Loading...</small></div></div>
<%--<div class="center">--%>
<div class="center-on-page">
<form onsubmit="return warn(this)" id="dispForm" method="get" action="/db/displaySchedule">

        <div class="select">
             <select name="group_num" id="selectGroup">
                <option>Choose group</option>
                <c:forEach var="groupList" items="${group}">
                    <option value="${groupList}">${groupList}</option>
                </c:forEach>
            </select>
</div>

    <div class="select">

             <select name="day_name" id="selectDay">
                <option>Choose Day</option>
                <option value="MON">Monday</option>
                <option value="TUE">Tuesday</option>
                <option value="WED">Wednesday</option>
                <option value="THU">Thursday</option>
                <option value="FRI">Friday</option>
                <option value="SAT">Saturday</option>
            </select>
    </div>
            <button form="dispForm">Show</button>


</form>
    <button class="edit_button" onclick=location.href="/db/editPage">Edit</button>
</div>
</body>
</html>
