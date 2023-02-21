<!-- 예제 7-5 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>JSTL 종합 예제</title>
	</head>
	<body>
		<h2>JSTL 종합 예제</h2><hr>
		<h2>c:set, c:out</h2>
		<c:set var="product1" value="<span>애플 아이폰</span>" />
		<c:set var="product2" value="삼성 갤럭시 노트" />
		<c:set var="intArray" value="${[1, 2, 3, 4, 5]}" />  <!-- 오류 발생하는데 실행되면 무시해도 됨 -->
		<p>
			product1(jstl);
			<c:out value="${product1}" default="Not registered" escapeXml="true" />
		</p>
		<p>product1(el) : ${product1}</p>
		<p>intArray[2]의 데이터 : ${intArray[2]}</p><hr>
		
		<h3>c:forEach - 배열 출력</h3>
		<ul>
			<c:forEach var="num" varStatus="i" items="${intArray}">
				<li>인덱스 [${i.index}]의 데이터 : ${num}</li>
			</c:forEach>
		</ul><hr>
		
		<h3>c:if</h3>
		<c:set var="checkout" value="true" />
		<c:if test="${checkout}">
			<p>주문 제품: ${product2} - if</p>
		</c:if>
		<c:if test="${!checkout}">
			<p>주문 제품이 아닙니다. - if</p>
		</c:if>
		<c:if test="${!empty product2}">
			<p><b>${product2}가 이미 추가되어 있습니다. - if</b></p>
		</c:if><hr>
		
		<h3>c:choose, c:when, c:otherwise</h3>
		<c:choose>
			<c:when test="${checkout}">
				<p>주문 제품: ${product2} - c,w,o</p>
			</c:when>
			<c:otherwise>
				<p>주문 제품이 아닙니다. - c,w,o</p>
			</c:otherwise>
		</c:choose><hr>
		
		<h3>c:forTokens</h3>
		<c:forTokens var="city" items="Seoul|Tokyo|New York|Toronto" delims="|" varStatus="j">
			<c:if test="${j.first}">도시 목록 : </c:if>
				${city}
			<c:if test="${!j.last}">,</c:if>
		</c:forTokens>
	</body>
</html>