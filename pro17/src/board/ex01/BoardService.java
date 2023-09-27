package board.ex01;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardService {
	BoardDAO boardDAO;
	public BoardService() {
		boardDAO = new BoardDAO();
	}
	public Map listArticles(int pageNum, int amount) {
		Map articlesMap = new HashMap();
		List<ArticleVO> articlesList = boardDAO.selectAllArticles(pageNum, amount);
		int totArticles = boardDAO.selectTotArticles();
		PageVO pageVO = new PageVO(pageNum, amount, totArticles);
		articlesMap.put("articlesList", articlesList);
		articlesMap.put("pageVO", pageVO);
		return articlesMap;
	}
	public int addArticle(ArticleVO article) {
		return boardDAO.insertNewArticle(article);
	}
	public ArticleVO viewArticle(int articleNO) {
		return boardDAO.selectArticle(articleNO);
	}
	public void modArticle(ArticleVO article) {
		boardDAO.updateArticle(article);
	}
	public void removeArticle(int articleNO) {
		boardDAO.selectRemovedArticles(articleNO);
	}
	
	public int addReply(ArticleVO article) {
		return boardDAO.insertNewArticle(article);
	}
}
