package com.mycom.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mycom.dao.TodoListDAO;
import com.mycom.dto.TodoListDTO;

@WebServlet("/write.do")
public class TodoListWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public TodoListWriteController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// write.jsp로 이동하는 코드를 작성한다. (반드시 컨트롤러를 통해서 뷰로 넘어가도록 해야 한다.)
		request.getRequestDispatcher("/write.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 화면에서 입력한 값을 모델에 넣어야 된다. ==> 모델 객체를 먼저 만들어야 된다.
		TodoListDTO dto = new TodoListDTO();
		// 화면에서 입력한 값 불러오기 => request.getParameter("name 값");
		// no, wdate는 쿼리문에서 자동으로 생성해주기 때문에 여기서 setter 메소드로 입력하지 않는다.
		dto.setContent(request.getParameter("content"));
		dto.setState(request.getParameter("state"));
		// DAO를 생성한다.
		TodoListDAO dao = new TodoListDAO();
		// dao의 insert 메소드를 호출한다.
		dao.insertTodoList(dto);
		// dao를 닫는다. (반드시 insertToDoList 다음에 위치해야 된다.)
		dao.close();
		// 목록으로 이동한다. (write.do -> list.do 로 주소가 바뀌어야 되는 상황이므로 sendRedirect 사용)
		response.sendRedirect("/Day10_ToDoList/list.do");
	}

}
