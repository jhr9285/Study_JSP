<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>상품정보 조회</title>
	</head>
	<body>
		<h1>상품정보 조회</h1>
		<hr>
		<ul>
			<li>상품코드: ${p.id}</li>
			<li>상 품 명: ${p.name}</li>
			<li>제 조 사: ${p.maker}</li>
			<li>가    격: ${p.price}</li>
			<li>등 록 일: ${p.date}</li>
		</ul>
	</body>
</html>