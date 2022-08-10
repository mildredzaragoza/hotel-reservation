<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns:th="http://www.thymeleaf.org">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="css/sign-in.css">
    <link rel="icon" href="assets/hotel-icon-page.png">
	<title>Sign in</title>
</head>
<body>
	<main>
        <form class="sign-in-form" action="/login" method="post" th:object="${user}">
            <h3>Sign in</h3>
            <label for="username">Username</label>
       		<input type="text" id="username" name="username" th:field="*{username}" required>
        	<label for="password">Password</label>
        	<input type="password" id="password" name="password" th:field="*{password}" required>
        	<a href="/update-password" class="forgot-password">Forgot your password?</a>
            <input type="submit" name="sign-in-button" id="sign-in-button" value="Sign in">
            <a href="./home" class="back-button">Back</a>
            <%if(request.getAttribute("error")!=null){%>
   			<h2 class="show-message">Username or password invalid</h2> 
   			<%}%>
        </form>
   		<div class="image-container"></div>
    </main>
</body>
</html>