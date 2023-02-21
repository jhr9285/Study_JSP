<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Servlet Calculator</title>
	</head>
	<body>
		<%@ include file="gnb.jsp" %>
		<h2>계산기 JSP</h2>
		<hr>
		<form method="POST" action="calc.jsp">
			<input type="text" name="n1" size="10">
			<select name="op">
				<option>+</option>
				<option>-</option>
				<option>*</option>
				<option>/</option>
			</select>
			<input type="text" name="n2" size="10">
			<input type="submit" value="실행">
		</form>
		<%@ include file="footer.jsp" %>
	</body>
</html>