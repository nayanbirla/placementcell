package com.placementcell.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.placementcell.dto.UserInfoDetailsList;
import com.placementcell.dto.UsersInfoDetails;
import com.placementcell.services.UserService;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserService userService;
	// View All Students
	@GetMapping("/allstudents")
	public ResponseEntity<List<UsersInfoDetails>> getAllStudents(){
		return new ResponseEntity<List<UsersInfoDetails>>(userService.getAllForAdmin(),HttpStatus.OK);
	}
}
