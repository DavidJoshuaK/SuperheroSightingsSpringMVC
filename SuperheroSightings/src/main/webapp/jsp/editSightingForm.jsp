<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- Directive for Spring Form tag libraries -->
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

            <hr/>

            <div class="navbar">
                <ul class="nav nav-tabs"> <li role="presentation"
                                              >
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
            <h2>Edit Sighting</h2>
            <sf:form class="form-horizontal" role="form" modelAttribute="SightingLoc2"
                     action="editSighting" method="POST">
                <div class="form-group">
                    <label for="add-name" class="col-md-4 control-label">Name:</label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-name"
                                  path="name" placeholder="Name"/>
                        <sf:errors path="name" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-description" class="col-md-4 control-label">Description:</label>
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-description"
                                  path="description" placeholder="Description"/>
                        <sf:errors path="description" cssclass="error"></sf:errors>

                        </div>
                    </div>

                    <div class="form-group">
                        <label for="add-address" class="col-md-4 control-label">Address:</label>
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-address"
                                  path="address" placeholder="Address"/>
                        <sf:errors path="address" cssclass="error"></sf:errors>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="add-city" class="col-md-4 control-label">City:</label>
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-city"
                                  path="city" placeholder="City"/>
                        <sf:errors path="city" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-state" class="col-md-4 control-label">State:</label>
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-state"
                                  path="state" placeholder="State"/>
                        <sf:errors path="state" cssclass="error"></sf:errors>
                        </div>
                    </div>    
                    <div class="form-group">
                        <label for="add-zipcode" class="col-md-4 control-label">Zip code:</label>
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-zipcode"
                                  path="zipcode" placeholder="Zip code:"/>
                        <sf:errors path="zipcode" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-latitude" class="col-md-4 control-label">Latitude:</label>
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-latitude"
                                  path="latitude" placeholder="latitude"/>
                        <sf:errors path="latitude" cssclass="error"></sf:errors>

                        </div>
                    </div>    
                    <div class="form-group">
                        <label for="add-longitude" class="col-md-4 control-label">Longitude</label>
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-longitude"
                                  path="longitude" placeholder="longitude"/>
                        <sf:errors path="longitude" cssclass="error"></sf:errors>


                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-date" class="col-md-4 control-label">Date seen:</label>
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-date"
                                  path="date" placeholder="date"/>
                        <sf:errors path="date" cssclass="error"></sf:errors>
                        <sf:hidden path="locationId"/>
                        <sf:hidden path="sightingId"/>
                    </div>
                </div>   
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-default" value="Update Sighting"/>
                    </div>
                </div>
            </sf:form>                
        </div>

        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/supers.js"></script>
    </body>
</html>