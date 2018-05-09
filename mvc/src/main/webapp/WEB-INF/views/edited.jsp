<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sum.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更新完了</title>
</head>
<body>
<h1>更新完了</h1>
<dl>
	<dt>名前</dt>
	<dd><c:out value="${editCustomer.name}"/></dd>
	<dt>住所</dt>
	<dd><c:out value="${editCustomer.address}"/></dd>
	<dt>Eメールアドレス</dt>
	<dd><c:out value="${editCustomer.emailAddress}"/></dd>
</dl>
<c:url var="url" value="/customer"/>
<a href="${url}">戻る</a>
</body>
</html>