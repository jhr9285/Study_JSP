package model2.mvcboard;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.BoardPage;

public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// DAO 생성
		MVCBoardDAO dao = new MVCBoardDAO();
		
		// 뷰에 전달할 매개변수 저장용 맵 생성
		Map<String, Object> map = new HashMap<>();
		
		String searchField = req.getParameter("searchField");
		String searchWord = req.getParameter("searchWord");
		
		if(searchWord != null) {
			map.put("searchField", searchField);
			map.put("searchWord", searchWord);
		}
		
		int totalCount = dao.selectCount(map); // 게시물 갯수
		
		//------------------------- 페이지 처리 START --------------------------//
		ServletContext application = getServletContext();
		int pageSize = Integer.parseInt(application.getInitParameter("POSTS_PER_PAGE"));
		int blockPage = Integer.parseInt(application.getInitParameter("PAGES_PER_BLOCK"));
		
		// 현재 페이지 확인
		int pageNum = 1;
		String pageTemp = req.getParameter("pageNum"); // * getParameter() 반환값 타입 : String
		
		if(pageTemp != null && !pageTemp.equals("")) { // 저장된 pageNum 파라미터의 값이 존재하고 공백이 아니라면
			pageNum = Integer.parseInt(pageTemp); // pageNum을 그 값(pageTemp)으로 수정
		}
		
		// 목록에 출력할 게시물 범위 계산
		int start = (pageNum - 1) * pageSize + 1; // 첫 게시물 번호
		int end = pageNum * pageSize; // 마지막 게시물 번호
		map.put("start", start);
		map.put("end", end);
		//------------------------- 페이지 처리 END --------------------------//
		
		List<MVCBoardDTO> boardLists = dao.selectListPage(map); // 게시물 목록 받기
		dao.close(); // DB 연결 닫기
		
		// 뷰에 전달할 매개변수 추가
		String pagingImg = BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, searchField, searchWord, "../mvcboard/list.do");
		map.put("pagingImg", pagingImg);
		map.put("totalCount", totalCount);
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		
		//  * map에 총 8개의 key가 들어있음
        //  ==> searchField, searchWord, start, end, pagingImg, totalCount, pageSize, pageNum
		
		// 전달할 데이터를 request 영역에 저장 후 List.jsp로 포워드
		req.setAttribute("boardLists", boardLists);
		req.setAttribute("map", map);
		req.getRequestDispatcher("/14MVCBoard/List.jsp").forward(req, resp);
	}
}
