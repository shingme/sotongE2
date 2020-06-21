<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, model.*"%>
<%@ include file="loginCheck.jsp" %>
<%  List<ProfDTO> profList = (List<ProfDTO>)request.getAttribute("prof"); 
	List<StudentDTO> stuList = (List<StudentDTO>)request.getAttribute("stu");
	String user_id = (String)session.getAttribute("user_id");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>검색해서 상담신청</title>
<style>
	div{
		margin-top: 50px;
		text-align: center;
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
</style>
<script type="text/javascript">
	function abc() {
		f.command.value = "search";
		f.action = "consult_search.m2";
		f.submit();
	}
	function my(){
		f.command.value = "search";
		f.forward.value = "y"
		f.action = "consult_search.m2";
		f.submit();
	}
</script>
</head>
	
<%if(request.getAttribute("forward")==null) {%>
<body onload="my();" topmargin="50">
<%}else{%>
<body topmargin="50">
<%
}
%>
<center><h1>검색</h1></center>
	<form name="f" method="POST" align="center">
		<input type="hidden" name="command"/>
		<input type="hidden" name="forward"/>
		<select name="search_value">
<%if(user_id.startsWith("s")){ %>
			<option value="my">수강교수</option>
			<option value="prof_name">교수명</option>
			<option value="dept_name">학과명</option>
<%}else{ %>
			<option value="my">수강학생</option>
			<option value="stu_name">학생명</option>
			<option value="dept_name">학과명</option>
<%} %>
		</select>
		<input type="text" name="search"/><input type="button" class="b" value="검색" onClick="abc()"/>
	</form>
	<br/>
	<br/>
<%
	//검색 결과가 없을 시에 출력
	if (request.getAttribute("forward") == "pr" && request.getAttribute("forward") == "st"){
		out.println("<center><h3>검색결과가 없습니다.</h3></center>");
	}
%>
<%	if (profList != null) {	
		Iterator<ProfDTO> profIter = profList.iterator();
%>
	<table class="type11">
		<tr width="700" align="center">
			<th>교수명</th><th>연구실</th><th>학과명</th>
		</tr>	
<%
	  //사용자 리스트를 클라이언트에게 보여주기 위하여 출력.
	  while (profIter.hasNext()) {
		  ProfDTO prof = (ProfDTO)profIter.next();
%>		  	
		<tr>
			<td width="200" height="20">
			<a href="include_time.m2?recipient_id=<%= prof.getProf_id() %>&recipient_name=<%=prof.getName() %>&command=profSearch"><%= prof.getName() %></a>
			</td>
			<td width="200" height="20">
				<%= prof.getOffice() %>
			</td>
			<td width="200" height="20">
				<%= prof.getDept_name() %>
			</td>
		</tr>
<%
	  }
	}
%>	  	
<%	if (stuList != null) {	
		Iterator<StudentDTO> stuIter = stuList.iterator();
%>
	<table class="type11">
		<tr width="600" align="center">
			<th>학생명</th><th>학번</th><th>학과명</th>
		</tr>		  	
<%
	  //사용자 리스트를 클라이언트에게 보여주기 위하여 출력.
	  while (stuIter.hasNext()) {
		  StudentDTO stu = (StudentDTO)stuIter.next();
%>

		<tr>
			<td width="200" height="20">
			<a href="consult.m2?recipient_id=<%= stu.getStu_id()%>&recipient_name=<%=stu.getName() %>&command=insert"><%= stu.getName() %></a>
			</td>
			<td width="200" height="20">
				<%= stu.getStu_id().substring(1) %>
				<!-- </a> -->
			</td>
			<td width="200" height="20">
				<%= stu.getDept_name() %>
			</td>
		</tr>
<%
	  }
	}
%>		  	
	</table>
</body>
</html>