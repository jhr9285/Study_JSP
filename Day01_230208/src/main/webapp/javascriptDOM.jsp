<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Vanilla JavaScript로 jQuery처럼 HTML과 JavaScript 코드를 분리</title>
		<script>
			window.onload = function() { 					// onload : window가 로딩됐을 때 함수 호출. 
				let div = document.getElementById("box1");  // 			JS에서도 jQuery처럼 script 태그 내용이 
				div.innerText = "new text";				    // 			body 태그 준비 후에 실행되게 한다.
			
				let btn = document.getElementById("btn1");
				btn.onclick = function() {
					console.log("btn1이 클릭됨");
				}
			}
		</script>
	</head>
	<body>
		<div id="box1">box1</div>
		<button id="btn1">btn1</button>
	</body>
</html>