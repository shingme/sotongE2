<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="loginCheck.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>prof_schedule_list</title>
</head>
<body>

<br/>
<table class="type11" align='center' width="500" height="20">
	<tr>
	<th>날짜</th><th>요일</th><th>교시</th><th>일정명</th></tr>
	<c:forEach var="profSchList" items="${profSchList}">
		<tr>
			<td>${profSchList.cons_date}</td>
			<td>${profSchList.day}</td>
			<td>${profSchList.time}</td>
			<td>${profSchList.title}</td>
		</tr>
	</c:forEach>

</table>
</body>
</html>