package model2.mvcboard;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mvcboard/view.do")
public class ViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 게시물 불러오기
		MVCBoardDAO dao = new MVCBoardDAO();
		String idx = req.getParameter("idx");
		dao.updateVisitCount(idx);
		MVCBoardDTO dto = dao.selectView(idx);
		dao.close();
		
		// 줄바꿈 처리
		dto.setContent(dto.getContent().replaceAll("\r\n", "<br />"));
		
		// 게시물(dto) 저장 후 뷰로 포워드
		req.setAttribute("dto", dto); // dto를 view로 보낼 때 setAttribute 사용 (속성명 "dto"로 지정함 ==> EL에 사용)
		req.getRequestDispatcher("/14MVCBoard/View.jsp").forward(req, resp); // 그러므로 view에서 받을 때 ${ dto.속성명 } 로 받아야 한다.
	}
}
