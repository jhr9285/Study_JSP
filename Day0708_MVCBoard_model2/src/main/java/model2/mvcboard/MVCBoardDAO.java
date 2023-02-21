package model2.mvcboard;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.DBConnPool;

// DAO - CRUD
public class MVCBoardDAO extends DBConnPool {
	public MVCBoardDAO() {
		super();
	}
	// -------------------------------- Read (Select) -----------------------------------
	// Read (목록) - 검색 조건에 맞는 게시물의 갯수 반환하는 메소드 작성
	public int selectCount(Map<String, Object> map) {
		int totalCount = 0;
		
		String query = "SELECT COUNT(*) FROM mvcboard ";
		
		if(map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField") + " LIKE '%" + map.get("searchWord") + "%' ";
		}
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			totalCount = rs.getInt(1);
		} catch (SQLException e) {
			System.out.println("게시물 카운트 중 예외 발생");
			e.printStackTrace();
		}
		return totalCount;
	}
	
	// Read (목록) - 검색 조건에 맞는 게시물 목록 반환하는 메소드 작성 (페이징 기능 지원)
	public List<MVCBoardDTO> selectListPage(Map<String, Object> map) {
		List<MVCBoardDTO> board = new ArrayList<>();
		
		String query = " SELECT * FROM ("
				     + " 	SELECT Tb.*, ROWNUM rNum FROM ("
				     + " 		SELECT * FROM mvcboard ";
		
		if(map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField") + " LIKE '%" + map.get("searchWord") + "%' ";
		}
		
		query += "		ORDER BY idx DESC "
			   + "		) Tb "
			   + " ) "
			   + " WHERE rNum BETWEEN ? AND ? ";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, map.get("start").toString());
			psmt.setString(2, map.get("end").toString());
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				MVCBoardDTO dto = new MVCBoardDTO();
				
				dto.setIdx(rs.getString("idx"));
				dto.setName(rs.getString("name"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setOfile(rs.getString("ofile"));
				dto.setSfile(rs.getString("sfile"));
				dto.setDowncount(rs.getInt("downcount"));
				dto.setPass(rs.getString("pass"));
				dto.setVisitcount(rs.getInt("visitcount"));
				
				board.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
		return board;
	}
	
	// Read (상세보기) - 주어진 일련번호에 해당하는 게시물을 DTO에 담아 반환하는 메소드 작성
	public MVCBoardDTO selectView(String idx) {
		MVCBoardDTO dto = new MVCBoardDTO();
		String query = "SELECT * FROM mvcboard WHERE idx = ? ";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto.setIdx(rs.getString("idx"));
				dto.setName(rs.getString("name"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setOfile(rs.getString("ofile"));
				dto.setSfile(rs.getString("sfile"));
				dto.setDowncount(rs.getInt("downcount"));
				dto.setPass(rs.getString("pass"));
				dto.setVisitcount(rs.getInt("visitcount"));
			}
		} catch (Exception e) {
			System.out.println("게시물 상세보기 중 예외 발생");
			e.printStackTrace();
		}
		return dto;
	}
	// -------------------------------- Create (Insert) -----------------------------------
	// Insert (글쓰기) - 게시글 데이터를 받아 DB에 추가하는 메소드 작성
	public int insertWrite(MVCBoardDTO dto) {
		int result = 0;
		
		try {
			String query = "INSERT INTO mvcboard(idx, name, title, content, ofile, sfile, pass)"
					+ " VALUES(seq_board_num.NEXTVAL, ?, ?, ?, ?, ?, ?)";
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getOfile());
			psmt.setString(5, dto.getSfile());
			psmt.setString(6, dto.getPass());
			result = psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("게시물 입력 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
	// -------------------------------- Update (Update) -----------------------------------
	// Update (상세보기,조회수) - 주어진 일련번호에 해당하는 게시물의 조회수를 1 증가시키는 메소드 작성
	public void updateVisitCount(String idx) {
		String query = "UPDATE mvcboard SET visitcount = visitcount + 1 WHERE idx = ? ";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			psmt.executeQuery();
		} catch (Exception e) {
			System.out.println("게시물 조회수 증가 중 예외 발생");
			e.printStackTrace();
		}
	}
	
	// Update (다운로드수) - 다운로드 횟수를 1 증가시키는 메소드 작성
	public void downCountPlus(String idx) {
        // throws Exception 입력하면 try~catch 블럭 입력 안해도 됨
        // catch 블럭에서 할 작업이 없을 때 사용
		// but 여기서는 사용하면 DownloadController.java에서 오류 발생함.. 
		String sql = "UPDATE mvcboard SET downcount = downcount + 1 WHERE idx = ? ";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, idx);
			psmt.executeUpdate();
		} catch(Exception e) {}
	}
	
	// Update (DB 데이터 수정) - 게시글 데이터를 받아 DB에 저장되어 있던 내용을 갱신하는 메소드 작성
	public int updatePost(MVCBoardDTO dto) {
		int result = 0;
		
		try {
			// 쿼리문 템플릿 준비
			String query = "UPDATE mvcboard SET title = ?, name = ?, content = ?, ofile = ?, sfile = ? "
						 + "WHERE idx = ? AND pass =? ";
			
			// 쿼리문 준비
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getName());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getOfile());
			psmt.setString(5, dto.getSfile());
			psmt.setString(6, dto.getIdx());
			psmt.setString(7, dto.getPass());
			
			// 쿼리문 실행
			result = psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("게시물 수정 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
	// -------------------------------- Delete (Delete) -----------------------------------
	// Delete (게시물 삭제) - 지정한 일련번호의 게시물을 삭제하는 메소드 작성
	public int deletePost(String idx) {
		int result = 0;
		
		try {
			String query = "DELETE FROM mvcboard WHERE idx = ?";
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			result = psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("게시물 삭제 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
	
	// ------------------------------- 기타 -----------------------------------
	// 입력한 비밀번호가 지정한 일련번호의 게시물의 비밀번호와 일치하는지 확인하는 메소드
	public boolean confirmPassword(String pass, String idx) {
		boolean isCorr = true;
		
		try {
			String sql = "SELECT COUNT(*) FROM mvcboard WHERE pass = ? AND idx = ? ";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, pass);
			psmt.setString(2, idx);
			rs = psmt.executeQuery();
			rs.next();
			if(rs.getInt(1) == 0) {
				isCorr = false;
			}
		} catch (Exception e) {
			isCorr = false;
			e.printStackTrace();
		}
		return isCorr;
	}
}
