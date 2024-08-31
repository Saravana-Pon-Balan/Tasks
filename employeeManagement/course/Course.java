package employeeManagement.course;

import java.util.ArrayList;
import java.util.List;

import employeeManagement.employee.Employee;

/**
 * It is represents an course.
 * One course is associate with multiple employee.
 */
public class Course {
  private int courseId;
  private String name;
  private String description;
  private List<Employee> employees;
  
  public Course(int courseId, String name, String description) {
    this.courseId = courseId;
    this.name = name;
    this.description = description;
    this.employees = new ArrayList<>();
  }
  
  public int getId() {
    return this.courseId;
  }
  
	public void setId(int id) {
		this.courseId = id;
	}
  public void setName(String name) {
    this.name = name;
  }
  
  public String getName() {
    return this.name;
  }
  
  public void setDescription(String description) {
    this.description = description;
  }
  
  public String getDescription() {
    return this.description;
  }
  
  public void setEmployee(Employee employee) {
    this.employees.add(employee);
  }
  
  public List<Employee> getEmployee() {
    return this.employees;
  }
  
  public String toString() {
    return "\n\t\t{\n\t\tId: " + this.courseId + "\n\t\tName: " + this.name + 
        "\n\t\tDescription: " + this.description + 
        "\n\t\tEmployee: " + this.employees + "\n\t\t}\n";
  }
  
  
  
  
  
}

