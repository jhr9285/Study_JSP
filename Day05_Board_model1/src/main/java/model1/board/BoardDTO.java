package model1.board;

// DTO에 있는 필드와 DB의 테이블 컬럼명을 맞추는 게 원칙이지만, 
// DB의 테이블에 없는 컬럼명인데 DTO에 필드로 만들어서 사용해야 하는 경우라면 해도 된다. 
public class BoardDTO {
	private String num, title, content, id, visitcount, name;
	private java.sql.Date postdate;  // Date를 명확하게 하기 위해 패키지명까지 입력한 케이스
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVisitcount() {
		return visitcount;
	}
	public void setVisitcount(String visitcount) {
		this.visitcount = visitcount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public java.sql.Date getPostdate() {
		return postdate;
	}
	public void setPostdate(java.sql.Date postdate) {
		this.postdate = postdate;
	}
	
	
}
