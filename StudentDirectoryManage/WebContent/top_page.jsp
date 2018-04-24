<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>生徒名簿管理システム</title>
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
				<h2 class="page-title">生徒名簿管理システム</h2>
				<p class="student-libraly student-square-text"><a href="<%=request.getContextPath()%>/StudentDirectoryManage?select=list">生徒一覧</a></p>
				<p class="student-register student-square-text"><a href="<%=request.getContextPath()%>/StudentDirectoryManage?select=register">新規登録</a></p>
			</div>
		</div>
</body>
</html>
