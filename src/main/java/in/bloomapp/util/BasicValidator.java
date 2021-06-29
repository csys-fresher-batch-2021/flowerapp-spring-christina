package in.bloomapp.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.bloomapp.exception.InvalidInputException;

public class BasicValidator {
	
	private BasicValidator(){
		//Default constructor
	}
	/**
	 * Checks weather the string is a valid string or not
	 * @param checkString
	 * @return
	 * @throws InvalidInputException 
	 */
	public static boolean isValidString(String checkString) throws InvalidInputException {
	boolean validity=false;
	if (checkString.trim().equals("")) {
		throw new InvalidInputException("Invalid String");
	}
	else {
		validity=true;
		return validity;
	}
	
}
	
	
	/**
	 * Checks if the the string contains only valid characters
	 * @param checkString
	 * @return
	 * @throws InvalidInputException 
	 */
	public static boolean isCharAllowed(String checkString) throws InvalidInputException {
		boolean isValid = false;
		int i = 0;
		while ((i <= checkString.length() - 1) && (checkString.length() >= 3)) {
			if ((checkString.charAt(i) >= 'A' && checkString.charAt(i) <= 'Z')
					|| (checkString.charAt(i) >= 'a' && checkString.charAt(i) <= 'z') ||
					(checkString.charAt(i) >= '0' && checkString.charAt(i) <= '9')
					||checkString.charAt(i) == ' ') {
				isValid = true;	
				
			}
			else {
				 throw new InvalidInputException("Invalid string");
			}
			i++;
		}
		return isValid;
	}
	
	/**
	 * Checks if the give email id is valid
	 * @param email
	 * @throws InvalidInputException
	 */
	public static void isValidEmail(String email) throws InvalidInputException {
		 String regex = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		 Pattern pattern = Pattern.compile(regex);  
		   Matcher matcher = pattern.matcher(email);  
		  boolean status= matcher.matches();
		   if( !status) {
			   throw new InvalidInputException("Please enter valid email");
		   }
	}
	
	/**
	 * Checks if the given mobile number is a valid mobile number
	 * @param mobileNo
	 * @throws InvalidInputException
	 */
	public static void isValidMobileNo(Long mobileNo) throws InvalidInputException {
		String mobileNum=mobileNo.toString();
		 String regex = "(0/91)?[6-9][0-9]{9}";
	      Pattern pattern = Pattern.compile(regex);  
		   Matcher matcher = pattern.matcher(mobileNum);  
		   boolean status= matcher.matches();
		   if( !status) {
			   throw new InvalidInputException("Please enter valid Mobile number");
		   }
	}
	
	/**
	 * Checks weather the given password meets the given level constrains
	 * Password should contain a Capital letter,small letter ,Special Character and a number.
	 * @param password
	 * @throws InvalidInputException
	 */
	public static void isValidPassword(String password) throws InvalidInputException {
	
		String regex = "^(?=.*[0-9])"
				               + "(?=.*[a-z])(?=.*[A-Z])"
			                   + "(?=.*[@#$%^&+=])"
			                   + "(?=\\S+$).{8,20}$";
     	 Pattern pattern = Pattern.compile(regex);  
		   Matcher matcher = pattern.matcher(password);  
		   boolean status= matcher.matches();
		   if( !status) {
			   throw new InvalidInputException("Please enter valid password");
		   }
	}	
}