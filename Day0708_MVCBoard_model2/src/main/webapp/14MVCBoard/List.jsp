<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!-- MVC 패턴에서는 % 쓰면 안됨! 태그 써야 됨! ==> JSTL, EL 사용 -->

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>파일 첨부형 게시판</title>
		<style>
			a {
				text-decoration: none;
			}
		</style>
	</head>
	<body>
		<h2 align="center">파일 첨부형 게시판 - 목록 보기(List)</h2>
	
		<!-- 검색 폼 -->
		<form method="get">
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
		
		<!-- 목록 테이블 -->
		<table border="1" width="100%">
			<tr>
				<th width="10%">번호</th>
				<th width="*">제목</th>
				<th width="15%">작성자</th>
				<th width="10%">조회수</th>
				<th width="15%">작성일</th>
				<th width="8%">첨부</th>
			</tr>
			<c:choose>
				<c:when test="${ empty boardLists }"> <!-- 게시물이 없으면 true -->
					<tr>
						<td colspan="6" align="center">등록된 게시물이 없습니다!</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${ boardLists }" var="row" varStatus="loop">
	       			<!-- 반복문도 while문 아니라 태그 사용. boardLists는 ListController에서 실제 그 ArrayList 객체 보낼 때 사용한 속성명 -->
	       			<!-- "row"가 가리키는 것 : MVCModelDTO (List<MVCBoardDTO> boardLists = dao.selectListPage(map);의 MVCModelDTO) -->					
						<tr align="center">
							<td> <!-- 번호 -->
								${ map.totalCount - (((map.pageNum - 1) * map.pageSize) + loop.index) }
							</td>
							<td align="left"> <!-- 제목(링크 ==> 주소는 반드시 서블릿 주소(가상url)여야 한다!) -->
								<a href="../mvcboard/view.do?idx=${ row.idx }">${ row.title }</a> 
	 						</td>
	 						<td>${ row.name }</td> <!-- 작성자 -->
	 						<td>${ row.visitcount }</td> <!-- 조회수 -->
	 						<td>${ row.postdate }</td> <!-- 작성일 -->
	 						<td> <!-- 첨부파일 -->
	 							<c:if test="${ not empty row.ofile }">
	 								<a href="../mvcboard/download.do?ofile=${ row.ofile }&sfile=${ row.sfile }&idx=${ row.idx }">[Down]</a>
	 							</c:if>
	 						</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>		
		</table>
			
		<!-- 하단 메뉴(바로가기, 글쓰기) -->
		<table border="1" width="100%">
			<tr align="center">
				<td>${ map.pagingImg }</td>
				<td width="100">
					<button type="button" onclick="location.href='../mvcboard/write.do';">글쓰기</button>
				</td>
			</tr>
		</table>
	</body>
</html>