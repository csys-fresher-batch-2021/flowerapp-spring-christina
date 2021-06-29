<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import ="java.util.List" %>
<%@ page import ="in.bloomapp.model.Flower" %>
<%@ page import ="in.bloomapp.service.CartManager" %>
<!DOCTYPE html>
<html lang="en">
<%
String loggedInUsername = (String)session.getAttribute("LOGGED_IN_USER");
String role = (String) session.getAttribute("ROLE");
%>
<head>
<meta charset="ISO-8859-1">
<title>View Cart</title>
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
		<h1 style="color:pink">Flower Cart</h1>
	<table class="table table-border">
	<caption style="color:black">Cart items</caption>
		<thead>
			<tr>
				<th scope="col" id= "serialnumber">S.NO</th>
				<th scope="col" id="category">Category</th>
				<th scope="col"id="type">Type</th>
				<th scope="col"id="price">Price</th>
				<th scope="col"id="Quantity">Quantity</th>
				<th scope="col"id="reduce">Reduce quantity</th>
				<th scope="col"id="Quantity">delete</th>			
			</tr>
		</thead>		
			<tbody>
		<%
		    final List<Flower> flowers = CartManager.getOrder(loggedInUsername);
			int i=0;
			for(Flower flower: flowers){
				i++;
			%>
			<tr>
			<th  scope="col"><%=i%></th>
			<td><%=flower.getCategory() %></td>
			<td><%=flower.getType()%></td>
			<td>Rs.<%=flower.getPrice()%>/-</td>
		    <td><%=flower.getQuantity() %></td>
		    <td> <a href="ReduceFromCartServlet?type=<%=flower.getType()%>&category=<%=flower.getCategory()%>&price=<%=flower.getPrice()%>
			&username=<%=loggedInUsername%>&quantity=<%=flower.getQuantity()%>" class="btn btn-danger">-</a></td>
				<td> <a href="DeleteFromCartServlet?type=<%=flower.getType()%>&category=<%=flower.getCategory()%>&price=<%=flower.getPrice()%>
			&username=<%=loggedInUsername%>&quantity=<%=flower.getQuantity()%>"  class="btn btn-danger">Delete</a></td>
				</tr>
			<%}%>	
		</tbody>
	</table>
		 		 <% if (loggedInUsername != null && role != null && role.equalsIgnoreCase("USER")){ %>
		 		 <% if(flowers.size()!=0){ %>
		 		 <a href="OrderProcedure.jsp" class="btn btn-warning">Place Order</a><br/><br/>
		 		 <%} %>
		 		 <a href="displayFlowers.jsp" class="btn btn-success">Flowers</a>
		 		<%}%>	 		 
</main>
</body>