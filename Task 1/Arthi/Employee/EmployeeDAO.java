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

  public void updateEmployeeDetails(String id, String name, String role, String dept) {
    Employee employee = employees.get(id);
    employee.setName(name);
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