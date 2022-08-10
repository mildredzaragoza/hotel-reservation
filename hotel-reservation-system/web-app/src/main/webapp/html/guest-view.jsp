<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="assets/hotel-icon-page.png">
	<link rel="stylesheet" href="css/admin-view.css">
    <title>Guest</title>
</head>
<body>
	 <header>
        <nav>
            <div class="branding">
                <span class="hotel-logo"></span>
            </div><!-- branding -->
            <div class="nav-options">
                <a href="./home">Home</a>
                <a href="./log-out">Log out</a>
            </div>
          </nav>
    </header>
   <main>
    	<div class="image-container"></div>
        <div class="options-container guest">
            <h2>Select an option</h2>
            <a href="./guests"  class="option">View all guest</a>
            <a href="./guest-form" class="option">Add new guest</a>
        </div>
    </main>
</body>
</html>