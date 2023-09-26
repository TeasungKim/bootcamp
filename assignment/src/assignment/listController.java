package assignment;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class listController
 */
@WebServlet("/list/*")
public class listController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    listDAO listdao;
	
    public listController() {
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doBoth(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doBoth(request,response);
	}

	protected void doBoth(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String action = request.getPathInfo();
		String nextPage = "";
		
		
		try {
			if(action.equals("/list.do")) {
				
				String word = request.getParameter("searchbox");
				
				Map<String, String> pagingMap = new HashMap<>();
				pagingMap.put("word", word);
				System.out.println(pagingMap.get("word")+" /list.do");
				
				listdao = new listDAO();
				listdao.addList(pagingMap);
				
				List<listVO> allList = listdao.selectAllList();
				
				System.out.println(allList.toString());
				
				request.setAttribute("pagingMap", allList);
				nextPage = "/jsp/list.jsp";
			
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
		
		
		
	}
	
}
