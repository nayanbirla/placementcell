package com.placementcell.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Announcement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private String content;
	private LocalDate datePosted;
	private LocalDate dateToRemove; // Date to remove the announcement
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="superAdminId")
	private Users superAdminId;
    

	public Announcement() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Constructor
	public Announcement(int id, String title, String content, LocalDate datePosted, LocalDate dateToRemove,Users superAdminId) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.datePosted = datePosted;
		this.dateToRemove = dateToRemove;
		this.superAdminId=superAdminId;
	}

	// Getters and Setters
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

	public LocalDate getDatePosted() {
		return datePosted;
	}

	public void setDatePosted(LocalDate date) {
		this.datePosted = date;
	}

	public LocalDate getDateToRemove() {
		return dateToRemove;
	}

	public void setDateToRemove(LocalDate dateToRemove) {
		this.dateToRemove = dateToRemove;
	}

	public Users getUsers() {
		return superAdminId;
	}

	public void setUsers(Users superAdminId) {
		this.superAdminId = superAdminId;
	}

	// toString() method to represent Announcement as a string
	@Override
	public String toString() {
		return "Announcement{" + "id=" + id + ", title='" + title + '\'' + ", content='" + content + '\''
				+ ", datePosted=" + datePosted + ", dateToRemove=" + dateToRemove + '}';
	}
}
