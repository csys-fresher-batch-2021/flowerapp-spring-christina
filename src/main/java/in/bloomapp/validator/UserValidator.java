package in.bloomapp.validator;

import java.util.List;
import in.bloomapp.dao.UserManagerDAO;
import in.bloomapp.exception.DBException;
import in.bloomapp.exception.UserValidationException;
import in.bloomapp.model.User;

public class UserValidator {
	
	private UserValidator() {
		
	}
	/**
	 * Checks if the given user is already available in the data
	 * @param userName
	 * @param password
	 * @return
	 * @throws DBException
	 */
	public static boolean userIsExist(String userName,String password) throws DBException {
		boolean status=false;
		UserManagerDAO userManagerDAO=new UserManagerDAO();
		final List<User> users = userManagerDAO.get();
		for(User user : users) {
		
			if (user.getName().equalsIgnoreCase(userName) && user.getPassword().equals(password)) {
				status=true;
			}
		}
		return status;
	}
	
	/**
	 * Checks if the user is already registered or not
	 * @param mobileNo
	 * @throws DBException
	 * @throws UserValidationException
	 */
	public static void isAlreadyRegistered(Long mobileNo) throws DBException, UserValidationException {
		UserManagerDAO userManagerDAO=new UserManagerDAO();
		final List<User> users = userManagerDAO.get();
		for(User user : users) {		
			if (mobileNo==user.getMobileNo()) {
				throw new UserValidationException("Mobile number already registered");
			}
		}
	}
}
