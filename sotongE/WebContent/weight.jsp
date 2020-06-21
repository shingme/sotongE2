<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "java.util.*"%>
<%@ include file="loginCheck.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
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
		</style>
	</head>
	<body topmargin="50">
	
		<div id="weight">
		<form name="f" method="GET" action="weight.m2">
		<input type="hidden" name="command" value="weight">
			<h1>가중치부여</h1>
			<table width="350">
				<tr>
					<td>교우관계</td>
					<td>
						<select name="sel1">
							<option value="0">0</option>		
							<option value="10">10</option>						
							<option value="25">25</option>
							<option value="50">50</option>
							<option value="75">75</option>
							<option value="90">90</option>
							<option value="100">100</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>학교생활</td>
					<td>
						<select name="sel2">
							<option value="0">0</option>		
							<option value="10">10</option>						
							<option value="25">25</option>
							<option value="50">50</option>
							<option value="75">75</option>
							<option value="90">90</option>
							<option value="100">100</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>학과문의<br/>(복수전공/부전공)</td>
					<td>
						<select name="sel3">
							<option value="0">0</option>		
							<option value="10">10</option>						
							<option value="25">25</option>
							<option value="50">50</option>
							<option value="75">75</option>
							<option value="90">90</option>
							<option value="100">100</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>수업문의</td>
					<td>
						<select name="sel4">
							<option value="0">0</option>		
							<option value="10">10</option>						
							<option value="25">25</option>
							<option value="50">50</option>
							<option value="75">75</option>
							<option value="90">90</option>
							<option value="100">100</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>취업문의</td>
					<td>
						<select name="sel5">
							<option value="0">0</option>		
							<option value="10">10</option>						
							<option value="25">25</option>
							<option value="50">50</option>
							<option value="75">75</option>
							<option value="90">90</option>
							<option value="100">100</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>과제문의</td>
					<td>
						<select name="sel6">
							<option value="0">0</option>		
							<option value="10">10</option>						
							<option value="25">25</option>
							<option value="50">50</option>
							<option value="75">75</option>
							<option value="90">90</option>
							<option value="100">100</option>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="부여"/>
					</td>
				</tr>
			</table>
		</form>
		</div>
		
		<div id="recommand">
		<table width="350">
		<h1>추천교수님</h1>
		선택한 가중치 : <br/>
		<c:forEach var="profL" items="${profList}">
			<c:set var="i" value="${i+1}" />
			<!-- for문으로 뿌려주기 -->
				${i}. ${profL} <br/>
		</c:forEach>

		</table>
		</div>
	</body>
</html>