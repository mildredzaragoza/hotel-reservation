<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="assets/hotel-icon-page.png">
    <link rel="stylesheet" href="css/show-all-guest.css">
    <title>Guest registered</title>
</head>
<body>
    <header>
        <nav>
            <div class="branding">
                <span class="hotel-logo"></span>
            </div><!-- branding -->
            <div class="nav-options">
                <a href="./home">Home</a>
                <a href="./admin"><%=session.getAttribute("username")%></a>
            </div>
        </nav>
    </header>
    <main>
        <table class="guest-table">
       		<caption>Current guest</caption>  
            <thead>
                <tr>
                  <th>ID</th>
                  <th>Name</th>
                  <th>Phone number</th>
                  <th>Email</th>
                  <th>Type guest</th>
                  <th>Check in date</th>
                  <th>Check out date</th>
                  <%if(session.getAttribute("username").equals("admin")){%>
                  	<th>Actions</th>
                  <%}%>
                </tr>
              </thead>
              <tbody>
               <c:if test="${guestList != null}">
              	<c:forEach var="guest" items="${guestList}">
	                <tr>
	                  <td>${guest.idGuest}</td>
	                  <td th:value="${guest.name}" th:field="*{name}" id="name">${guest.name}</td>
	                  <td>${guest.phoneNumber}</td>
	                  <td>${guest.email}</td>
	                  <td>${guest.typeGuest}</td>
	                  <td>${guest.checkInDate}</td>
	                  <td>${guest.checkOutDate}</td>
	                  <%if(session.getAttribute("username").equals("admin")){%>
		                  <td class="actions-container">
		                    <a href="/edit-guest<c:out value='${guest.idGuest}'/>"><span class="action edit-button" title="Edit"></span></a>
		                    <a href="/delete-guest<c:out value='${guest.idGuest}'/>"><span class="action delete-button" title="Delete"></span></a>
		                  </td>
		              <%}%>
	                </tr>
	            </c:forEach>
        			</c:if>
              </tbody>
	        </table>
    </main>
</body>
</html>