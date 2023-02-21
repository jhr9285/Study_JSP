// 1. MVC패턴 연습하기
//    https://www.w3schools.com/howto/tryit.asp?filename=tryhow_css_responsive_form
// 	  폼에 입력한 데이터를 Model에 넣은 다음 화면에 값을 출력하기 
package day03Quiz;
public class Model {
	private String firstname, lastname, country, subject;

	public Model(String firstname, String lastname, String country, String subject) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.country = country;
		this.subject = subject;
	}
	public void modelSubmit() {}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
}
