package model2.mvcboard;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import fileupload.FileUtil;
import utils.JSFunction;

/* 서블릿 주소가 annotation으로 쓰이지 않은 경우 ==> web.xml 파일에 입력! */
public class WriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/14MVCBoard/Write.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 파일 업로드 처리 ========================
		// 업로드 디렉터리의 물리적 경로 확인
		// C:\class\jsp\jsp_workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\Day0708_MVCBoard_model2\Uploads
		String saveDirectory = request.getServletContext().getRealPath("/Uploads");
		
		// 초기화 매개변수로 설정한 첨부파일 최대용량(maxPostSize) 확인 ==> web.xml에 설정해야 함
		ServletContext application = getServletContext();
		int maxPostSize = Integer.parseInt(application.getInitParameter("maxPostSize"));
		
		// 파일 업로드 
		MultipartRequest mr = FileUtil.uploadFile(request, saveDirectory, maxPostSize);
		if(mr == null) {
			// 파일 업로드 실패 (실패 사유의 대부분은 용량 초과임)
			JSFunction.alertLocation(response, "첨부파일이 제한 용량을 초과합니다.", "../mvcboard/write.do");
			return;
		}
	
		// 2. 파일 업로드 외 처리 ===========================
		// 폼값을 DTO에 저장
		MVCBoardDTO dto = new MVCBoardDTO();
		dto.setName(mr.getParameter("name"));
		dto.setTitle(mr.getParameter("title"));
		dto.setContent(mr.getParameter("content"));
		dto.setPass(mr.getParameter("pass"));
		
		// 원본 파일명과 저장된 파일명 설정
		String fileName = mr.getFilesystemName("ofile");
		// 첨부파일이 있을 경우 파일명 변경
		if(fileName != null) {
			// 새로운 파일명 생성
			String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
			String ext = fileName.substring(fileName.lastIndexOf("."));
			String newFileName = now + ext;
			
			// 파일명 변경 (File.separator은 File에 내장된 멤버로 '구분자' 출력!)
			File oldFile = new File(saveDirectory + File.separator + fileName);
			File newFile = new File(saveDirectory + File.separator + newFileName);
			oldFile.renameTo(newFile);
			
			dto.setOfile(fileName); // 원본 파일명
			dto.setSfile(newFileName); // 서버에 저장된 파일명
		}
		
		// DAO를 통해 DB에 게시 내용 저장
		MVCBoardDAO dao = new MVCBoardDAO();
		int result = dao.insertWrite(dto);
		dao.close();
		
		// 성공 or 실패?
		if(result == 1) {
			// 성공 시
			response.sendRedirect("../mvcboard/list.do");
		} else {
			// 실패 시
			response.sendRedirect("../mvcboard/write.do");
		}
	}
}
