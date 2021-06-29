package in.bloomapp.exception;

public class ServiceException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * If a task cannot be completed because of error or exception this type of
	 * exception is used
	 * 
	 * @param message
	 */
	public ServiceException(String message) {
		super(message);
	}

	/**
	 * If a task cannot be completed because of error or exception this type of
	 * exception is used we can also pass a exception message in parameter
	 * 
	 * @param e
	 * @param message
	 */
	public ServiceException(Exception e, String message) {
		super(message, e);
	}
}
