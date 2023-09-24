package taesung;

import java.util.List;

public class TodoService {
	 TodoDAO todoDAO;
	public TodoService() {
		todoDAO = new TodoDAO();
	}
	public List<TodoVO> listArticles() {
		List<TodoVO> articleList = todoDAO.selectAllArticles();
		return articleList;
	}

	
	
}
