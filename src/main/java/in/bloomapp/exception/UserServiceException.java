package in.bloomapp.exception;

@SuppressWarnings("serial")
public class UserServiceException extends Exception {
	/**
	 * this exception is thrown when any occurs during user management
	 * 
	 * @param message
	 */
	public UserServiceException(String message) {
		super(message);
	}

	/**
	 * this exception is thrown when any occurs during user management and can also
	 * add a exception message in parameter
	 * 
	 * @param e
	 * @param message
	 */
	public UserServiceException(Exception e, String message) {
		super(message, e);
	}
}
