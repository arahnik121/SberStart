<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<h2>Hello World!</h2>

<jsp:useBean id="cardList" scope="request" type="java.util.List"/>
<c:forEach items="${cardList}" var="card">
    <jsp:useBean id="card" type="main.webapp.app.model.Card"/>
</c:forEach>

</html>
