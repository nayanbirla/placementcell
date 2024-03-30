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

import com.placementcell.dto.StudyMaterialResponse;
import com.placementcell.request.StudyMaterialRequest;
import com.placementcell.services.JwtService;
import com.placementcell.services.StudyMaterialService;
import com.placementcell.utility.Message;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/studymaterial")
@CrossOrigin
public class StudyMaterialController {

	@Autowired
	private StudyMaterialService studyMaterialService;

	@Autowired
	private JwtService jwtService;

	@PostMapping("/add")
	public ResponseEntity<StudyMaterialResponse> add(@RequestBody StudyMaterialRequest studyMaterialRequest,
			HttpServletRequest request) {
		String token = jwtService.extractToken(request);
		return new ResponseEntity<StudyMaterialResponse>(
				studyMaterialService.addStudyMaterial(studyMaterialRequest, token), HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<StudyMaterialResponse> update(@RequestBody StudyMaterialRequest studyMaterialRequest) {
		return new ResponseEntity<StudyMaterialResponse>(studyMaterialService.updateStudyMaterial(studyMaterialRequest),
				HttpStatus.OK);
	}

	@GetMapping("/getall")
	public ResponseEntity<List<StudyMaterialResponse>> getAll() {
		return new ResponseEntity<List<StudyMaterialResponse>>(studyMaterialService.getAll(), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Message> delete(@PathVariable int id) {
		return new ResponseEntity<Message>(studyMaterialService.delete(id), HttpStatus.OK);
	}
}
