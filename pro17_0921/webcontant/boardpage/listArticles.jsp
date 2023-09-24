<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
  request.setCharacterEncoding("UTF-8");
%>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"  /> 
<c:set var="articles" value="${requestScope.articles}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h2> 자유게시판 </h2>
	<table  border="1"  width="80%"  >
  <tr   bgcolor="lightgreen">
     <td >글번호</td>
     <td >작성자</td>              
     <td >제목</td>
     <td >작성일</td>
  </tr>
  
  <c:forEach items="${articles}" var="article">
  <tr >
     <td >${article.articleNO}</td>
     <td >${article.id}</td>              
     <td >${article.title }</td>
     <td >${article.writeDate}</td>
  </tr>
  </c:forEach>
  
  
	</table>
	<a href="${contextPath}/boardpage/articleForm.jsp">글쓰기</a>
	


</body>
</html>