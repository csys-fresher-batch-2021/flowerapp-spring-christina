<link rel="stylesheet" href="assets/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/fontawesome.min.css">
<link rel="stylesheet" href="assets/css/style.css">

<%
String loggedInUsername = (String)session.getAttribute("LOGGED_IN_USER");
String role = (String) session.getAttribute("ROLE");
%>
<header>
<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
  <a class="navbar-brand" style="color: pink" href="#">BLOOM</a>
  <button class="navbar-toggler d-lg-none" type="button" data-toggle="collapse" data-target="#collapsibleNavId" aria-controls="collapsibleNavId"
      aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="collapsibleNavId">
    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
      <li class="nav-item active">
        <a class="nav-link" href="index.jsp">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="displayFlowers.jsp">Flowers</a>
      </li>
       <% if (loggedInUsername != null && role != null && role.equalsIgnoreCase("ADMIN")){ %>
       <li class="nav-item active">
        <a class="nav-link" href="addFlower.jsp">Add Product</a>
      </li>
        <li class="nav-item active">
        <a class="nav-link" href="ToApproveList.jsp">Delivery Approval</a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="SummaryHome.jsp">Admin Summary</a>
      </li>
      <%} %>
       <li class="nav-item active">
        <a class="nav-link" href="DisplayCities.jsp">Cities available <span class="sr-only">(current)</span></a>
      </li>
    </ul>
     <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
          <% if (loggedInUsername == null){ %>
      <li class="nav-item active">
        <a class="nav-link" href="Login.jsp">Login</a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="registerUser.jsp">Register</a>
      </li>
      <%} else { %>
         <li class="nav-item active">
        <a class="nav-link" style="color: pink" href="#">Welcome <%=loggedInUsername %></a>
      </li>
        <li class="nav-item active">
        <a class="nav-link" style="color: red" href="LogoutServlet">Logout</a>
      </li>
      <% if (loggedInUsername != null && role != null && !role.equalsIgnoreCase("ADMIN")){ %>
         <li class="nav-item active">
        <a class="nav-link" href="UserSummary.jsp">My orders</a>
      </li>
       <% }%> 
      <% }%> 
      </ul> 
  </div>
</nav>
</header>