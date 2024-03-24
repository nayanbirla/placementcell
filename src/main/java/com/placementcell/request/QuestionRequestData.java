package com.placementcell.request;

public class QuestionRequestData {

	private String companyName;
	private String[] questions;
	public QuestionRequestData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public QuestionRequestData(String companyName, String[] questions) {
		super();
		this.companyName = companyName;
		this.questions = questions;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String[] getQuestions() {
		return questions;
	}
	public void setQuestions(String[] questions) {
		this.questions = questions;
	}
	
	
}
