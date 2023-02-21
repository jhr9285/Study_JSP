<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Cookie</title>
	</head>
	<body> <!-- 쿠키는 자주 쓰는 기술이라 알아두면 좋다!  -->
		<h2>1. 쿠키 설정</h2> <!-- 쿠키 생성 코드 ==> 생성하면 사용자 컴퓨터에 저장됨 -->
		<%
		Cookie cookie = new Cookie("myCookie", "쿠키맛나요");  /*  쿠키 생성 (myCookie : 쿠키 이름, "쿠키맛나요" : 쿠키 값) */
		cookie.setPath(request.getContextPath());			   /* getContextPath() : 경로를 "컨텍스트 루트"로 설정 */
		cookie.setMaxAge(3600);								   /* 유지 기간을 1시간으로 설정 (단위 : 초 ==> 3600초 = 1시간)*/
		response.addCookie(cookie); 						   /* 응답 헤더에 쿠키 추가 */
		%>
		
		<h2>2. 쿠키 설정 직후 쿠키값 확인하기</h2> <!-- F12 개발자도구 - application 메뉴 - Storage 탭에서 확인 가능 -->
		<%
		Cookie[] cookies = request.getCookies(); 	/* 요청 헤더의 모든 쿠키 얻어서 배열에 넣음 */
		if(cookies != null){
			for(Cookie c : cookies) { 			 	/* 배열의 원소를 Cookie 객체에 순차적으로 넣음 */
				String cookieName = c.getName(); 	/* 쿠키 이름 얻기 */
				String cookieValue = c.getValue();  /* 쿠키 값 얻기 */
				/* 화면에 출력 (%s : cookieName 출력위치, %s : cookieValue 출력위치) */
				out.println(String.format("%s : %s<br/>", cookieName, cookieValue));
			}
		}
		%>
		
		<h2>3. 페이지 이동 후 쿠키값 확인하기</h2>
		<a href="CookieResult.jsp">
			다음 페이지에서 쿠키값 확인하기
		</a>
	</body>
</html>