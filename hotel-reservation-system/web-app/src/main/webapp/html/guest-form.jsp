<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/guest-form.css">
    <link rel="icon" href="assets/hotel-icon-page.png">
    <title>Guest's information</title>
</head>
<body>
    <main>
   		<div class="image-container"></div>
        <c:if test="${guest != null}">
			<form class="guest-form" action="./update-guest" method="post">
				<h1>Edit guest</h1>
				<label for="name">Name</label>
	            <input type="text" id="name" name="name" value="${guest.name}" required>
	            <label for="email">Email</label>
	            <input type="text" id="email" name="email" value="${guest.email}">
	            <label for="phoneNumber">Phone number</label>
	            <input type="tel" pattern = "[0-9]{10}" 
	                   title="A valid phone number consist of 10 digits"
	                   id="phoneNumber" name="phoneNumber" value="${guest.phoneNumber}" required>
	            <label for="typeGuest">Select type guest</label>
	            <select id="typeGuest" name="typeGuest">
	                <option value="basic">Basic</option>
	                <option value="plus">Plus</option>
	            </select>
	            <div class="date-container">
	                <div class="check-in-date">
	                    <label for="checkindate">Check in date</label>
	                    <input type="date" id="checkindate" name="checkindate" value="${guest.checkInDate}" required>
	                </div>
	                <div class="chek-out-date">
	                    <label for="checkoutdate">Check out date</label>
	                    <input type="date" id="checkoutdate" name="checkoutdate" value="${guest.checkOutDate}" required>
	                </div>
	            </div>
	            <input type="submit" name="save-button" id="save-button" value="Update guest">
	            <a href="/admin" class="cancel-button">Cancel</a>
	            <%if(request.getAttribute("errorMessage")!=null){%>
	            <h3><%=request.getAttribute("errorMessage")%></h3>
				<%}%>
			</form>
        </c:if>
        
        <c:if test="${guest == null}">
			<form class="guest-form" action="./add-guest" method="post">
				<h1>Add new guest</h1>
				<label for="name">Name</label>
	            <input type="text" id="name" name="name" required>
	            <label for="email">Email</label>
	            <input type="text" id="email" name="email">
	            <label for="phoneNumber">Phone number</label>
	            <input type="tel" pattern = "[0-9]{10}" 
	                   title="A valid phone number consist of 10 digits"
	                   id="phoneNumber" name="phoneNumber" required>
	            <label for="typeGuest">Select type guest</label>
	            <select id="typeGuest" name="typeGuest">
	                <option value="basic">Basic</option>
	                <option value="plus">Plus</option>
	            </select>
	            <div class="date-container">
	                <div class="check-in-date">
	                    <label for="checkindate">Check in date</label>
	                    <input type="date" id="checkindate" name="checkindate" required>
	                </div>
	                <div class="chek-out-date">
	                    <label for="checkoutdate">Check out date</label>
	                    <input type="date" id="checkoutdate" name="checkoutdate" required>
	                </div>
	            </div>
				<input type="submit" name="save-button" id="save-button" value="Add guest">
           		<a href="./admin" class="cancel-button">Cancel</a>
           		 <%if(request.getAttribute("errorMessage")!=null){%>
	            <h3><%=request.getAttribute("errorMessage")%></h3>
				<%}%>
			</form>
        </c:if>
    </main>
</body>
</html>