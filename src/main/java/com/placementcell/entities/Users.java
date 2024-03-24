package com.placementcell.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="users_info")
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private int id;
    @Column(unique = true,nullable = false)
	private String email;
    @JsonIgnore
	private String password;
    @JsonIgnore
	private String role;
	@JsonManagedReference
	@OneToOne(mappedBy = "users", cascade = CascadeType.ALL)
	private UsersDetails usersDetails;
	@JsonManagedReference
	@OneToOne(mappedBy = "users", cascade = CascadeType.ALL)
	private EducationDetails educationDetails;
	// only for super admin
	@OneToMany(mappedBy = "superAdminId")
	private List<Announcement> announcement;
	// only for admin
	@OneToMany(mappedBy = "adminId")
	private List<CompanyDetails> companyDetails;
	// only for superAdmin
	@OneToMany(mappedBy = "superAdminId")
	private List<StudyMaterial> studyMaterials;
	
	
	
	
	
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Users(int id, String email, String password, String role) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	
	public Users(int id, String email, String password, String role, UsersDetails usersDetails,
			EducationDetails educationDetails) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.role = role;
		this.usersDetails = usersDetails;
		this.educationDetails = educationDetails;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "Users [id=" + id + ", email=" + email + ", password=" + password + ", role=" + role  + "]";
	}
	public UsersDetails getUsersDetails() {
		return usersDetails;
	}
	public void setUsersDetails(UsersDetails usersDetails) {
		this.usersDetails = usersDetails;
	}
	public EducationDetails getEducationDetails() {
		return educationDetails;
	}
	public void setEducationDetails(EducationDetails educationDetails) {
		this.educationDetails = educationDetails;
	}	
	
	
}
