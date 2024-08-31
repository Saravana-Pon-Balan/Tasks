package employeeManagement.passport;

import java.util.Date;

import employeeManagement.employee.Employee;

/**
 * It is represents an passport.
 * It associate with one employee.
 */
public class Passport {
  private int passportId;
  private Employee employee;
  private String placeOfBirth;
  private String passportNumber;
  private Date dateOfExpiry;
  
  public Passport(int id, Employee employee, String placeOfBirth, 
	                  String passportNumber, Date dateOfExpiry) {
    this.passportId = id;
    this.employee = employee;
    this.placeOfBirth = placeOfBirth;
    this.passportNumber = passportNumber;
    this.dateOfExpiry = dateOfExpiry;
  }  
  
  public int getId() {
    return this.passportId;
  }
	
	public void setId(int id) {
		this.passportId = id;
	}
  
  public void setEmployee(Employee employee) {
    this.employee = employee;
  }
  
  public Employee getEmployee() {
    return this.employee;
  }
  
  public void setPlaceOfBirth(String placeOfBirth) {
    this.placeOfBirth = placeOfBirth;
  }
  
  public String getPlaceOfBirth() {
    return this.placeOfBirth;
  }
  
  public void setPassportNumber(String passportNumber) {
    this.passportNumber = passportNumber;
  }
  
  public String getPassportNumber() {
    return this.passportNumber;
  }
  
  public void setDateOfExpiry(Date dateOfExpiry) {
    this.dateOfExpiry = dateOfExpiry;
  }
  
  public Date getDateOfExpiry() {
    return this.dateOfExpiry;
  }
    
  public String toString() {
    return "\n\t\t{\n\t\tId: " + this.passportId + "\n\t\tPlace of Birth: " + this.placeOfBirth + 
        "\n\t\tPassport Number: " + this.passportNumber + 
        "\n\t\tDate of Expiry: " + this.dateOfExpiry + "\n\t\t}\n";
  }
  
}
