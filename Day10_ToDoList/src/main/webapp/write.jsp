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
		<style type="text/css">
			input {
				text-align: center;
			}
		</style>
	</head>
	<body style="font-family:'IBM Plex Sans KR', sans-serif;">
		<div class="container" style="margin-top: 50px;">
			<h2 align="center">To Do List</h2>
			<!-- form 태그에 action 속성값으로 주소를 입력하지 않으면 submit 했을 때 데이터가 주소표시줄의 주소로 전송된다. -->
			<form method="post" style="margin-top: 20px; margin-bottom: 5px;" > 
				<input type="text" name="content" placeholder="To Do" class="form-control form-group">
				<input type="text" name="state" placeholder="State" class="form-control form-group">
				<div align="center">
					<input type="submit" value="등록" class="btn btn-primary">
				</div>
			</form>
		</div>
	</body>
</html>