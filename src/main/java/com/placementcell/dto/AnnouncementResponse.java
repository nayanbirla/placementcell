package com.placementcell.dto;

public class AnnouncementResponse {

	private int id;
	private String title;
	private String content;
	public AnnouncementResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AnnouncementResponse(String title, String content) {
		super();
		this.title = title;
		this.content = content;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
	
}
