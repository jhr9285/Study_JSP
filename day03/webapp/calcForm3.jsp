<!-- 7-1 : 액션 종합 예제 - 계산기 구현 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>계산기 - useBean</title>
	</head>
	<body>
		<h2>계산기 - useBean</h2>
		<hr>
		<form method="POST" action="calc3.jsp">
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
	</body>
</html>