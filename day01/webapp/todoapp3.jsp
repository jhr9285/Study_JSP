<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.2.3/css/bootstrap.min.css" integrity="sha512-SbiR/eusphKoMVVXysTKG/7VseWii+Y3FdHrt0EpKgpToZeemhqHeZeLWLhJutz/2ut2Vw1uQEj2MbRF+TVBUA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js" integrity="sha512-STof4xm1wgkfm7heWqFJVn58Hm3EtS31XFaagaa8VMReCXAkQnJZ+jEy8PCC/iT18dFy95WcExNHFTqLyp72eQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
		<script>
			function addItem(){
				let todo=$("#item"); // input 태그  $("#item")
				let list=$("#todolist"); // ul 태그
				let listitem=$("<li>"); //li태그. $("<div>") , $("div")
				//<li class="d-flex list-group-item list-group-item-warning"> 처럼 구현
				listitem.addClass("d-flex list-group-item list-group-item-warning");
				let xbtn=$("<button>"); // button태그
				xbtn.addClass("btn-close ms-auto"); // <button class="btn-close ms-auto"> 처럼 구현
				xbtn.on("click",function(){
						let pnode=$(this).closest("li");
						pnode.remove();
					}
				);
						
				
				listitem.text(todo.val()); // input태그의 값을 <li></li>사이에 출력
				listitem.append(xbtn); // x버튼 <li></li>사이에 추가
				list.append(listitem); // <li></li>를 <ul></ul>안에 출력
				todo.val("");// input태그 초기화
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
		<ul id="todolist" class="list-group"></ul>
	</div>
</body>
</html>