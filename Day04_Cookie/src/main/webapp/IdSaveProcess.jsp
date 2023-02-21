<!-- 1. 04Cookie의 IdSaveMain.jsp, IdSaveProcess.jsp, CookieManager 작성 -->
<!-- 자바 코드만 있음 ==> 자바스크립트 코드를 JSFunction 클래스에서 불러와서 사용함 -->
<!-- ctrl + shift + m = ctrl + shift + O 대체 단축키 (O는 JSP에서 사용 불가) -->
<%@ page import="utils.CookieManager"%>
<%@ page import="utils.JSFunction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String user_id = request.getParameter("user_id");
String user_pw = request.getParameter("user_pw");
String save_check = request.getParameter("save_check");

if("must".equals(user_id) && "1234".equals(user_pw)) {
	// 로그인 성공 시 (아이디를 자동 저장하는 경우)
	if(save_check != null && "1234".equals(user_pw)) {
		CookieManager.makeCookie(response, "loginId", user_id, 60*60*24);
	} else { // 로그인 실패 시 (아이디 자동저장을 해제하는 경우)
		CookieManager.deleteCookie(response, "loginId");
	}
	JSFunction.alertLocation("로그인에 성공했습니다.", "IdSaveMain.jsp", out);
} else {
	// 로그인 실패 시
	JSFunction.alertBack("로그인에 실패했습니다.", out);
}
%>