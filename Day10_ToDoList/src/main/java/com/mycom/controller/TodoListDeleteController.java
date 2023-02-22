package com.mycom.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mycom.dao.TodoListDAO;

@WebServlet("/delete.do")
public class TodoListDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public TodoListDeleteController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 번호만으로 삭제 가능하다. 모델 생성 생략. dao 생성.
		TodoListDAO dao = new TodoListDAO();
		// 삭제 메소드 호출
		dao.deleTodoList(Integer.parseInt(request.getParameter("no")));
		// 바로 dao 닫기
		dao.close();
		// list로 이동
		response.sendRedirect("/Day10_ToDoList/list.do");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
