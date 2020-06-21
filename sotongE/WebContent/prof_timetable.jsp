<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, java.text.SimpleDateFormat" %>
<%@ include file="loginCheck.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>prof_timetable</title>
</head>
<body>
<h1>교수님 강의시간표</h1>	
	<table border="1" width="300">
	<tr align='center' bordercolorlight='#efecde' bordercolordark='#eeebdd' height ="40">
	<td width="20"></td><td>월</td><td>화</td><td>수</td><td >목</td><td>금</td>
	</tr>
	<c:forEach var="timetable" items="${timetable}">
	<c:set var="i" value="${i+1}"/>
		<tr height ="40">
			<td>${i}</td>
			<td>${timetable.first}</td>
			<td>${timetable.second}</td>
			<td>${timetable.three}</td>
			<td>${timetable.four}</td>
			<td>${timetable.five}</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>