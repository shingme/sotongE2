<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.*"%>
<%@ include file="loginCheck.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String recipient_id = (String)request.getAttribute("recipient_id");
	String recipient_name = (String)request.getAttribute("recipient_name");
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>상담신청</title>
<style>
	@font-face {
	    font-family:'NanumGothic';
	
	    src: url('images/NanumGothic.eot');
	
	    src: url('images/NanumGothic.eot?#iefix') format('embedded-opentype'),
	
	         url('images/NanumGothic.woff') format('woff'),
	
	         url('images/NanumGothic.ttf') format('truetype');
	
	    font-weight: normal;
	
	    font-style: normal;

	}
	.b{
		background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #ffbcb5), color-stop(1, #ffbdbd) );
		background:-moz-linear-gradient( center top, #ffbcb5 5%, #ffbdbd 100% );
		filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#ffbcb5', endColorstr='#ffbdbd');
		background-color:#ffbcb5;
		-webkit-border-top-left-radius:0px;
		-moz-border-radius-topleft:0px;
		border-top-left-radius:0px;
		-webkit-border-top-right-radius:0px;
		-moz-border-radius-topright:0px;
		border-top-right-radius:0px;
		-webkit-border-bottom-right-radius:0px;
		-moz-border-radius-bottomright:0px;
		border-bottom-right-radius:0px;
		-webkit-border-bottom-left-radius:0px;
		-moz-border-radius-bottomleft:0px;
		border-bottom-left-radius:0px;
		text-indent:0;
		border:1px solid #ff9a91;
		display:inline-block;
		color:#b55771;
		font-family:Arial Black;
		font-size:12px;
		font-weight:bold;
		font-style:normal;
		height:25px;
		line-height:25px;
		width:50px;
		text-decoration:none;
		text-align:center;
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
	table.type11 {
		width : 700px;
	    border-collapse: separate;
	    border-spacing: 1px;
	    line-height: 1.5;
	    margin: 20px 10px;
	}
	table.type11 th {
    	text-align: center;
	    font-weight: bold;
	    vertical-align: top;
	    color: #fff;
	    background: #ce4869 ;
	}
	table.type11 td {
	    vertical-align: top;
	    border-bottom: 1px solid #ccc;
	    background: #eee;
	}
	a:link{
		text-decoration:none;
		color:black;
	}
	a:visited {
		text-decoration: none;
		color:black;
	}
</style>
<script>
function userCreate() {
	f.command.value = "insert";
	f.action = "consult.m2";
	f.submit();
}
</script>
</head>
<body topmargin="100">
	<center><h1>상담신청</h1></center>
	<form name="f" method="POST">
	<input type="hidden" name="command"/>
	<input type="hidden" name="recipient" value="<%=recipient_id %>"/>
	<table class="type11" width="600" align="center" cellpadding="5" cellspacing="0">
		<tr>
			<th>카테고리</th>
			<td colspan="3">
			<select name="category">
				<option value="교우관계">교우관계</option>
				<option value="학교생활">학교생활</option>
				<option value="학과">학과</option>
				<option value="수업">수업</option>
				<option value="취업(진로)">취업(진로)</option>
				<option value="과제">과제</option>
			</select></td>
		</tr>
		<tr>
			<th>상담자</th>
			<td colspan="3"><input type="text" name="recipient_id" readonly value="<%= recipient_name %>" size="50"></td>
		</tr>
		<tr>			
			<th>날짜</th>	
			<td width="20"><input type="date" name="cons_date"></td>
			<td align="center"><b>교시</td>
			<td><select name="time">
					<option value="1">1교시</option>
					<option value="2">2교시</option>
					<option value="3">3교시</option>
					<option value="4">4교시</option>
					<option value="5">5교시</option>
					<option value="6">6교시</option>
					<option value="7">7교시</option>
				</select></td>
		</tr>
		<tr>
			<th>제목</th>
			<td colspan="3"><input type="text" name="title" size="50"></td>
		</tr>
		<tr>
			<th>내용</th>
			<td colspan="3"><textarea name="content" cols="50" rows="5"></textarea></td>	
		</tr>
		<tr>
			<td colspan="4" align="center"><input type="button" class="b" value="신청" onClick="userCreate()"></td>
		</tr>
	</table>
	</form>
</body>
</html>