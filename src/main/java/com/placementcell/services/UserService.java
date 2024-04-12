package com.placementcell.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.placementcell.dto.UserInfoDetailsList;
import com.placementcell.dto.UsersInfoDetails;
import com.placementcell.entities.EducationDetails;
import com.placementcell.entities.Users;
import com.placementcell.entities.UsersDetails;
import com.placementcell.exceptions.InvalidCourseException;
import com.placementcell.exceptions.InvalidEmailException;
import com.placementcell.exceptions.UserNotFoundException;
import com.placementcell.repository.CourseRepository;
import com.placementcell.repository.UserRepository;
import com.placementcell.request.UserRequestData;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CourseRepository courseRepository;

	public List<UsersInfoDetails> getAllForAdmin() {
		List<UsersInfoDetails> allStudents= userRepository.findAllUserInfoDetails();
//		List<UserInfoDetailsList> userInfoDetailsLists=new ArrayList<>();
		
		return allStudents;
	}

	public Users add(Users users) {
		return userRepository.save(users);
	}

	public List<Users> addAll(List<Users> users) {
		return userRepository.saveAll(users);
	}

	public Users get(int id) {
		return userRepository.findById(id).orElseThrow();
	}

	public Users getByEmail(String email) {
		return userRepository.FindByEmail(email).get();
	}

	public Users saveUserRequestData(UserRequestData userRequestData)
			throws UserNotFoundException, InvalidEmailException, InvalidCourseException {
		Users existingUser = userRepository.FindByEmail(userRequestData.getEmail())
				.orElseThrow(() -> new UserNotFoundException("User Not Found"));
		if (!(existingUser.getEmail().equals(userRequestData.getEmail()))) {
			throw new InvalidEmailException("User Mail Not Found");
		}
		UsersDetails userDetails = existingUser.getUsersDetails();
		if (userDetails == null) {
			userDetails = new UsersDetails();
		}

		userDetails.setUsers(existingUser);
		userDetails.setFirstName(userRequestData.getFirstName());
		userDetails.setMiddleName(userRequestData.getMiddleName());
		userDetails.setLastName(userRequestData.getLastName());
		userDetails.setPersonalEmail(userRequestData.getPersonalEmail());
		userDetails.setGender(userRequestData.getGender());
		userDetails.setContact(userRequestData.getContact());
		userDetails.setImage(userRequestData.getImage());
		if (userRequestData.getCourse() != null && !userRequestData.getCourse().equals("")) {
			try {
				int id = courseRepository.findByName(userRequestData.getCourse());
				userDetails.setCourse(courseRepository.findById(id).get());
			} catch (Exception ee) {
				throw new InvalidCourseException("Course Not Exist");
			}

		}
		existingUser.setUsersDetails(userDetails);
		EducationDetails educationDetails = existingUser.getEducationDetails();
		if (educationDetails == null) {
			educationDetails = new EducationDetails();
		}
		educationDetails.setUsers(existingUser);
		educationDetails.setTenthPercentage(userRequestData.getTenthPercentage());
		educationDetails.setTwelthPercentage(userRequestData.getTwelthPercentage());
		educationDetails.setUg(userRequestData.getUg());
		educationDetails.setPg(userRequestData.getPg());

		existingUser.setEducationDetails(educationDetails);
//		System.out.println(existingUser);
//		System.out.println(existingUser.getUsersDetails());
		userRepository.save(existingUser);

		return existingUser;
	}
	
	
}
