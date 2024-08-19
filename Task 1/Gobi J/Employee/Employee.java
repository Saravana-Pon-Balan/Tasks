import java.time.LocalDate;
import java.time.Period;

public class Employee {
  private String id;
  private String name;
  private String dob;
  private String mail;
  private long mobileNo;
  private String role;
  private String dept;

  public Employee(String id, String name, String dob, String mail, long mobileNo,
                     String role, String dept) {
    this.id = id;
    this.name = name;
    this.dob = dob;
    this.mail = mail;
    this.mobileNo = mobileNo;
    this.role = role;
    this.dept = dept;
  }

  public String getID() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDOB() {
    return this.dob;
  }

  public void setDOB(String dob) {
    this.dob = dob;
  }

  public String getMail() {
    return this.mail;
  }

  public void setMail(String mail) {
    this.mail = mail;
  }

  public long getMobileNo() {
    return this.mobileNo;
  }

  public void setMobileNo(long mobileNo) {
    this.mobileNo = mobileNo;
  }

  public String getRole() {
    return this.role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getDept() {
    return this.dept;
  }

  public void setDept(String dept) {
    this.dept = dept;
  }

  private int calculateAge() {
    LocalDate birthDate = LocalDate.parse(this.dob);
    LocalDate currDate = LocalDate.now();
    if(birthDate != null) {
      return Period.between(birthDate, currDate).getYears();
    } else {
      return 0;
    }
  }

  public String toString() {
    return "EmpID: " + id + "\nName: " + name + "\nDate of birth: " + dob + 
           "\nAge: " + calculateAge() + "\nEmail: " + mail + "\nMobile Number: " + mobileNo + 
           "\nRole: " + role + "\nDept: " + dept + "\n";
  }
}