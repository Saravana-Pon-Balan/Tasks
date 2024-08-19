import java.util.HashMap;
import java.util.Map;

public class EmployeeService {
  EmployeeDAO employeeDAO = new EmployeeDAO();

  public boolean isEmployeePresent(String id) {
    return employeeDAO.isEmployeePresent(id);
  }

  public boolean addEmployee(String id, String name, String dob, String mail, 
                             long mobileNo, String role, String dept) {
    if(employeeDAO.isEmployeePresent(id)) 
      return false;
    Employee employee = new Employee(id, name, dob, mail, mobileNo, role, dept);
    employeeDAO.addEmployee(employee);
    return true;
  }

  public Employee getEmployee(String id) {
    if(employeeDAO.isEmployeePresent(id)) {
      Employee employee = employeeDAO.getEmployee(id);
      return employee;
    } else {
      return null;
    }
  }

  public boolean updateEmployeeDetails(String id, String field, String updatedValue) {
    if(employeeDAO.isEmployeePresent(id)) {
      field = field.toLowerCase();
      employeeDAO.updateSingleField(id, field, updatedValue);
      return true;
    } else {
      return false;
    }
  }

  public boolean updateEmployeeDetailsOrAddNewEmployee(String id, String name, String dob, String mail, 
                                       long mobileNo, String role, String dept) {
    if(employeeDAO.isEmployeePresent(id)) {
      employeeDAO.updateEmployeeDetails(id, name, dob, mail, mobileNo, role, dept);
      return true;
    } else {
      addEmployee(id, name, dob, mail, mobileNo, role, dept);
      return false;
    }
  }

  public boolean deleteEmployee(String id) {
    if(employeeDAO.isEmployeePresent(id)) {
      employeeDAO.deleteEmployee(id);
      return true;
    } else {
      return false;
    }
  }

  public Map<String, Employee> getAllEmployees() {
    Map<String, Employee> employees = employeeDAO.getAllEmployees();
    return employees;
  }
}