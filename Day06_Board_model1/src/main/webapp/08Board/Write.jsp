<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./IsLoggedIn.jsp" %>   
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원제 게시판</title>
		<link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR&display=swap" rel="stylesheet">
		<link rel="stylesheet" href="../Css/Style.css">
		<script type="text/javascript">
			function validateForm(form) {

			}
		</script>
	</head>
	<body>
		<jsp:include page="../Common/Link.jsp"></jsp:include>
		<h2 align="center">회원제 게시판 - 글쓰기(Write)</h2>
		<form name="writeFrm" method="post" action="WriteProcess.jsp" onsubmit="return validteForm(this);">
			<table border="1" width="100%">
				<tr>
					<td>제목</td>
					<td>
						<input type="text" name="title" style="width: 100%;" />
					</td>
				</tr>
				<tr>
					<td>내용</td>
					<td>
						<textarea name="content" style="width: 100%; height: 100px;"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button type="submit">작성완료</button>
						<button type="reset">다시입력</button>
						<button type="button" onclick="location.href='List.jsp';">목록보기</button>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>