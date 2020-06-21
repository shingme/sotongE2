<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>동덕소통이</title>
	</head>
	<body>
	<center>
		<h1>동덕소통이 로그인</h1>
		<form name="f" method="post" action="login.m2">
		<input type="hidden" name="command" value="login"/> 
		<table>
		<!--  exception 객체가 전달된 경우 오류메시지를 출력 -->
		  <c:if test="${not empty exception}">
	  	<font color="red"><c:out value="${exception.getMessage()}" /></font>
	 	 </c:if>
	 	 <br>
			<tr>
				<td>
					<!-- 이걸로 구분해서 stu_id, prof_id에 값 넣으면 되지 않을까 -->
					<select name="user_val">
						<option value="student">학부생</option>
						<option value="professor">교수</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>
					아이디 &nbsp;&nbsp; <input type="text" name="user_id" size="14"/>
				</td>
				<td rowspan="2">
					<input type="submit" value="로그인"/>
				</td>
			</tr>
			<tr>
				<td>
					비밀번호 <input type="password" name="user_pwd" size="15"/>
				</td>
			</tr>
		</table>
		</form>
	</center>
	</body>
</html>