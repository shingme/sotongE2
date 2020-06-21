<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="loginCheck.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	div{
		float : left;
	}
	table{
		border : 0px;
		border-color : #eee;
		border-collapse : collapse;
		font-size : 20px;
	}
	tr, td{
		padding : 10px;
	}
	a:link{
		text-decoration:none;
		color:black;
	}
	a:visited {
		text-decoration: none;
		color: black;
	}
</style>
</head>
<body>
<br/>
<br/>
<br/>
<br/>
<table border="1">
<tr>
	<td><a href="consult_search.m2?command=search" target="con">검색</a></td>
</tr>
<tr>
	<td><a href="consult_list.jsp" target="con">상담리스트</a></td>
</tr>
<tr>
<%if(session.getAttribute("user_id").toString().startsWith("P")) { %>
	<td><a href="prof_schedule.m2?command=insert_sch" target="con">교수일정등록</a></td>
<%}else{ %>
	<td><a href ="student_timetable.m2?command=profSearch" target="con">시간표</a></td>
</tr>
<tr>
	<td><a href="weight.jsp" target="con">가중치</a></td>
<%} %>
</tr>
<tr>
<td><a href="logout.m2?command=logout">로그아웃</a></td>
</tr>
</table>

</body>
</html>