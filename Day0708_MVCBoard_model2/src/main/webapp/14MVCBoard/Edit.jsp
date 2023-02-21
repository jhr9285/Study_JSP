<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>파일 첨부형 게시판</title>
		<script type="text/javascript">
			function validateForm(form) {
				if(form.name.value == "") {
					alert("작성자를 입력하세요.");
					form.name.focus();
					return false;
				}
				if(form.title.value == "") {
					alert("제목을 입력하세요.");
					form.title.focus();
					return false;
				}
				if(form.content.value == "") {
					alert("내용을 입력하세요.");
					form.content.focus();
					return false;
				}
			}
		</script>
	</head>
	<body>
		<h2 align="center">파일 첨부형 게시판 - 수정하기(Edit)</h2>
		<form name="writeFrm" method="post" enctype="multipart/form-data"
			  action="../mvcboard/edit.do" onsubmit="return validateForm(this);">
			<input type="hidden" name="idx" value="${ dto.idx }">
			<input type="hidden" name="prevOfile" value="${ dto.ofile }">
			<input type="hidden" name="prevSfile" value="${ dto.sfile }">
			<!-- name에 prev 붙은 이유는 수정 전 파일명임을 명시하기 위함. 
    			 수정하여 새로 올린 첨부파일 name이 ofile이라서 겹치지 않도록 하기 위함. -->
    		<table border="1" width="100%">
    			<tr>
			        <td>작성자</td>
			        <td>
			            <input type="text" name="name" style="width:150px;" value="${ dto.name }" />
			        </td>
			    </tr>
			    <tr>
			        <td>제목</td>
			        <td>
			            <input type="text" name="title" style="width:95%;" value="${ dto.title }" />
			        </td>
			    </tr>
			    <tr>
			        <td>내용</td>
			        <td>
			            <textarea name="content" style="width:95%;height:100px;">${ dto.content }</textarea>
			        </td>
			    </tr>
			    <tr>
			        <td>첨부 파일</td>
			        <td>
			            <input type="file" name="ofile" />
			        </td>
			    </tr>
			    <tr>
			        <td colspan="2" align="center">
			            <button type="submit">작성완료</button>
			            <button type="reset">RESET</button>
			            <button type="button" onclick="location.href='../mvcboard/list.do';">
			                목록 바로가기
			            </button>
			        </td>
			    </tr>
    		</table>
		</form>
	</body>
</html>