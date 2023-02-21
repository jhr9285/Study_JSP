<!-- 여기에서 쿠키를 만든다 (서버쪽 코드) -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String chkVal = request.getParameter("inactiveToday"); // data에 들어있는 inactiveToday 속성

if(chkVal != null && chkVal.equals("1")) {
	Cookie cookie = new Cookie("PopupClose", "off");   // OFF 기능 하는 쿠키 생성 
	cookie.setPath(request.getContextPath()); 		   // 경로 설정
	cookie.setMaxAge(60*60*24);  					   // 유지 기간 설정 => 체크박스 체크 됐으면 24시간 동안 무언가 함 ==> 하루 지나면 쿠키값 자동소멸됨
	response.addCookie(cookie); 					   // 응답 객체에 추가 
	out.println("쿠키 : 하루 동안 열지 않음");
}
%>
