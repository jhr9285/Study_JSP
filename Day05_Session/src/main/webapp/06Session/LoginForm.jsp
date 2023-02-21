<!-- Quiz230214 - 1. 로그인 페이지에 css 적용하기 ==> 부트스트랩 사용 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Session - Login Form</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.2.3/css/bootstrap.min.css" 
			  integrity="sha512-SbiR/eusphKoMVVXysTKG/7VseWii+Y3FdHrt0EpKgpToZeemhqHeZeLWLhJutz/2ut2Vw1uQEj2MbRF+TVBUA==" 
			  crossorigin="anonymous" referrerpolicy="no-referrer" />
		<link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR&display=swap" rel="stylesheet">
		<link rel="stylesheet" href="../Css/Style.css">
	</head>
	<body>
		<jsp:include page="../Common/Link.jsp"></jsp:include>   <%-- <jsp:include /> = <%@ include %> ==> 액션태그 --%>
		<div class="center shadow col p-2">
			<div class="mw-100 bg-secondary p-1">
				<h2 class="p-1">로그인 페이지</h2>
			</div>
			<span style="color: red; font-size: 1.2em;">
				<%=  /* 3항 조건 연산자 */
					request.getAttribute("LoginErrMsg") == null ? "" : request.getAttribute("LoginErrMsg")
				%>
			</span>
			<%
				if(session.getAttribute("UserId") == null) {
			%>
			<script>
				function validateForm(form) {
					if(!form.user_id.value) {
						alert("아이디를 입력하세요.");
						return false;
					}
					if(form.user_pw.value == "") {
						alert("패스워드를 입력하세요.");
						return false;
					}
				}
			</script>
			
			<!-- 로그인 되지 않은 상태 : 로그인 폼을 보여준다. -->   <!-- onsubmit : 전송될 때 속성값을 실행 -->
			<form action="LoginProcess.jsp" method="post" name="loginFrm" onsubmit="return validateForm(this);">
				<div id="area">
					<div class="col-sm-9">
						<label for="user_id" class="col-sm-3">아 이 디  : </label>
						<input type="text" name="user_id" id="user_id" class="col-sm-8 mt-2 ms-2" /><br />
						<label for="user_pw" class="col-sm-3">패스워드 : </label>
						<input type="password" name="user_pw" id="user_pw" class="col-sm-8 mt-3 ms-2" /><br />
					</div>
					<div class="col-sm-2">
						<input type="submit" value="로그인하기" class="btn btn-outline-success" />
					</div>
				</div>
			</form>
			<%  /* 로그인 된 상태 */		
				} else {
			%>		<div id="result" class="mt-3 md-2">
						[ <%= session.getAttribute("UserName") %> ] 회원님, 로그인하셨습니다. <br />
						<a href="Logout.jsp">[로그아웃]</a>
					</div>
			<%
				}
			%>
		</div>
	</body>
</html>