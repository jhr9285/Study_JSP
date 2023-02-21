/* 1. 04Cookie의 IdSaveMain.jsp, IdSaveProcess.jsp, CookieManager 작성 */
package utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieManager {
	public static void makeCookie(HttpServletResponse response, String cName, String cValue, int cTime) {
		Cookie cookie = new Cookie(cName, cValue); // 쿠키 생성
		cookie.setPath("/Day06_Board_model1"); 	 	// 경로 설정
		cookie.setMaxAge(cTime); 	// 유지 시간 설정
		response.addCookie(cookie); // 응답 객체에 추가
	}
	
	// 명시한 이름의 쿠키 값 반환하는 메소드 작성
	public static String readCookie(HttpServletRequest request, String cName) {
		String cookieValue = "";
		
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for (Cookie c : cookies) {
				String cookieName = c.getName();
				if (cookieName.equals(cName)) {
					cookieValue = c.getValue(); // 반환값 갱신
				}
			}
		}
		return cookieValue;  // 반환값
	}
	
	// 명시한 이름의 쿠키 삭제하는 메소드 작성
	public static void deleteCookie(HttpServletResponse response, String cName) {
		makeCookie(response, cName, "", 0);
	}
}
