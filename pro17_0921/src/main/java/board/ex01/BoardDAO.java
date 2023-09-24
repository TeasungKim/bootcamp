package board.ex01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public BoardDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/test");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List selectAllArticles() {
	
		List<ArticleVO> articlesList = new ArrayList<>();
		try {
			con = dataFactory.getConnection();
			String query = "select * from freeboard";
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while( rs.next() ) {
				 int articleNO = rs.getInt("articleNO");
			      int parentNO = rs.getInt("parentNO");
			      String title = rs.getString("title");
			      String content = rs.getString("content");
			      String id = rs.getString("id");
			      Date writeDate= rs.getDate("writeDate");
			      ArticleVO article = new ArticleVO();
			      article.setArticleNO(articleNO);
			      article.setParentNO(parentNO);
			      article.setTitle(title);
			      article.setContent(content);
			      article.setId(id);
			      article.setWriteDate(writeDate);
			      articlesList.add(article);	
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return  articlesList;
		
	}
	
	public int insertNewArticle(ArticleVO article) {
		
		try { 
			con = dataFactory.getConnection();
			String title = article.getTitle();
			String content = article.getContent();
			String imageFileName = article.getImageFileName();
			String id = article.getId();
			String query = "insert into freeboard";
			query += " (title, content, imageFileName,id)";
			query += " values(?, ?, ? ,?)";
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, imageFileName);
			pstmt.setString(4, id);
			pstmt.executeUpdate();
			
			String query1 = "select max(articleNO) from freeboard";
			pstmt = con.prepareStatement(query1);
			pstmt.executeUpdate();
			ResultSet rs1 = pstmt.executeQuery();
			if (rs1.next()) {
				return (rs1.getInt(1));
			}
			
			pstmt.close();
			con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return articleNO;
	}
	
	
	
}
