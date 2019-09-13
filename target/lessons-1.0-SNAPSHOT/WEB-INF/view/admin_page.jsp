<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ADMIN</title>
</head>
<body>
    <h1>Hello ADMIN!</h1>

    <c:forEach var="post" items="${requestScope.posts}">
        <ul>
            <li>Id: <c:out value="${post.id}"/></li>
            <li>Text: <c:out value="${post.text}"/></li>
            <li>Author: <c:out value="${post.author}"/></li>
            <form method="get" action="<c:url value='/update'/>">
                <input type="number" hidden name="id" value="${post.id}" />
                <input type="submit" value="Edit"/>
            </form>
            <form method="post" action="<c:url value='/delete'/>">
                <input type="number" hidden name="id" value="${post.id}" />
                <input type="submit" value="Delete"/>
            </form>
        </ul>
        <hr/>
    </c:forEach>
    <br><br>
    <form method="post" action="<c:url value='/home'/>">
        <label>Text: <input type="text" name="text"></label><br>
        <input type="submit" value="Post" name="Post"><br>
    </form>
    <br><br>
    <a href="<c:url value='/logout'/>">Logout</a>
</body>
</html>