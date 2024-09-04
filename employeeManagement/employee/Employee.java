package employeeManagement.employee;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import employeeManagement.passport.Passport;
import employeeManagement.branch.Branch;
import employeeManagement.task.TaskAssignment;
import employeeManagement.course.Course;

/**
 * It is represents an employee.
 */
public class Employee {
  private int employeeId;
  private String name;
  private Date dob;
  private String mobileNumber;
  private String role;
  private String address;
  private Branch branch;
  private List<Course> course;
  private Passport passport;
  private List<TaskAssignment> tasks;  
  
  public Employee(int id, String name, Date dob, String mobileNumber, String role, String address) {
    this.employeeId = id;
    this.name = name;
    this.dob = dob;
    this.mobileNumber = mobileNumber;
    this.role = role;
    this.address = address;
    this.tasks = new ArrayList<>();
    this.course = new ArrayList<>();
  }
  
  public int getId() {
    return this.employeeId;
  }
	
	public void setId(int id) {
		this.employeeId = id;
	}
  
  public String getName() {
    return this.name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public Date getDob() {
    return this.dob;
  }
  
  public void setDob(Date dob) {
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
  
  public void setPassport(Passport passport) {
    this.passport = passport;
  }
  
  public Passport getPassport() {
    return this.passport;
  }
  
  public void setTask(TaskAssignment task) {
    this.tasks.add(task);
  }
  
  public List<TaskAssignment> getTask() {
    return this.tasks;
  }
  
  public void setBranch(Branch branch) {
    this.branch = branch;
  }
  
  public Branch getBranch() {
    return this.branch;
  }
  
  public void setCourse(Course course) {
    this.course.add(course);
  }
  
  public List<Course> getCourse() {
    return this.course;
  }
}