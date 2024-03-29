package com.placementcell.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.placementcell.dto.PlacedStudentResponse;
import com.placementcell.entities.CompanyDetails;
import com.placementcell.entities.PlacedStudent;
import com.placementcell.exceptions.UserExceptions;
import com.placementcell.repository.CompanyDetailsRepository;
import com.placementcell.repository.PlacedStudentRepositroy;
import com.placementcell.request.PlacedStudentRequest;

@Service
public class PlacedStudentService {

	@Autowired
	private PlacedStudentRepositroy placedStudentRepositroy;

	@Autowired
	private CompanyDetailsRepository companyDetailsRepository;

	public PlacedStudent addPlacedStudent(PlacedStudentRequest placedStudentRequest) {
		CompanyDetails companyDetails = companyDetailsRepository
				.findCompanyObjectByName(placedStudentRequest.getCompanyName());
		PlacedStudent placedStudent = new PlacedStudent();
		placedStudent.setFirstName(placedStudentRequest.getFirstName());
		placedStudent.setLastName(placedStudentRequest.getLastName());
		placedStudent.setEmail(placedStudentRequest.getEmail());
		placedStudent.setLinkedin(placedStudentRequest.getLinkedin());
		placedStudent.setImage(placedStudentRequest.getImage());
		placedStudent.setDate(placedStudentRequest.getDate());
		placedStudent.setCompanyDetailsPlaced(companyDetails);
		return placedStudentRepositroy.save(placedStudent);

	}

	public PlacedStudent updatePlacedStudent(PlacedStudentRequest placedStudentRequest) throws UserExceptions {
		CompanyDetails companyDetails = companyDetailsRepository
				.findCompanyObjectByName(placedStudentRequest.getCompanyName());
		PlacedStudent placedStudent = placedStudentRepositroy.findById(placedStudentRequest.getId())
				.orElseThrow(() -> new UserExceptions("user not found"));
		placedStudent.setCompanyDetailsPlaced(companyDetails);
		placedStudent.setDate(placedStudentRequest.getDate());
		placedStudent.setEmail(placedStudentRequest.getEmail());
		placedStudent.setFirstName(placedStudentRequest.getFirstName());
		placedStudent.setImage(placedStudentRequest.getImage());
		placedStudent.setLastName(placedStudentRequest.getLastName());
		placedStudent.setLinkedin(placedStudentRequest.getLinkedin());
		return placedStudentRepositroy.save(placedStudent);
	}
	
	public List<PlacedStudent> getAll(){
		return placedStudentRepositroy.findAll();
	}
	
	public void delete(int id) throws UserExceptions {
	    try {
	        placedStudentRepositroy.deleteById(id);
	    } catch (Exception e) {
	        // You can log the exception or handle it as needed.
	        // For now, let's throw a UserExceptions with a custom message.
	        throw new UserExceptions("user not found");
	    }
	}


}
