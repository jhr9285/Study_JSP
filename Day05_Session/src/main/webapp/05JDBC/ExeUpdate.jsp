<%@page import="java.sql.PreparedStatement"%>
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
		<h2>회원 추가 테스트(executeUpdate() 사용)</h2>		
		<%
		// DB에 연결
		JDBConnect jdbc = new JDBConnect();
		
		// 테스트용 입력값 준비
		String id = "test1";
		String pass = "1111";
		String name = "테스트1회원";
		
		// 쿼리문 생성
		String sql = "INSERT INTO member VALUES(?, ?, ?, sysdate)";  // values() 안에 물음표를 입력했음. 자리를 잡아놓는 목적.
		PreparedStatement psmt = jdbc.con.prepareStatement(sql); // ★★★ PreparedStatement 이용해서 인스턴스 생성
		psmt.setString(1, id);   // 하나씩 매칭시킴 (setString()은 인덱스를 1부터 센다.)
		psmt.setString(2, pass); // 하나씩 매칭시킴
		psmt.setString(3, name); // 하나씩 매칭시킴
		// ==> PreparedStatement가 ?에 들어가는 id, pass, name값을 작은따옴표 붙여서 적용해준다.
		//     sql injection 공격을 방어하는 데 도움이 된다.
		// (기존에는 Statement를 사용하면서 작은따옴표를 직접 붙였다가 sql 삽입 공격으로 로그인이 쉽게 뚫림)
		
		// 쿼리 수행
		int inResult = psmt.executeUpdate();
		out.println(inResult + "행이 입력되었습니다.");
		
		// 연결 닫기
		jdbc.close();
		%>
	</body>
</html>