<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import ="java.util.List" %>
<%@ page import ="in.bloomapp.model.City" %>
<%@ page import ="in.bloomapp.service.CityManager" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Display Cities</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
		<h1 style="color:pink">Cities available</h1>
	<table class="table table-border">
	<caption style="color:red">Natural flowers will be only delivered to the following location</caption>
		<thead>
			<tr>
				<th scope="col" id= "serialnumber">S.NO</th>
				<th scope="col" id="DistrictCode">District Code</th>
				<th scope="col"id="CityName">City</th>
				<th scope="col"id="Charge">Delivery Charge</th>				
			</tr>
		</thead>	
			<tbody>
		<%
		    final List<City> cities = CityManager.getCity();
			int i=0;
			for(City city: cities){
				i++;
			%>
			<tr>
			<th  scope="col"><%=i%></th>
			<td><%=city.getDistrictCode() %></td>
			<td><%=city.getCity()%></td>
			<td>Rs.<%=city.getDelivaryCharge()%>/-</td>
		    </tr>
		      <%} %>
		    </tbody>
		    </table>
		    <a href="displayFlowers.jsp">Flowers</a>
</main>
</body>
</html>