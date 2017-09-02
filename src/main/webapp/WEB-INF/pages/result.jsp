
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="../../resources/img/favicon.ico" type="image/x-icon">
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script type="text/javascript" src="../../resources/js/preload.js"></script>
    <style><%@include file="../../resources/css/preload.css"%></style>
    <style><%@include file="../../resources/css/result_table.css"%></style>
    <style><%@include file="../../resources/css/button.css"%></style>
    <title>Schedule</title>
</head>
<body>
<div id="p_prldr"><div class="contpre"><span class="svg_anm"></span><br>Wait For<br><small>Loading...</small></div></div>
<h2>Schedule info for group ${groupNumber} on ${dayName}</h2>

<%--<form>--%>
    <%--<input type="button" value="Main" onClick='location.href="/"'>--%>
<%--</form>--%>

<table width="80%" align="center">

    <div id="head_nav">
    <tr><th>Lesson Number</th>
        <c:forEach var="lsnum" items="${lesNum}">
            <td><c:out value="${lsnum}"/></td>
        </c:forEach>
    </tr>
    </div>

    <tr><th>Begin Time</th>
        <c:forEach var="begtm" items="${begTime}">
            <td><c:out value="${begtm}"/></td>
        </c:forEach>
    </tr>

    <tr><th>End Time</th>
        <c:forEach var="endtm" items="${endTime}">
            <td><c:out value="${endtm}"/></td>
        </c:forEach>
    </tr>

    <tr><th>Corpus Address</th>
        <c:forEach var="corpid" items="${corpId}">
            <td><c:out value="${corpid}"/></td>
        </c:forEach>
    </tr>

    <tr><th>Classroom Number</th>
        <c:forEach var="classid" items="${classrId}">
            <td><c:out value="${classid}"/></td>
        </c:forEach>
    </tr>

    <tr><th>Lesson Name</th>
        <c:forEach var="lesname" items="${lesName}">
            <td><c:out value="${lesname}"/></td>
        </c:forEach>
    </tr>

    <tr><th>Type of lesson</th>
        <c:forEach var="lest" items="${lesType}">
            <td><c:out value="${lest}"/></td>
        </c:forEach>
    </tr>

    <tr><th>Teacher</th>
        <c:forEach var="teach" items="${teacher}">
            <td><c:out value="${teach}"/></td>
        </c:forEach>
    </tr>

</table>


    <button class="home_button" onclick=location.href="/">Home</button>

</body>
</html>


