<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Login page</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
		<h3>Login Page</h3>
		<%
		String Message = request.getParameter("msg");
		if(Message != null){
			out.println("<font color='green'>" + Message + "</font>");
		}
		%>
		<%
		String Msg = request.getParameter("errorMessage");
		if(Msg != null){
			out.println("<font color='red'>" + Msg + "</font>");
		}
		%>
<form action="LoginServlet" method="post">
		<label for="username">User Name</label>
		<input type="text" name="username" placeholder="User Name" pattern="[a-zA-Z0-9\s]{3,}" required autofocus />
		<br/>
		<label for="password">Password</label>
		<input type="password" name="password" placeholder="Password"  required/>
		<br/>
		<button type="submit" class="btn btn-info">Submit</button>
		<button type="reset" class="btn btn-danger">Reset</button>
		<br/><p>for admin username: admin  password= admin123</p>	
</form>
</main>
</body>
</html>