package taesung;

import java.sql.Date;

public class TodoVO {
	private int no;
	private String name;
	private String contant;
	private Date date;
	public TodoVO(int no, String name, String contant, Date date) {
		super();
		this.no = no;
		this.name = name;
		this.contant = contant;
		this.date = date;
	}
	public TodoVO() {
		// TODO Auto-generated constructor stub
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContant() {
		return contant;
	}
	public void setContant(String contant) {
		this.contant = contant;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
}
