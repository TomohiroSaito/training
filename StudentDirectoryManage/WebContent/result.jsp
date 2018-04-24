<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
				<h2 class="register-result student-square-text">登録が完了しました。</h2>
				<p><a class="top-page-link" href="<%=request.getContextPath()%>/top_page.jsp">TOPページへ</a></p>
			</div>
		</div>
</body>
</html>
