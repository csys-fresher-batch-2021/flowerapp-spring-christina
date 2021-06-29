package in.bloomapp.exception;

public class UserValidationException extends Exception {

	private static final long serialVersionUID = 1L;
	/**
	 * this exception is thrown when the user dose'nt meet the BasicValidator constrains.
	 * @param message
	 */
	public UserValidationException(String message) {
		super(message);
	}
	/**
	 *  this exception is thrown when the user dose'nt meet the BasicValidator constrains and can also add a exception 
	 *  message in parameter
	 * @param e
	 * @param message
	 */
	public UserValidationException(Exception e,String message) {
		super(message, e);
	}
}
