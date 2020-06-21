<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, model.*"%>
<%@ include file="loginCheck.jsp" %>
<% 	ResultDTO result = (ResultDTO)request.getAttribute("result"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
.simple_table {
    width: 100%;
    border: none;
    border-collapse: separate;
    border-spacing: 2px;
}
 
.simple_table th {
    padding: 15px;
    border: none;
    border-left: 5px solid #C03;
    border-bottom: 1px solid #DDD;
    background: #FCF0F3;
    font-weight: normal;
    text-align:center;
    text-shadow: 0 1px #FFF;
    vertical-align: middle;
}
 
.simple_table td {
    padding: 15px;
    border: none;
    border-bottom: 1px solid #DDD;
    text-align: left;
    vertical-align: baseline;
}
</style>
<script>
function resultCreate() {
	result.command.value = "result";
	result.action = "consult_content.m2";
	result.submit();
}
function resultDelete() {
	result.command.value = "resultDelete";
	result.action = "consult_content.m2";
	result.submit();
}
</script>
</head>
<body topmargin="50">
	<form name="result">
	<input type="hidden" name="command"/>
	<input type="hidden" name="consult_id" value="<%=request.getParameter("consult_id") %>"/>
	<input type="hidden" name="user_id" value="<%=session.getAttribute("user_id")%>"/>
	<%
	if(request.getAttribute("after").equals("y")){
		if(request.getAttribute("reg").equals("yet")){
	%>
	<table width="700" border="1" class="simple_table" align="center" cellpadding="10" cellspacing="0">
		<tr><td colspan="2"><b>상담결과</b></td></tr>
		<tr>
			<td>
				<table width="680" align="center" cellpadding="10" cellspacing="0">
				<tr>
					<td><textarea name="result_cons" cols="80" rows="5" style="resize: none;"></textarea></td>
					<td width="80" align="center"> <br/><input class="b" type="button" value="등록" onClick="resultCreate()"></td>
				</tr>	
				</table>
			</td>
		</tr>
	</table>
	<%
		}
		else{
	%>
	<table width="700" align="center" cellpadding="10" cellspacing="0">
		<tr><td colspan="2"><b>상담결과</b></td></tr>
		<tr>
			<td>
				<table width="680" align="center" cellpadding="10" cellspacing="0">
				<tr>
					<td><%=result.getCons_date() %></td>
					<td width="80" align="center" rowspan="2">
						<br/>
						<input class="b" type="button" value="삭제" onClick="resultDelete()">
					</td>
				</tr>
				<tr>
					<td><%= result.getResult_cons() %></td>
				</tr>	
				</table>
			</td>
		</tr>
	</table>
	<%
		}
	}
	%>
	
	</form>
</body>
</html>