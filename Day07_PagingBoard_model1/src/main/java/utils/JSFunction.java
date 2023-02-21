// 1. 04Cookie의 IdSaveMain.jsp, IdSaveProcess.jsp, CookieManager 작성
// 자바스크립트 코드
// 좋은 코드!! 실제로도 활용하기 좋다.
package utils;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;

public class JSFunction {
	// 메시지 알림창을 띄운 후 명시한 URL로 이동하는 메소드 작성
	public static void alertLocation(String msg, String url, JspWriter out) {
		try {
			String script = "" // 삽입할 자바스크립트 코드
							+ "<script>"
							+ "	   alert('" + msg + "');"
							+ "	   location.href='" + url + "';"
							+ "</script>";
			out.println(script); // 자바스크립트 코드를 out 내장 객체로 출력(삽입)
		}
		catch(Exception e) {}
	}
	
	// 메시지 알림창을 띄운 후 이전 페이지로 돌아가는 메소드 작성
	public static void alertBack(String msg, JspWriter out) {
		try {
			String script = ""
							+ "<script>"
							+ "    alert('" + msg + "');"
							+ "    history.back();"  // history.back() : 이전 페이지로 돌아가는 메소드
							+ "</script>";
			out.println(script);
		}
		catch(Exception e) {}
	}
	
	// 메시지 알림창을 띄운 후 명시한 URL로 이동하는 메소드 작성
	public static void alertLocation(HttpServletResponse resp, String msg, String url) {
		try {
			resp.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = resp.getWriter();
			String script = "" 
							+ "<script>"
							+ "	   alert('" + msg + "');"
							+ "	   location.href='" + url + "';"
							+ "</script>";
			writer.print(script);
		}
		catch(Exception e) {}
	}
	
	// 메시지 알림창을 띄운 후 이전 페이지로 돌아가는 메소드 작성
	public static void alertBack(HttpServletResponse resp, String msg) {
		try {
			resp.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = resp.getWriter();
			String script = ""
					+ "<script>"
					+ "    alert('" + msg + "');"
					+ "    history.back();"  // history.back() : 이전 페이지로 돌아가는 메소드
					+ "</script>";
			writer.print(script);
		}
		catch(Exception e) {}
	}
	
}
