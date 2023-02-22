<!-- Skill : JSP, MVC, Oracle, DBCP, BOOTSTRAP -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
		<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.slim.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
		<link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR&display=swap" rel="stylesheet">
		<style type="text/css">
			button {
			 	position: relative;
			 	bottom: 10px;
			 }
		</style>
	</head>
	<body style="font-family:'IBM Plex Sans KR', sans-serif;">
		<div class="container" style="margin-top: 50px;">
			<h2 align="center">To Do List</h2>
			
			<!-- 검색 폼 START ------------------------------------------------------------------------------------>
			<form method="get" style="margin-top: 20px; margin-bottom: 5px;" class="form-inline">
				<table width="70%" class="table table-borderless">
					<tr>
						<td align="center">
							<input type="text" name="searchWord" class="form-control" />
							<input type="submit" value="검색하기" class="btn btn-primary" />
						</td>
					</tr>
				</table>
			</form>
			<!-- 검색 폼 END -->
			
			<!-- 출력 목록 START ------------------------------------------------------------------------->
			<table width="70%" class="table">
				<c:forEach items="${ list }" var="row">
					<tr>
						<td width="10%" align="center">${ row.no }</td>
						<!-- 클릭한 글의 번호를 가지고 이동해야 된다. ==> 링크 뒤에 ?no= 붙임 ==> get방식 -->
						<td width="50%"><a href="/Day10_ToDoList/view.do?no=${row.no}">${ row.content }</a></td>
						<td width="15%" align="center">${ row.state }</td>
						<td width="25%" align="center">${ row.wdate }</td>
					</tr>
				</c:forEach>
			</table>
			<!-- 출력 목록 END -->
			
			<!-- 페이지 번호 START ------------------------------------------------------------------------->
			<table width="70%" class="table table-borderless">
				<tr align="center">
					<td>${ map.pagingStr }</td>
					<td width="100">
						<button type="button" onclick="location.href='/Day10_ToDoList/write.do';"
								class="btn btn-primary">글쓰기</button>
					</td>
				</tr>
			</table>
			<!-- 페이지 번호 END -->
		</div>
	</body>
</html>