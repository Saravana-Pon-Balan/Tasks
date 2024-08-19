import java.util.HashMap;
import java.util.Map;

public class EmployeeService {
  EmployeeDAO employeeDAO = new EmployeeDAO();

  public boolean addEmployee(String id, String name, String role, String dept) {
    if(employeeDAO.isEmployeePresent(id)) 
      return false;
    Employee employee = new Employee(id, name, role, dept);
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

  public boolean updateEmployeeDetails(String id, String name, String role, String dept) {
    if(employeeDAO.isEmployeePresent(id)) {
      employeeDAO.updateEmployeeDetails(id, name, role, dept);
      return true;
    } else {
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