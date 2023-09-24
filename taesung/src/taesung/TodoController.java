package taesung;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TodoController
 */
@WebServlet("/Todo/*")
public class TodoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public TodoController() {
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request,response);
	}

	protected void doHandle (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nextPage=null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String action = request.getPathInfo();
		TodoService todoService = new TodoService();
		try {
			if(action==null||action.equals("/listArticles.do")) {
				List<TodoVO> list = todoService.listArticles();
				request.setAttribute("articles", list);
				nextPage = "/second.jsp";				
	} 
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
		} catch(Exception e) {
		e.printStackTrace();
	}
	
	}
	
}
