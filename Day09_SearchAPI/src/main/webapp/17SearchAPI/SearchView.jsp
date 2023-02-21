<!-- Quiz230220 - 1. 검색API 결과 화면에 페이징처리하기 -->
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="utils.Paging"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>검색 API</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script>
			$(document).ready(function(){
				for(i = 0; i < totalCount; i++){
					$('#startNum > tr').append("<td val='"+pageNum+"'></td>");
				}
				
				// [검색 요청] 버튼 클릭 시 실행할 메서드를 정의합니다.
				$('#searchBtn').click(function() {
					$.ajax({
						url : "../NaverSearchAPI.do",   // 요청 URL (서블릿 가상 url 주소)
						type : "get",					// HTTP 메서드 (전달 방식)
						data : {						// 매개변수로 전달할 데이터 (중괄호 사용했으므로 객체임)
							keyword : $('#keyword').val(),					 // 검색어
							startNum : $('#startNum > tr > td').val()  // 검색 시작 위치 (#startnum 태그의 하위 태그 중 selected 되어있는 option 태그 찾기)
						},
						dataType : "json",		// 응답 데이터 형식 (네이버에서 전송해오는 데이터 형식)
						success : sucFuncJson,	// 요청 성공 시 호출할 메서드 설정 (이름 있는 함수)
						error : errFunc			// 요청 성공 시 호출할 메서드 설정 (이름 있는 함수)
					});	
				});
			});
				
			// 검색 성공 시 결과를 화면에 뿌려주는 함수 작성
			function sucFuncJson(d) { // d : 네이버에서 전송 받은 json 형식의 data
				int totalCount = d.total;
				int totalPage = (int)Math.ceil((double)totalCount/pageSize);
				int pageSize = 10; // 한 페이지당 데이터 10개
				int blockPage = 5;// 한 블럭당 페이지 버튼 5개
				int pageNum = 1; // 초기값 1 (첫 페이지)
				String pageTemp = request.getParameter("start");
				if(pageTemp != null && !pageTemp.equals("")) {
					pageNum = Integer.parseInt(pageTemp); // 요청받은 페이지로 수정
				}
				int start = (pageNum - 1) * pageSize + 1; // 첫 게시물 번호 (ex, 3페이지 : 21번 ~ 30번)
				
				var str = "";
				
				$.each(d.items, function(index, item) { // d.items : 배열, function : 배열에서 idx 순서대로 원소를 꺼내서 item에 담는 함수
					// 콤보박스 말고 페이징처리를 1,2,3,4,5 로 링크 걸린 숫자 나열로 바꾸기 (화면 하단에 위치하도록)
					str += "<ul>";
					str += "	<li>" + (index + 1) + "</li>";
					str += "	<li>" + item.title + "</li>";
					str += "	<li>" + item.description + "</li>";
					str += "	<li>" + item.bloggername + "</li>";  // 블로그로 검색하는 경우에 쓰는 코드, 블로그 아닌 경우 수정 또는 삭제 해야 함
					str += "	<li>" + item.bloggerlink + "</li>";  // 블로그로 검색하는 경우에 쓰는 코드, 블로그 아닌 경우 수정 또는 삭제 해야 함
					str += "	<li>" + item.postdate + "</li>";	 // 블로그로 검색하는 경우에 쓰는 코드, 블로그 아닌 경우 수정 또는 삭제 해야 함
					str += "	<li><a href='" + item.link + "' target='_blank'>바로가기</a></li>";
					str += "</ul>";
				});
				$('#searchResult').html(str);
				$('#paging').append("<div>"+Paging.pagingStr(totalCount, pageSize, blockPage, pageNum, location.path())+"</div>");
			};
			
			// 검색 실패 시 경고창을 띄워주는 함수 작성
			function errFunc(e) {
				alert("실패 : " + e.status);
			}
		</script>
		<style>
		    ul{border:2px #cccccc solid;}
		</style>
	</head>
	<body>
		<div>
			<form id="searchFrm">
				한 페이지에 10개씩 출력됨 <br />
<!--  			
				<select id="startNum">
					<option value="1">1페이지</option>  // value값 : start (1 -> 1+10 -> 1+10+10 ...)
					<option value="11">2페이지</option> 
					<option value="21">3페이지</option> 
					<option value="31">4페이지</option> 
					<option value="41">5페이지</option> 
				</select>
 -->				
				<input type="text" id="keyword" placeholder="검색어를 입력하세요." />
				<button type="button" id="searchBtn">검색 요청</button>
			</form>
		</div>
		<div class="row" id="searchResult">
			여기에 검색 결과가 출력됩니다.
		</div>	
		<!-- 페이징 처리 -->
		<table width="100%" id="startNum">
			<tr align="center">
				<td value="1"></td>
			</tr>
		</table>	 		
	</body>
</html>