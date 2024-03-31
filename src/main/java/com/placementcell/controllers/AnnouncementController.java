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
import com.placementcell.dto.AnnouncementResponse;
import com.placementcell.entities.Announcement;
import com.placementcell.exceptions.InvalidExpiryDateException;
import com.placementcell.request.AnnouncementRequest;
import com.placementcell.services.AnnouncementService;
import com.placementcell.services.JwtService;
import com.placementcell.utility.Message;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/announcement")
@CrossOrigin
public class AnnouncementController {

	@Autowired
	private AnnouncementService announcementService;

	@Autowired
	private JwtService jwtService;

	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody AnnouncementRequest announcementRequest, HttpServletRequest request) {

		String token = jwtService.extractToken(request);
		try {
			return new ResponseEntity<AnnouncementResponse>(
					announcementService.addAnnouncement(announcementRequest, token), HttpStatus.OK);
		} catch (InvalidExpiryDateException invalidExpiryDateException) {
			return new ResponseEntity<Message>(new Message(invalidExpiryDateException.getMessage()),
					HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody AnnouncementRequest announcementRequest) {
		try {
			return new ResponseEntity<AnnouncementResponse>(announcementService.updateAnnouncement(announcementRequest),
					HttpStatus.OK);
		} catch (InvalidExpiryDateException invalidExpiryDateException) {
			return new ResponseEntity<Message>(new Message(invalidExpiryDateException.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/getallactive")
	public ResponseEntity<List<AnnouncementResponse>> getAllActive() {
		return new ResponseEntity<List<AnnouncementResponse>>(announcementService.getAllActiveAnnouncement(),
				HttpStatus.OK);
	}

	@GetMapping("/getall")
	public ResponseEntity<List<Announcement>> getAll() {
		return new ResponseEntity<List<Announcement>>(announcementService.getAllAnnouncement(), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Message> delete(@PathVariable int id) {
		return new ResponseEntity<Message>(announcementService.delete(id), HttpStatus.OK);
	}

}
