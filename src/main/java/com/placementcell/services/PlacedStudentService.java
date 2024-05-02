package com.placementcell.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.placementcell.dto.PlacedStudentDTO;
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

	public PlacedStudent addPlacedStudent(PlacedStudentRequest placedStudentRequest, MultipartFile file, String path)
			throws IOException {
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
		placedStudent.setCtc(placedStudentRequest.getCtc());
		

		// Setting up the path of the file
		String uuid = UUID.randomUUID().toString();
		 
		// Get the file extension from the original filename (if needed)
		String originalFilename = file.getOriginalFilename();
		String fileExtension = "";
		if (originalFilename != null) {
		    int extensionIndex = originalFilename.lastIndexOf(".");
		    if (extensionIndex != -1) {
		        fileExtension = originalFilename.substring(extensionIndex);
		    }
		}

		// Construct the new filename using the UUID and file extension
		String newFilename = uuid + fileExtension;
		placedStudent.setOfferLetter(newFilename);  
		// Construct the full file path with the new filename
		String filePath = path + File.separator + newFilename;

		File f = new File(path);
		if (!f.exists()) {
			f.mkdir();
		}
		Files.copy(file.getInputStream(), Paths.get(filePath));
		return placedStudentRepositroy.save(placedStudent);

	}

	public PlacedStudent updatePlacedStudent(PlacedStudentRequest placedStudentRequest)
			throws UserExceptions, IOException {
		CompanyDetails companyDetails = companyDetailsRepository
				.findCompanyObjectByName(placedStudentRequest.getCompanyName());
		PlacedStudent placedStudent = placedStudentRepositroy.findById(placedStudentRequest.getId())
				.orElseThrow(() -> new UserExceptions("user not found"));

//		if (file != null) {
//
//			String fileName = placedStudent.getOfferLetter(); // Assuming this is the file name
//			String filePath = path + File.separator + fileName;
//
//			// Create a File object representing the file
//			File fileToDelete = new File(filePath);
//
//			// Check if the file exists before attempting to delete
//			if (fileToDelete.exists()) {
//				// Attempt to delete the file
//				if (fileToDelete.delete()) {
//					System.out.println("File deleted successfully.");
//				} else {
//					System.out.println("Failed to delete the file.");
//				}
//			} else {
//				System.out.println("File does not exist.");
//			}
//
//			String uuid = UUID.randomUUID().toString();
//
//			// Get the file extension from the original filename (if needed)
//			String originalFilename = file.getOriginalFilename();
//			String fileExtension = "";
//			if (originalFilename != null) {
//			    int extensionIndex = originalFilename.lastIndexOf(".");
//			    if (extensionIndex != -1) {
//			        fileExtension = originalFilename.substring(extensionIndex);
//			    }
//			}
//
//			// Construct the new filename using the UUID and file extension
//			String newFilename = uuid + fileExtension;
//
//			// Construct the full file path with the new filename
//			String filePath1 = path + File.separator + newFilename;
//
//
//			File f = new File(path);
//			if (!f.exists()) {
//				f.mkdir();
//			}
//			Files.copy(file.getInputStream(), Paths.get(filePath1));

		
		placedStudent.setCompanyDetailsPlaced(companyDetails);
		placedStudent.setDate(placedStudentRequest.getDate());
		placedStudent.setEmail(placedStudentRequest.getEmail());
		placedStudent.setFirstName(placedStudentRequest.getFirstName());
		placedStudent.setImage(placedStudentRequest.getImage());
		placedStudent.setLastName(placedStudentRequest.getLastName());
		placedStudent.setLinkedin(placedStudentRequest.getLinkedin());
		placedStudent.setCtc(placedStudentRequest.getCtc());
//		placedStudent.setOfferLetter(newFilename);
		
		return placedStudentRepositroy.save(placedStudent);
	}

	public List<PlacedStudentDTO> getAll() {
		return placedStudentRepositroy.findAllWithCompanyName();
	}

	public void delete(int id) throws UserExceptions {
		try {
			//placed student offer letter not deleted (need to implement)
			placedStudentRepositroy.deleteById(id);
		} catch (Exception e) {
			// You can log the exception or handle it as needed.
			// For now, let's throw a UserExceptions with a custom message.
			throw new UserExceptions("user not found");
		}
	}
	
	public ResponseEntity<Resource> download(int id,String path) throws FileNotFoundException {
		
		String fileName=placedStudentRepositroy.getFileName(id);
		
		String filePath=path+File.separator+fileName;
		
		File file = new File(filePath);
        FileInputStream fileInputStream = new FileInputStream(file);
        
        // Create an InputStreamResource from the file InputStream
        InputStreamResource resource = new InputStreamResource(fileInputStream);
        
        // Set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        
        // Build the response entity with the resource, headers, and content length
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);

		
			
	}

}
