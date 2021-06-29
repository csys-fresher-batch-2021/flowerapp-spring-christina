<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@ page import="java.util.List"%>
<%@ page import="in.bloomapp.model.Flower"%>
<%@ page import="in.bloomapp.service.SummaryManager"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Most Ordered</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<main class="container-fluid">
<form onsubmit="getSummaryList()" >
	<h1 style="color: Pink">Most ordered on date</h1>
	<label for="SummaryDate">Enter date to get summary:</label>
		<input type="date" name="SummaryDate" id="SummaryDate"required /> <br />
	<button type="submit" class="btn btn-info">Submit</button><br/><br/>
	<table class="table table-bordered" ><caption>List of item</caption><thead><tr>
					<th scope="col" id= "serialnumber">S.NO</th>
					<th scope="col" id= "category">Category</th>
					<th  scope="col" id= "type">Type</th>
					<th scope="col" id= "orders">Total orders</th>
				</tr>
			</thead>
			<tbody id="table">
			</tbody>
			</table>
			</form>
	<script type="text/javascript">
	function getSummaryList(){	
		event.preventDefault();
		let summaryDate=document.querySelector("#SummaryDate").value;
		const queryParameter="?summaryDate="+summaryDate;
		let url = "MostOrderedServlet"+queryParameter;
		fetch(url).then(res=> res.json()).then(res=>{
			let totalSummary=res;
		let content="";
		let i=1;
		for(let flower of totalSummary){	
			content+="<tr><td>"+i+
			"</td><td>"+flower.category+
			"</td><td>"+flower.type+
			"</td><td>"+flower.quantity+"</td></tr>";
			i++;
		}
		document.querySelector("#table").innerHTML= content;
	});	
	}
	</script>
	</main>
</body>
</html>