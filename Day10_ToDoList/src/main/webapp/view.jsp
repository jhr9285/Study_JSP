<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<script>
			function deleteConfirm() {
				if(confirm("삭제하시겠습니까?")) {
					alert("삭제되었습니다.");
					location.href="/Day10_ToDoList/delete.do?no=${ dto.no }";
				} 
			}
		</script>
		<style type="text/css">
			button {
				margin : 0px auto;
			}
			table {
				margin-top: 25px;
				margin-bottom: 5px;
				text-align: center;
			}
		</style>
	</head>
	<body style="font-family:'IBM Plex Sans KR', sans-serif;">
		<div class="container" style="margin-top: 50px;">
			<h2 align="center">To Do List</h2>
			<table width="70%" class="table table-striped">
				<tr>
					<td>${ dto.no }</td>
				</tr>
				<tr>
					<td>${ dto.content }</td>
				</tr>
				<tr>	
					<td>${ dto.state }</td>
				</tr>
				<tr>	
					<td>${ dto.wdate }</td>
				</tr>
			</table>
			<div class="container" align="center">
				<button type="button" onclick="location.href='/Day10_ToDoList/update.do?no=${ dto.no }';" class="btn btn-primary">수정</button>
				<button type="button" onclick="deleteConfirm();" class="btn btn-primary">삭제</button>
				<button type="button" onclick="location.href='/Day10_ToDoList/list.do'" class="btn btn-primary">목록</button>
			</div>
		</div>
	</body>
</html>