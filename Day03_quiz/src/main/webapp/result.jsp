<!-- 1. MVC패턴 연습하기
        https://www.w3schools.com/howto/tryit.asp?filename=tryhow_css_responsive_form
 	    폼에 입력한 데이터를 Model에 넣은 다음 화면에 값을 출력하기  -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>입력한 정보 조회</title>
	</head>
	<body>
		<h1>입력한 정보 조회</h1>
		<hr>
 		<ul>
			<li>이름: ${m.firstname}</li>
			<li>성씨: ${m.lastname}</li>
			<li>국가: ${m.country}</li>
			<li>내용: ${m.subject}</li>
		</ul> 
	</body>
</html>