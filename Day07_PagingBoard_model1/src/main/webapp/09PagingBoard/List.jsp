<%@ page import="utils.BoardPage"%>
<%@ page import="model1.board.BoardDTO"%>
<%@ page import="model1.board.BoardDAO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- DAO를 생성해 DB에 연결 -->
<% 
BoardDAO dao = new BoardDAO(application);

/* 사용자가 입력한 검색 조건을 Map에 저장 */    
Map<String, Object> param = new HashMap<String, Object>(); /* 업캐스팅 */
String searchField = request.getParameter("searchField");
String searchWord = request.getParameter("searchWord");

if(searchWord != null) { /* 검색어가 null이 아니면 (= 검색했을 때) */
	param.put("searchField", searchField);
	param.put("searchWord", searchWord);
}

int totalCount = dao.selectCount(param); // 게시물 수 확인 

/* 페이징 기능 구현을 위해 추가된 코드 */

// 전체 페이지 수 계산
/* POSTS_PER_PAGE[BLOCK] 속성과 속성값이 WEB_INF 폴더의 web.xml에 입력되어 있어야 한다.
     근데 그냥 이 파일에서 int pageSize = 10; 이라고 해도 된다.  */
int pageSize = Integer.parseInt(application.getInitParameter("POSTS_PER_PAGE"));
int blockPage = Integer.parseInt(application.getInitParameter("PAGES_PER_BLOCK"));
int totalPage = (int)Math.ceil((double) totalCount / pageSize); // 전체 페이지 수 
/* 전체 페이지 수는 ceil로 올림 계산하는 것이 중요 */
 
// 현재 페이지 확인
int pageNum = 1; // 기본값
String pageTemp = request.getParameter("pageNum");
if(pageTemp != null && !pageTemp.equals("")){
	pageNum = Integer.parseInt(pageTemp); // 요청받은 페이지로 수정
}

// 목록에 출력할 게시물 범위 계산
int start = (pageNum - 1) * pageSize + 1; // 첫 게시물 번호 (ex, 3페이지 : 21번 ~ 30번)
int end = pageNum * pageSize; // 마지막 게시물 번호
param.put("start", start);
param.put("end", end);

/* 페이징 기능 구현을 위해 추가된 코드 */

List<BoardDTO> boardLists = dao.selectListPage(param); // 게시물 목록 받기
dao.close(); // DB 연결 닫기
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원제 게시판</title>
		<link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR&display=swap" rel="stylesheet">
		<link rel="stylesheet" href="../Css/Style.css">
	</head>
	<body>
		<jsp:include page="../Common/Link.jsp"></jsp:include>
		
		<div>
			<h2 align="center">목록 보기(List) - 현재 페이지 : <%= pageNum %> (전체 : <%= totalPage %>)</h2>
			<!-- 검색폼 -->
			<form method="get"> <!-- form 태그에 action 주소가 없으면 데이터를 현재 주소(List.jsp)로 전송함 -->
				<table border="1" width="100%">
					<tr>
						<td align="center">
							<select name="searchField">
								<option value="title">제목</option>
								<option value="content">내용</option>
							</select>
							<input type="text" name="searchWord" />
							<input type="submit" value="검색하기" />
						</td>
					</tr>
				</table>
			</form>
			
			<!-- 게시물 목록 테이블(표) -->
			<table border="1"  width="100%">
				
				<!-- 테이블의 컬럼명 -->
				<tr>
					<th width="10%">번호</th>
					<th width="50%">제목</th>
					<th width="15%">작성자</th>
					<th width="10%">조회수</th>
					<th width="15%">작성일</th>
				</tr>
				
				<!-- 목록의 내용 -->
				<%
					if(boardLists.isEmpty()) {
						// 게시물이 하나도 없을 때
				%>
						<tr>
							<td colspan="5" align="center">등록된 게시물이 없습니다*^^*</td>
						</tr>
				<%
					} else {
						// 게시물이 있을 때
						int virtualNum = 0; // 화면상에서의 게시물 번호 (실제 글번호X)
						int countNum = 0;
						for(BoardDTO dto : boardLists) {
							virtualNum = totalCount- (((pageNum - 1) * pageSize) + countNum++);
				%>
							<tr align="center">
								<td><%= virtualNum %></td> <!-- 게시물 번호 -->
								<td align="left">
									<a href="View.jsp?num=<%= dto.getNum()  %>"><%= dto.getTitle() %></a> <!-- ★★★ 'num' : url parameter -->
								</td>
								<td align="center"><%= dto.getId() %></td>
								<td align="center"><%= dto.getVisitcount() %></td>
								<td align="center"><%= dto.getPostdate() %></td>
							</tr>
				<%
						}
					}
				%>
			</table>
			
			<!-- 목록 하단의 글쓰기 버튼 -->
			<table border="1" width="100%">
				<tr align="center">
				<!-- 페이징 처리 -->
					<td>
						<%= BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, request.getRequestURI()) %>
					</td>
				<!-- 글쓰기 버튼 -->
					<td><button type="button" onclick="location.href='Write.jsp';">글쓰기</button></td>
				</tr>			
			</table>
		</div>
	</body>
</html>