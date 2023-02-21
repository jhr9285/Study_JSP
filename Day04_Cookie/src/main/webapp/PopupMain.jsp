<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String popupMode = "on";

Cookie[] cookies = request.getCookies();  		// 쿠키 생성 후에 확인하는 코드
if(cookies != null) {
	for(Cookie c : cookies) {
		String cookieName = c.getName();
		String cookieValue = c.getValue();
		if(cookieName.equals("PopupClose")) { 	// PopupCookie 파일의 cookieName이 PopupClose이면
			popupMode = cookieValue;			// popupMode 값을 cookieValue(="off")로 바꾼다
		}
	}
}
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>쿠키를 이용한 팝업 관리</title>
		<style>
			#popup {
				position: absolute;
				top: 100px;
				left: 100px;
				color: yellow;
				width: 300px;
				height: 100px;
				background-color: black;
			}
			#popup > div {
				position: relative;
				background-color: #ffffff;
				top: 0px;
				border: 1px solid gray;
				padding: 10px;
				color: black;
			}
		</style>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script>
			$(function(){
				$('#closeBtn').click(function() {
					$('#popup').hide();
					var chkVal = $('#inactiveToday:checked').val();
					$.ajax({
						url: './PopupCookie.jsp',
						type: 'get',
						data: {inactiveToday : chkVal}, // url 주소 편으로 보내는 데이터, json 형식
						dataType: "text", 				// 서버에서 전송하는 데이터의 형식 (data와 dataType 혼동 주의)
						success: function(resData) {	// resData에 '하루 동안 열지 않음' 메시지가 들어옴
							if(resData != '') {			// resData 값이 있으면
								location.reload(); 		// location.reload() : 현재 페이지를 새로고침한다
							}
						}
					});
				});
			});
		</script>
	</head>
	<body>
		<h2>팝업 메인 페이지</h2>
		<%  /* 메시지를 열 번 출력하는 코드 (기능적으로는 별 의미 없음) */
		    for (int i = 1; i <= 10; i++) {
		        out.print("현재 팝업창은 " + popupMode + " 상태입니다.<br/>");
		    }
		    if (popupMode.equals("on")) { // popupMode가 ON이면 아래 팝업창 영역 실행, popupMode가 OFF면 실행X
		%>                              
		<!----------------------- 팝업창 ----------------------------->
		    <div id="popup">
		        <h2 align="center">공지사항 팝업입니다.</h2>
		        <div align="right"><form name="popFrm">
		            <input type="checkbox" id="inactiveToday" value="1" />
		            하루 동안 열지 않음
		            <input type="button" value="닫기" id="closeBtn" />
		        </form></div>
		    </div>
		<%
		    }
		%>
	</body>
</html>