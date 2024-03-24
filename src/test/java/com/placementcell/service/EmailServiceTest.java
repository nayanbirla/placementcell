package com.placementcell.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import com.placementcell.dto.OtpTokenSenderResponse;
import com.placementcell.request.EmailData;
import com.placementcell.services.EmailService;
import com.placementcell.services.JwtService;

@SpringBootTest
public class EmailServiceTest {
	
	@InjectMocks
	EmailService emailService;
	
	@Mock
	JwtService jwtService;
	
	@Mock
	MailSender mailSender;
	
	@Test
	public void EmailServiceTestValidateEmail() {
		
		EmailData emailData=new EmailData("abc");
		OtpTokenSenderResponse otpTokenSenderResponse= emailService.sendEmail(emailData);
		assertEquals(false,otpTokenSenderResponse.isStatus());
	}
	
	@Test
	public void EmailServiceTestData() {
		EmailData emailData=new EmailData("nayanbirla9893@gmail.com");
		SimpleMailMessage message = new SimpleMailMessage();
//		when(mailSender.send(message))
		doNothing().when(mailSender).send(message);
		when(jwtService.generateTokenWithOtp("nayanbirla9893@gmail.com",123 + "")).thenReturn("123");
		OtpTokenSenderResponse otpTokenSenderResponse= emailService.sendEmail(emailData);
		assertEquals(true,otpTokenSenderResponse.isStatus());
	}
}
