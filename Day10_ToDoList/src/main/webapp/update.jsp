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
			function updateConfirm() {
				if(confirm("수정하시겠습니까?")) {
					location.href="/Day10_ToDoList/view.do?no=${ dto.no }";
				} else {
					location.href="/Day10_ToDoList/update.do?no=${ dto.no }";
				}
			}
		</script>
		<style type="text/css">
			button {
				margin-top: -30px;
				margin-left: 5px;
			}
			input {
				text-align: center;
			}
		</style>
	</head>
	<body style="font-family:'IBM Plex Sans KR', sans-serif;">
		<div class="container" style="margin-top: 50px;">
			<h2 align="center">To Do List</h2>
			<form method="post">
				<table width="70%" class="table table-borderless">
					<tr>
						<td>
							<input type="text" name="no" value="${ dto.no }" class="form-control form-group" readonly> <!-- 읽기전용 설정하여 편집 막기  -->
						</td>
					</tr>
					<tr>
						<td>
							<input type="text" name="content" value="${ dto.content }" class="form-control form-group" style="margin-top: -20px;">
						</td>
					</tr>
					<tr>	
						<td>
							<input type="text" name="state" value="${ dto.state }" class="form-control form-group" style="margin-top: -20px;">
						</td>
					</tr>
					<tr>	
						<td>
							<input type="text" name="wdate" value="${ dto.wdate }" class="form-control form-group" style="margin-top: -20px;" readonly> <!-- 읽기전용 설정하여 편집 막기  -->
						</td>
					</tr>
				</table>
				<div align="center">
					<button type="submit" onclick="updateConfirm();" class="btn btn-primary" >수정완료</button> <!-- button type의 기본값: submit ==> 생략해도 됨 -->
					<button type="button" onclick="location.href='/Day10_ToDoList/view.do?no=${ dto.no }'" class="btn btn-primary">본문보기</button>
				</div>
			</form>
		</div>
	</body>
</html>