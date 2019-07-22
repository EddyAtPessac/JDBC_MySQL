<%@ page import="fr.wcs.jedi.model.Jedi" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: eddy
  Date: 12/07/19
  Time: 12:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@include file="header.jsp"%>
<head>
    <title >JEDIS</title>
</head>
<body>
<H1 class="text-danger" class="text-center">JEDIS list</H1>
<div class="table-responsive">
    <table class="table table-bordered  table-striped">
        <thead class="thead-dark">
        <tr class="text-center" >
            <th  scope="col">ID</th>
            <th scope="col">Name</th>
            <th scope="col">Surname</th>
        </tr>

<%--
        <%
        List<Jedi> jediList= (List<Jedi>) request.getAttribute("jedilist");
        for (Jedi jedi :jediList ) {
            System.out.println(jedi.name + " "+ jedi.surname);
        }
        %>
--%>

        <c:forEach items="${jedilist}" var="jedi">
        <tr class="text-center" scope="row ">
            <td><c:out value="${jedi.id}"/></td>
            <td><c:out value="${jedi.name}" /></td>
            <td><c:out value="${jedi.surname}" /></td>
        </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
