<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WEBBANK</title>
<link rel="stylesheet" type="text/css" href="styles/myStyle.css">
</head>
<body>

<div id="header">
<img src="pictures/CrownBankLogo2.png" alt="Logo" width="800" height="140">
</div>

<div id="nav">
<a href="Login.jsp" target="_self">Login</a> <br><br>
<a href="CreateAccount.jsp" target="_self">Create Account</a> <br><br>
<a href="Home.jsp" target="_self">Home</a> <br><br>
<center>
<img src="pictures/Logo.png" alt="Logo" width="140" height="140">
</center>
</div>

<div id="section">
<h1>Create account</h1>
<p>
<p>
<form action="Create_account_request.php" method="post">
  
  <label for="firstname">First name:</label>
  <input id="firstname" placeholder="Jesper" type="text" name="firstname" required><br>
  <label for="lastname">Last name:</label>
  <input id="lastname" type="text" placeholder="Douglas" name="lastname"><br>
  <input type="radio" name="gender" value="male" checked> Male<br>
  <input type="radio" name="gender" value="female"> Female<br>
  <input type="radio" name="gender" value="other"> Other<br>
  <input type="submit" value="Submit"><br>
</form>
</p>

</div>

<div id="footer">
Copyright � Michael Romer and Jesper Douglas
</div>

</body>
</html>