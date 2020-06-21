<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="loginCheck.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>student_timetable</title>
<style type="text/css">
			div{
				float : left;
				padding-left : 10px;
				padding-right : 10px;
			}
			h1 {
				font-size: 22px !important;
				text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.2);
				padding: 3px 5px;
				margin: 5px 0;
				BACKGROUND-COLOR: #FFF;
				BORDER-BOTTOM: 2px solid #ccc;
				BORDER-TOP: 2px solid #ccc;
				BORDER-LEFT: #ce4869 8px solid;
				BORDER-RIGHT: 2px solid #ccc;
				border-bottom: 2px solid #ccc; 
			}
			a:link{
				text-decoration:none;
				color:black;
			}
			a:visited {
				text-decoration: none;
				color:black;
			}
			table.type07 {
			    border-collapse: collapse;
			    text-align: left;
			    line-height: 1.5;
			    border: 1px solid #ccc;
			    margin: 20px 10px;
			}
			table.type07 thead {
			    border-right: 1px solid #ccc;
			    border-left: 1px solid #ccc;
			    background: #e7708d;
			}
			table.type07 thead th {
			    padding: 10px;
			    font-weight: bold;
			    vertical-align: top;
			    color: #fff;
			}
			table.type07 tbody th {
			    width: 150px;
			    padding: 10px;
			    font-weight: bold;
			    vertical-align: top;
			    border-bottom: 1px solid #ccc;
			    background: #fcf1f4;
			}
			table.type07 td {
			    width: 350px;
			    padding: 10px;
			    vertical-align: top;
			    border-bottom: 1px solid #ccc;
			}
</style>
</head>
<body>
<h1>내 시간표</h1>
	<table class="type07">
	<tr align='center' bordercolorlight='#efecde' bordercolordark='#eeebdd' height ="40">
	<th width="20"></th><th scope="row">월</th><th scope="row">화</th><th scope="row">수</th><th scope="row">목</th><th scope="row">금</th>
	</tr>
	<c:set var = "intArray" value = "<%=new int[]{1,2,3,4,5,6}%>"/>
	<% int i = 1; %>
	<c:forEach var="timetable" items="${timetable}">
		<tr height ="40">
			<td><% out.println(i++); %></td>
			<td><a href="consult.m2?subject=${timetable.first}&command=insert" >${timetable.first}</a></td>
			<td><a href="consult.m2?subject=${timetable.second}&command=insert" >${timetable.second}</a></td>
			<td><a href="consult.m2?subject=${timetable.three}&command=insert" >${timetable.three}</a></td>
			<td><a href="consult.m2?subject=${timetable.four}&command=insert" >${timetable.four}</a></td>
			<td><a href="consult.m2?subject=${timetable.five}&command=insert" >${timetable.five}</a></td>
		</tr>
	</c:forEach>

	
	 
</table>

</body>
</html>