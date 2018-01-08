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
                    <li role="presentation" class="active">
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
            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <p>Hello : ${pageContext.request.userPrincipal.name}
                    | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
                </p>
            </c:if>

            <div class="row">
                <div class="col-md-6">
                    <h2>Organizations</h2>
                    <table id="superTable" class="table table-hover">
                        <tr>
                            <th width="30%">Name</th>
                            <th width="30%">Description</th>
                            <th width="20%"></th>
                            <th width="20%"></th>
                        </tr>
                        <c:forEach var="currentOrg" items="${orgList}">
                            <tr>
                                <td>
                                    <a href="displayOrganizationDetails?organizationId=${currentOrg.organizationId}">
                                        <c:out value="${currentOrg.name}"/> 
                                </td>
                                <td>
                                    <c:out value="${currentOrg.description}"/>
                                </td>
                                <td>
                                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                                        <a href="displayEditOrganizationForm?organizationId=${currentOrg.organizationId}">
                                            Edit
                                        </a>
                                    </sec:authorize>
                                </td>
                                <td>
                                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                                        <a href="deleteOrganization?organizationId=${currentOrg.organizationId}">
                                            Delete
                                        </a>
                                    </sec:authorize>
                                </td>

                            </tr>
                        </c:forEach>
                    </table>

                </div>
                <div class="col-md-6">
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <h2>Add New Organization</h2>
                        <form class="form-horizontal" 
                              role="form" method="POST" name="organization"
                              action="createOrganization" >
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
                                <label for="add-type" class="col-md-4 control-label">Type:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="type" placeholder="Type"/>
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
                                <label for="add-phone" class="col-md-4 control-label">Phone:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="phone" placeholder="Phone (e.g. 000-000-0000"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <input id="submit" type="submit" class="btn btn-default" value="Create Organization"/>
                                </div>
                            </div>
                        </form>
                    </sec:authorize>
                    <div  id="resultContainer" style="display: none;">
                        <h2>Organizations Added</h2>
                        <table id="jjj" class="table table-hover">
                            <tr>
                                <th width="40%">Name</th>
                                <th width="30%">Description</th>
                                <th width="15%"></th>
                                <th width="15%"></th>
                            </tr>
                            <tbody id="jj"/>
                        </table>                    
                    </div>
                </div> 


            </div>   

        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/organization.js"></script>
    </body>
</html>
