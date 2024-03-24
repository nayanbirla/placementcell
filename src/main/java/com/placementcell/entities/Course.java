package com.placementcell.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Course {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private int courseId;
	private String courseName;
	
	@JsonBackReference
	@OneToMany(mappedBy ="course",cascade=CascadeType.ALL)
	private List<UsersDetails> users;
	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Course(int courseId, String courseName) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
//	public List<UsersDetails> getUsers() {
//		return users;
//	}
//	public void setUsers(List<UsersDetails> users) {
//		this.users = users;
//	}
//	
	
}
