<%@ page import="model1.board.BoardDTO"%>
<%@ page import="model1.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./IsLoggedIn.jsp" %>
<%
String num = request.getParameter("num");
BoardDAO dao = new BoardDAO(application);
BoardDTO dto = dao.selectView(num);
String sessionId = session.getAttribute("UserId").toString();
if(!sessionId.equals(dto.getId())) {
	JSFunction.alertBack("작성자 본인만 수정할 수 있습니다.", out);
	return;
}
dao.close();
%>    
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
		<h2 align="center">회원제 게시판 - 수정하기(Edit)</h2>
		<form name="writeFrm" method="post" action="EditProcess.jsp" onsubmit="return validateForm(this);">
			<input type="hidden" name="num" value=" <%= dto.getNum() %> "/>
			<table border="1" width="100%">
				<tr>
					<td>제목</td>
					<td>
						<input type="text" name="title" style="width: 100%;" value=" <%= dto.getTitle() %>" />
					</td>
				</tr>
				<tr>
					<td>내용</td>
					<td>
						<textarea name="content" style="width: 100%; height: 100px;"><%= dto.getContent() %></textarea>
						<!-- <textarea>"textarea는 태그 사이에만 value값을 쓸 수 있는데
							  공백도 반영되니 띄어쓰기, 줄바꿈 자제!"</textarea> -->
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button type="submit">수정완료</button>
						<button type="reset">다시입력</button>
						<button type="button" onclick="location.href='List.jsp';">목록보기</button>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>