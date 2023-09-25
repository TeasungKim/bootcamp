package taesung;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.util.Date;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class TodoDAO {
	
	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;

	public TodoDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/test");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	public List<TodoVO> selectAllArticles() {
		List<TodoVO> todovo = new ArrayList<>();
		try {
		con = dataFactory.getConnection();
		String query = "select * from freeboard";
		pstmt = con.prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			int no = rs.getInt("no");
			String name = rs.getString("name");
			String contant = rs.getString("contant");
			Date date = rs.getDate("date");
			
			TodoVO todoVO = new TodoVO();
			todoVO.setNo(no);
			todoVO.setName(name);
			todoVO.setContant(contant);
			todoVO.setDate(date);
		
			todovo.add(todoVO);
			
		} 
		
		rs.close();
		pstmt.close();
		con.close();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return todovo;
	}
}
