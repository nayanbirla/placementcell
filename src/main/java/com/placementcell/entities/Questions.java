package com.placementcell.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Questions {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JsonBackReference
	private CompanyDetails companyDetails;
	@Column(length = 5000)
	private String questionDetails;
	public Questions() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Questions(int id, CompanyDetails companyDetails, String questionDetails) {
		super();
		this.id = id;
		this.companyDetails = companyDetails;
		this.questionDetails = questionDetails;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public CompanyDetails getCompanyDetails() {
		return companyDetails;
	}
	public void setCompanyDetails(CompanyDetails companyDetails) {
		this.companyDetails = companyDetails;
	}
	public String getQuestionDetails() {
		return questionDetails;
	}
	public void setQuestionDetails(String questionDetails) {
		this.questionDetails = questionDetails;
	}
}
