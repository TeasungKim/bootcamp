package testing;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import board.ex01.ArticleVO;
import board.ex01.BoardService;

/**
 * Servlet implementation class Testing
 */
@WebServlet("/Board/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static String ARTICLE_IMAGE_REPO = "C:\\Board\\article_image";
	BoardService boardService;
	ArticleVO articleVO;
	
   public void init(ServletConfig config) throws ServletException{
	   boardService = new BoardService();
	   articleVO = new ArticleVO();
   }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request,response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage=null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String action = request.getPathInfo();
		try {
			if(action==null||action.equals("/listArticles.do")) {
				List<ArticleVO> list = boardService.listArticles();
				request.setAttribute("articles", list);
				nextPage = "/boardpage/listArticles.jsp";
				
			} else if(action.equals("/addArticle.do")) {
				int articleNO=0;
				Map<String,String> articleMap = upload(request,response);
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String imageFileName = articleMap.get("imageFileName");
				String id = articleMap.get("id");
				articleVO.setId(id);
				articleVO.setContent(content);
				articleVO.setImageFileName(imageFileName);
				articleVO.setTitle(title);
				
				boardService.addArticle(articleVO);
				articleNO = boardService.addArticle(articleVO);
				if(imageFileName != null && imageFileName.length() !=0) {
					File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\"+imageFileName);
					File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + articleNO)	;	
					destDir.mkdirs();
					FileUtills.moveFIleToDirectory(srcFile,destDir,True);
					
				}
				
//				String title = request.getParameter("title");
//				String content = request.getParameter("content");
//				String imageFileName = request.getParameter("imageFileName");
//				String id = request.getParameter("id");
//				articleVO.setId(id);
//				articleVO.setContent(content);
//				articleVO.setImageFileName(imageFileName);
//				articleVO.setTitle(title);
				boardService.addArticle(articleVO);
				//nextPage = "/Board/listArticles.do";
				PrintWriter pw = response.getWriter();
	            pw.print("<script> location.href='"+request.getContextPath()+"/Board/listArticles.do'</script>");
				return;
			}

			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}
		
	private Map<String,String> upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,String> articleMap = new HashMap<>();
		String encoding="utf-8";
		File currentDirPath=new File(ARTICLE_IMAGE_REPO);
		//글 이미지저장폴더에 대해 파일 객체를 생성합니다.
		DiskFileItemFactory factory=new DiskFileItemFactory();
		factory.setRepository(currentDirPath);
		factory.setSizeThreshold(1024*1024);
		ServletFileUpload upload=new ServletFileUpload(factory);
		
		try {
		List items = upload.parseRequest(request);
			for (int i=0; i<items.size(); i++) {
			FileItem fileItem = (FileItem)items.get(i);
			if(fileItem.isFormField()) {
				articleMap.put(fileItem.getFieldName(),fileItem.getString(encoding));
				
			} else {
				if(fileItem.getSize()>0) {
					int idx = fileItem.getName().lastIndexOf("\\");
					if(idx==-1) {
						idx=fileItem.getName().lastIndexOf("/");
					}
					String fileName = fileItem.getName().substring(idx+1);
					articleMap.put(fileItem.getFieldName(), fileName);
					File uploadFile = new File(currentDirPath + "\\temp\\" + fileName);
					fileItem.write(uploadFile);
					
				}	
			}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		return articleMap;
	}
	
	
	
}
