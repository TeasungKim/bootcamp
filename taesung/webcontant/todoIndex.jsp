<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    session="true"
%>
<%
  request.setCharacterEncoding("UTF-8");
%>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"  /> 
<c:set var="userId" value="${sessionScope.userid}" />    
<c:set var="info" value="${requestScope.info}" />    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

	<form action="listArticles.do" method="post">
	<a href="${contextPath}/Todo/listArticles.do">1</a>
	</form> 

</body>
</html>