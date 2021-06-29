 package in.bloomapp.model;

/**
 * Main model class with required field for an user
 * @author chri2631
 *
 */
public class User {
	
	private String name;
	private String password; 
	private String email;
	private Long mobileNo;
	private String address;
	
	public String getName() {
		return name;
	}
	public String getPassword() {
		return password;
	}
	public String getEmail() {
		return email;
	}
	public Long getMobileNo() {
		return mobileNo;
	}
	public String getAddress() {
		return address;
	}
	public void setName(String name) {
		this.name = name;
	}	
	public void setPassword(String password) {
		this.password = password;
	}	
	public void setEmail(String email) {
		this.email = email;
	}	
	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}	
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", mobileNo=" + mobileNo
				+ ", address=" + address + "]";
	}
}
