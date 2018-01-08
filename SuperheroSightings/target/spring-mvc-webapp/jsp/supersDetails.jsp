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
                    <li role="presentation" class="active">
                        <a href="${pageContext.request.contextPath}/displaySuperheroPage">
                            Supers
                        </a> </li>   
                    <li role="presentation">
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
                <div class="col-md-6">
                    <h2>
                      <c:out value="${supers.name}"/> 
                    </h2>
                    <p>
                        Description: <c:out value="${supers.description}"/>
                    </p>
                    <p>Powers: <c:forEach var = "currentPower" items="${powers}">
                            <a class="ex1" href="viewPower?powerId=${currentPower.powerId}">
                                ${currentPower.description}
                            </a>
                        </c:forEach>
                    </p> 
                    <p>Organizations: <c:forEach var = "currentOrganization" items="${organizations}">
                            <a class="ex1" href="displayOrganizationDetails?organizationId=${currentOrganization.organizationId}">
                                ${currentOrganization.name}
                            </a>
                        </c:forEach>
                        
                    </p>
                    <p> Places this super was sighted:
                        <c:forEach var="currentSighting" items="${sighting}">
                          ${currentSighting.name}
                        </c:forEach>
                </div>
                <div class="col-md-6">
                    <h2>Add Power</h2>
                    <sf:form class="form-horizontal" 
                             role="form" method="POST" 
                             modelAttribute="supers"
                             action="createPower">
                        <div class="form-group">
                            <label for="add-description" class="col-md-4 control-label">Description:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="description" placeholder="Description"
                                       path="description"/>
                                <sf:hidden path="superId"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Create Power" <c:out value="${supers.description}"/>/>
                            </div>
                        </div>
                    </sf:form>
                </div>
            </div>    
            <div class ="row">
                <div class="col-md-4">
                    <table id="powerTable" class="table table-hover">
                        <thead>
                        <tr>
                            <th>Click to Add Power</th>
                        </tr>
                      </thead>
                      <tbody>
                        <c:forEach var="currentPower" items="${powerList}">
                            <tr>
                                <td>
                                    <!--<form action= a href="addPowerToSuper?superId=${supers.superId}&powerId=${currentPower.powerId}" method="POST">-->
                                    <a class="ex1" href="addPowerToSuper?superId=${supers.superId}&powerId=${currentPower.powerId}">
                                        <c:out value="${currentPower.description}"/>

                                        <!--</form>-->
                                </td>
                            </tr>
                        </c:forEach>
                      </tbody>
                    </table>
                </div>
          
                <div class="col-md-4">
                    <table id="powerTable" class="table table-hover">
                        <tr>
                            <th>Click to add Membership</th>
                        </tr>
                        <c:forEach var="currentOrg" items="${orgList}">
                            <tr>
                                <td>
                                    <!--<form action= a href="addPowerToSuper?superId=${supers.superId}&powerId=${currentPower.powerId}" method="POST">-->
                                    <a class="ex1" href="addOrganizationToSuper?superId=${supers.superId}&organizationId=${currentOrg.organizationId}">
                                        <c:out value="${currentOrg.name}"/>

                                        <!--</form>-->
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