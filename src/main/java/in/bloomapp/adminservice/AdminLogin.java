package in.bloomapp.adminservice;

public class AdminLogin {
	
	private AdminLogin() {	
	}
	/**
	 * Checks weather the user name and password is valid
	 * @param username
	 * @param password
	 * @return
	 */
	public static boolean login(String username, String password) {
		boolean valid=false;
		if(username.equalsIgnoreCase("admin")&& password.equals("admin123")){
			valid=true;
		}
		return valid;
	}
}
