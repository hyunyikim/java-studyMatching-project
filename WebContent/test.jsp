<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<%@ page 
			import="java.sql.*, 
		javax.sql.*, 
		java.io.*, 
		javax.naming.InitialContext, 
		javax.naming.Context"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf8">
	</head>
	<body>
		<h1>JDBC JNDI Resource Test</h1>
	
		<% 
		try {
			InitialContext initCtx = new InitialContext();
			DataSource ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/oracle"); 
			Connection conn = ds.getConnection(); 
			
			out.println("<h3>연결 성공</h3>");
			
		} catch(Exception e){
			out.println("<h3>연결 실패</h3>");
			out.println(e.getMessage());
		}
		%>
	</body>
</html>

