<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, model.*"%>
<%@ include file="loginCheck.jsp" %>
<% ConsultDTO content = (ConsultDTO)request.getAttribute("content"); %>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>상담내용</title>
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
	    padding: 10px;
	    font-weight: bold;
	    vertical-align: top;
	    color: #fff;
	    background: #ce4869 ;
	}
	table.type11 td {
	    padding: 10px;
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
	td.right{
		text-align: right;
	}
</style>
<script>
	function acc(yn){
		f.command.value="accept";
		f.accept.value=yn;
		f.action="consult_content.m2";
		f.submit();
	}
	function del(){
		f.command.value="delete";
		f.action="consult_delete.m2";
		f.submit();
	}
	
</script>
</head>
<body topmargin="50">
	<center><h1>상담내용</h1></center>
	<table class="type11" width="700" align="center" cellpadding="10" cellspacing="0">
		<tr>
			<th width="80">제목</th>
			<td><%= content.getTitle() %></td>
			<th width="80">예약 날짜</th>
			<td><%= content.getCons_date() %></td>
			<th width="80">교시</th>
			<td><%= content.getTime() %></td>
		</tr>
		<tr>
			<th width="80">상담자</th>
			<td><%= content.getRecipient() %> </td>
			<th width="80">신청자</th>
			<td><%= content.getSend() %> </td>
			<th width="80">상태</th>
			<td><%= content.getResult() %> </td>
		</tr>
		<tr>
			<td width="680" height="200" colspan="6">
				<%= content.getContent() %>
			</td>
		</tr>
		<tr>
		<td colspan="6" align="right">
		<form name="f">
			<input type="hidden" name="consult_id" value="<%=content.getConsult_id()%>"/>
			<input type="hidden" name="command"/>
		<%if(content.getAccept().equals("") && content.getRecipient_id().equals(session.getAttribute("user_id"))){ %>
			<input class="b" type="hidden" name="accept"/>
			<input class="b" type="button" value="수락" onClick="acc('Y')"/> <input class="b" type="button" value="거절" onClick="acc('N')"/>
		<%} %>
			<input class="b" type="button" value="목록" onClick="javascript:history.go(-1)">
		<%if(content.getSend_id().equals(session.getAttribute("user_id")+"") && content.getRead().equals("N")){ %>
			<input class="b" type="button" value="삭제" onClick="del()">
		<%} %>
		</form>
		</td>
		</tr>
		<tr>
			<td colspan="6"><%@include file="consult_result.jsp"%></td></tr>	
	</table>
</body>
</html>