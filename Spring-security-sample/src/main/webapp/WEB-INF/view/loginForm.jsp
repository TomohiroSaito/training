<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%-- --%>
<div id="wrapper">
	<h3>ログインフォーム</h3>
	${param.containsKey('error')}
	<c:if test="${param.containsKey('error')}">
		<span style="color: red;">
			<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>
		</span>
	</c:if>
	<c:url var="loginUrl" value="/authentication"/>
	<form:form action="${loginUrl}">
		<table>
			<tr>
				<td><label for="username">ユーザー名</label></td>
				<td><input type="text" id="username" name="uid"></td>
			</tr>
			<tr>
				<td><label for="password">パスワード</label></td>
				<td><input type="password" id="password" name="pwd"></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><button>ログイン</button></td>
			</tr>
		</table>
	</form:form>
</div>