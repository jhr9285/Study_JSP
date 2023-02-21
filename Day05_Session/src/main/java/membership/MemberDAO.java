package membership;

import common.JDBConnect;

// DAO : CRUD 담당 클래스. 이 클래스 안에 DB와 연결하는 코드가 작성된다. //
public class MemberDAO extends JDBConnect {
	
     // 명시한 데이터베이스로의 연결이 완료된 MemberDAO 객체를 생성합니다.
	 // 드라이버이름, 드라이버주소, DB아이디, DB비밀번호가 매개변수인 JDBConnect 생성자 호출 //
	public MemberDAO(String driver, String url, String id, String pwd) {
		super(driver, url, id, pwd);  // super : 부모클래스 JDBConnect 생성자 호출 //
	}
	 // 명시한 아이디/패스워드와 일치하는 회원 정보를 반환합니다.
	public MemberDTO getMemberDTO(String uid, String upass) {
		MemberDTO dto = new MemberDTO();
		String query = "SELECT * FROM member WHERE id=? AND pass=?"; // 쿼리문 템플릿 (아이디와 비밀번호가 같은지 알아보는 목적)
		
		try {
			psmt = con.prepareStatement(query); // 동적 쿼리문 준비
			psmt.setString(1, uid); 	// 쿼리문의 첫 번재 인파라미터에 값 설정
			psmt.setString(2, upass);	// 쿼리문의 두 번째 인파라미터에 값 설정
			rs = psmt.executeQuery();	// 쿼리문 실행
			
			if(rs.next()) { // id와 pass가 조건에 맞는 경우는 한 건밖에 없으므로 이런 경우는 if문을 사용한다 (보통 while 사용함) //
				// 쿼리 결과로 얻은 회원 정보를 DTO 객체에 저장
				dto.setId(rs.getString("id")); // 모델(DTO)이 있을 때는 rs로 읽은 select 결과를 이렇게 모델에 넣어줘야 한다 //
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString(3));
				dto.setRegidate(rs.getString(4));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return dto; // DTO 객체 반환
	}
}
