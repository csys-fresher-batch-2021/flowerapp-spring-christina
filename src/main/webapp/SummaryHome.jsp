<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Summary Display</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
<div  class="d-flex justify-content-center"><h1>Admin Summary Fields</h1><br/></div>
<br/><br/><br/><br/>
<div  class="d-flex justify-content-center">
<a href="MostOrdered.jsp" class="btn btn-info" ><h5>Order Summary</h5></a>
</div>
<br/><br/><br/><br/>
<div  class="d-flex justify-content-center">
<a href="TodaysOrders.jsp" class="btn btn-info" ><h5>Yet to deliver List</h5></a>
</div>
</main>
</body>
</html>