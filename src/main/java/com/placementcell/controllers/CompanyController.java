package com.placementcell.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.placementcell.entities.CompanyDetails;
import com.placementcell.services.CompanyDetailsService;
import com.placementcell.services.JwtService;
import com.placementcell.utility.Message;

import io.jsonwebtoken.Header;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	private CompanyDetailsService companyDetailsService;

	@Autowired
	private JwtService jwtService;

	@GetMapping("/getall")
	public ResponseEntity<List<CompanyDetails>> getAll() {
		return new ResponseEntity<List<CompanyDetails>>(companyDetailsService.getAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CompanyDetails> get(@PathVariable int id) {
		return new ResponseEntity<CompanyDetails>(companyDetailsService.get(id), HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<CompanyDetails> add(@RequestBody CompanyDetails companyDetails, HttpServletRequest request) {
		String token = jwtService.extractToken(request);

		return new ResponseEntity<CompanyDetails>(companyDetailsService.add(companyDetails, token), HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody CompanyDetails companyDetails) {
		try {
			return new ResponseEntity<CompanyDetails>(companyDetailsService.update(companyDetails), HttpStatus.OK);
		} catch (Exception ee) {
			Message message = new Message(ee.getMessage());
			return new ResponseEntity<Message>(message, HttpStatus.BAD_REQUEST);
		}
	}

}
