package com.placementcell.request;

import java.util.List;

public class StudyMaterialRequest {

	private int id;
	private String subjectName;
	private String topicName;
	private String description;
	private List<String> links;

	public StudyMaterialRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudyMaterialRequest(int id, String subjectName, String topicName, String description, List<String> links) {
		super();
		this.id = id;
		this.subjectName = subjectName;
		this.topicName = topicName;
		this.description = description;
		this.links = links;
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

	public List<String> getLinks() {
		return links;
	}

	public void setLinks(List<String> links) {
		this.links = links;
	}

}
