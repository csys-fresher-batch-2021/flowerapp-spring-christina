package in.bloomapp.exception;

public class DBException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * This exception specially thrown when error in Database and its connections
	 * 
	 * @param message
	 */
	public DBException(String message) {
		super(message);
	}

	/**
	 * This exception specially thrown when error in Database and its connections we
	 * can also pass a exception message in parameter
	 * 
	 * @param e
	 * @param message
	 */
	public DBException(Exception e, String message) {
		super(message, e);
	}

}
