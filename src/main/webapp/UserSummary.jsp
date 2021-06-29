<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List"%>
<%@ page import="in.bloomapp.model.Order"%>
<%@ page import="in.bloomapp.service.SummaryManager"%>
<%@ page import="in.bloomapp.service.CityManager"%>
<!DOCTYPE html>
<html lang="en">
<%
String loggedInUsername = (String) session.getAttribute("LOGGED_IN_USER");
String role = (String) session.getAttribute("ROLE");
%>
<head>
<meta charset="ISO-8859-1">
<title>User order summary</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
	<h1 style="color: pink">Order List</h1>
		<table class="table table-bordered" style="font-size: 15px">
			<caption style="color: red">Flowers which you ordered are shown here</caption>
			<thead>
				<tr>
					<th scope="col" id="serialnumber">S.NO</th>
					<th scope="col" id="category">Category</th>
					<th scope="col" id="type">Type</th>
					<th scope="col" id="price">Price</th>
					<th scope="col" id="DeliveryCity">Delivery City</th>
					<th scope="col" id="DeliveryAddress">Delivery Address</th>
					<th scope="col" id="DeliveryDate">Delivery Date</th>
					<th scope="col" id="DeliveryTime">Delivery Time</th>
					<th scope="col" id="UserName">Customer Name</th>
					<th scope="col" id="MobileNo">Phone NO</th>
					<th scope="col" id="OrderDate">Order Date</th>
					<th scope="col" id="Status">Approve</th>
				</tr>
			</thead>
			<tbody>
				<%
		    final List<Order> list = SummaryManager.getOrderList(loggedInUsername);
			int i=0;
			int count=0;
			for(Order item: list){
				i++;
			%>
				<tr>
					<td><%=i%></td>
					<td><%=item.getOrderCategory() %></td>
					<td><%=item.getOrderType()%></td>
					<td>Rs.<%=item.getOrderPrice()%>/-
					</td>
					<td><%=item.getDeliveryCity()%></td>
					<td><%=item.getDeliverAddress()%></td>
					<td><%=item.getDeliveryDate()%></td>
					<td><%=item.getDeliveryTime()%></td>
					<td><%=item.getUserName()%></td>
					<td><%=item.getUserMobileNo()%></td>
					<td><%=item.getOrderDate()%></td>
					<td style="color: green">Yet to deliver</td>
					<%if(item.getOrderCategory().equalsIgnoreCase("Natural")){
						count++;
					}
					%>	
				</tr>
				<%} %>
					<%
		    final List<Order> rejectedList = SummaryManager.rejectedItems(loggedInUsername);
			int k=0;
			for(Order item: rejectedList){
				k++;
			%>
				<tr>
					<td><%=k%></td>
					<td><%=item.getOrderCategory() %></td>
					<td><%=item.getOrderType()%></td>
					<td>Rs.<%=item.getOrderPrice()%>/-
					</td>
					<td><%=item.getDeliveryCity()%></td>
					<td><%=item.getDeliverAddress()%></td>
					<td><%=item.getDeliveryDate()%></td>
					<td><%=item.getDeliveryTime()%></td>
					<td><%=item.getUserName()%></td>
					<td><%=item.getUserMobileNo()%></td>
					<td><%=item.getOrderDate()%></td>
					<td style="color: red">Rejected</td>					
				</tr>
				<%} %>
			</tbody>
		</table>
			<%
		    final List<Order> summary = SummaryManager.getOrderSummary(loggedInUsername);
			int j=0;
			for(Order order: summary){
				j++;
			%>
					<p><h5>Order No <%=j%></h5></p>
					<p><h5>************</h5></p>
					<p><h5>Total price: Rs.<%=order.getOrderPrice()%>/-</h5></p>
					<p><h5> Delivery City: <%=order.getDeliveryCity()%></h5></p>
					<p><h5>Delivery Address: <%=order.getDeliverAddress()%></h5></p>
					<p><h5>Delivery Date: <%=order.getDeliveryDate()%></h5></p>
					<p><h5>Max Delivery time: <%=order.getDeliveryTime()%></h5></p>
					<p><h5>Mobile No: <%=order.getUserMobileNo()%></h5></p>
					<p><h5>Ordered Date: <%=order.getOrderDate()%></h5></p>
					<%if(count>0){
					int deliveryCharge=CityManager.getDeliveryCharge(order.getDeliveryCity());%>
					<p><h5>Delivery Charge: Rs. <%=deliveryCharge %></h5></p>
					<%}else{ %>
						<p><h5>Delivery Charge: Rs. 75</h5></p>
						<%} %>
					<% } %>						
	</main>
</body>
</html>