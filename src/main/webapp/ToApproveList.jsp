<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="in.bloomapp.model.Order"%>
<%@ page import="in.bloomapp.service.OrderProcedureManager"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>To Approve List</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
		<h1 style="color: pink">To Approve List</h1>
		<div>
		<%
		String Message = request.getParameter("infoMessage");
		if(Message != null){
			out.println("<font color='green'>" + Message + "</font>");
		}
		%>
		<%
		String Msg = request.getParameter("errorMessage");
		if(Msg != null){
			out.println("<font color='red'>" + Msg + "</font>");
		}
		%></div>
		<table class="table table-border" style="font-size: 15px">
			<caption style="color: red">Flower whose order needs
				approval is shown here</caption>
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
		    final List<Order> approve = OrderProcedureManager.approveItem();
			int i=0;
			for(Order order: approve){
				i++;
			%>
				<tr>
					<th scope="col"><%=i%></th>
					<td><%=order.getOrderCategory() %></td>
					<td><%=order.getOrderType()%></td>
					<td>Rs.<%=order.getOrderPrice()%>/-
					</td>
					<td><%=order.getDeliveryCity()%></td>
					<td><%=order.getDeliverAddress()%></td>
					<td><%=order.getDeliveryDate()%></td>
					<td><%=order.getDeliveryTime()%></td>
					<td><%=order.getUserName()%></td>
					<td><%=order.getUserMobileNo()%></td>
					<td><%=order.getOrderDate()%></td>
					<td><a
						href="ToApproveServlet?category=<%=order.getOrderCategory()%>&type=<%=order.getOrderType()%>&price=<%=order.getOrderPrice()%>
						&deliveryAddress=<%=order.getDeliverAddress()%>
						&deliveryDate=<%=order.getDeliveryDate()%>
						&deliveryTime=<%=order.getDeliveryTime()%>
						&UserMobile=<%=order.getUserMobileNo()%>
						&OrderDate=<%= order.getOrderDate()%>
						&deliveryStatus=<%="yetToDeliver"%>"
						class="btn btn-success"></a> <a href="ToApproveServlet?category=<%=order.getOrderCategory()%>&type=<%=order.getOrderType()%>
						&price=<%=order.getOrderPrice()%>
						&deliveryAddress=<%=order.getDeliverAddress()%>
						&deliveryDate=<%=order.getDeliveryDate()%>
						&deliveryTime=<%=order.getDeliveryTime()%>
						&UserMobile=<%=order.getUserMobileNo()%>
						&OrderDate=<%= order.getOrderDate()%>
						&deliveryStatus=<%="Rejected"%>"
						class="btn btn-danger"></a></td>
				</tr>
				<%} %>
			</tbody>
		</table>
		<a href="displayFlowers.jsp">Flowers</a>
	</main>
</body>
</html>