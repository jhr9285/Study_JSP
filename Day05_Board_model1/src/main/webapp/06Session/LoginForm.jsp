<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Session - Login Form</title>
		<link rel="../Css/Style.css">
	</head>
	<body>
		<jsp:include page="../Common/Link.jsp"></jsp:include>   <%-- <jsp:include /> = <%@ include %> ==> 액션태그 --%>
		<h2>로그인 페이지</h2>
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
			아이디 : <input type="text" name="user_id" /><br />
			패스워드 : <input type="password" name="user_pw" /><br />
			<input type="submit" value="로그인하기" />
		</form>
		<%  /* 로그인 된 상태 */		
			} else {
		%>
			<%= session.getAttribute("UserName") %> 회원님, 로그인하셨습니다. <br />
			<a href="Logout.jsp">[로그아웃]</a>
		<%
			}
		%>
	</body>
</html>