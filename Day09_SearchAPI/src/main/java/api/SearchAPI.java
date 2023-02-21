package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/NaverSearchAPI.do")
public class SearchAPI extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 인증 정보 설정 (네이버 개발자센터의 client id, client secret 입력)
		String clientId = "G47tqyxmwdRrma9pGeJL";
		String clientSecret = "ofLN7yi1dQ";
		
		// 2. 검색 조건 설정
		int startNum = 0; // 검색 시작 위치
		String text = null; // 검색어
		
		try {
		startNum = Integer.parseInt(req.getParameter("startNum"));
		String searchText = req.getParameter("keyword");
		text = URLEncoder.encode(searchText, "UTF-8");
		} catch(Exception e) {
			throw new RuntimeException("검색어 인코딩 실패", e);
		}
		
		// 3. API URL 조합
		// json 결과 (블로그에 한정해서 검색하는 url)
		 String apiURL = "https://openapi.naver.com/v1/search/blog?query=" + text + "&display=10&start=" + startNum;
		// xml 결과 (참고)
		//String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query=" + text;  
		// json 결과 (책에 한정해서 검색하는 url)
		// String apiURL = "https://openapi.naver.com/v1/search/book/json?query=" + text + "&display=10&start=" + startNum;
		// json 결과 (모든 웹문서에 대해 검색하는 url)
		// String apiURL = "https://openapi.naver.com/v1/search/webkr.json?query=" + text + "&display=10&start=" + startNum;
		// json 결과 (모든 웹문서에 대해 + 사이트 지정해서 검색하는 url) (: => %3A, 한칸띄어쓰기 => + 로 인코딩)
		// String apiURL = "https://openapi.naver.com/v1/search/webkr.json?query=" + "site%3Amoozi.tistory.com+" + text + "&display=10&start=" + startNum;
		
		// 아래 사이트에서 각 탭 별로 다른 url 주소 확인 가능
		// https://developers.naver.com/docs/serviceapi/search/blog/blog.md
		// 검색 결과 전체 갯수 확인하는 방법
		// 웹 브라우저에 이 주소 입력 : http://localhost:8181/Day09_SearchAPI/NaverSearchAPI.do?keyword=jsp&startNum=1
		// 해당 화면의 total 속성값이 전체 갯수임 (NaverSearchAPI.do?keyword=jsp&startNum=1 를 쓰면 됨)
		
		// 4. API 호출
		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("X-Naver-Client-Id", clientId);
		requestHeaders.put("X-Naver-Client-Secret", clientSecret);
		String responseBody = get(apiURL, requestHeaders);
		
		// 5. 결과 출력
		System.out.println(responseBody); // 콘솔에 출력 (개발자 확인용 코드)
		
		resp.setContentType("text/html; charset=utf-8");
		resp.getWriter().write(responseBody); // 서블릿에서 클라이언트 쪽으로 즉시 출력
	}

	private static String get(String apiUrl, Map<String, String> requestHeaders) {
		HttpsURLConnection con = connect(apiUrl);
	    // ★ HttpURLConnection : java class. 서버에 연결할 때 사용한다.
        // connect : 사용자가 만든 메소드. 아래에 있음
		try {
			con.setRequestMethod("GET");
			
			// entrySet() 결과를 header에 하나씩 넣는 for-each문
			for(Map.Entry<String, String> header : requestHeaders.entrySet()) {
				con.setRequestProperty(header.getKey(), header.getValue());
			}
			
			int responseCode = con.getResponseCode();
			if(responseCode == HttpsURLConnection.HTTP_OK) { // 정상 호출
				return readBody(con.getInputStream());
			} else { // 에러 발생
				return readBody(con.getErrorStream());
			}
		} catch (IOException e) {
			throw new RuntimeException("API 요청과 응답 실패", e);
		} finally {
			con.disconnect();
		} 
	}
	
	private static HttpsURLConnection connect(String apiUrl) {
		try {
			URL url = new URL(apiUrl);
            // ★ HttpURLConnection : java class. 서버에 연결할 때 사용한다. (openConnection()을 사용하여 진짜 연결함)
			return (HttpsURLConnection)url.openConnection();
		} catch (MalformedURLException e) {
			throw new RuntimeException("API URL이 잘못되었습니다.");
		} catch (IOException e) {
			throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
		}
	}
	
	private static String readBody(InputStream body) {
		InputStreamReader streamReader = new InputStreamReader(body);
		
        // try 뒤에 소괄호() 붙는 문법 : 괄호 안의 코드를 자동으로 close 해준다.
		try (BufferedReader lineReader = new BufferedReader(streamReader)) {
			StringBuilder responseBody = new StringBuilder();
			
			String line;
			while((line = lineReader.readLine()) != null) {
				responseBody.append(line);
			}
			
			return responseBody.toString();
		} catch(IOException e) {
			throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
		}
		
	}
}
