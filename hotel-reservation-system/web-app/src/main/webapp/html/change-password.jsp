<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/change-password.css">
    <link rel="icon" href="assets/hotel-icon-page.png">
    <title>Change password</title>
</head>
<body>
    <main>
      <div class="image-container"></div>
      <form class="sign-in-form" action="/update-password" method="post" th:object="${guest}">
            <h3>Change password</h3>
            <label for="username">Username</label>
            <input id="username" type="text" name="username" th:field="*{username}" required>
            <label for="password">New password</label>
            <input id="password" type="password" name="password" th:field="*{password}"  required>
            <label for="re-password">Reenter password</label>
            <input id="re-password" type="password" name="re-password" required>
            <input type="submit" name="sign-in-button" id="sign-in-button" value="Save">
            <a href="./home" class="back-button">Cancel</a>
            <%if(request.getAttribute("errorMessage")!=null){%>
   			<h2 class="show-message"><%=request.getAttribute("errorMessage")%></h2> 
   			<%}%>
        </form>
    </main>
</body>
</html>