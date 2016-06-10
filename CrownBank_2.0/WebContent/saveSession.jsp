<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String name = request.getParameter("user");
	session.setAttribute( "userID", name );
	session.setAttribute( "isLoggedIn", "true" );
	String failedLogin = request.getParameter("failedLogin");
	request.setAttribute("failedLogin", failedLogin);
	
	ServletContext sc = this.getServletContext();
	RequestDispatcher rd = sc.getRequestDispatcher("/userHome.jsp");
	System.out.println("You reaced the save sessionfile!");
	System.out.println(session.getAttribute("isLoggedIn"));
	rd.forward(request, response);
	
%>
<HTML>
<BODY>
<A HREF="userHome.jsp">Continue</A>
</BODY>
</HTML>