package com.placementcell.request;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class PlacedStudentRequest {

	private int id;
	private String firstName;
	private String lastName;
	private String linkedin;
	private Date date;
	private String image;
	private String email;
	private String companyName;
    private String ctc;
  
	public PlacedStudentRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PlacedStudentRequest(int id, String firstName, String lastName, String linkedin, Date date, String image,
			String email, String companyName, String ctc) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.linkedin = linkedin;
		this.date = date;
		this.image = image;
		this.email = email;
		this.companyName = companyName;
		this.ctc=ctc;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLinkedin() {
		return linkedin;
	}

	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCtc() {
		return ctc;
	}

	public void setCtc(String ctc) {
		this.ctc = ctc;
	}

		
	

}
