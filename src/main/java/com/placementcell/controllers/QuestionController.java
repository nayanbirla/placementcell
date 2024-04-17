package com.placementcell.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.placementcell.exceptions.InvalidInputException;
import com.placementcell.request.QuestionRequestData;
import com.placementcell.request.QuestionUpdateRequest;
import com.placementcell.services.QuestionService;
import com.placementcell.utility.Message;

@CrossOrigin
@RestController
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@PostMapping("/add")
	public ResponseEntity<Message> addQuestions(@RequestBody QuestionRequestData data) {
		try {
			boolean status = questionService.addQuestions(data);
			// currently it sends only true so we are not handling true false condition
			Message message = new Message();
			message.setMessage("Questions Added Successfully");
			return new ResponseEntity<Message>(message, HttpStatus.OK);
		} catch (Exception ee) {
			Message message = new Message();
			message.setMessage("Company Not Found");
			return new ResponseEntity<Message>(message, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<Message> updateQuestion(@RequestBody QuestionUpdateRequest questionUpdateRequest)
	{
		try {
			questionService.updateQuestion(questionUpdateRequest);
			return new ResponseEntity<Message>(new Message("Question Updated Successfully"),HttpStatus.OK);
		}catch(InvalidInputException exception)
		{
			return new ResponseEntity<Message>(new Message(exception.getMessage()),HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Message> deleteQuestion(@PathVariable("id") int id)
	{
		try {
			questionService.deleteQuestion(id);
			return new ResponseEntity<Message>(new Message("Question Deleted Successfully"),HttpStatus.OK);
		}catch(InvalidInputException exception)
		{
			return new ResponseEntity<Message>(new Message(exception.getMessage()),HttpStatus.BAD_REQUEST);
		}
	}
	
}
