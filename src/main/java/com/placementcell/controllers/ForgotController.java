package com.placementcell.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.placementcell.dto.OtpReceivedResponse;
import com.placementcell.dto.OtpTokenSenderResponse;
import com.placementcell.exceptions.InvalidEmailException;
import com.placementcell.request.EmailData;
import com.placementcell.request.OtpPasswordData;
import com.placementcell.services.EmailService;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/forgot")
public class ForgotController {

	@Autowired
	private EmailService emailService;
	
	@PostMapping("/otpsend")
	public ResponseEntity<?> sendOtp(@RequestBody EmailData email)
	{
		OtpTokenSenderResponse otpTokenSenderResponse=emailService.sendEmail(email);
		if(otpTokenSenderResponse.isStatus())
		{
			return new ResponseEntity<OtpTokenSenderResponse>(otpTokenSenderResponse,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<OtpTokenSenderResponse>(otpTokenSenderResponse,HttpStatus.BAD_REQUEST);
		}
	}
	// otp and password
	@PostMapping("/otpreceive")
	public ResponseEntity<OtpReceivedResponse> receiveOtp(@RequestBody OtpPasswordData otpPasswordData)
	{
		try {
		OtpReceivedResponse otpReceivedResponse =emailService.verifyOTP(otpPasswordData);
		if(otpReceivedResponse.isStatus())
		return new ResponseEntity<OtpReceivedResponse>(otpReceivedResponse,HttpStatus.OK);
		else
		return new ResponseEntity<OtpReceivedResponse>(otpReceivedResponse,HttpStatus.BAD_REQUEST);	
		}catch(Exception message)
		{
			return new ResponseEntity<OtpReceivedResponse>(new OtpReceivedResponse(false,message.getMessage()),HttpStatus.BAD_REQUEST); 
		}
	}
	
	
}
