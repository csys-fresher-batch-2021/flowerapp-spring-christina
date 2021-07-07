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
			<tbody id="cityTable">
			</tbody>
		    </table>
	<script>
		function getCities(){
			let url="DisplayCitiesServlet";
			fetch(url).then(res=> res.json()).then(res=>{
				let cities=res;
				console.log(cities);
				console.log("replied");
				let content="";
				let serial=1;
				for(let city of cities){
					content += "<tr><td>"+serial+
					"</td><td>"+city.districtCode+
					"</td><td>"+city.cityName+
					"</td><td>"+city.delivaryCharge;
					serial++;
				}
			console.log(content);
			document.querySelector("#cityTable").innerHTML= content;
			});
		}
		getCities();
	</script>
</main>
</body>
</html>