<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List"%>
    <%@ page import="java.time.LocalDate"%>
    <%@ page import="in.bloomapp.service.SummaryManager"%>
    <%@ page import="in.bloomapp.model.Order"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Todays Orders</title>
</head>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
<body>
<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
	<h1 style="color:pink">Flowers to be delivered</h1>
	<table class="table table-bordered" style="font-size: 15px">
	<caption style="color:white">Flowers available</caption>
		<thead>
			<tr>
				<th scope="col" id= "serialnumber" >S.NO</th>
				<th scope="col" id= "category">Category</th>
				<th scope="col" id= "type">Type</th>
				<th scope="col" id= "quantity">Quantity</th>
				<th scope="col" id= "price">Price</th>
				<th scope="col" id= "deliveryCity">Delivery City</th>
				<th scope="col" id= "deliveryAddress">Delivery address</th>
				<th scope="col" id= "deliveryDate">Delivery date</th>
				<th scope="col" id= "time">Max Time</th>
				<th scope="col" id= "userName">User name</th>
				<th scope="col" id= "mobileNo">user mobile No</th>
				<th scope="col" id= "orderdate">Order date</th>
			</tr>
		</thead>	
<tbody id="order-tbl">
</table>
<script type="text/javascript">
function getApprovalList(){
	let url = "TodaysOrderServlet";
	fetch(url).then(res=> res.json()).then(res=>{
		let orders = res;
		let SNO=1;
		console.log("Got response from servlet");
		console.log(orders);
		let content = "";
		for(let order of orders){
			content += "<tr><td>" +SNO +"</td><td>" +order.orderCategory+"</td><td>"+order.orderType+"</td><td>"+order.orderQuantity
			+"</td><td>"+order.orderPrice+"</td><td>"+order.deliveryCity+"</td><td>"+order.deliverAddress+"</td><td>"+
			moment(order.deliveryDate).format('DD-MM-YYYY')+"</td><td>"+moment(order.deliveryTime).format('h:mm:ss')+"</td><td>"+
			order.userName+"</td><td>"+order.userMobileNo+
			"</td><td>"+moment(order.orderDate).format('DD-MM-YYYY')+"</td></tr>";	
			SNO++;
		}
		console.log(content);
		document.querySelector("#order-tbl").innerHTML= content;
	})
}
getApprovalList();
</script>
</main>
</body>
</html>