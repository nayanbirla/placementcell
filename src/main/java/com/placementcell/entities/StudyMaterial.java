package com.placementcell.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class StudyMaterial {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int studyMaterialId;
	private String subjectName;
	private String topicName;
	@Column(length=10000)
	private String description;
	@OneToMany(mappedBy = "studyMaterial",cascade = CascadeType.ALL)
	@JsonManagedReference
	
	private List<Links> links;
	@ManyToOne
	@JsonIgnore
	private Users superAdminId;

	public StudyMaterial() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudyMaterial(int studyMaterialId, String subjectName, String topicName, String description,
			List<Links> links) {
		super();
		this.studyMaterialId = studyMaterialId;
		this.subjectName = subjectName;
		this.topicName = topicName;
		this.description = description;
		this.links = links;
	}

	public int getStudyMaterialId() {
		return studyMaterialId;
	}

	public void setStudyMaterialId(int studyMaterialId) {
		this.studyMaterialId = studyMaterialId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Links> getLinks() {
		return links;
	}

	public void setLinks(List<Links> links) {
		this.links = links;
	}

	public Users getSuperAdminId() {
		return superAdminId;
	}

	public void setSuperAdminId(Users superAdminId) {
		this.superAdminId = superAdminId;
	}

}
