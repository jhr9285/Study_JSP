package com.mycom.dto;

import java.sql.Date;

public class TodoListDTO {
	private int no;
	private String content, state;
	private Date wdate;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Date getWdate() {
		return wdate;
	}
	public void setWdate(Date wdate) {
		this.wdate = wdate;
	}
}
// dto 생성 후, Servers - context.xml과 server.xml에 sql과 연결되는 코드 입력해야 함
// context.xml : <ResourceLink global="dbcp_myoracle" name="dbcp_myoracle" type="javax.sql.DataSource"/>
// server.xml : 
// (아파치 톰캣 서버 연결) <Resource auth="Container" description="User database that can be updated and saved" factory="org.apache.catalina.users.MemoryUserDatabaseFactory" name="UserDatabase" pathname="conf/tomcat-users.xml" type="org.apache.catalina.UserDatabase"/>
// (오라클 연결)		   <Resource auth="Container" driverClassName="oracle.jdbc.OracleDriver" initialSize="0" maxIdle="20" maxTotal="20" maxWaitMillis="5000" minIdle="5" name="dbcp_myoracle" password="1234" type="javax.sql.DataSource" url="jdbc:oracle:thin:@localhost:1521:xe" username="musthave"/>

// 도메인 네임 + 패키지 : 이 프로젝트에 한정하여 사용하는 데이터 모음
// 도메인 네임 없는 패키지 : 여러 프로젝트에 사용하는 데이터 모음