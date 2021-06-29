package in.bloomapp.exception;

public class InvalidInputException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * if the string contains invalid characters this type of exception is used
	 * 
	 * @param message
	 */
	public InvalidInputException(String message) {
		super(message);
	}

	/**
	 * if the string contains invalid characters this type of exception is used and
	 * to pass a message in its parameter
	 * 
	 * @param e
	 * @param message
	 */
	public InvalidInputException(Exception e, String message) {
		super(message, e);
	}
}
