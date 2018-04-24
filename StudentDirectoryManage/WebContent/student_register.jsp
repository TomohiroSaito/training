<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新規登録</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<header>
		<h1><a href="<%=request.getContextPath()%>/top_page.jsp">生徒名簿管理システム</a></h1>
		<p class="nav-link"><a href="<%=request.getContextPath()%>/StudentDirectoryManage?select=list" class="nav-link-text">生徒一覧</a></p>
		<p class="nav-link"><a href="<%=request.getContextPath()%>/StudentDirectoryManage?select=register" class="nav-link-text">新規登録</a></p>
	</header>
		<div class="main-content-wrap">
			<div class="main-content">
				<h2 class="page-title student-square-text">新規登録</h2>
<%
String message = (String)request.getAttribute("message");
if(!(message == "" || message ==null)) {
%>
				<p class="message"><%=message %></p>
<%
}
%>
				<form class="student-register-form" action="<%=request.getContextPath()%>/CreateStudentsLogic" method="post">
					<p class="student-input-title input-name">生徒名</p>
					<input type="text" name="name" class="student-input-name">
					<p class="asterisk">※</p><br>
					<p class="student-input-title input-class">クラス</p>
					<input type="text" name="party" class="student-input-class"><p class="quantifier">組</p>
					<p class="asterisk">※</p><br>
					<p class="student-input-title input-record">英語</p>
					<input type="text" name="english" class="student-input-record"><p class="quantifier">点</p><br>
					<p class="student-input-title input-record">数学</p>
					<input type="text" name="math" class="student-input-record"><p class="quantifier">点</p><br>
					<p class="student-input-title input-record">国語</p>
					<input type="text" name="japanese" class="student-input-record"><p class="quantifier">点</p><br>
					<p class="student-input-title input-record">社会</p>
					<input type="text" name="social" class="student-input-record"><p class="quantifier">点</p><br>
					<p class="student-input-title input-record">理科</p>
					<input type="text" name="science" class="student-input-record"><p class="quantifier">点</p><br>
					<p class="note">※付いている項目は必須項目です。</p>
					<input type="reset" value="クリア" class="student-register-button">
					<input type="submit" value="登録" class="student-register-button">
				</form>
			</div>
		</div>
</body>
</html>
