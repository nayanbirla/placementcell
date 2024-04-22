package com.placementcell.entities;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class PlacedStudent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JsonBackReference
	private CompanyDetails companyDetailsPlaced;
	private String firstName;
	private String lastName;
	private String linkedin;
	private Date date;
	private String image;
	private String email;
	private String ctc;
	private String offerLetter;
	
	public PlacedStudent() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PlacedStudent(int id, CompanyDetails companyDetailsPlaced, String firstName, String lastName,
			String linkedin, Date date, String image, String email,String ctc,String offerLetter) {
		super();
		this.id = id;
		this.companyDetailsPlaced = companyDetailsPlaced;
		this.firstName = firstName;
		this.lastName = lastName;
		this.linkedin = linkedin;
		this.date = date;
		this.image = image;
		this.email = email;
		this.ctc=ctc;
		this.offerLetter=offerLetter;
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
	public CompanyDetails getCompanyDetailsPlaced() {
		return companyDetailsPlaced;
	}

	public void setCompanyDetailsPlaced(CompanyDetails companyDetailsPlaced) {
		this.companyDetailsPlaced = companyDetailsPlaced;
	}

	public String getCtc() {
		return ctc;
	}

	public void setCtc(String ctc) {
		this.ctc = ctc;
	}

	public String getOfferLetter() {
		return offerLetter;
	}

	public void setOfferLetter(String offerLetter) {
		this.offerLetter = offerLetter;
	}
	
	
}
