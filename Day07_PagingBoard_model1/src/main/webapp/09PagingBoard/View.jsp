<%@ page import="utils.CookieManager"%>
<%@ page import="model1.board.BoardDTO"%>
<%@ page import="model1.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String num = request.getParameter("num"); // 일련번호 받기 ★★★ getParameter("num") 중요!!

BoardDAO dao = new BoardDAO(application); // DAO 생성
BoardDTO dto = dao.selectView(num); // 게시물 가져오기

String isCookie = "N"; // 쿠키 유무 판단 변수 "N"으로 초기화
Cookie[] cookies = request.getCookies(); // 저장된 쿠키 정보를 모두 읽어서 cookies에 담는다.
if(cookies != null) { // 쿠키가 비어 있지 않다면
	for(Cookie c : cookies) { // 모든 쿠키를 살펴보고
		if(c.getName().equals(num)) { // num이라는 이름의 쿠키가 있다면
			isCookie = "Y"; // isCookie 변수 값을 Y로 바꾼다.
		}
	}
	if(isCookie == "N"){ // isCookie 변수 값이 N이면
		dao.updateVisitCount(num); // 조회수를 증가시킨다.
		
		Cookie viewCookie = new Cookie(num, num); // 쿠키이름과 쿠키값이 num인 새 쿠키(변수명 viewCookie)를 만든다.
		viewCookie.setMaxAge(60*60*24); // 쿠키의 유지 시간을 24시간으로 지정한다.
		response.addCookie(viewCookie); // 쿠키값을 추가해준다.
	}
}

dao.close(); // DB 연결 해제
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원제 게시판</title>
		<link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR&display=swap" rel="stylesheet">
		<link rel="stylesheet" href="../Css/Style.css">
		<script> // JavaScript 코드
			function deletePost() {
				var confirmed = confirm("정말로 삭제하시겠습니까?");
				if(confirmed) {
					var form = document.writeFrm;
					form.method = "post";
					form.action = "DeleteProcess.jsp";
					form.submit(); // 해당 코드가 type="button"이라 submit을 따로 해줘야 한다.
				}
			}
		</script>
	</head>
	<body>
		<jsp:include page="../Common/Link.jsp" />
		<div>
			<h2 align="center">회원제 게시판 - 내용 보기(View)</h2>
			<form name="writeFrm"> <!-- method 값 생략하면 기본값 get으로 적용 -->
				<input type="hidden" name="num" value="<%= num %>">  <!-- 글번호. 페이지 소스 보기 화면에서 value="글번호" 확인 가능 -->
				<table border="1" width="100%">
					<tr>
						<td>번호</td>
						<td><%= dto.getNum() %></td>
						<td>작성자</td>
						<td><%= dto.getName() %></td>
					</tr>
					<tr>
						<td>작성일</td>
						<td><%= dto.getPostdate() %></td>
						<td>조회수</Td>
						<td><%= dto.getVisitcount() %></td>
					</tr>
					<tr>
						<td>제목</td>
						<td colspan="3"><%= dto.getTitle() %></td>
					</tr>
					<tr>
						<td>내용</td>
						<td colspan="3" height="100">
							<%= dto.getContent().replace("\r\n", "<br/>") %> 
							<!-- ★ replace("\r\n", "<br/>") 중요! (줄바꿈을 위해 엔터키 문자를 브레이크 태그로 바꿔줌) -->
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<%
							if(session.getAttribute("UserId") != null
							   && session.getAttribute("UserId").toString().equals(dto.getId())) { // 로그인한 사람 아이디와 글 작성자 아이디가 같으면
							%>
							<button type="button" onclick="location.href='Edit.jsp?num=<%= dto.getNum() %>';">수정하기</button>
							<button type="button" onclick="deletePost();">삭제하기</button>			
							<%
							}
							%>
							<button type="button" onclick="location.href='List.jsp';">목록보기</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>