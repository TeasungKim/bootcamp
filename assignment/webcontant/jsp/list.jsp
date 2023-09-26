<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%
  request.setCharacterEncoding("UTF-8");
%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"  />
<c:set var="word" value="${pagingMap.word}"/>
<c:set var="wordList" value="${pagingMap}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	
	<form action="${contextPath}/list/list.do" method="post">
	<input type="text" name="searchbox"/>
	<input type="submit" value="제출"/>
	
	</form> 

    
	<c:forEach var="wordlist" items="${wordList}">
  <p>${wordlist.word}</p>
</c:forEach>
	
	<button>+</button>
	<button>-</button>
	
	
	
	
</body>
</html>