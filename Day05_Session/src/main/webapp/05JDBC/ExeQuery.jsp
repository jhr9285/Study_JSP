<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="common.JDBConnect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<body>
		<h2>회원 목록 조회 테스트(executeQuery() 사용)</h2>
		<%
		// DB에 연결
		JDBConnect jdbc = new JDBConnect();
		
		// 쿼리문 생성
		String sql = "SELECT id, pass, name, regidate FROM member";
		Statement stmt = jdbc.con.createStatement(); // JDBConnect 클래스에 만들어진 con을 바로 불러온 코드
		
		// 쿼리 수행
		ResultSet rs = stmt.executeQuery(sql);
		
		// 결과 확인
		while(rs.next()){
			String id = rs.getString(1); 		// sql 쿼리문의 id (getString() 함수는 인덱스를 1부터 센다.)
			String pw = rs.getString(2); 		// sql 쿼리문의 pass
			String name = rs.getString("name"); // sql 쿼리문의 name
			// sql 패키지의 Date  = sql 쿼리문의 regidate
			java.sql.Date regidate = rs.getDate("regidate");
				
			out.println(String.format("%s %s %s %s", id, pw, name, regidate) + "<br/>");		
		}
		
		// 연결 닫기
		jdbc.close();
		%>
	
		
	</body>
</html>