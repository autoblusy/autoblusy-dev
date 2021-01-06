package com.spring.WEB2.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class StringUtils {
	
	
	public static boolean compareForgotPasswords(String testValue1, String testValue2, String userValue1, String userValue2) {
		if(testValue1.equalsIgnoreCase(userValue1)) {
			if(testValue2.equalsIgnoreCase(userValue2)) {
				return true;
			}
		}
		
		return false;
	}
	
	
	public static String createPasswordWithSecurity(String password) {
		if(password.length() < 20) {
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
			String encodedPassword = passwordEncoder.encode(password);
			return encodedPassword;
		}
		return password;
	}
}
