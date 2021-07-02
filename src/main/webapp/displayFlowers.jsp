<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import ="java.util.List" %>
<%@ page import ="in.bloomapp.model.Flower" %>
<%@ page import ="in.bloomapp.service.FlowerManager" %>
<!DOCTYPE html>
<html lang="en">
<%
String loggedInUsername = (String)session.getAttribute("LOGGED_IN_USER");
String role = (String) session.getAttribute("ROLE");
%>
<head>
<meta charset="ISO-8859-1">
<title>Flowers available</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
		<h1 style="color:pink">Flowers available</h1>
		<input type="text" id="myInput" onkeyup="myFunction()"
			placeholder="Search By Category">
		<input type="text" id="flowerInput" onkeyup="myFlowerType()"
			placeholder="Search By flower type">
	<table class="table table-border"  id="myTable">
	<caption style="color:white">Flowers available</caption>
		<thead>
			<tr>
				<th scope="col" id= "serialnumber">S.NO</th>
				<th scope="col" id="category">Category</th>
				<th scope="col"id="type">Type</th>
				<th scope="col"id="price">Price</th>
				<% if (loggedInUsername != null && role != null && role.equalsIgnoreCase("USER")){ %>
				<th scope="col"id="quantity">Add</th>
				<% } %>
				<% if (loggedInUsername != null && role != null && role.equalsIgnoreCase("ADMIN")){ %>
				<th scope="col"id="delete">Delete</th>
				<% } %>
			</tr>
		</thead>	
			<tbody>
		<%
			final List<Flower> flowers = FlowerManager.getFLowerList();
			int i=0;
			for(Flower flower: flowers){
				i++;
			%>
			<tr>
			<td><%=i%></td>
			<td><%=flower.getCategory() %></td>
			<td><%=flower.getType()%></td>
			<td>Rs.<%=flower.getPrice()%>/-</td>
			 <% if (loggedInUsername != null && role != null && role.equalsIgnoreCase("ADMIN")){ %>
			<td><a href="DeleteFlowerServlet?type=<%=flower.getType()%>&category=<%=flower.getCategory()%>"class="btn btn-danger">Delete</a></td>
			 <%} %> 
			 <% if (loggedInUsername != null && role != null && role.equalsIgnoreCase("USER")){ %>
			<td><a href="CartServlet?type=<%=flower.getType()%>&category=<%=flower.getCategory()%>&price=<%=flower.getPrice()%>
			&username=<%=loggedInUsername%>" class="btn btn-success">ADD TO CART</a></td>
				<%}%>
					<% } %>
				</tr>	
		</tbody>
	</table>	
	<script>
		function getAllFlowers(){
			
		}
		function myFunction() {
			var input, filter, table, tr, td, i, txtValue;
			input = document.getElementById("myInput");
			filter = input.value.toUpperCase();
			table = document.getElementById("myTable");
			tr = table.getElementsByTagName("tr");
			for (i = 0; i < tr.length; i++) {
				td = tr[i].getElementsByTagName("td")[1];
				if (td) {
					txtValue = td.textContent || td.innerText;
					if (txtValue.toUpperCase().indexOf(filter) > -1) {
						tr[i].style.display = "";
					} else {
						tr[i].style.display = "none";
					}
				}
			}
		}
		
		function myFlowerType(){
			var input, filter, table, tr, td, i, txtValue;
			input = document.getElementById("flowerInput");
			filter = input.value.toUpperCase();
			table = document.getElementById("myTable");
			tr = table.getElementsByTagName("tr");
			for (i = 0; i < tr.length; i++) {
				td = tr[i].getElementsByTagName("td")[2];
				if (td) {
					txtValue = td.textContent || td.innerText;
					if (txtValue.toUpperCase().indexOf(filter) > -1) {
						tr[i].style.display = "";
					} else {
						tr[i].style.display = "none";
					}
				}
			}
		}
	</script> 
	 <% if (loggedInUsername != null && role != null && role.equalsIgnoreCase("ADMIN")){ %>
		<a href="addFlower.jsp">Add flowers</a>
		 <% } %>
		 		 <% if (loggedInUsername != null && role != null && role.equalsIgnoreCase("USER")){ %>
		 		 <a href="DisplayCart.jsp" class="btn btn-success">CART</a>
		 		<%}%>		 
</main>
</body>
</html>