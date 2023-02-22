package com.mycom.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mycom.dao.TodoListDAO;
import com.mycom.dto.TodoListDTO;

import utils.BoardPage;

@WebServlet("/list.do")
public class TodoListListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public TodoListListController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 컨트롤러에서는 dao의 메소드를 호출해야 된다. ==>  dao 객체를 먼저 생성해야 한다.
		
		// DAO 객체 생성
		TodoListDAO dao = new TodoListDAO();
		// 뷰에 전달할 매개변수 저장용 맵을 생성해야 된다. (dao 메소드의 매개변수가 Map)
		Map<String, Object> map = new HashMap<>();
		// 요청 객체에 검색어가 있는지 확인한다.
		if(request.getParameter("searchWord") != null) { 
			// 있으면 map에 키가 searchWord인 값에 그 검색어를 넣는다.
			map.put("searchWord", request.getParameter("searchWord")); 
		}
		// dao 메소드 selectCount에 매개변수로 map을 넣어서 총 검색 결과 갯수를 구한다.
		int totalCount = dao.selectCount(map);
		// 페이지 넘버는 1로 초기화한다.
		int pageNum = 1; 
		// 페이지 넘버가 null인지 아닌지 확인한다.
		if(request.getParameter("pageNum") != null) { 
			// null이 아니면(+1이 아니면) 그 넘버를 pageNum에 넣는다. 단, 반환값 타입은 String이므로 int로 변환한다.
			pageNum = Integer.parseInt(request.getParameter("pageNum")); 
		}
		// 한 페이지에 띄울 검색 결과 갯수를 10으로 지정한다.
		int pageSize = 10;
		// 화면 하단에 띄울 페이지 넘버(누르면 그 페이지로 이동함) 갯수를 5로 지정한다.
		int blockPage = 5;
		// 검색어를 불러와서 searchWord에 담는다. (검색 결과 화면 페이징 처리에 필요)
		String searchWord = request.getParameter("searchWord");
		// 목록에 출력할 게시물 범위를 계산해서 map에 넣는다.
		int start = (pageNum - 1) * pageSize + 1; // 첫 게시물 번호
		int end = pageNum * pageSize; // 마지막 게시물 번호
		map.put("start", start);
		map.put("end", end);
		
		// 이동할 페이지를 /list.do로 지정한다. 
		String reqUrl = "/Day10_ToDoList/list.do";
		// 페이징 처리 클래스의 BoardPage의 static 메소드 pagingStr을 호출한다.
		// (매개변수는 위에서 모두 값을 구해두었기 때문에 바로 적용된다.)
		String pagingStr = BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, searchWord, reqUrl);
		// 페이징 처리한 글목록을 dao의 selectListPage 메소드를 사용해서 dto에 담는다.
		// 요청 객체에 list를 list 속성의 값으로 넣는다.
		List<TodoListDTO> list = dao.selectListPage(map); // ★★★기억하기
		request.setAttribute("list", list);
		
		// dao를 닫는다. (반드시 selectListPage 다음에 위치해야 된다.)
		dao.close();

		// map에 위 데이터를 넣는다.
		map.put("pagingStr", pagingStr);
		map.put("totalCount", totalCount);
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		
		// 요청 객체에 map을 map 속성의 값으로 넣는다.
		request.setAttribute("map", map);
		
		// /list.jsp로 이동한다. (루트디렉토리가 webapp이라고 생각하고, 그 하위 폴더가 있는 경우 ' 폴더명/파일명 '으로 입력한다.)
		request.getRequestDispatcher("/list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
