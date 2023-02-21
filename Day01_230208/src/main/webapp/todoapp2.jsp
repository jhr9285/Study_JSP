<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<!-- 부트스트랩은 css CDN과 js CDN이 세트임 -->
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.2.3/css/bootstrap.min.css" integrity="sha512-SbiR/eusphKoMVVXysTKG/7VseWii+Y3FdHrt0EpKgpToZeemhqHeZeLWLhJutz/2ut2Vw1uQEj2MbRF+TVBUA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
		<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.2.3/js/bootstrap.min.js" integrity="sha512-1/RvZTcCDEUjY/CypiMz+iqqtaoQfAITmNSJY17Myp4Ms5mdxPS5UV7iOfdZoxcGhzFbOm6sntTKJppjvuhg4g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
		<script>
			function addItem(){
				let todo=document.getElementById("item"); // input 태그
				let list=document.getElementById("todolist"); // ul 태그
				let listitem=document.createElement("div"); //div태그
				//<div class="alert alert-info alert-dismissible"> 처럼 구현
				listitem.className="alert alert-success alert-dismissible";
				let xbtn=document.createElement("button"); // button태그
				xbtn.className="btn-close"; // <button class="btn-close"> 처럼 구현
				xbtn.setAttribute("data-bs-dismiss","alert"); // <button data-bs-dismiss="alert"></button>				
				listitem.innerText=todo.value; // input태그의 값을 <div></div>사이에 출력
				listitem.appendChild(xbtn); // x버튼 <div></div>사이에 추가
				list.appendChild(listitem); // <div></div>를 <div></div>안에 출력
				todo.value="";// input태그 초기화
				todo.focus();
				
			}
		</script>
	</head>
<body>
	<div class="container bg-warning shadow mx-auto mt-5 p-5 w-75">
		<h2>My ToDo App</h2>
		<hr>
		<div class="input-group">
			<input id="item" class="form-control" placeholder="할일을 입력하세요">
			<button type="button" class="btn btn-primary" onclick="addItem()">할일 추가</button>
			<!-- <a href="#" class="btn btn-primary" onclick="addItem()">할일 추가</a> -->
		</div>
		<hr>
		<div id="todolist" class="list-group"></div>
	</div>
</body>
</html>