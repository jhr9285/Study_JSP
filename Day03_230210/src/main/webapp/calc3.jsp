<!-- 7-1 : 액션 종합 예제 - 계산기 구현 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<jsp:useBean id="calc" class="calcPackage.Calculator"></jsp:useBean>
<jsp:setProperty property="*" name="calc"/>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<body>
		<h2>계산 결과 - useBean</h2>
		<hr>
		결과: <%= calc.calc() %>
	</body>
</html>