<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
                <ul class="nav nav-tabs"> <li role="presentation">                                            
                        <a href="${pageContext.request.contextPath}/index">
                            Home
                        </a> </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displaySuperheroPage">
                            Supers
                        </a> </li>   
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displayOrganizationPage">
                            Organizations
                        </a>
                    </li>  
                    <li role="presentation" class="active">
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
                <div class="col-md-6">
                    
                    <h3>
                        The heroes and villains seen at this location on <c:out value="${sighting.date}"/>: 
                        
                    </h3>
                        <c:forEach var = "currentSuper" items="${supers}">
                            <a class="ex1" href="displaySupersDetails?superId=${currentSuper.superId}">
                                ${currentSuper.name}
                            </a>
                        </c:forEach>
                    <p>
                        Name: <c:out value="${location.name}"/> 
                    </p>
                    <p>
                        Description: <c:out value="${location.description}"/>
                    </p>
                    <p>
                        Address: <c:out value="${location.address}"/>
                    </p>
                    <p>
                        City: <c:out value="${location.city}"/>
                    </p>
                    <p>
                        State: <c:out value="${location.state}"/>
                    </p>
                    <p>
                        Zip code: <c:out value="${location.zipcode}"/>
                    </p>
                    <p>
                        Latitude: <c:out value="${location.latitude}"/>
                    </p>
                    <p>
                        Longitude: <c:out value="${location.longitude}"/>
                    </p>


                </div>


                <div class="col-md-6">
                    <table id="superTable" class="table table-hover">
                        <tr>
                            <h3>What superheroes/villains were seen at this location?</h3>
                        </tr>
                        <c:forEach var="currentSuper" items="${superList}">
                            <tr>
                                <td>
                                    <a href="addSuperToSighting?superId=${currentSuper.superId}&sightingId=${sighting.sightingId}">
                                        <c:out value="${currentSuper.name}"/>

                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>


            </div>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
         <script src="${pageContext.request.contextPath}/js/supers.js"></script>
    </body>
</html>