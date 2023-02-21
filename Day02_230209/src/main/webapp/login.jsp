<%-- 1. 로그인 폼을 만들어서 입력한 값을 로그인 버튼을 누르면 화면에 출력하기
   		로그인 폼 : login.jsp / 화면에 출력 : servlet으로 구현 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>LOGIN</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.2.3/css/bootstrap.min.css" 
			  integrity="sha512-SbiR/eusphKoMVVXysTKG/7VseWii+Y3FdHrt0EpKgpToZeemhqHeZeLWLhJutz/2ut2Vw1uQEj2MbRF+TVBUA==" 
			  crossorigin="anonymous" referrerpolicy="no-referrer" />
		<style>
			h1 {
				text-align: center;
				font-weight: bold;
				padding: 10px;
				color: white;
				margin-bottom: 0px;
				border-radius: 1mm;
				position: relative;
				top: 9px;
			}
			form {
				margin: 0px auto;
				text-align: center;
			}
			input {
				border: 1px solid gray;
				padding: 2px;
			}
	 		label {
				line-height: 150%;
				font-weight: bold;
				font-size: 17px;
				color: gray;
			}
			.input-group {
				margin: 10px auto;
				position: relative;
				right: 10px;
				top: 42px;
			}
			.input-group:first-child {
				margin-top: -10px!important;
			}
			#submit {
				position: relative;
				left: 155px;
				bottom: 37px;
			}
		</style>
	</head>
	<body>
		<div class="container mw-auto mt-1 p-5">
			<h1 class="bg-success">LOGIN</h1>
			<form method="POST" action="/login" name="frm" id="frm" class="form-control col shadow">
				<div class="p-1">
					<div class="input-group">
						<label for="id" class="col-sm-3"> 아 이 디 </label>
						<input type="text" name="id" id="id" placeholder="아이디를 입력하세요." class="col-sm-6">
					</div>
					<div class="input-group">
						<label for="pw" class="col-sm-3">비밀번호 </label>
						<input type="password" name="pw" id="pw" placeholder="비밀번호를 입력하세요." class="col-sm-6">
					</div>
					<div>
						<input type="submit" name="submit" id="submit" value="login" 
							   class="btn btn-warning btn-lg p-3 shadow col-sm-3">
					</div>
				</div>
			</form>
		</div>
	</body>
</html>