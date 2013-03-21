<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Login Form Styling</title>

<link rel="shortcut icon" href="../favicon.ico">
<link rel="stylesheet" type="text/css" href="css/style.css" />
<script src="js/modernizr.custom.63321.js"></script>
<!--[if lte IE 7]><style>.main{display:none;} .support-note .note-ie{display:block;}</style><![endif]-->
<style>
@import
	url(http://fonts.googleapis.com/css?family=Montserrat:400,700|Handlee);

body {
	background: #eedfcc url(images/bg3.jpg) no-repeat center top;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	background-size: cover;
}

.container>header h1,.container>header h2 {
	color: #fff;
	text-shadow: 0 1px 1px rgba(0, 0, 0, 0.5);
}
</style>
<script type="text/javascript" src="js/script.js">
</script>
</head>
<body>
	<form class="form-5 clearfix" name = "loginForm" action="/socialfood/loginAction.do" onsubmit="return loginCheck()">
		<p>
			
			<input type="text" id="id" name="id" placeholder="id">
			<input type="password" name="password" id="password"
				placeholder="password">
		</p>
		<button type="submit" name="submit">
			<i class="icon-arrow-right"></i> <span>Sign in</span>
		</button>
	</form>
	<p align="center">
		<font size="2" color="white">Copyright @ 2013 SocialFood.com
		</font>
	</p>

	<!-- jQuery if needed -->
	<script type="text/javascript"
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery.placeholder.min.js"></script>
	<script type="text/javascript">
		$(function() {
			$('input, textarea').placeholder();
		});
	</script>
</body>
</html>