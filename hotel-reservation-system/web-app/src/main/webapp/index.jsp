<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Hotel Reservation System</title>
	<link rel="stylesheet" href="css/styles.css"> 
    <link rel="icon" href="assets/hotel-icon-page.png">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<header>
		<nav class="nav">
			<div class="branding">
				<span class="hotel-logo"></span>
			</div>
			<div class="nav-options">
				<a href="">Home</a>
				<%if(session.getAttribute("username")!=null){%>
				<a href="/admin">Hi, <%=session.getAttribute("username")%></a>
				<%}else{%>
				<a href="/admin">Sign in <span class="user-logo"></span></a>
				<%}%> 
			</div>
		</nav>
	</header>
	<main>
		<div class="image-container"></div>
        <div class="presentation-container">
            <h1>HOTEL RESERVATION SYSTEM</h1>
            <%if(session.getAttribute("username") != null){ %>
			<a href="/admin">START</a> 
			<%}else{%>
			<a href="/login">START</a>
			<%} %>
        </div>
	</main>
</body>
</html>
