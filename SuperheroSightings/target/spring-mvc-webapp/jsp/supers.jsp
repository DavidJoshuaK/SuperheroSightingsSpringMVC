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
                            
            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <p>Hello : ${pageContext.request.userPrincipal.name}
                    | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
                </p>
            </c:if>
                
            <div class="row">
                <div class="col-md-6">
                    <h2>Superheroes/villains</h2>
                    <div style="overflow-y:auto;"> 
                        <table id="superTable" class="table table-hover">
                            <thead>
                                <tr>
                                    <th width="40%">Name</th>
                                    <th width="30%">Description</th>
                                    <th width="15%"></th>
                                    <th width="15%"></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="currentSuper" items="${superList}" >

                                    <tr>
                                        <td>
                                            <a href="displaySupersDetails?superId=${currentSuper.superId}">
                                                <c:out value="${currentSuper.name}"/> 
                                        </td>
                                        <td>
                                            <c:out value="${currentSuper.description}"/>
                                        </td>
                                        <td>
                                            <sec:authorize access="hasRole('ROLE_USER')">
                                                <a href="displayEditSuperForm?superId=${currentSuper.superId}">
                                                    Edit
                                                </a>
                                            </sec:authorize>    
                                        </td>
                                        <td>
                                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                                <a href="deleteSuper?superId=${currentSuper.superId}">
                                                    Delete
                                                </a>
                                            </sec:authorize>    
                                        </td>

                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>

                </div>
                <div class="col-md-6">
                    <sec:authorize access="hasRole('ROLE_USER')">
                        <h2>Add New Super</h2>
                        <form class="form-horizontal" 
                              role="form" method="POST" 
                              action="createSuper"
                              name="supers" modelAttribute="supers">
                            <div class="form-group">
                                <label  for="add-name" class="col-md-4 control-label">Name:</label>
                                <div class="col-md-8">

                                    <input  type="text"  class="form-control" name="name" placeholder="Name"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-description" class="col-md-4 control-label">Description:</label>
                                <div class="col-md-8">
                                    <input  type="text" class="form-control"  name="description" placeholder="Description"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <input id="submit" type="submit" class="btn btn-default" value="Create Super"/>
                                </div>
                            </div>
                        </form>
                    </sec:authorize>

                    <div id="resultContainer" style="display: none;">
                        <h2>Heroes/Villains Added</h2>
                        <table id="jjj" class="table table-hover">
                            <tr>
                                <th width="40%">Name</th>
                                <th width="30%">Description</th>
                                <th width="15%"></th>
                                <th width="15%"></th>
                            </tr>
                            <tbody id="j"/>
                        </table>                    
                    </div>
                </div> 

            </div>        

            <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/supers.js"></script>

    </body>
</html>
