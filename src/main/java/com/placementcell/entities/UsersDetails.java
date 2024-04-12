package com.placementcell.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="users_details")
public class UsersDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private int id;
	@OneToOne
	@JsonBackReference
	@JoinColumn(name = "user_id",insertable = true)
	private Users users;
	private String firstName;
	private String middleName;
	private String lastName;
	private String personalEmail;
	private String gender;
	private String contact;
	@JsonManagedReference
	@ManyToOne
	private Course course;
	private String image;
	
	public UsersDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public UsersDetails(int id,Users users, String firstName, String middleName, String lastName, String personalEmail,
			String gender, String contact,Course course, String image) {
		super();
		this.id = id;
		this.users = users;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.personalEmail = personalEmail;
		this.gender = gender;
		this.contact = contact;
		this.course=course;
		this.image = image;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPersonalEmail() {
		return personalEmail;
	}

	public void setPersonalEmail(String personalEmail) {
		this.personalEmail = personalEmail;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	
	public Course getCourse() {
		return course;
	}


	public void setCourse(Course course) {
		this.course = course;
	}


	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}


	@Override
	public String toString() {
		return "UsersDetails [users=" + users + ", firstName=" + firstName + ", middleName=" + middleName
				+ ", lastName=" + lastName + ", personalEmail=" + personalEmail + ", gender=" + gender + ", contact="
				+ contact +"course="+ course + ", image=" + image + "]";
	}
	
}
