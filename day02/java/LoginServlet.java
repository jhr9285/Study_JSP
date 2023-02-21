/* 1. 로그인 폼을 만들어서 입력한 값을 로그인 버튼을 누르면 화면에 출력하기
   		로그인 폼 : login.jsp / 화면에 출력 : servlet으로 구현 */
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String inputId = request.getParameter("id");
		String inputPw = request.getParameter("pw");
		
		response.setContentType("text/html; charset = utf-8");
		PrintWriter out = response.getWriter();
		out.append("<html><head><title>LOGIN INFO</title></head><body>").append("<h2>로그인한 회원 정보</h2><hr>" 
					+ "<label>입력한 아이디: </label><span>" + inputId + "</span><br>" 
					+ "<label>입력한 비밀번호: </label><span>" + inputPw + "</span><br>");
	}

}
