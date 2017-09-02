<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="../../resources/img/favicon.ico" type="image/x-icon">
    <%--Load JQuery--%>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <%--Load Preload Script--%>
    <script type="text/javascript" src="../../resources/js/preload.js"></script>
    <%--Load style for tab--%>
    <style>
        <%@include file="../../resources/css/tab.css" %>
    </style>
    <%--Load style for Preload--%>
    <style>
        <%@include file="../../resources/css/preload.css" %>
    </style>
    <%--Load style for table--%>
    <style>
        <%@include file="../../resources/css/table.css" %>
    </style>
    <%--Navigation Bar style--%>
    <style>
        <%@include file="../../resources/css/navbar.css" %>
    </style>
    <%--add-button--%>
    <style>
        <%@include file="../../resources/css/add_button.css" %>
    </style>
    <%--Charts Styles--%>
    <style>
        <%@include file="../../resources/css/graph.css" %>
    </style>
    <%--Load autocomplete time script--%>
    <script type="text/javascript" src="../../resources/js/autocomplete.js"></script>
    <%--Load script that alert`s the result of INSERT into schedule result--%>
    <script type="text/javascript" src="../../resources/js/insert_result.js"></script>
    <%--Load google chart API--%>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <%--add-button js--%>
    <script type="text/javascript" src="../../resources/js/add_button.js"></script>
    <%--Load Draw Google Chart Script--%>
    <%--AIR CONDITION CHART--%>
    <script type="text/javascript" src="../../resources/js/draw_chart.js"></script>

    <script type="text/javascript" src="../../resources/js/motivation_lvl.js"></script>

    <%-- <script type="text/javascript">
         // Load the Visualization API and the piechart package.
         google.charts.load('current', {'packages':['corechart']});

         // Set a callback to run when the Google Visualization API is loaded.
         google.charts.setOnLoadCallback(drawChart);

         function drawChart() {
             var jsonData = $.ajax({
                 url: "/db/jsondata",
                 dataType: "json",
                 async: false
             }).responseText;

             // Create our data table out of JSON data loaded from server.
             var data = new google.visualization.DataTable(jsonData);

             // Instantiate and draw our chart, passing in some options.
             var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
             chart.draw(data, {width: 400, height: 240});
         }
     </script>--%>


    <title>Editor`s</title>
</head>
<body onload="insertResult(${insRes})">
<div id="p_prldr">
    <div class="contpre"><span class="svg_anm"></span><br>Wait For<br>
        <small>Loading...</small>
    </div>
</div>

<c:url value="/j_spring_security_logout" var="logoutUrl"/>
<form action="${logoutUrl}" method="post" id="logoutForm"></form>

<script>
    function formSubmit() {
        document.getElementById("logoutForm").submit();
    }
</script>

<div id="pagewrap">
    <header id="main">
    </header>
    <nav>
        <ul>
            <li><a href="../../">Home</a></li>
            <li><a href="javascript:formSubmit()">LogOut</a></li>
            <%-- <li><a href="#">Gallery</a></li>
             <li><a href="#">Contacts</a></li>--%>
        </ul>
    </nav>
</div>
<div class="main">

    <div class="wrapper">
        <div class="tabs">
            <span class="tab">Lesson-Classroom</span>
            <span class="tab">Teacher-Lesson</span>
            <span class="tab">Lesson-Group</span>
            <span class="tab">Add record</span>
            <span class="tab">Performance</span>
        </div>
        <div class="tab_content">

            <div class="tab_item">

                <table border="1" style="float:left;">
                    <tr>
                        <th>Lesson</th>
                    </tr>
                    <c:forEach var="firstTabLes" items="${firstTabLes}">
                        <tr>
                            <td><c:out value="${firstTabLes}"/></td>
                        </tr>
                    </c:forEach>
                </table>

                <table border="1">
                    <tr>
                        <th>Classroom</th>
                    </tr>
                    <c:forEach var="firstTabClass" items="${firstTabClass}">
                        <tr>
                            <td><c:out value="${firstTabClass}"/></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>


            <div class="tab_item">

                <table border="1" style="float:left;">
                    <tr>
                        <th>Teacher</th>
                    </tr>
                    <c:forEach var="secondTabFIO" items="${secondTabFIO}">
                        <tr>
                            <td><c:out value="${secondTabFIO}"/></td>
                        </tr>
                    </c:forEach>
                </table>

                <table border="1">
                    <tr>
                        <th>Lesson</th>
                    </tr>
                    <c:forEach var="secondTabLeson" items="${secondTabLesson}">
                        <tr>
                            <td><c:out value="${secondTabLeson}"/></td>
                        </tr>
                    </c:forEach>
                </table>

            </div>


            <div class="tab_item">

                <table border="1" style="float:left;">
                    <tr>
                        <th>Group</th>
                    </tr>
                    <c:forEach var="thirdTabGroupNum" items="${thirdTabGroupNum}">
                        <tr>
                            <td><c:out value="${thirdTabGroupNum}"/></td>
                        </tr>
                    </c:forEach>
                </table>

                <table border="1">
                    <tr>
                        <th>Lesson</th>
                    </tr>
                    <c:forEach var="thirdTabLessonName" items="${thirdTabLessonName}">
                        <tr>
                            <td><c:out value="${thirdTabLessonName}"/></td>
                        </tr>
                    </c:forEach>
                </table>

            </div>


            <div class="tab_item">

                <form method="get" id="insertForm" action="/db/insSchedule">
                    <table border="1">
                        <tr>
                            <th align="left">DAY:</th>
                            <td><select name="day_name" id="day-name">
                                <option selected value="MON">Monday</option>
                                <option value="TUE">Tuesday</option>
                                <option value="WED">Wednesday</option>
                                <option value="THU">Thursday</option>
                                <option value="FRI">Friday</option>
                                <option value="SAT">Saturday</option>
                            </select></td>
                        </tr>

                        <tr>
                            <th align="left">LESSON NUMBER:</th>
                            <td><select name="lesson_number" id="lesson-number" onchange="autocompleteBegEndTime()">
                                <option selected value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                                <option value="6">6</option>
                                <option value="7">7</option>
                            </select></td>
                        </tr>

                        <tr>
                            <th align="left">BEGIN TIME:</th>
                            <td><select name="begin_time" id="begin-time">
                                <option selected value="08:30">08:30</option>
                            </select></td>
                        </tr>

                        <tr>
                            <th align="left">END TIME:</th>
                            <td><select name="end_time" id="end-time">
                                <option selected value="10:00">10:00</option>
                            </select></td>
                        </tr>

                        <tr>
                            <th align="left">GROUP NUMBER:</th>
                            <td><select name="group_number">
                                <c:forEach var="fourthTabGroupNum" items="${fourthTabGroupNum}">
                                    <option value="${fourthTabGroupNum}">${fourthTabGroupNum}</option>
                                </c:forEach>
                            </select></td>
                        </tr>

                        <tr>
                            <th align="left">CORPUS ADDRESS:</th>
                            <td><select name="corpus_id">
                                <option selected value="1">Kronverskiy pr. 49</option>
                                <option value="2">Lomonosova d. 9</option>
                            </select></td>
                        </tr>

                        <tr>
                            <th align="left">CLASSROOM NUMBER:</th>
                            <td><select name="classroom_id">
                                <c:forEach var="fourthTabClass" items="${fourthTabClass}">
                                    <option value="${fourthTabClass}">${fourthTabClass}</option>
                                </c:forEach>
                            </select></td>
                        </tr>

                        <tr>
                            <th align="left">LESSON NAME:</th>
                            <td><select name="lesson_name">
                                <c:forEach var="fourthTabLesson" items="${fourthTabLesson}">
                                    <option value="${fourthTabLesson}">${fourthTabLesson}</option>
                                </c:forEach>
                            </select></td>
                        </tr>

                        <tr>
                            <th align="left">LESSON TYPE:</th>
                            <td><select name="lesson_type" id="lesson-type">
                                <option selected value="lecture">Lecture</option>
                                <option value="practice">Practice</option>
                                <option value="lab">laboratory</option>
                            </select></td>
                        </tr>

                        <tr>
                            <th align="left">TEACHER:</th>
                            <td><select name="teacher_id">
                                <c:forEach var="fourthTabTeacher" items="${fourthTabTeacher}">
                                    <option value="${fourthTabTeacher}">${fourthTabTeacher}</option>
                                </c:forEach>
                            </select></td>
                        </tr>


                        <%--   <tr>


                               <td><input type="submit" value="Add"/></td>

                           </tr>--%>

                    </table>

                    <div class="container">
                        <button type="submit" form="insertForm" id="button"></button>
                    </div>

                </form>
            </div>

            <div class="tab_item">
                <div class="chart_air" id="myPieChart"></div>
                <div id="chart_div"></div>
                <div class="chart_mot" id="chart_mot_div"></div>
            </div>

        </div>
    </div>

</div>


<%--<form>
    <input type="button" value="Main" onClick='location.href="http://itmo-schedule.azurewebsites.net/"'>
</form>--%>

<%--Load tab script--%>
<script type="text/javascript" src="../../resources/js/tab.js"></script>

</body>
</html>
