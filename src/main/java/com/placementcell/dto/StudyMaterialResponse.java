package com.placementcell.dto;

import java.util.List;

public class StudyMaterialResponse {

	private int id;
	private String subjectName;
	private String topicName;
	private String description;
	private List<String> links;
	private String adderName;

	public StudyMaterialResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudyMaterialResponse(int id, String subjectName, String topicName, String description, String adderName,List<String> links) {
		super();
		this.id = id;
		this.subjectName = subjectName;
		this.topicName = topicName;
		this.description = description;
		this.adderName = adderName;
		this.links=links;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getAdderName() {
		return adderName;
	}

	public void setAdderName(String adderName) {
		this.adderName = adderName;
	}

	public List<String> getLinks() {
		return links;
	}

	public void setLinks(List<String> links) {
		this.links = links;
	}
	
	

}
