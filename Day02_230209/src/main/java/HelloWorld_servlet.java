// 실습 5-1 : 서블릿으로 HelloWorld 찍기
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloWorld_servlet
 */
// @WebServlet : 자바 애너테이션(JAVA Annotation)
@WebServlet("/intro.githrd") // () 내의 문자열 : 가상 url 주소. 기본적으로 클래스명과 동일하게 설정됨. 변경 가능.
							 // 기본적으로 프로젝트명+가상url주소 로 브라우저 주소가 만들어지는데,
							 // Servers-서버 더블클릭-모듈탭-특정 프로젝트 선택-Edit-/만 남기고 지워서 저장 하면
							 // 브라우저 주소에 /+가상url주소 만 나타난다. (주소가 길어지지 않게 하는 방법)
							 // (Servers 폴더 - 서버 폴더 - server.xml 맨 하단에서 Path 속성값이 함께 바뀌는데,
							 //  여기 파일에서 바꾸고 저장하고 다시 실행해도 주소가 변경됨. 단 서버 중지 상태에서 수정)
public class HelloWorld_servlet extends HttpServlet { // HttpServlet 상속
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloWorld_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html; charset = utf-8");
		PrintWriter out = response.getWriter();
			out.append("<!DOCTYPE html><html><head><title>Hello World Servlet</title></head><body>").append("<h2>Hello World!!</h2></body><html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
	}

}
