package com.mycom.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.taglibs.standard.tag.common.fmt.RequestEncodingSupport;

import com.mycom.dto.TodoListDTO;

import common.DBConnPool;

public class TodoListDAO extends DBConnPool { // CRUD 작성

	// 밑작업. DBConnPool 생성자를 호출하여 DB 연결 ==========================================================
	public TodoListDAO() {
		super(); 
	} 
	
	// 검색 결과 갯수 조회 메소드 작성 (READ - SELECT - Search) ===============================================
	public int selectCount(Map<String, Object> map) {
		int totalCount = 0;
		
		String query = "SELECT COUNT(*) FROM todolist ";
		
		if(map.get("searchWord") != null) {
			query += " WHERE content LIKE '%" + map.get("searchWord") + "%' ";
		}
		
		System.out.println("쿼리문 디버깅 : " + query);
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			if(rs.next()) { // rs.next() 결과가 1개일 때는 if문 권장
				totalCount = rs.getInt(1); // rs.getInt(1) = COUNT(*) 컬럼
			}
		} catch (SQLException e) {
			System.out.println("게시물 카운트 중 예외 발생");
			e.printStackTrace();
		}
		return totalCount;
	}
	
	// 목록 조회 메소드 작성 (READ - SELECT - List) ==========================================================
	public List<TodoListDTO> selectListPage(Map<String, Object> map) {
		// 기본 쿼리문
		String query = " SELECT * FROM ("
						+ "        SELECT tb.*, rownum rNum FROM ("
						+ "                SELECT * FROM todolist ";
		
		// 검색어가 있는 경우 WHERE절을 추가한다.
		if(map.get("searchWord") != null) {
			// 검색할 컬럼이 content밖에 없으므로 content는 그대로 두면 되고,
			// 'MVC'부분이 검색 가능하도록 변동 가능한 값으로 입력해야 한다.
			query += " WHERE content LIKE '%" + map.get("searchWord") + "%' ";
		}
		
		// 페이징 처리한다.
		query += " ORDER BY no DESC		) tb	)"
			  + " WHERE rNum BETWEEN ? AND ? ";
		
		// List를 만든다.
		List<TodoListDTO> list = new ArrayList<>(); 
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, map.get("start").toString());
			psmt.setString(2, map.get("end").toString());
			rs = psmt.executeQuery();
			
			while(rs.next()) { // rs.next() : rs를 한 행씩 읽어온다. 값이 있으면 true 반환, 없으면 false 반환
				// 모델(dto)을 만든다.
				TodoListDTO dto = new TodoListDTO();
				
				// 읽어온 rs 값을 dto에 담는다.
				dto.setNo(rs.getInt("no"));
				dto.setContent(rs.getString("content"));
				dto.setState(rs.getString("state"));
				dto.setWdate(rs.getDate("wdate"));
				
				// dto를 List에 담는다.
				list.add(dto);
			}
			
		} catch (SQLException e) {
			System.out.println("목록 조회 중 예외 발생");
			e.printStackTrace();
		}
		// list를 반환한다.
		return list;
	}
	
	// 상세보기 메소드 작성 (READ - SELECT - View) ==========================================================
	public TodoListDTO viewTodoList(int no) {
		// 데이터를 담기 위해 먼저 DTO를 만든다.
		TodoListDTO dto = new TodoListDTO();
		// 쿼리문을 작성, 실행하여 rs에 쿼리문 실행결과를 담는다.
		String query = " SELECT no, content, state, wdate FROM todolist WHERE no = ? ";
		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, no);
			rs = psmt.executeQuery();
			if(rs.next()) {
				// DTO에 데이터를 담는다.
				dto.setNo(rs.getInt("no"));
				dto.setContent(rs.getString("content"));
				dto.setState(rs.getString("state"));
				dto.setWdate(rs.getDate("wdate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}

	// 등록 메소드 작성 (CREATE - INSERT - Write) ==========================================================
	// ★★★파라미터가 dto(모델)여야 된다.(MVC패턴의 규칙)
	public void insertTodoList(TodoListDTO dto) {
		// 기본 쿼리문
		String query = " INSERT INTO todolist VALUES(seq_todolist.NEXTVAL, ?, ?, sysdate)";
		try {
			psmt = con.prepareStatement(query);
			// dto에서 content와 state를 꺼내서 두 번째 매개변수에 입력한다.
			psmt.setString(1, dto.getContent());
			psmt.setString(2, dto.getState());
			// insert라서 ResultSet 없어도 된다.
			psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 수정 메소드 작성 (UPDATE - UPDATE - Update) ==========================================================
	public void updateTodoList(TodoListDTO dto) {
		String query = " UPDATE todolist SET content = ?, state = ? WHERE no = ?";
		// 기본 쿼리문
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getContent());
			psmt.setString(2, dto.getState());
			psmt.setInt(3, dto.getNo());
			psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// 삭제 메소드 작성 (DELETE - DELETE - Delete) ==========================================================
	public void deleTodoList(int no) {
		String query = "DELETE FROM todolist WHERE no = ?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, no);
			psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
