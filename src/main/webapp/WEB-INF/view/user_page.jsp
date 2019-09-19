<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>USER</title>
</head>
<body>
    <h1>Hello USER!</h1>

    <c:forEach var="post" items="${requestScope.posts}">
        <ul>
            <li>Text: <c:out value="${post.text}"/></li>
            <li>Author: <c:out value="${post.author}"/></li>
        </ul>
        <hr/>
    </c:forEach>
    <br><br>
    <a href="<c:url value='/logout'/>">Logout</a>
</body>
</html>