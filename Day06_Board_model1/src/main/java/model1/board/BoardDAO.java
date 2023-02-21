package model1.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import common.JDBConnect;

public class BoardDAO extends JDBConnect {

	public BoardDAO(ServletContext application) {
		super(application);
	}
	
	// 검색 조건에 맞는 게시물의 갯수 반환하는 메소드 작성
	public int selectCount(Map<String, Object> map) { // COUNT : 페이징 처리 할 때 필요 //
		int totalCount = 0;
		
		// 게시물 갯수 얻어오는 쿼리문 작성
		String query = "SELECT COUNT(*) FROM board"; // (=) String query = "SELECT COUNT(*) as countboard FROM board"; ==> countboard : 별칭 //
		if(map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField") + " LIKE '%" + map.get("searchWord") + "%'";
		}
		
		try {
			stmt = con.createStatement(); // 쿼리문 객체 생성
			rs = stmt.executeQuery(query); // 쿼리 실행
			
			if(rs.next()) { // rs.next()가 있으면
				totalCount = rs.getInt(1); // 첫 번째 컬럼 값을 가져옴 // totalCount = rs.getInt("countboard"); ==> 별칭 입력해서 가져올 수도 있음 //
			}
		}
		catch(Exception e) {
			System.out.println("게시물 갯수 구하는 중 예외 발생");
			e.printStackTrace();
		}
		return totalCount;
	}
	
	// 검색 조건에 맞는 게시물 목록 반환하는 메소드 작성
	public List<BoardDTO> selectList(Map<String, Object> map) {
		List<BoardDTO> bbs = new ArrayList<BoardDTO>();
		
		String query = "SELECT * FROM board ";
		if(map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField") + " LIKE '%" + map.get("searchWord") + "%' ";
		}
		query += " ORDER BY num DESC ";
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				
				dto.setNum(rs.getString("num"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setId(rs.getString("id"));
				dto.setVisitcount(rs.getString("visitcount"));
				
				bbs.add(dto);
			}
		}
		catch(Exception e) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
		return bbs;
	}
	
	// 검색 조건에 맞는 게시물 목록 반환하는 메소드 작성(페이징 기능 지원)
	public List<BoardDTO> selectListPage(Map<String, Object> map) {
		List<BoardDTO> bbs = new ArrayList<BoardDTO>();
		
		// 쿼리문 템플릿
		String query = "SELECT * FROM ("
						+ "		SELECT Tb.*, ROWNUM rNUM FROM ("
						+ " 	SELECT * FROM board ";
		
		// 검색 조건 추가
		if(map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField") + " LIKE '%" + map.get("searchWord") + "%' ";
		}
		query += " ORDER BY num DESC 	"
				+ ") Tb 	"
				+ ") WHERE rNum BETWEEN ? AND ? ";
		
		try {
			// 쿼리문 완성
			psmt = con.prepareStatement(query);
			psmt.setString(1, map.get("start").toString());
			psmt.setString(2, map.get("end").toString());
			
			// 쿼리문 실행
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				// 게시물 하나의 데이터를 DTO에 저장
				BoardDTO dto = new BoardDTO();
				dto.setNum(rs.getString("num"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setId(rs.getString("id"));
				dto.setVisitcount(rs.getString("visitcount"));
				
				bbs.add(dto);
			}
		} catch(Exception e) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
		return bbs;
	}
	
	// 게시글 데이터를 받아 DB에 추가하는 메소드 작성
	public int insertWrite(BoardDTO dto) {
		int result = 0;
		
		try {
			// INSERT문 작성
			String query = "INSERT INTO board(num, title, content, id, visitcount) "
						 + "VALUES( seq_board_num.NEXTVAL, ?, ?, ?, 0 )";
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle()); // ? 자리에 동적으로 데이터 입력
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getId());
			
			result = psmt.executeUpdate(); // 반환값 : int (쿼리문 실행으로 영향 받은 row의 갯수)
			
		} catch(Exception e) {
			System.out.println("게시물 입력 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
	
	// 지정한 게시물 찾아 내용 반환하는 메소드 작성
	public BoardDTO selectView(String num) {
		BoardDTO dto = new BoardDTO();
		
		String query = "SELECT B.*, M.name FROM member M "
					 + "INNER JOIN board B ON M.id = B.id WHERE num = ?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, num);		// 인파라미터를 일련번호로 설정
			rs = psmt.executeQuery(); 	// 쿼리 실행
			
			if(rs.next()) {
			// SELECT 질의명령 결과를 모델(dto)에 담는다.
			dto.setNum(rs.getString("num"));
			dto.setTitle(rs.getString("title"));
			dto.setContent(rs.getString("content"));
			dto.setPostdate(rs.getDate("postdate"));
			dto.setId(rs.getString("id"));
			dto.setVisitcount(rs.getString("visitcount"));
			dto.setName(rs.getString("name"));
			}
			
		} catch(Exception e) {
			System.out.println("게시물 상세보기 중 예외 발생");
			e.printStackTrace();
		}
		return dto;
	}
	
	// 지정한 게시물의 조회수를 1 증가시키는 메소드
	public void updateVisitCount(String num) {
		String query = "UPDATE board SET visitcount = visitcount + 1 WHERE num = ?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, num);	// 인파라미터를 일련번호로 설정
			psmt.executeQuery();	// 쿼리 실행
			
		} catch(Exception e) {
			System.out.println("게시물 조회수 증가 중 예외 발생");
			e.printStackTrace();
		}
	}
	
	// 지정한 게시물 수정하는 메소드
	public int updateEdit(BoardDTO dto) {
		int result = 0;
		
		try {
			String query = "UPDATE board SET title = ?, content = ? WHERE num = ?";
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getNum());
			
			result = psmt.executeUpdate();
			
		} catch(Exception e) {
			System.out.println("게시물 수정 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
	
	// 지정한 게시물 삭제하는 메소드
	public int deletePost(BoardDTO dto) {
		int result = 0;
		
		try {
			String query = "DELETE FROM board WHERE num = ?";
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getNum());
			
			result = psmt.executeUpdate();
			
		} catch(Exception e) {
			System.out.println("게시물 삭제 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}	
	
}
