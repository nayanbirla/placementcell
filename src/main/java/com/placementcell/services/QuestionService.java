package com.placementcell.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.placementcell.entities.CompanyDetails;
import com.placementcell.entities.Questions;
import com.placementcell.exceptions.InvalidInputException;
import com.placementcell.repository.CompanyDetailsRepository;
import com.placementcell.repository.QuestionsRepository;
import com.placementcell.request.QuestionRequestData;
import com.placementcell.request.QuestionUpdateRequest;
import com.placementcell.utility.Message;

@Service
public class QuestionService {

	@Autowired
	private QuestionsRepository questionsRepository;

	@Autowired
	private CompanyDetailsRepository companyDetailsRepository;

	// method to add question
	// it contains company id
	// question set is an array
	public boolean addQuestions(QuestionRequestData questionRequestData) {

		CompanyDetails companyDetails = companyDetailsRepository
				.findCompanyObjectByName(questionRequestData.getCompanyName());

		List<Questions> questions = new ArrayList<>();
		for (String s : questionRequestData.getQuestions()) {
			Questions question = new Questions();
			question.setQuestionDetails(s);
			question.setCompanyDetails(companyDetails);
			questions.add(question);
		}
		questionsRepository.saveAllAndFlush(questions);
		return true;
	}

	public void updateQuestion(QuestionUpdateRequest questionUpdateRequest) throws InvalidInputException {
		if (questionUpdateRequest.getQuestion() == null) {
			throw new InvalidInputException("Question is Empty");
		}
		try {
			Questions questions = questionsRepository.findById(questionUpdateRequest.getId()).get();
			questions.setQuestionDetails(questionUpdateRequest.getQuestion());
			questionsRepository.save(questions);
		} catch (Exception exception) {
			throw new InvalidInputException("Invalid Question Id");
		}
	}
	
	public void deleteQuestion(int id) throws InvalidInputException
	{
		try {
		questionsRepository.deleteById(id);
		}catch(Exception e) {
			throw new InvalidInputException("Question not exist");
		}
	}

}
