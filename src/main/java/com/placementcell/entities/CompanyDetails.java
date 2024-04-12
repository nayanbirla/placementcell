package com.placementcell.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class CompanyDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int companyId;
	@Column(unique = true)
    private String companyName;
    @Column(length = 20000)
    private String description;
    private boolean hiringStatus;
    private String logo;
    private String coverImage;
    @OneToMany(mappedBy = "companyDetailsPlaced")
    @JsonManagedReference
    private List<PlacedStudent> placedStudent;
    
    @OneToMany(mappedBy = "companyDetails")
    @JsonManagedReference
    private List<Questions> questions;
    
    @ManyToOne
    @JsonIgnore
    private Users adminId;
    
	public CompanyDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CompanyDetails(int companyId, String companyName, String description, boolean hiringStatus, String logo,
			String coverImage, List<PlacedStudent> placedStudent, List<Questions> questions, Users adminId) {
		super();
		this.companyId = companyId;
		this.companyName = companyName;
		this.description = description;
		this.hiringStatus = hiringStatus;
		this.logo = logo;
		this.coverImage = coverImage;
		this.placedStudent = placedStudent;
		this.questions = questions;
		this.adminId = adminId;
	}

	public long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isHiringStatus() {
		return hiringStatus;
	}
	public void setHiringStatus(boolean hiringStatus) {
		this.hiringStatus = hiringStatus;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public List<PlacedStudent> getPlacedStudent() {
		return placedStudent;
	}
	public void setPlacedStudent(List<PlacedStudent> placedStudent) {
		this.placedStudent = placedStudent;
	}
	public List<Questions> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Questions> questions) {
		this.questions = questions;
	}
	public Users getAdminId() {
		return adminId;
	}
	public void setAdminId(Users adminId) {
		this.adminId = adminId;
	}
	public String getCoverImage() {
		return coverImage;
	}
	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}
    
    
}
