<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>



<html>
<head>
    <title>Title</title>
</head>
<body>
<h2> Hello ${param.name} </h2>


<c:forEach var = "film" items = "${list}" >
    <p> <a href="view?id=<c:out value="${film.id}"/>"> <c:out value="${film.title}"/> </a> </p>
</c:forEach>








<a href="HelloUser.jsp">Back for new login</a>




</body>
</html>