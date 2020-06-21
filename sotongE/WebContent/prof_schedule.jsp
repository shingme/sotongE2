<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.*, java.util.*"%>
<%@ page import="java.util.Date, java.text.SimpleDateFormat"%>
<% request.setCharacterEncoding("UTF-8");%>
<% 
	Date d = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String today = sdf.format(d);
	
	String time = (String)request.getAttribute("time");
	List<ProfSchDTO> schList = (List<ProfSchDTO>)request.getAttribute("schList");
	String overlap = (String)request.getAttribute("overlap");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
					
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>교수님 스케쥴</title>
<style>
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
	    margin: 10px 10px;
	}
	table.type11 th {
    	text-align: center;
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
function schCreate() {
	f.command.value = "insert_sch";
	f.action = "prof_schedule.m2";
	f.submit();
}

</script>
</head>
<body topmargin="50">
	<center><h1>일정등록</h1></center>
	<form name="f" method="POST">
	<input type="hidden" name="command"/>
	<table class="type11" width="600" align="center" cellpadding="10" cellspacing="0">
		<tr>			
			<th>날짜</th>	
			<td width="20"><input type="date" name="cons_date" min="<%=sdf.format(d)%>"></td>
			<th align="center"><b>교시</b></th>
			<td colspan="2">
				<select name="time">
					<option value="1">1교시</option>
					<option value="2">2교시</option>
					<option value="3">3교시</option>
					<option value="4">4교시</option>
					<option value="5">5교시</option>
					<option value="6">6교시</option>
					<option value="7">7교시</option>
				</select>
			</td>
		</tr>
		<tr>
			<th>일정명</th>
			<td colspan="3"><input type="text" name="title" size="50"></td>
			<td><input class="b" type="button" value="등록" onClick="schCreate()"/></td>
		</tr>
	</table>
	</form>
	
	<%@ include file="prof_schedule_list.jsp" %>
</body>
</html>