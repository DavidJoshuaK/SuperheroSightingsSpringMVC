<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
        <link href="${pageContext.request.contextPath}/css/glDatePicker.default.css" rel="stylesheet" type="text/css">
        <link href="${pageContext.request.contextPath}/css/jquery.timepicker.css" rel="stylesheet" type="text/css"  />
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
            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <p>Hello : ${pageContext.request.userPrincipal.name}
                    | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
                </p>
            </c:if>

            <div class="row">
                <div class="col-md-6">
                    <h2>Sightings</h2>
                    <table id="superTable" class="table table-hover">
                        <tr>
                            <th width="30%">Date and Time</th>
                            <th width="30%">Sighted</th>
                            <th width="20%"></th>
                            <th width="20%"></th>
                        </tr>
                        <c:forEach var="currentSighting" items="${sightingList}">
                            <tr>
                                <td>
                                    <a href="displaySightingDetails?sightingId=${currentSighting.sightingId}">
                                        <c:out value="${currentSighting.date}"/> 
                                </td>
                                <td>
                                    <c:forEach var="currentSuper" items="${currentSighting.supers}">
                                        <c:out value="${currentSuper.name}"/>
                                    </c:forEach>
                                </td>
                                <td>
                                    <sec:authorize access="hasRole('ROLE_USER')">
                                        <a href="displayEditSightingForm?sightingId=${currentSighting.sightingId}">
                                            Edit
                                        </a>
                                    </sec:authorize>
                                </td>
                                <td>
                                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                                        <a href="deleteSighting?sightingId=${currentSighting.sightingId}">
                                            Delete
                                        </a>
                                    </sec:authorize>
                                </td>

                            </tr>
                        </c:forEach>
                    </table>

                </div>
                <div class="col-md-6">
                    <sec:authorize access="hasRole('ROLE_USER')">
                        <h2>Add Sighting</h2>
                        <form class="form-horizontal" 
                              role="form" method="POST" 
                              action="createSighting" name="sighting" modelAttribute="sighting" >
                            <div class="form-group">
                                <label for="add-date" class="col-md-4 control-label">Date:</label>
                                <div class="col-md-8">
                                    <input type="text" id="datepicker" name="date" class="form-control" placeholder="Click to select date" readonly/>

                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-time" class="col-md-4 control-label">Time:</label>
                                <div class="col-md-8">
                                    <input type="text" id="timepicker" name="time" class="form-control"/>

                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-name" class="col-md-4 control-label">Name:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="name" placeholder="Name"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-description" class="col-md-4 control-label">Description:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="description" placeholder="Description"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="add-address" class="col-md-4 control-label">Address:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="address" placeholder="Address"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-city" class="col-md-4 control-label">City:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="city" placeholder="City"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-state" class="col-md-4 control-label">State:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="state" placeholder="State"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-zipcode" class="col-md-4 control-label">Zip code:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="zipcode" placeholder="Zip code"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-latitude" class="col-md-4 control-label">Latitude:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="latitude" placeholder="Latitude (00.000000)"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-longitude" class="col-md-4 control-label">Longitude</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="longitude" placeholder="Longitude (00.000000)"/>
                                </div>
                            </div>


                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <input id="submit" type="submit" class="btn btn-default" value="Create Sighting"/>
                                </div>
                            </div>
                        </form>
                    </sec:authorize>
                </div> 



                <div id="resultContainer" style="display: none;">
                    <h4>Sighting Added</h4><br>
                    <table id="jjj" class="table table-hover">
                        <tr>
                            <th width="40%">Name</th>
                            <th width="30%">Description</th>
                            <th width="15%"></th>
                            <th width="15%"></th>
                        </tr>
                        <tbody id="newSights"/>
                    </table>  
                </div>

            </div>
        </div>        

        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/sighting.js"></script>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/glDatePicker.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery.timepicker.min.js"></script>

    </body>
</html>
