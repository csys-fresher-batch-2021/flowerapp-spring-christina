<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="in.bloomapp.model.Flower"%>
<%@ page import="in.bloomapp.service.CartManager"%>
<%@ page import="in.bloomapp.model.City"%>
<%@ page import="in.bloomapp.service.CityManager"%>
<%@ page import="java.time.LocalDate"%>
<!DOCTYPE html>
<html lang="en">
<%
String loggedInUsername = (String) session.getAttribute("LOGGED_IN_USER");
String role = (String) session.getAttribute("ROLE");
%>
<head>
<meta charset="ISO-8859-1">
<title>place order</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>

	<main class="container-fluid">
		<h1 style="color: black">Place order</h1>
		<form onsubmit="proceed()" method="post">
		<label  class="d-flex justify-content-center" id="testLabel" style="color:red"></label>
		<h5><label id="UserName" style="color: blue"> Hi <%=loggedInUsername %></label></h5>
		<table style="width: 100%">
			<caption style="color: black">items you ordered</caption>
			<thead>
				<tr>
					<th scope="col" id="serialnumber">S.NO</th>
					<th scope="col" id="category">Category</th>
					<th scope="col" id="type">Type</th>
					<th scope="col" id="price">Price</th>
					<th scope="col" id="Quantity">Quantity</th>
				</tr>
			</thead>
			<tbody>
				<%final List<Flower> flowers = CartManager.getOrder(loggedInUsername);
				int i = 0;
				int count = 0;
				int sum=0;
				for (Flower flower : flowers) {
					i++;%>
				<tr>
					<td><%=i%></td>
					<td><%=flower.getCategory()%></td>
					<td><%=flower.getType()%></td>
					<td>Rs.<%=flower.getPrice()%>/-</td>
					<td><%=flower.getQuantity()%></td>
					
					<% sum=sum+(flower.getPrice()*flower.getQuantity());
					if (flower.getCategory().equalsIgnoreCase("Natural")) {
						count++;}%>
						</tr>
					<%}%>
					<tr><h5>TOTAL AMOUNT:<%=sum%></h5></tr>
			</tbody>
		</table>
		<label for="DeliveryDate">Delivery Date</label>
		<input type="date" name="DeliveryDate" min=<%=LocalDate.now().plusDays(2) %> id="DeliveryDate"required /> <br />
  		<label for="DeliveryTime">Timings</label>
     	<input type="time" name="DeliveryTime" id="DeliveryTime" required /><br />
		<label for="DelivaryAddress">Delivery Address</label> <input
			type="text" name="DelivaryAddress" id="DelivaryAddress"
			placeholder="Enter delivery address"  required autofocus /><br />
		<%if (count > 0) {%>
		<label>Select city :</label> 
		<select name="CityName" id="CityName"required>
			<option disabled>--Select city--</option>
			<%
			final List<City> cityName = CityManager.getCity();
			for (City cities : cityName) {%>
			<option><%=cities.getCity()%></option>
			<% }%>
		</select> <br />
		<%} else {%>
		<label for="CityName">Delivery city</label>
		 <input type="text" name="CityName" id="CityName" placeholder="Enter delivery city"
			required autofocus /> <br />
		<%}%>
		<%if (loggedInUsername != null && role != null && role.equalsIgnoreCase("USER")) {%>
		<button type="submit" class="btn btn-info">Submit</button><br/><br/>
		<a href="DisplayCart.jsp" class="btn btn-warning">Cart</a>
		<a href="displayFlowers.jsp" class="btn btn-success">Flowers</a>
		<%}%>
		</form>
		<script type="text/javascript">
		function proceed(){
			event.preventDefault();
			let delivaryCity=document.querySelector("#CityName").value;
			let delivaryAddress=document.querySelector("#DelivaryAddress").value;
			let deliveryDate=document.querySelector("#DeliveryDate").value;
			let deliveryTime=document.querySelector("#DeliveryTime").value;
			let userName='<%= loggedInUsername%>';
			const queryParameter="?delivaryCity="+delivaryCity+"&delivaryAddress="+delivaryAddress+
					"&deliveryDate="+deliveryDate+
					"&deliveryTime="+deliveryTime+"&userName="+userName;
			let url="OrderProcedureServlet"+queryParameter;
			fetch(url,{ method:'POST'}).then(res => res.json()).then(res=>{			
				if(res.IS_ADDED=="Order Initiated"){		
					alert(res.IS_ADDED);
					window.location.href="UserSummary.jsp";				
				}	
				else{
					document.getElementById('testLabel').innerHTML = (res.IS_ADDED);
				}
			});
		}
		</script>
		<br/><a class="btn btn-info" href=UserSummary.jsp>Summary</a>
	</main>
</body>