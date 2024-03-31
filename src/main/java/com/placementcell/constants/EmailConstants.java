package com.placementcell.constants;

import org.springframework.beans.factory.annotation.Value;

public class EmailConstants {

	public final static String EmailSubject = "OTP for Resetting Your SCSIT Placement Cell Password";

	public final static String emailBodyBeforeOTP = "You've requested to reset your password for your SCSIT Placement Cell account. To proceed, please use the following OTP (One-Time Password):\n\n";

	public final static String emailBodyAfterOTP = "\n\nPlease enter this OTP on the password reset page to verify your identity and proceed with setting a new password. Ensure that you keep this OTP confidential for security purposes.\n\n"
            + "If you haven't requested this password reset or have any concerns regarding your account security, please immediately contact Placement Cell Coordinator.\n\n"
            + "Thank you for your cooperation.\n\n"
            + "Best regards,\n"
            + "SCSIT Placement Cell Team";
	@Value("${emailSender}")
	public static String Email;

}
