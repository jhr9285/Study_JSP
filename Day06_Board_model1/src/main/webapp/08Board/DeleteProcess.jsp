<%@ page import="model1.board.BoardDAO"%>
<%@ page import="model1.board.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./IsLoggedIn.jsp" %>
<%
String num = request.getParameter("num");

BoardDTO dto = new BoardDTO();
BoardDAO dao = new BoardDAO(application);
dto = dao.selectView(num); /* 주어진 일련번호에 해당하는 기존 게시물 내용 반환 */

// 로그인된 사용자 ID 얻기
String sessionId = session.getAttribute("UserId").toString();
/* Attribute 반환값 타입 : Object ==> 그래서 toString()으로 문자열로 변환한 것 */

int delResult = 0;

if(sessionId.equals(dto.getId())){ // 작성자가 본인인가?
	// 작성자 본인이 맞는 경우
	dto.setNum(num); /* 이미 상단에서 dto = dao.selectView(num); 했기 때문에 여기서 set하지 않아도 됨 */
	delResult = dao.deletePost(dto); // 삭제!
	dao.close();
	
	// 성공/실패 처리
	if(delResult == 1) {
		// 성공 시 목록 페이지로 이동
		JSFunction.alertLocation("삭제되었습니다.", "List.jsp", out);
	} else {
		// 실패 시 이전 페이지로 이동
		JSFunction.alertBack("삭제에 실패하였습니다.", out);
	}
} else {
	// 작성자 본인이 아닌 경우
	JSFunction.alertBack("본인만 삭제할 수 있습니다.", out);
	return;
}
%>