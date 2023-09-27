package board.ex01;

import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import member.ex01.MemberBean;

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
	
	public List<ArticleVO> selectAllArticles(int pageNum, int amount) {
		List<ArticleVO> articlesList = new ArrayList<>();
		try {
			con = dataFactory.getConnection();
			String query = "select B.* from (select @rownum := @rownum+1 rn, A.* from freeboard A, (select @rownum:=0) R) B where rn > ? and rn <= ? ";
			pstmt = con.prepareStatement(query);	
			pstmt.setInt(1, (pageNum-1)*amount);
			pstmt.setInt(2, pageNum*amount);
			ResultSet rs = pstmt.executeQuery();
			while( rs.next() ) {
				  int recNO = rs.getInt("rn");
				  int articleNO = rs.getInt("articleNO");
			      int parentNO = rs.getInt("parentNO");
			      String title = rs.getString("title");
			      String content = rs.getString("content");
			      String id = rs.getString("id");
			      Date writeDate= rs.getDate("writeDate");
			      ArticleVO article = new ArticleVO();
			      article.setRecNO(recNO);
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
		return articlesList;
	}
	
	private int getNewArticleNO() {
		try {
			con = dataFactory.getConnection();
			String query = "SELECT  max(articleNO) from freeboard ";
			System.out.println(query);
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery(query);
			if (rs.next())
				return (rs.getInt(1) + 1);
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int insertNewArticle(ArticleVO article) {
		int articleNO = getNewArticleNO();
		try {
			con = dataFactory.getConnection();
			int parentNO = article.getParentNO();
			int groupNO = 0;
			if(parentNO!=0) {
				groupNO = article.getGroupNO();
				
			} else {
				groupNO = articleNO;
			}
			
			String title = article.getTitle();
			String content = article.getContent();
			String id = article.getId();
			String imageFileName = article.getImageFileName();
			String query = "INSERT INTO freeboard ";
			query += " (title, content, imageFileName, id, parentNO, groupNO, articleNO) ";
			query += " VALUES (?, ?, ?, ?, ? ,?)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, imageFileName);
			pstmt.setString(4, id);
			pstmt.setInt(5, parentNO);
			pstmt.setInt(6, groupNO);
			pstmt.setInt(7, articleNO);
			pstmt.executeUpdate();
			
			pstmt.close();
			con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return articleNO;
	}
	
	// 상세보기글 레코드 검색
		public ArticleVO selectArticle(int articleNO) {
			ArticleVO article = new ArticleVO();
			try {
				con = dataFactory.getConnection();
				String query = "select articleNO,parentNO,title,content, imageFileName, id, writeDate";
				query += " from freeboard";
				query += " where articleNO=?";
				System.out.println(query);
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, articleNO);
				ResultSet rs = pstmt.executeQuery();
				rs.next();
				int _articleNO = rs.getInt("articleNO");
				int parentNO = rs.getInt("parentNO");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String imageFileName = rs.getString("imageFileName");
				if (imageFileName==null) {
					imageFileName = "null";
				} else {
					imageFileName = URLEncoder.encode(rs.getString("imageFileName"), "UTF-8");  // 파일명이 한글인 경우 필요함
				}
				String id = rs.getString("id");
				Date writeDate = rs.getDate("writeDate");

				article.setArticleNO(_articleNO);
				article.setParentNO(parentNO);
				article.setTitle(title);
				article.setContent(content);
				article.setImageFileName(imageFileName);
				article.setId(id);
				article.setWriteDate(writeDate);
				rs.close();
				pstmt.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return article;
		}
	
		public void updateArticle(ArticleVO article) {
			int articleNO = article.getArticleNO();
			String title = article.getTitle();
			String content = article.getContent();
			String imageFileName = article.getImageFileName();
			try {
				con = dataFactory.getConnection();
				String query = "update freeboard  set title=?,content=?";
				if (imageFileName != null && imageFileName.length() != 0) {
					query += ",imageFileName=?";
				}
				query += " where articleNO=?";

				System.out.println(query);
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, title);
				pstmt.setString(2, content);
				if (imageFileName != null && imageFileName.length() != 0) {
					pstmt.setString(3, imageFileName);
					pstmt.setInt(4, articleNO);
				} else {
					pstmt.setInt(3, articleNO);
				}
				pstmt.executeUpdate();
				pstmt.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void selectRemovedArticles(int articleNO) {
			try {
				con = dataFactory.getConnection();
				String query = "DELETE FROM freeboard ";
				query += " WHERE articleNO=?";
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, articleNO);
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
		public int selectTotArticles() {
			try {
				con = dataFactory.getConnection();
				String query = "select count(articleNO) from freeboard ";
				System.out.println(query);
				pstmt = con.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next())
					return (rs.getInt(1));
				rs.close();
				pstmt.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}
		
// DAO 마지막괄호		
}
