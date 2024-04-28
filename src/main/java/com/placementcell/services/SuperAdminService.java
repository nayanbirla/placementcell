package com.placementcell.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.management.relation.RoleNotFoundException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.placementcell.dto.EmailInvalid;
import com.placementcell.entities.Users;
import com.placementcell.exceptions.InvalidExcelException;
import com.placementcell.exceptions.UserNotFoundException;
import com.placementcell.repository.UserRepository;
import com.placementcell.request.RoleChangingRequest;
import com.placementcell.utility.EmailValidation;
import com.placementcell.utility.Message;

@Service
public class SuperAdminService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;

	public EmailInvalid addEmailsFromExcel(MultipartFile file) throws InvalidExcelException {
		List<String> invalidEmails = new ArrayList<>();
		List<String> validEmails = new ArrayList<>();
		List<String> emailAlreadyExist=new ArrayList<>(); 
		try {
			Workbook workbook = new XSSFWorkbook(file.getInputStream());
			Sheet sheet = workbook.getSheetAt(0); // Assuming the first sheet
			int emailColumnIndex = -1;

			// Find the column index of the email column
			Row headerRow = sheet.getRow(0);
			for (int i = 0; i < headerRow.getLastCellNum(); i++) {
				Cell cell = headerRow.getCell(i);
				if (cell != null && "email".equalsIgnoreCase(cell.getStringCellValue().trim())) {
					emailColumnIndex = i;
					break;
				}
			}

			if (emailColumnIndex != -1) {
				// Iterate through each row and validate the email column
				for (int i = 1; i <= sheet.getLastRowNum(); i++) {
					Row row = sheet.getRow(i);
					if (row != null) {
						Cell emailCell = row.getCell(emailColumnIndex);
						if (emailCell != null) {
							String email = emailCell.getStringCellValue().trim();
							if (!EmailValidation.validate(email)) {
								invalidEmails.add(email);
							} else {
								validEmails.add(email);
							}
						}
					}
				}
			}
			System.out.println(validEmails);
			workbook.close();
			List<Users> users = new ArrayList<>();
            
			for (String email : validEmails) {
				int count = userRepository.checkEmailExistance(email);
				if (count == 0) {
//					System.out.println("nhi hai re");
					Users newUser = new Users();
					newUser.setEmail(email);
					users.add(newUser);
				}else {
					emailAlreadyExist.add(email);
				}
			}
			users = userRepository.saveAll(users);
//			System.out.print(users);
		} catch (IOException e) {
			throw new InvalidExcelException("Excel file is not valid");
			// Handle the IOException
		}
		
		return new EmailInvalid(invalidEmails, emailAlreadyExist);
	}

	public Message changeRole(RoleChangingRequest roleChangingRequest)
			throws UserNotFoundException, RoleNotFoundException {
		Users users = userRepository.FindByEmail(roleChangingRequest.getEmail())
				.orElseThrow(() -> new UserNotFoundException("User not found"));
		String role = roleChangingRequest.getRole().toUpperCase();
		System.out.print(role);
		if (!role.equals("USER") && !role.equals("ADMIN") && !role.equals("SUPERADMIN")) {
			throw new RoleNotFoundException("Invalid Role");
		}

		users.setRole("ROLE_" + role);
		userRepository.save(users);
		return new Message("Role Has Been Changed");
	}
}
