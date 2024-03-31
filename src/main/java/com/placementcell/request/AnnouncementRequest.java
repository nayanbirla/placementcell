package com.placementcell.request;

import java.sql.Date;
import java.time.LocalDate;

public class AnnouncementRequest {

	private int id;
	private String title;
	private String content;
	private LocalDate expiryDate;

	public AnnouncementRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AnnouncementRequest(int id, String title, String content, LocalDate expiryDate) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.expiryDate = expiryDate;
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

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

}
