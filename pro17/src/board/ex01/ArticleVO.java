package board.ex01;

import java.sql.Date;

public class ArticleVO {
	private int recNO;
	private int articleNO;
	private int parentNO;
	private String title;
	private String content;
	private String imageFileName;
	private String id;
	private Date writeDate;
	private int groupNO;
	
	
	public ArticleVO() {}
	public ArticleVO(int articleNO, int parentNO,  int groupNO, String title, String content, String imageFileName, String id,
			Date writeDate) {
		super();
		this.articleNO = articleNO;
		this.parentNO = parentNO;
		this.title = title;
		this.content = content;
		this.imageFileName = imageFileName;
		this.id = id;
		this.writeDate = writeDate;
		this.groupNO = groupNO;
	}
	
	public int getRecNO() {
		return recNO;
	}
	public void setRecNO(int recNO) {
		this.recNO = recNO;
	}
	
	public int getArticleNO() {
		return articleNO;
	}
	public void setArticleNO(int articleNO) {
		this.articleNO = articleNO;
	}
	public int getParentNO() {
		return parentNO;
	}
	public void setParentNO(int parentNO) {
		this.parentNO = parentNO;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	public int getGroupNO() {
		return groupNO;
	}
	public void setGroupNO(int groupNO) {
		this.groupNO = groupNO;
	}
	
}
