import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class DAO {
	
	private static Map<String, Employee> employees = new HashMap<>();
	String EmployeeKey;
	public boolean isEmployeeFound(String id) {
		if (employees.containsKey(id)) {
			return true;
		} else {
			this.EmployeeKey = id;
			return false;
		}
	}
	
	public boolean addEmployee(Employee employeeData) {
		try {
			employees.put(EmployeeKey,employeeData);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	public Employee getEmployee(String id) {
		return employees.get(id);
	}
	
	public boolean removeEmployee(String id) {
		try { 
			employees.remove(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	public ArrayList<Employee> getAllEmployee() {
		return new ArrayList<>(employees.values());
	}
	
}