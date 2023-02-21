package model2.mvcboard;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import fileupload.FileUtil;
import utils.JSFunction;

@WebServlet("/mvcboard/edit.do")
public class EditController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idx = request.getParameter("idx");
		MVCBoardDAO dao = new MVCBoardDAO();
		MVCBoardDTO dto = dao.selectView(idx);
		request.setAttribute("dto", dto);
		request.getRequestDispatcher("/14MVCBoard/Edit.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 파일 업로드 처리 =============================
		// 업로드 디렉터리의 물리적 경로 확인
		String saveDirectory = request.getServletContext().getRealPath("/Uploads");
		
		// 초기화 매개변수로 설정한 첨부파일 최대용량 확인
		ServletContext application = this.getServletContext();
		int maxPostSize = Integer.parseInt(application.getInitParameter("maxPostSize"));
		
		// 파일 업로드
		MultipartRequest mr = FileUtil.uploadFile(request, saveDirectory, maxPostSize);
		
		if(mr == null) {
			// 파일 업로드 실패 시
			JSFunction.alertBack(response, "첨부파일이 제한 용량을 초과합니다.");
			return;
		}
		
		// 2. 파일 업로드 외 처리 ===========================
		// 수정 내용을 매개변수에서 얻어옴
		String idx = mr.getParameter("idx");
		String prevOfile = mr.getParameter("prevOfile");
		String prevSfile = mr.getParameter("precSfile");
		
		String name = mr.getParameter("name");
		String title = mr.getParameter("title");
		String content = mr.getParameter("content");
		
		// 비밀번호는 session에서 가져옴
		HttpSession session = request.getSession();
		String pass = (String)session.getAttribute("pass");
		
		// DTO에 저장
		MVCBoardDTO dto = new MVCBoardDTO();
		dto.setIdx(idx);
		dto.setName(name);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setPass(pass);
		
		// 원본 파일명과 저장된 파일명 설정
		String fileName = mr.getFilesystemName("ofile"); // 새로 추가한 파일 읽어오기
		if(fileName != null) {
			// 새로 추가한 첨부파일이 있을 경우 파일명 변경
			// 새로운 파일명 생성
			String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
			String ext = fileName.substring(fileName.lastIndexOf("."));
			String newFileName = now + ext;
			
			// 파일명 변경
			File oldFile = new File(saveDirectory + File.separator + fileName);
			File newFile = new File(saveDirectory + File.separator + newFileName);
			oldFile.renameTo(newFile);
			
			// DTO에 저장
			dto.setOfile(fileName);
			dto.setSfile(newFileName);
			
			// 기존 파일 삭제
			FileUtil.deleteFile(request, "/Uploads", prevSfile);
		} else {
			// 새로 추가한 첨부파일이 없는 경우 기존 이름 유지
			dto.setOfile(prevOfile);
			dto.setSfile(prevSfile);
		}
		
		// DB에 수정 내용 반영
		MVCBoardDAO dao = new MVCBoardDAO();
		int result = dao.updatePost(dto);
		dao.close();
		
		// 성공 or 실패?
		if(result == 1) {
			// 수정 성공 시
			session.removeAttribute("pass");
			response.sendRedirect("../mvcboard/view.do?idx=" + idx);
		} else {
			// 수정 실패 시
			JSFunction.alertLocation(response, "비밀번호 검증을 다시 진행해주세요.", "../mvcboard/view.do?idx=" + idx);
		}
	}

}
