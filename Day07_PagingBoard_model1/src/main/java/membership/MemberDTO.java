package membership;
// DTO 클래스는 이름을 지을 때 이름 뒤에 DTO나 VO를 붙이는 편이다. //
public class MemberDTO {
	private String id, pass, name, regidate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegidate() {
		return regidate;
	}

	public void setRegidate(String regidate) {
		this.regidate = regidate;
	}
}
