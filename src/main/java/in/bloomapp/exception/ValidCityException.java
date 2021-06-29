package in.bloomapp.exception;

public class ValidCityException extends Exception {	
	private static final long serialVersionUID = 1L;
	/**
	 * This exception specially thrown when error in validation of city
	 * @param message
	 */
	public ValidCityException(String message) {
		super(message);
	}
	/**
	 *  This exception specially thrown when error in validation of city
	 * we can also pass a exception message in parameter
	 * @param e
	 * @param message
	 */
	public ValidCityException(Exception e,String message) {
		super(message, e);
	}

}
