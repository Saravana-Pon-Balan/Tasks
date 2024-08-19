
import java.util.Scanner;
public class Employee {
	private String id;
	private String name;
	private String dob;
	private String mobileNumber;
	private String role;
	private String address;
	
	
	
	public Employee(String id, String name, String dob, String mobileNumber, String role, String address) {
		this.id = id;
		this.name = name;
		this.dob = dob;
		this.mobileNumber = mobileNumber;
		this.role = role;
		this.address = address;
	}
	public String getId() {
		return this.id;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDob() {
		return this.dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getMobileNumber() {
		return this.mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getRole() {
		return this.role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getAddress() {
		return this.address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	

}