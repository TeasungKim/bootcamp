package assignment;

import java.sql.Connection;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class listDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
   public listDAO()	{
	   
	   try{	
		   
		   Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/test");
			
	} catch (Exception e) {
		e.printStackTrace();
	}
}
   public void addList(Map<String, String> pagingMap){
	  
	   System.out.println(pagingMap.get("word")+" addList");
	   try {
		   con = dataFactory.getConnection();
		   String query = "insert into todolist (contant) values(?)";
		   pstmt = con.prepareStatement(query);
		   pstmt.setString(1,pagingMap.get("word"));
		   pstmt.executeUpdate();
		  
		   
		   pstmt.close();
			con.close();
			
	   } catch(Exception e) {
		   e.printStackTrace();
	   }
	   
   }
   
   
   
   public List<listVO> selectAllList(){
	   List<listVO> allList = new ArrayList<>();
	   try {
		   con = dataFactory.getConnection();
		   String query = "select * from todolist";
		   pstmt = con.prepareStatement(query);
		   ResultSet rs = pstmt.executeQuery();
		   
		   while(rs.next()) {
			  
			   String contant = rs.getString("contant");
			   
			   listVO listvo = new listVO();
			   
			   listvo.setContant(contant);
			  
			   allList.add(listvo);
		   }
		   rs.close();
		   pstmt.close();
		   con.close();
		   
		   
	   } catch (Exception e) {
		   e.printStackTrace();
	   }
	   
	   
	   
	   return allList;
   }
   
   
   
}
