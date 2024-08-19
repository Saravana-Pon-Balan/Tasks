import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EmployeeService {
	DAO dao = new DAO();
	public boolean isEmployeeFound(String id) {
		return dao.isEmployeeFound(id);
	}
	
	public boolean addEmployee(String id, String name, String dob, String mobileNumber,
								String role, String address) {
		Employee employee = new Employee(id, name, dob, mobileNumber, role, address);
		return dao.addEmployee(employee);
	}
	
	public Map<String, Object> getEmployee(String id) {
		Employee employeeData = dao.getEmployee(id);
		Map<String, Object> employeeDataMap = new LinkedHashMap<>();
		employeeDataMap.put("id", employeeData.getId());
		employeeDataMap.put("name", employeeData.getName());
		employeeDataMap.put("dob", employeeData.getDob());
		employeeDataMap.put("mobileNumber", employeeData.getMobileNumber());
		employeeDataMap.put("role", employeeData.getRole());
		employeeDataMap.put("address", employeeData.getAddress());
		return employeeDataMap;
	}
	
	public boolean updateEmployeeName(String id, String name) {
		Employee updateEmployeeData = dao.getEmployee(id);
		if (updateEmployeeData != null) {
			updateEmployeeData.setName(name);
			return true;
		}
		return false;
		
	}
	
	public boolean updateEmployeeDob(String id, String dob) {
		Employee updateEmployeeData = dao.getEmployee(id);
		if (updateEmployeeData != null) {
			updateEmployeeData.setDob(dob);
			return true;
		}
		return false;	
	}
	
	public boolean updateEmployeeMobileNumber(String id, String mobileNumber) {
		Employee updateEmployeeData = dao.getEmployee(id);
		if (updateEmployeeData != null) {
			updateEmployeeData.setMobileNumber(mobileNumber);
			return true;
		}
		return false;
	}
	
	public boolean updateEmployeeRole(String id, String role) {
		Employee updateEmployeeData = dao.getEmployee(id);
		if (updateEmployeeData != null) {
			updateEmployeeData.setRole(role);
			return true;
		}
		return false;
	}
	
	public boolean updateEmployeeAddress(String id, String address) {
		Employee updateEmployeeData = dao.getEmployee(id);
		if (updateEmployeeData != null) {
			updateEmployeeData.setAddress(address);
			return true;
		}
		return false;	
	}
	
	public boolean removeEmployee(String id) {
		if (dao.removeEmployee(id)) {
			return true;
		}
		return false;
	}
	
	public List<Map<String, Object>> getAllEmployee() {
		
		List<Employee> employeeList = new ArrayList<>(dao.getAllEmployee());
		List<Map<String, Object>> employeeData = new ArrayList<>();
		Map<String, Object> singleEmployee = new LinkedHashMap<>();
        for (Employee employee : employeeList) {
            singleEmployee.put("Id: ", employee.getId());
			singleEmployee.put("Name: ", employee.getName());
			singleEmployee.put("DOB: ", employee.getDob());
			singleEmployee.put("MobileNumber", employee.getMobileNumber());
			singleEmployee.put("Role", employee.getRole());
			singleEmployee.put("Address", employee.getAddress());
			employeeData.add(new LinkedHashMap<String, Object>(singleEmployee));
			singleEmployee.clear();
        }
		return employeeData;
	}
	
}