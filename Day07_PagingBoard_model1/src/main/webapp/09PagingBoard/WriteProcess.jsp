<%@ page import="model1.board.BoardDAO"%>
<%@ page import="model1.board.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./IsLoggedIn.jsp" %>
<%
/* ★ form으로 전송받은 데이터를 파라미터 기준으로 가져올 때 request.getParameter 사용! */
String title = request.getParameter("title");
String content = request.getParameter("content");

/* insert 할 때 '★★★form에 입력한 값을 dto에 넣은 후'에 insert 해야 한다! */
BoardDTO dto = new BoardDTO();
dto.setTitle(title);
dto.setContent(content);
dto.setId(session.getAttribute("UserId").toString());

/* DAO 객체를 통해 DB에 DTO 저장 */
BoardDAO dao = new BoardDAO(application);

int iResult = dao.insertWrite(dto);
dao.close();

if(iResult == 1) {
	response.sendRedirect("List.jsp");
} else {
	JSFunction.alertBack("글쓰기에 실패하였습니다.", out);
}
%>