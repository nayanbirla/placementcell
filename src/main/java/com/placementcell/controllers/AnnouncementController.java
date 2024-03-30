package com.placementcell.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.placementcell.dto.AnnouncementResponse;
import com.placementcell.entities.Announcement;
import com.placementcell.request.AnnouncementRequest;
import com.placementcell.services.AnnouncementService;
import com.placementcell.services.JwtService;

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
	public ResponseEntity<AnnouncementResponse> add(@RequestBody AnnouncementRequest announcementRequest,
			HttpServletRequest request) {

		String token = jwtService.extractToken(request);

		return new ResponseEntity<AnnouncementResponse>(announcementService.addAnnouncement(announcementRequest, token),
				HttpStatus.OK);

	}

	@PutMapping("/update")
	public ResponseEntity<AnnouncementResponse> update(@RequestBody AnnouncementRequest announcementRequest) {
		return new ResponseEntity<AnnouncementResponse>(announcementService.updateAnnouncement(announcementRequest),
				HttpStatus.OK);
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

}
