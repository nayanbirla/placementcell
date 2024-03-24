package com.placementcell.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.placementcell.constants.EmailConstants;
import com.placementcell.dto.OtpReceivedResponse;
import com.placementcell.dto.OtpTokenSenderResponse;
import com.placementcell.entities.Users;
import com.placementcell.exceptions.InvalidEmailException;
import com.placementcell.exceptions.UserNotFoundException;
import com.placementcell.repository.UserRepository;
import com.placementcell.request.EmailData;
import com.placementcell.request.OtpPasswordData;
import com.placementcell.utility.EmailValidation;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserInfoServices userInfoServices;

	public OtpTokenSenderResponse sendEmail(EmailData to) {
		try {
			if (!EmailValidation.validate(to.getEmail())) {
				throw new InvalidEmailException("Invalid Email");
			}
			// one logic needs to check that
			// check user email exist in database or not
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(EmailConstants.Email);
			message.setTo(to.getEmail());
			int otpLength = 6;
			// Define the range for the OTP (6-digit number)
			int min = (int) Math.pow(10, otpLength - 1);
			int max = (int) Math.pow(10, otpLength) - 1;
			// Generate a random number within the defined range
			Random random = new Random();
			int generatedOTP = random.nextInt(max - min + 1) + min;

			message.setSubject(EmailConstants.EmailSubject);
			message.setText(EmailConstants.emailBodyBeforeOTP + generatedOTP + EmailConstants.emailBodyAfterOTP);
			mailSender.send(message);

			String otpToken = jwtService.generateTokenWithOtp(to.getEmail(), generatedOTP + "");

			OtpTokenSenderResponse otpTokenSenderResponse = new OtpTokenSenderResponse(otpToken, true);
			return otpTokenSenderResponse;

		} catch (Exception e) {
			return new OtpTokenSenderResponse("Ivalid Email", false);
		}
	}

	public OtpReceivedResponse verifyOTP(OtpPasswordData otpPasswordData) throws UserNotFoundException {

		String otpsend = jwtService.extractOtp(otpPasswordData.getToken());
		String otpreceived = otpPasswordData.getOtp();
		if (otpsend.equals(otpreceived)) {
			String email = jwtService.extractUserName(otpPasswordData.getToken());
			String password = otpPasswordData.getPassword();

			Users user = userRepository.FindByEmail(email)
					.orElseThrow(() -> new UserNotFoundException("User Not Found"));

			user.setPassword(password);
			if (user.getRole() == null)
				user.setRole("ROLE_USER");
			userInfoServices.addUser(user);
			return new OtpReceivedResponse(true, "Your password has been changed successfully");
		}

		return new OtpReceivedResponse(false, "Wrong Otp");
	}

}
