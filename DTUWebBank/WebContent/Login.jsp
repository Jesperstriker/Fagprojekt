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

<!--  The section source code is copied from http://www.cssflow.com/snippets/login-form/demo/html  -->
<div id="section">
      <h1>Login</h1>
      <form method="post" action="index.html">
        <p><input type="text" name="login" value="" placeholder="Username"></p>
        <p><input type="password" name="password" value="" placeholder="Password"></p>
        <p class="remember_me">
          <label>
            <input type="checkbox" name="remember_me" id="remember_me">
            Remember me on this computer
          </label>
        </p>
        <p class="submit"><input type="submit" name="commit" value="Login"></p>
      </form>
    </div>

    <div class="login-help">
      <p>Forgot your password? <a href="index.html">Click here to reset it</a>.</p>
</div>

<div id="footer">
Copyright © Michael Romer and Jesper Douglas
</div>

</body>
</html>