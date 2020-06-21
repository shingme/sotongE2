<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, model.*"%>
<%@ include file="loginCheck.jsp" %>
<% ConsultDTO content = (ConsultDTO)request.getAttribute("content"); 
   
%>
<% request.setCharacterEncoding("EUC-KR"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>상담내용</title>
<script>
	function upd(){
		f.command.value="update";
		f.update.value="y";
		f.action="consult_update.m2";
		f.submit();
	}
</script>
</head>
<body topmargin="100">

	<form name="f">
	<center><u><h1>상담내용</h1></u></center>
	<table border="1" width="700" align="center" cellpadding="10" cellspacing="0">
		<tr>
			<th width="80">제목</th>
			<td colspan="3"><input type="text" size="50" name="title" value="<%= content.getTitle() %>"/></td>
			<th width="80">날짜</th>
			<td width="100"><%= content.getCons_date() %></td>
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
				<textarea name="content" cols="100" rows="20"><%= content.getContent() %></textarea>
			</td>
		</tr>
		<tr>
		<td colspan="6" align="right">
				<input type="hidden" name="consult_id" value="<%=content.getConsult_id()%>"/>
				<input type="hidden" name="command"/>
				<input type="hidden" name="update"/>
				<input type="button" value="취소" onClick="javascript:history.go(-1)">
				<input type="button" value="수정" onClick="upd()">
		</td>
		</tr>
	</table>
	</form>
</body>
</html>