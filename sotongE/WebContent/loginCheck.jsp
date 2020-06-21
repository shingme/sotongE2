<%
	session = request.getSession();

	if ((session == null) || (session.getAttribute("user_id") == null)) {
		response.sendRedirect("login.m2?command=login");
	}
%>