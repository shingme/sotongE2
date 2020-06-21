<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="loginCheck.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>

</head>
<body>
<table border=0 cellpadding=5 cellspacing=1>
	<tr>
		<td><%@include file="prof_timetable.jsp" %></td>
		<td width="30">&nbsp;</td>
		<td><%@include file="prof_schedule_list.jsp" %></td>
	</tr>
		
</table>
<%@include file="consult.jsp"%>

</body>
</html>