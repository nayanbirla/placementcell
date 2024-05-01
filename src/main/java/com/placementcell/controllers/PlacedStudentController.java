package com.placementcell.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.placementcell.dto.PlacedStudentDTO;
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
    @Value("${project.image}")
	private String path;
	
	@PostMapping("/add")
	public ResponseEntity<PlacedStudent> add(@ModelAttribute PlacedStudentRequest placedStudentRequest, @RequestParam("file") MultipartFile file) throws IOException {
		return new ResponseEntity<PlacedStudent>(placedStudentService.addPlacedStudent(placedStudentRequest,file,path),
				HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<?> update(@ModelAttribute PlacedStudentRequest placedStudentRequest, @RequestParam("file") MultipartFile file) throws IOException {
		try {
			return new ResponseEntity<PlacedStudent>(placedStudentService.updatePlacedStudent(placedStudentRequest,file,path),
					HttpStatus.OK);
		} catch (UserExceptions userExceptions) {
			return new ResponseEntity<Message>(new Message(userExceptions.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/getall")
	public ResponseEntity<List<PlacedStudentDTO>> getAll() {
		return new ResponseEntity<List<PlacedStudentDTO>>(placedStudentService.getAll(), HttpStatus.OK);
	}


	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Message> delete(@PathVariable int id) {
		try {
			placedStudentService.delete(id);
			return new ResponseEntity<Message>(new Message("Placed Student Deleted Successfully"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Message>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/download/{id}")
	public ResponseEntity<Resource> download(@PathVariable int id) throws FileNotFoundException{
		return placedStudentService.download(id,path);
	}

}
