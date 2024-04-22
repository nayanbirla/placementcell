package com.placementcell.controllers;

import java.util.List;

import javax.management.relation.RoleNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.placementcell.dto.EmailInvalid;
import com.placementcell.exceptions.UserNotFoundException;
import com.placementcell.request.RoleChangingRequest;
import com.placementcell.services.SuperAdminService;
import com.placementcell.utility.Message;

@RestController
@RequestMapping("/superadmin")
@CrossOrigin
public class SuperAdminController {

	@Autowired
	private SuperAdminService superAdminService;

	@PostMapping("/sendfile")
	public ResponseEntity<?> validateEmails(@RequestParam("file") MultipartFile file) {
		try {
			return new ResponseEntity<EmailInvalid>(superAdminService.addEmailsFromExcel(file), HttpStatus.OK);
		} catch (Exception ee) {
			return new ResponseEntity<Message>(new Message(ee.getMessage()), HttpStatus.OK);
		}
	}

	@PostMapping("/changerole")
	public ResponseEntity<Message> changeRole(@RequestBody RoleChangingRequest roleChangingRequest) {
		try {
			return new ResponseEntity<Message>(superAdminService.changeRole(roleChangingRequest), HttpStatus.OK);
		} catch (UserNotFoundException userNotFoundException) {
			return new ResponseEntity<Message>(new Message(userNotFoundException.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (RoleNotFoundException roleNotFoundException) {
			return new ResponseEntity<Message>(new Message(roleNotFoundException.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

}
