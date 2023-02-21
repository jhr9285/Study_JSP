// 1. MVC패턴 연습하기
//    https://www.w3schools.com/howto/tryit.asp?filename=tryhow_css_responsive_form
// 	  폼에 입력한 데이터를 Model에 넣은 다음 화면에 값을 출력하기 
package day03Quiz;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/day03quiz")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public Controller() {}
    
    HashMap<String, Model> models = new HashMap<>();
    Model m;
    String fn, ln, cn, sj;
    String address2;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		fn = request.getParameter("firstname");
		ln = request.getParameter("lastname");
		cn = request.getParameter("country");
		sj = request.getParameter("subject");
		
		m = new Model(fn, ln, cn, sj);
		models.put(cn, m);
		
		String action = request.getParameter("action");
		String address = "";
		
			if(action == null) {
				getServletContext().getRequestDispatcher("/day03quiz?action=view").forward(request, response);
			} else {
				switch(action) {
				case "view": 
					if(address2 == "view") {
						address = result(request, response, m);
					} else {
						address = view(request, response); break;
					}
				case "result": 
					address = result(request, response, m); break;
			}
			getServletContext().getRequestDispatcher("/" + address).forward(request, response);
		}
	}

//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
//	}
	
	protected String view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		address2 = "view";
		return "view.jsp";
	}
	
	protected String result(HttpServletRequest request, HttpServletResponse response, Model m) throws ServletException, IOException {
		request.setAttribute("m", models.get(cn));
		return "result.jsp";
	}
}