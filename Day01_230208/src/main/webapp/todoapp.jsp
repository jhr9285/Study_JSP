<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>To Do App</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.2.3/css/bootstrap.min.css" integrity="sha512-SbiR/eusphKoMVVXysTKG/7VseWii+Y3FdHrt0EpKgpToZeemhqHeZeLWLhJutz/2ut2Vw1uQEj2MbRF+TVBUA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
		<script>
			function addItem() {
				let todo = document.getElementById("item");
				let list = document.getElementById("todolist");
				let listitem = document.createElement("li");
				listitem.className = "d-flex list-group-item list-group-item-action list-group-item-warning";
				
				let xbtn = document.createElement("button");
				xbtn.className = "btn-close ms-auto";
				xbtn.onclick = function(e){
					let pnode = e.target.parentNode;
					list.removeChild(pnode);
				};
				
				listitem.innerText = todo.value;
				listitem.appendChild(xbtn);
				list.appendChild(listitem);
				
				todo.value = "";
				todo.focus();
			}
		</script>
	</head>
	<body>
		<div class="container bg-warning shadow mw-auto mt-5 p-5 w-75">
			<h2>To Do List App</h2>
			<div class="input-group" style="margin-top: 20px;"> <!-- input-group : 두 요소를 붙여주는 부트스트랩 클래스 -->
				<input type="text" id="item" class="form-control" placeholder="할일을 입력하세요.">
				<button type="button" class="btn btn-primary" onclick="addItem()">할일 추가</button>
				<!-- <a href="#" class="btn btn-primary onclick="addItem()">할일 추가</a> --> <!-- 링크 태그도 btn 클래스 사용하면 버튼 모양이 됨 -->
			</div>
			<ul id="todolist" class="list-group"></ul>
		</div>
	</body>
</html>