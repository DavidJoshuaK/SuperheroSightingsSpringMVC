<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Superhero Database</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">  
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/SuperSightingStyle.css">
    </head>
    <body>
        <div class="container">
            <div id="topPage">
                <header id="subtitle">
                    <div class="row">
                        <div class="col-md-12"><h1>H.E.R.O</h1></div>
                    </div>              
                </header>
                <div class="row" >
                    <div class="col-md-12"><h3>The Hero Education and Relationship Organization</h3></div>
                </div>
            </div>

            <div class="navbar">
                <ul class="nav nav-tabs"> <li role="presentation"
                                              class="active">
                        <a href="${pageContext.request.contextPath}/index">
                            Home
                        </a> </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displaySuperheroPage">
                            Supers
                        </a> </li>   
                    <li role="presentation" >
                        <a href="${pageContext.request.contextPath}/displayOrganizationPage">
                            Organizations
                        </a>
                    </li>     
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displaySightingsPage">
                            Sightings
                        </a>
                    </li>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <li role="presentation">
                            <a href="${pageContext.request.contextPath}/displayUserList">
                                User Admin
                            </a>
                        </li>                        
                    </sec:authorize>
                </ul> 
            </div>
            <div class="row"> 
                <div class="col-md-4"></div>
                <p class="col-md-4">
                    Welcome to the H.E.R.O sightings page! Until now, a place to record sightings was unavailable. At H.E.R.O,
                    we wanted to provide a place to give you opportunity to keep track of all your favorite, and most-feared, supers!
                </p> 
                <div class="col-md-4"></div>
            </div>

            <div class="col-md-3"></div>
            <div class="row">
                <div class="col-md-6">
                    <h2>Most recent Sightings!</h2>
                    <div style="overflow-y:auto;"> 
                        <table id="superTable" class="table table-hover">
                            <thead>
                                <tr>
                                    <th width="50%">Date</th>
                                    <th width="50%">Heroes and Villains Seen</th>

                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="currentSighting" items="${sightingsByDate}">
                                    <tr>
                                        <td>
                                            <a href="displaySightingDetails?sightingId=${currentSighting.sightingId}">
                                                <c:out value="${currentSighting.date}"/> 
                                            </a>
                                        </td>
                                        <td>
                                            <c:forEach var = "currentSuper" items="${currentSighting.supers}">
                                                <a href="displaySupersDetails?superId=${currentSuper.superId}">
                                                    ${currentSuper.name}
                                                </a>
                                            </c:forEach>

                                        </td>
                                     
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>

                </div>
                <div class="col-md-3"></div>

            </div>
            <!-- Placed at the end of the document so the pages load faster -->
            <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/supers.js"></script>
    </body>
</html>

