<!-- 네비게이션 역할 하는 코드 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<table border="1" width="90%" style="margin: 15px auto 7px; border: 0px;">
	<tr>
		<td align="center" style="margin: 15px auto 7px; border: 0px;">
			<!-- 로그인 여부에 따른 메뉴 변화 -->
			<%
				if(session.getAttribute("UserId") == null) {
			%>
				<a href="../06Session/LoginForm.jsp">로그인</a>
			<%
				} else {
			%>
				<a href="../06Session/Logout.jsp">로그아웃</a>
			<% } %>
			
			<!-- 8장, 9장 회원제 게시판 프로젝트에서 사용할 링크 -->
 			&nbsp;&nbsp;&nbsp; 
<!--             <a href="../08Board/List.jsp">게시판(페이징X)</a>
 -->            &nbsp;&nbsp;&nbsp; 
            <a href="../09PagingBoard/List.jsp">게시판(페이징O)</a>
		</td>
	</tr>
</table>