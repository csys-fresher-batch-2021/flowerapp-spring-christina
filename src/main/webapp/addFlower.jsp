<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Add flower to the table</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"></script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
		<div class="d-flex justify-content-center">
		<h3>Add Flowers</h3>
		</div>
		<form onsubmit="addFlower()" method="POST">
		<label  class="d-flex justify-content-center" id="testLabel" style="color:red"></label>
		<div class="d-flex justify-content-center">	
		<label for="Category">Flower Category</label>
		<input type="text" name="Category" id="Category" placeholder="Enter Flower Category" pattern="[a-zA-Z0-9\s]{3,}" required autofocus />
		</div>
		<br/>
		<div class="d-flex justify-content-center">
		<label for="flowerType">Flower Type</label>
		<input type="text" name="flowerType" id="flowerType" placeholder="Enter Flower Type" pattern="[a-zA-Z0-9\s]{3,}"  required autofocus />
		</div>
		<br/>
		<div class="d-flex justify-content-center">
		<label for="price">Price</label>
		<input type="number" name="price" id="price" placeholder="Enter Flower price" step="0.1" required autofocus />
		</div>
		<br/>
		<div class="d-flex justify-content-center">
		<button type="submit" class="btn btn-info">Submit</button>
		</div>
		<div class="d-flex justify-content-center">
		<a href="displayFlowers.jsp">Flower List</a>
		</div>
		</form>
		<script type="text/javascript">
		function addFlower(){
			event.preventDefault();
			let category=document.querySelector("#Category").value;
			let type=document.querySelector("#flowerType").value;
			let price=document.querySelector("#price").value;
			const queryParameter="?category="+category+"&type="+type+"&price="+price;
			let url="AddFlowerServlet"+queryParameter;
			/* let data={"category":category,
					"type":type,
					"price":price}; */
					let data={};
			axios.post(url,data).then(res=> {
				let data = res.data;
				alert(data.infoMessage);
				window.location.href="displayFlowers.jsp";
				}
			).catch(err=>{
				let data = err.response.data;
				alert(data.errorMessage);
			});
		}
		</script>
	</main>
</body>
</html>