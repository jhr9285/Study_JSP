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
		
		String query = "SELECT * FROM board";
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
}
