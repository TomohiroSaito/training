<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sum.com/jsp.jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>顧客一覧画面</title>
</head>
<body>
<h2>顧客一覧画面</h2>
<c:if test="${editedCustomer != null}">
以下の顧客が更新されました。
<dl>
	<dt>名前</dt>
	<dd><c:out value="${editedCustomer.name}"/></dd>
	<dt>住所</dt>
	<dd><c:out value="${editedCustomer.address}"/></dd>
	<dt>Eメールアドレス</dt>
	<dd><c:out value="${editedCustomer.emailAddress}"/></dd>
</dl>
</c:if>
<table border="1">
	<tr>
		<th>Id</th>
		<th>名前</th>
		<th>住所</th>
		<th>Eメールアドレス</th>
		<td></td>
	</tr>
	<c:forEach items="${customers}" var="customer">
	<tr>
		<td><c:out value="${customer.id}"/></td>
		<td><c:out value="${customer.name}"/></td>
		<td><c:out value="${customer.address}"/></td>
		<td><c:out value="${customer.emailAddress}"/></td>
		<td>
			<c:url value="/customer/${customer.id}" var="url"/>
			<a href="${url}">詳細</a>
			<c:url value="/customer/${customer.id}/edit" var="url"/>
			<a href="${url}">編集</a>
		</td>
	</tr>
	</c:forEach>
</table>
</body>
</html>