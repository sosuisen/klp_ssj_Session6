<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="message" method="POST">
		メッセージ入力 <input type="text" name="message">
		<button>送信</button>
	</form>
	<form action="clear" method="GET">
		<button>Clear</button>
	</form>	
	<hr>
	<h1>メッセージ一覧</h1>
	<br>
	<%
	ArrayList<String> list = (ArrayList<String>) session.getAttribute("history");
	for (String mes : list) {
	%>
	<%=mes%><br>
	<%
	}
	%>
</body>
</html>