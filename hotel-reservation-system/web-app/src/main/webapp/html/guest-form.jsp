<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
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
			<form class="guest-form" action="./update-guest" method="post" th:object="${guest}">
				<h1>Edit guest</h1>
				<label for="name">Name</label>
	            <input type="text" id="name" name="name" value="${guest.name}" th:field="*{name}" required>
	            <label for="email">Email</label>
	            <input type="text" id="email" name="email" value="${guest.email}" th:field="*{email}">
	            <label for="phoneNumber">Phone number</label>
	            <input type="tel" pattern = "[0-9]{10}" 
	                   title="A valid phone number consist of 10 digits"
	                   id="phoneNumber" name="phoneNumber" value="${guest.phoneNumber}" th:field="*{phoneNumber}" required>
	            <label for="typeGuest">Select type guest</label>
	            <select id="typeGuest" name="typeGuest" th:field="*{typeGuest}">
	                <option value="basic">Basic</option>
	                <option value="plus">Plus</option>
	            </select>
	            <div class="date-container">
	                <div class="check-in-date">
	                    <label for="checkindate">Check in date</label>
	                    <input type="date" id="checkInDate" name="checkInDate" value="${guest.checkInDate}" th:field="*{checkInDate}" required>
	                </div>
	                <div class="chek-out-date">
	                    <label for="checkoutdate">Check out date</label>
	                    <input type="date" id="checkOutDate" name="checkOutDate" value="${guest.checkOutDate}" th:field="*{checkOutDate}" required>
	                </div>
	            </div>
	            <input type="submit" name="save-button" id="save-button" value="Update guest">
	            <a href="/main" class="cancel-button">Cancel</a>
	            <%if(request.getAttribute("errorMessage")!=null){%>
	            <h3><%=request.getAttribute("errorMessage")%></h3>
				<%}%>
			</form>
        </c:if>
        
        <c:if test="${guest == null}">
			<form class="guest-form" method="post" action="./add-guest" th:object="${guest}">
				<h1>Add new guest</h1>
				<label for="name">Name</label>
	            <input type="text" id="name" name="name" th:field="*{name}" required>
	            <label for="email">Email</label>
	            <input type="text" id="email" name="email" th:field="*{email}">
	            <label for="phoneNumber">Phone number</label>
	            <input type="tel" pattern = "[0-9]{10}" 
	                   title="A valid phone number consist of 10 digits"
	                   id="phoneNumber" name="phoneNumber" th:field="*{phoneNumber}" required>
	            <label for="typeGuest">Select type guest</label>
	            <select id="typeGuest" name="typeGuest" th:field="*{typeGuest}">
	                <option value="basic">Basic</option>
	                <option value="plus">Plus</option>
	            </select>
	            <div class="date-container">
	                <div class="check-in-date">
	                    <label for="checkindate">Check in date</label>
	                    <input type="date" id="checkInDate" name="checkInDate" th:field="*{checkInDate}" required>
	                </div>
	                <div class="chek-out-date">
	                    <label for="checkoutdate">Check out date</label>
	                    <input type="date" id="checkOutDate" name="checkOutDate" th:field="*{checkOutDate}" required>
	                </div>
	            </div>
				<input type="submit" name="save-button" id="save-button" value="Add guest">
           		<a href="./main" class="cancel-button">Cancel</a>
           		 <%if(request.getAttribute("errorMessage")!=null){%>
	            <h3><%=request.getAttribute("errorMessage")%></h3>
				<%}%>
			</form>
        </c:if>
    </main>
</body>
</html>