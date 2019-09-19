<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ADMIN EDIT PAGE</title>
</head>
<body>
    <h1>Edit post:</h1>
    <c:out value="${post}"/>
    <form method="post" action="<c:url value='/update'/>">
        <input type="number" hidden name="id" value="${post.id}" />
        <label>Text: <input type="text" name="text" value="${post.text}" ></label><br>
        <input type="number" hidden name="user_id" value="${post.userId}" />
        <input type="submit" value="Edit" name="Edit"><br>
    </form>
</body>
</html>