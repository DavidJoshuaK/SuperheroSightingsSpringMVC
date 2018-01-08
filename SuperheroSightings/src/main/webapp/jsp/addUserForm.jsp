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
                    <li role="presentation" >
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
                        <li role="presentation" class="active">
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
                    <h2>Add User Form</h2>
                </div>
            </div>
            <form method="POST" action="addUser">
                Username: <input type="text" 
                                 name="username"/><br/>
                Password: <input type="password" 
                                 name="password"/><br/>
                Admin User? <input type="checkbox" 
                                   name="isAdmin" value="yes"/> <br/>
                <input type="submit" value="Add User"/><br/>
            </form>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>