<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*, model.*"%>
<%@ include file="loginCheck.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>상담기록리스트</title>
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
<script language="JavaScript">
</script>
</head>
<body topmargin="50">
	
	<center><h1>상담기록리스트</h1></center>
	<form name="f" method="post">
	<input type="hidden" name="command"/>
	<input type="hidden" name="type"/>
	<input type="hidden" name="currentPage"/>
	<input type="hidden" name="consult_id"/>
	<table align="center" cellpadding="10">
		<tr>
			<td>&nbsp;</td>
			<td width="600" class="right">
				<!-- 사용자가 선택 가능한 리스트 목록  -->
			<a href="consult_list.m2?command=list&type=receive&currentPage=1">수신</a>
				
			<a href="<c:url value='consult_list.m2'>
			<c:param name='command' value='list'/>
			<c:param name='type' value='sending'/>
			<c:param name='currentPage' value='1'/></c:url>">발신</a>
			
			</td>
		</tr>
		<c:if test="${not empty consultList}">
		<tr>
			<td colspan="2">
			<table cellspacing="0" cellpadding="5" class="type11">
				<tr align="center">
				</tr>
					<th>수/발</th>
					<th>번호</th>
					<th>분류</th>
					<th>제목</th>
					<th>보낸 날짜</th>
					<th>상태</th>
				
					<c:forEach var="consultList" items="${consultList}" varStatus="status">
					<tr>
						<c:choose>
							<c:when test="${sessionScope.user_id eq consultList.send}">
							<td>발신</td>
							</c:when>
							<c:otherwise>
							<td>수신</td>
							</c:otherwise>
						</c:choose>
						<td>${consultPager.start + status.count}</td>
						<td>${consultList.category}</td>
						<td>
							<a href="<c:url value='consult_content.m2'>
							<c:param name='command' value='content'/>
							<c:param name='consult_id' value='${consultList.consult_id}'/>
								</c:url>">${consultList.title}</a>
						</td> <!-- 클릭 시 상세 내용 보기 -->
						<td>${consultList.current_date}</td>
						<td>${consultList.result}</td>
					</tr>	
					</c:forEach>
			</table>
			<c:if test="${consultPager.prev}">
			<a href="<c:url value='consult_list.m2'>
			<c:param name='command' value='list'/>
			<c:param name='type' value='sending'/>
			<c:param name='currentPage' value='${consultPager.tempStart-1}'/></c:url>">이전</a>
 			</c:if>
 			
 			<c:forEach varStatus="status" begin="${consultPager.tempStart}" end="${consultPager.tempEnd}">
 				<a href="<c:url value='consult_list.m2'>
					<c:param name='command' value='list'/>
					<c:param name='type' value='sending'/>
					<c:param name='currentPage' value='${consultPager.tempStart + (status.count-1)}'/></c:url>">${consultPager.tempStart + (status.count-1)}</a>
 			</c:forEach>
 			
 			<c:if test="${consultPager.next}">
 			<a href="<c:url value='consult_list.m2'>
			<c:param name='command' value='list'/>
			<c:param name='type' value='sending'/>
			<c:param name='currentPage' value='${consultPager.tempEnd+1}'/></c:url>">다음</a>
 			</c:if>
			
		</c:if>
	</table>
	
	</form>
</body>
</html>