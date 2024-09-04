package employeeManagement.branch;

import java.util.HashSet;
import java.util.Set;

import employeeManagement.employee.Employee;

/**
 * It is represents an Branch.
 * one branch is associate with multiple employees.
 */
public class Branch {
  private int branchId;
  private String name;
  private String location;
  private Set<Employee> employees;

  public Branch(int id, String name, String location) {
    this.branchId = id;
    this.name = name;
    this.location = location;
    this.employees = new HashSet<>();
  }

  public int getId() {
    return this.branchId;
  }

  public void setId(int id) {
		this.branchId = id;
	}
  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getLocation() {
    return this.location;
  }
  
  public void setEmployee(Employee employee) {
    this.employees.add(employee);
  }
  
  public Set<Employee> getEmployee() {
    return this.employees;
  }
  
  public String toString() {
    return "\n\t\t{\n\t\tId: " + this.branchId + "\n\t\tName: " + this.name + 
              "\n\t\tLocation: " + this.location + "\n\t\t}";
  }

}
