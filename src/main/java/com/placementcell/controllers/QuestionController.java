package com.placementcell.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.placementcell.request.QuestionRequestData;
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
	
	
}
