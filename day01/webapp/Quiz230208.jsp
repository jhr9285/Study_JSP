<!-- 1. todoapp.jsp를 jquery로 코딩하기 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>To Do App - Quiz</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.2.3/css/bootstrap.min.css" 
			  integrity="sha512-SbiR/eusphKoMVVXysTKG/7VseWii+Y3FdHrt0EpKgpToZeemhqHeZeLWLhJutz/2ut2Vw1uQEj2MbRF+TVBUA==" 
			  crossorigin="anonymous" referrerpolicy="no-referrer" />
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js" 
		 	    integrity="sha512-STof4xm1wgkfm7heWqFJVn58Hm3EtS31XFaagaa8VMReCXAkQnJZ+jEy8PCC/iT18dFy95WcExNHFTqLyp72eQ==" 
		 	    crossorigin="anonymous" referrerpolicy="no-referrer"></script>
		<script>
			function addItem() {
				var listItem = $('<li>');
				listItem.addClass('d-flex list-group-item list-group-item-action list-group-item-warning');
				
				var xbtn = $('<button>');
				xbtn.addClass('btn-close ms-auto');
				
				xbtn.click(function(){
					$(this).parent().remove();
				});
				
				listItem.text($('#item').val());
				listItem.append(xbtn);
				$('#todolist').append(listItem);
				
				$('#item').val('');
				$('#item').focus();
			};
		</script>
	</head>
	<body>
		<div class="container bg-warning shadow mw-auto mt-5 p-5 w-75">
			<h2>To Do List App</h2>
			<div class="input-group" style="margin-top: 20px;">
				<input type="text" id="item" class="form-control" placeholder="할일을 입력하세요.">
				<button type="button" class="btn btn-primary" onclick="addItem()">할일 추가</button>
			</div>
			<ul id="todolist" class="list-group"></ul>
		</div>
	</body>
</html>