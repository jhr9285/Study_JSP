<%@ page import="model1.board.BoardDAO"%>
<%@ page import="model1.board.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./IsLoggedIn.jsp" %>
<%
String num = request.getParameter("num");
String title = request.getParameter("title");
String content = request.getParameter("content");

BoardDTO dto = new BoardDTO();
dto.setNum(num);
dto.setTitle(title);
dto.setContent(content);

/* ★★★update 할 때도 form 태그 통해서 전송 받은 데이터를 반드시 dto에 먼저 저장해야 한다! */
BoardDAO dao = new BoardDAO(application);
int affected = dao.updateEdit(dto);
dao.close();

if(affected == 1) {
	/* 성공 시 상세보기 페이지로 이동  (★num= 을 꼭 넣고 그 뒤에 dto에 담긴 글번호를 넣는 것이 중요하다!) */
	response.sendRedirect("View.jsp?num=" + dto.getNum());
} else {
	/* 실패 시 이전 페이지로 이동 */
	JSFunction.alertBack("수정하기에 실패하였습니다.", out);
}
%>