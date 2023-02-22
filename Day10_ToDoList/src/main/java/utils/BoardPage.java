// Quiz230217 - 1. MVCBoard에서 검색후 페이지번호를 클릭하면, 검색결과의 페이지로 가도록 수정하기
package utils;

public class BoardPage {
	public static String pagingStr(int totalCount, int pageSize, int blockPage, 
								   int pageNum, String searchWord, String reqUrl) {
		// totalCount : 전체 글 수, pageSize : 한 페이지에 보여줄 글 갯수, blockPage : 하단 페이지 번호의 갯수, 
		// pageNum : 페이지 번호, reqUrl : 페이지 번호 클릭 시 이동하는 주소
		
		String pagingStr = "";
		
		// 전체 페이지 수를 올림으로 계산
		int totalPages = (int)(Math.ceil(((double) totalCount / pageSize)));
		
		// '이전 페이지 블럭으로 바로가기' 출력
		int pageTemp = (((pageNum - 1) / blockPage ) * blockPage ) + 1 ;
		if(pageTemp != 1) {
			if(searchWord != null) {
				pagingStr += "<a href='" + reqUrl + "?pageNum=1&searchWord=" + searchWord +"'>[첫 페이지]</a>";
				pagingStr += "&nbsp;";
				pagingStr += "<a href='" + reqUrl + "?pageNum=" + (pageTemp - 1) + "&searchWord=" + searchWord + "'>[이전 블럭]</a>";
			} else {
				pagingStr += "<a href='" + reqUrl + "?pageNum=1'>[첫 페이지]</a>";
				pagingStr += "&nbsp;";
				pagingStr += "<a href='" + reqUrl + "?pageNum=" + (pageTemp - 1) + "'>[이전 블럭]</a>";
			}
		}
		
		// 각 페이지 번호 출력
		int blockCount = 1;
		while(blockCount <= blockPage && pageTemp <= totalPages) {
			if(pageTemp == pageNum) { // 현재 페이지인가?
				// 현재 페이지임. 현재 페이지에는 링크를 걸지 않음
				pagingStr += "&nbsp;" + pageTemp + "&nbsp;";
			} else { // 현재 페이지가 아님. 링크 걸기
				if(searchWord != null) {
					pagingStr += "&nbsp;<a href='" + reqUrl + "?pageNum=" + pageTemp + "&searchWord=" + searchWord + "'>" + pageTemp + "</a>&nbsp;";
				} else {
					pagingStr += "&nbsp;<a href='" + reqUrl + "?pageNum=" + pageTemp + "'>" + pageTemp + "</a>&nbsp;";
				}
			}
			pageTemp++;
			blockCount++;
		}
		
		// '다음 페이지 블럭으로 바로가기' 출력
		if(pageTemp <= totalPages) {
			if(searchWord != null) {
				pagingStr += "<a href='" + reqUrl + "?pageNum=" + pageTemp + "&searchWord=" + searchWord + "'>[다음 블럭]</a>";
				pagingStr += "&nbsp;";
				pagingStr += "<a href='" + reqUrl + "?pageNum=" + totalPages + "&searchWord=" + searchWord + "'>[마지막 페이지]</a>";
			} else {
				pagingStr += "<a href='" + reqUrl + "?pageNum=" + pageTemp + "'>[다음 블럭]</a>";
				pagingStr += "&nbsp;";
				pagingStr += "<a href='" + reqUrl + "?pageNum=" + totalPages + "'>[마지막 페이지]</a>";
			}
		}
		return pagingStr;
	}
}
