package com.placementcell.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Links {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String link;
	
	@ManyToOne
	@JsonBackReference
	private StudyMaterial studyMaterial;

	public Links() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Links(int id, String link, StudyMaterial studyMaterial) {
		super();
		this.id = id;
		this.link = link;
		this.studyMaterial = studyMaterial;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public StudyMaterial getStudyMaterial() {
		return studyMaterial;
	}

	public void setStudyMaterial(StudyMaterial studyMaterial) {
		this.studyMaterial = studyMaterial;
	} 
	
	
}
