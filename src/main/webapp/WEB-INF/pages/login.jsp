<%@page session="true"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="../../resources/img/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,700">
    <style><%@include file="../../resources/css/login.css"%></style>
    <title>LogIn To Continue</title>
    <style>
        .error {
            padding: 15px;
            margin-bottom: 20px;
            border: 1px solid transparent;
            border-radius: 4px;
            color: #a94442;
            background-color: #f2dede;
            border-color: #ebccd1;
            text-align: center;
        }

        .msg {
            padding: 15px;
            margin-bottom: 20px;
            border: 1px solid transparent;
            border-radius: 4px;
            color: #31708f;
            background-color: #d9edf7;
            border-color: #bce8f1;
            text-align: center;
        }

    </style>
</head>
<body>

<c:if test="${not empty error}">
    <div class="error">${error}</div>
</c:if>
<c:if test="${not empty msg}">
    <div class="msg">${msg}</div>
</c:if>

<div id="login">
    <form name='form-login' action= "<c:url value='/j_spring_security_check' />" method='POST'>
        <span class="fontawesome-user"></span>
        <input type="text" id="user" name="username" placeholder="Username">

        <span class="fontawesome-lock"></span>
        <input type="password" id="pass" name="password" placeholder="Password">

        <input type="submit" value="Login">

        <input type="button" value="Back to Main" onclick=location.href="/">

    </form>

</div>
</body>
</html>
