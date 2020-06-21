<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="loginCheck.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>동덕소통이</title>
<style type="text/css">
	*{
		background-color : #eee;
	}
	div{
		float : left;
	}
</style>
</head>
<body>
<div id="left">
<%@include file="left.jsp"%>
</div>
<div id="right">
<iframe name="con" src="consult_list.jsp" frameborder="0" width="1000" height="1200">
</iframe>
</div>

</body>
</html>