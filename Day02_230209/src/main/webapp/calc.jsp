<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
	<% // JAVA 코드는 %(scriptlet) 기호 안에 입력해야 한다.
		int n1 = Integer.parseInt(request.getParameter("n1"));
		int n2 = Integer.parseInt(request.getParameter("n2"));
		String op = request.getParameter("op");
		
		double result = 0;
		
		switch(op) {
			case "+": 
				result = n1 + n2;
				break;
			case "-": 
				result = n1 - n2;
				break;
			case "*": 
				result = n1 * n2;
				break;
			case "/": 
				result = (double)n1 / n2;
				break;
		}
	%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>						<%-- <!-- --> 주석 기호 : HTML 주석으로, 소스 보기에서는 보인다. --%>
	<body>						<%-- <%-- --> 주석 기호 : JSP 주석으로, 소스 보기에서도 보이지 않는다. --%>
		<h2>계산 결과-JSP</h2>  <%-- 서블릿(CalcServlet.java)에서처럼 append로 모든 코드를 다 적지 않아도 된다. --%>
		<hr>					
		결과: <%= result %> <%-- JSP 코드 (<%= %>: println 기능) --%>
	</body>
</html>