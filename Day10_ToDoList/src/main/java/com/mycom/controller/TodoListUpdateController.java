package com.mycom.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mycom.dao.TodoListDAO;
import com.mycom.dto.TodoListDTO;

@WebServlet("/update.do")
public class TodoListUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TodoListUpdateController() {
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
		// update.do 로 보낸다. (update.jsp 에서는 이 dto를 받아서 화면에 출력한다.)
		request.getRequestDispatcher("/update.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 화면에서 입력한 값을 모델에 넣어야 된다. ==> 모델 객체를 먼저 만들어야 된다.
		TodoListDTO dto = new TodoListDTO();
		// 화면에서 입력한 값 불러오기 => request.getParameter("name 값");
		// wdate는 수정할 일 없어서 수정X, no는 primary key라, 데이터 찾는 지표라서 수정 필요하면 작업.
		dto.setNo(Integer.parseInt(request.getParameter("no")));
		dto.setContent(request.getParameter("content"));
		dto.setState(request.getParameter("state"));
		// DAO를 생성한다.
		TodoListDAO dao = new TodoListDAO();
		// dao의 update 메소드를 호출한다.
		dao.updateTodoList(dto);
		// dao를 닫는다. (반드시 updateToDoList 다음에 위치해야 된다.)
		dao.close();
		// 목록으로 이동한다. (write.do -> view.do 로 주소가 바뀌어야 되는 상황이므로 sendRedirect 사용)
		response.sendRedirect("/Day10_ToDoList/view.do?no=" + request.getParameter("no"));
	}

}
