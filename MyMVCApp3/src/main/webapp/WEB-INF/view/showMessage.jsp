<!DOCTYPE html>

<%@ page language="java"
 contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<html>
	<head>
		<meta charset="utf-8">
		<title>Welcome</title>
		<style>
		body { font-size:14pt; color:#666; }
		h1 { font-size:70pt; color:#aaa; margin:-15px 0px; }
		</style>
	</head> 
	<body>
		<h1>Welcome</h1>
		<p>${msg}</p>
		<form method="post" action="./post">
			<input type="text" name="text1">
			<input type="submit">
		</form>
	</body>
</html>
