package com.placementcell.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="education_details")
public class EducationDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private int id;
	@OneToOne
	@JsonBackReference
	@JoinColumn(name = "user_id")
	private Users users;
	private double tenthPercentage;
	private double twelthPercentage;
	private double ug;
	private double pg;
	public EducationDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EducationDetails(int id,Users users, double tenthPercentage, double twelthPercentage, double ug, double pg) {
		super();
		this.id = id;
		this.users=users;
		this.tenthPercentage = tenthPercentage;
		this.twelthPercentage = twelthPercentage;
		this.ug = ug;
		this.pg = pg;
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
	public double getTenthPercentage() {
		return tenthPercentage;
	}
	public void setTenthPercentage(double tenthPercentage) {
		this.tenthPercentage = tenthPercentage;
	}
	public double getTwelthPercentage() {
		return twelthPercentage;
	}
	public void setTwelthPercentage(double twelthPercentage) {
		this.twelthPercentage = twelthPercentage;
	}
	public double getUg() {
		return ug;
	}
	public void setUg(double ug) {
		this.ug = ug;
	}
	public double getPg() {
		return pg;
	}
	public void setPg(double pg) {
		this.pg = pg;
	}
	@Override
	public String toString() {
		return "EducationDetails [id=" + id + ", tenthPercentage=" + tenthPercentage + ", twelthPercentage="
				+ twelthPercentage + ", ug=" + ug + ", pg=" + pg + "]";
	}
	
}
