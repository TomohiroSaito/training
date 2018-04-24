<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,studentmodel.student.Student" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>生徒一覧</title>
<link rel="stylesheet" href="style.css">
</head>
<body>

<%
ArrayList<Student> studentList = (ArrayList<Student>)request.getAttribute("studentList");
%>

	<header>
		<h1><a href="<%=request.getContextPath()%>/top_page.jsp">生徒名簿管理システム</a></h1>
		<p class="nav-link"><a href="<%=request.getContextPath()%>/StudentDirectoryManage?select=list" class="nav-link-text">生徒一覧</a></p>
		<p class="nav-link"><a href="<%=request.getContextPath()%>/StudentDirectoryManage?select=register" class="nav-link-text">新規登録</a></p>
	</header>
		<div class="main-content-wrap">
			<div class="main-content">
				<h2 class="page-title student-square-text">生徒一覧</h2>
				<table class="student-list-table">
					<tr>
						<th class="table-col-group" colspan="3">生徒情報</th>
						<th class="table-col-group" colspan="6">成績</th>
					</tr>
					<tr>
						<th class="table-col-name">生徒番号</th>
						<th class="table-col-name">生徒名</th>
						<th class="table-col-name">クラス</th>
						<th class="table-col-name">英語</th>
						<th class="table-col-name">数学</th>
						<th class="table-col-name">国語</th>
						<th class="table-col-name">社会</th>
						<th class="table-col-name">理科</th>
						<th class="table-col-name">合計</th>
					</tr>
<%
int sum = 0;
for(Student student : studentList) {
%>
					<tr>
						<td class=""><%=String.format("%04d", student.getStudentNumber().getNumber()) %></td>
						<td class=""><%=student.getStudentName().getName() %></td>
						<td class=""><%=student.getParty().getPartyName().getName() %></td>
<%
	if(student.getPersonalRecordList().size() > 0) {
		int english = student.getPersonalRecordList().get(0).getRecord().getRecord();
		int math = student.getPersonalRecordList().get(1).getRecord().getRecord();
		int japanese = student.getPersonalRecordList().get(2).getRecord().getRecord();
		int social = student.getPersonalRecordList().get(3).getRecord().getRecord();
		int science = student.getPersonalRecordList().get(4).getRecord().getRecord();
		sum = english + math + japanese + social + science;
%>
						<td class=""><%=english %>点</td>
						<td class=""><%=math %>点</td>
						<td class=""><%=japanese %>点</td>
						<td class=""><%=social %>点</td>
						<td class=""><%=science %>点</td>
						<td class=""><%=sum %>点</td>
<%
	}
	if(student.getPersonalRecordList().size() == 0) {
%>
						<td class="">-</td>
						<td class="">-</td>
						<td class="">-</td>
						<td class="">-</td>
						<td class="">-</td>
						<td class="">-</td>
<%
	}
%>
					</tr>
<%
}
%>
				</table>
			</div>
		</div>
</body>
</html>
