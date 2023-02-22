package com.mycom.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mycom.dao.TodoListDAO;
import com.mycom.dto.TodoListDTO;

@WebServlet("/view.do")
public class TodoListViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public TodoListViewController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// DAO를 만든다.
		TodoListDAO dao = new TodoListDAO();
		// DTO를 만들고 dao의 viewTodoList 메소드를 호출한다. request.getParameter 반환값은 String 타입이므로 정수로 변환한다.
		// viewTodoList의 반환값인 dto를 새로 만든 dto에 넣는다.
		TodoListDTO dto = dao.viewTodoList(Integer.parseInt(request.getParameter("no")));
		// dto라는 속성의 속성값으로 dto를 넣는다.
		request.setAttribute("dto", dto);
		// view.do 로 보낸다. (view.jsp 에서는 이 dto를 받아서 화면에 출력한다.)
		request.getRequestDispatcher("/view.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
