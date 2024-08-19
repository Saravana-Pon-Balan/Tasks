import java.util.HashMap;
import java.util.Map;

public class EmployeeDAO {
  private static Map<String, Employee> employees = new HashMap<>();

  public boolean isEmployeePresent(String id) {
    return employees.containsKey(id);
  }

  public void addEmployee(Employee employee) {
    employees.put(employee.getID(), employee);
  }

  public Employee getEmployee(String id) {
      return employees.get(id);
  }

  public void updateSingleField(String id, String field, String updatedValue) {
    Employee employee = employees.get(id);
    switch(field) {
      case "name":
        employee.setName(updatedValue);
        break;
      case "dob":
        employee.setDOB(updatedValue);
        break;
      case "mail":
        employee.setMail(updatedValue);
        break;
      case "mobileNo":
        Long updatedMobileNo = Long.parseLong(updatedValue);
        employee.setMobileNo(updatedMobileNo);
        break;
      case "role":
        employee.setRole(updatedValue);
        break;
      case "dept":
        employee.setDept(updatedValue);
        break;
    }
  }

  public void updateEmployeeDetails(String id, String name, String dob, String mail, 
                                    long mobileNo, String role, String dept) {
    Employee employee = employees.get(id);
    employee.setName(name);
    employee.setDOB(dob);
    employee.setMail(mail);
    employee.setMobileNo(mobileNo);
    employee.setRole(role);
    employee.setDept(dept);
  }

  public void deleteEmployee(String id) {
    employees.remove(id);
  }

  public Map<String, Employee> getAllEmployees() {
    return employees;
  }
}