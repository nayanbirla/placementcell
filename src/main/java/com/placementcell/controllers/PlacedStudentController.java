package com.placementcell.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.placementcell.entities.PlacedStudent;
import com.placementcell.exceptions.UserExceptions;
import com.placementcell.request.PlacedStudentRequest;
import com.placementcell.services.PlacedStudentService;
import com.placementcell.utility.Message;

@CrossOrigin
@RestController
@RequestMapping("/placed")
public class PlacedStudentController {

	@Autowired
	private PlacedStudentService placedStudentService;

	@PostMapping("/add")
	public ResponseEntity<PlacedStudent> add(@RequestBody PlacedStudentRequest placedStudentRequest) {
		return new ResponseEntity<PlacedStudent>(placedStudentService.addPlacedStudent(placedStudentRequest),
				HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody PlacedStudentRequest placedStudentRequest) {
		try {
			return new ResponseEntity<PlacedStudent>(placedStudentService.updatePlacedStudent(placedStudentRequest),
					HttpStatus.OK);
		} catch (UserExceptions userExceptions) {
			return new ResponseEntity<Message>(new Message(userExceptions.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/getall")
	public ResponseEntity<List<PlacedStudent>> getAll() {
		return new ResponseEntity<List<PlacedStudent>>(placedStudentService.getAll(), HttpStatus.OK);
	}


	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Message> delete(@PathVariable int id) {
		try {
			return new ResponseEntity<Message>(new Message("Placed Student Deleted Successfully"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Message>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

}
