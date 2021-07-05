<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import ="java.util.List" %>
<%@ page import ="in.bloomapp.model.Flower" %>
<%@ page import ="in.bloomapp.service.FlowerManager" %>
<!DOCTYPE html>
<html lang="en">
<%
String loggedInUsername = (String)session.getAttribute("LOGGED_IN_USER");
String role = (String)session.getAttribute("ROLE");
%>
<head>
<meta charset="ISO-8859-1">
<title>Flowers available</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"></script>
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
			<tbody id="flower-data">
	
		</tbody>
	</table>	
	<script>
		function getAllFlowers(){
			//event.preventDefault();
			console.log("Fetching flowers");
			let role='<%=role%>';
			let loggedInUsername='<%=loggedInUsername%>';
			let url="DisplayFlowersServlet";
			console.log(role);
			fetch(url).then(res=> res.json()).then(res=>{
				let flowers=res;
				console.log(flowers);
				console.log("replied");
				let content="";
				let serial=1;
				for(let item of flowers){
					content += "<tr><td>"+serial+
					"</td><td id=getCategory>"+item.category+
					"</td><td id=getType>"+item.type+
					"</td><td>"+item.price;
					let type=(item.type);
					 if (role=="ADMIN"){
						/* content+="</td ><td><a class=\"btn btn-danger\" href=\"DeleteFlowerServlet?category="+item.category+"&type="
								+item.type+"\">Delete</a></td></tr>"; */
						content+="</td><td><button class=\"btn btn-danger\" onclick=\"deleteFlower('"+item.type+"','"+item.category+"')\" >Delete</button></td></tr>";
					 }
					else{ 
						content+="</td></tr>";	
					} 
					serial++;
				}
			console.log(content);
			document.querySelector("#flower-data").innerHTML= content;
			});
		}
		
		function deleteFlower(type,category){
			event.preventDefault();
			console.log("delete CS");
			console.log(type);
			console.log(category);
			const queryParameter="?category="+category+"&type="+type;
			let url="DeleteFlowerServlet"+queryParameter;
			let data={};
			axios.get(url,data).then(res=> {
				let data = res.data;
				alert(data.infoMessage);
				window.location.href="displayFlowers.jsp";
				}
			).catch(err=>{
				let data = err.response.data;
				alert(data.errorMessage);
				window.location.href="displayFlowers.jsp";
			}); 
		} 
		function myFunction() {
			var input, filter, table, tr, td, i, txtValue;
			input = document.getElementById("myInput");
			filter = input.value.toUpperCase();
			table = document.getElementById("myTable");
			tr = table.getElementsByTagName("tr");
			for (i = 0; i < tr.length; i++){
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
		getAllFlowers();
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