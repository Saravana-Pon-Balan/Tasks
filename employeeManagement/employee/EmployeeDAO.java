package employeeManagement.employee;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;

import employeeManagement.DBConnection.DbConnection;
/**
 * <p>
 * Implementation to access/modify/delete employee in database.
 * </p>
 */
public class EmployeeDAO {
	
  private static Map<String, Employee> employees = new HashMap<>();
	
	/**
   * <p>
   * It get the employee if exist.
   * </p>
   * @param id is used for searching the employee from employee Data by id.
   * @return The employee object if exist or null.
   */
	 
  public List<Object> getEmployeeIfExist(int id) {
    List<Object> employeeData = new ArrayList<>();
		try {
		Connection conn = DbConnection.getInstance();
		String sql = "SELECT * FROM employee where id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		Employee employee = new Employee(rs.getInt(1), rs.getString(2), new java.util.Date(rs.getDate(3).getTime()), rs.getString(4), rs.getString(5), rs.getString(6));
		employeeData.add(employee);
		employeeData.add(rs.getInt(7));
    return employeeData;
		} catch(SQLException e) {
			System.out.println(e);
			return null;
		}
  }
  
	/**
   * <p>
   * It insert the employee into database.
   * </p>
   * @param employeeData is used for insert employee object into database.
   * @return The employee object.
   */
  public Employee insertEmployee(Employee employeeData, int branchId) {
 
    try {
			Connection conn = DbConnection.getInstance();
			String name = employeeData.getName();
			Date dob = new Date(employeeData.getDob().getTime());
			
			String mobileNumber = employeeData.getMobileNumber();
			String role = employeeData.getRole();
			String address = employeeData.getAddress();
			String sql = "insert into employee(name, dob, mobile_number, employee_role, address, branch_id) values(?, ?, ?, ?, ?, ?)";
			PreparedStatement myStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			myStmt.setString(1, name);
			myStmt.setDate(2, dob);
			myStmt.setString(3, mobileNumber);
			myStmt.setString(4, role);
			myStmt.setString(5, address);
			myStmt.setInt(6, branchId);
			int execution = myStmt.executeUpdate();
			ResultSet rs = myStmt.getGeneratedKeys();
			rs.next();
			employeeData.setId(rs.getInt(1));
			return employeeData;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
  }
  
	/**
   * <p>
   * It get the employee if exist.
   * </p>
   * @param id is used for searching the employee from employee Data by id.
   * @return The employee object if exist or null.
   */
  public List<Object> fetchEmployee(int id) {
		List<Object> employeeData = new ArrayList<>();
		try(Connection conn = DbConnection.getInstance()) {
		  String sql = "SELECT * FROM employee where id = ?";
		  PreparedStatement ps = conn.prepareStatement(sql);
		  ps.setInt(1, id);
		  ResultSet rs = ps.executeQuery();
		  rs.next();
		
		  Employee employee = new Employee(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getString(5), rs.getString(6));
			employeeData.add(employee);
		  employeeData.add(rs.getInt(7));
      return employeeData;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
  }
	
	public Employee updateEmployee(Employee employee) {
		 try {
			Connection conn = DbConnection.getInstance();
			String name = employee.getName();
			Date dob = new Date(employee.getDob().getTime());
			String mobileNumber = employee.getMobileNumber();
			String role = employee.getRole();
			String address = employee.getAddress();
			int branchId = employee.getBranch().getId();
			System.out.println("branch"+branchId);
			String sql = "update employee set name = ?, dob = ?, mobile_number = ?, employee_role = ?, address = ?, branch_id = ? where id = " + employee.getId();
			PreparedStatement myStmt = conn.prepareStatement(sql);
			myStmt.setString(1, name);
			myStmt.setDate(2, dob);
			myStmt.setString(3, mobileNumber);
			myStmt.setString(4, role);
			myStmt.setString(5, address);
			myStmt.setInt(6, branchId);
			int execution = myStmt.executeUpdate();
			return employee;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}
  
	public Employee updateEmployeeBranch(Employee employee) {
		try {
			Connection conn = DbConnection.getInstance();
			int branchId = employee.getBranch().getId();
	
			String sql = "update employee set branch_id = ? where id = " + employee.getId();
			PreparedStatement myStmt = conn.prepareStatement(sql);
			myStmt.setInt(1, branchId);
			int execution = myStmt.executeUpdate();
			return employee;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}
	/**
   * <p>
   * It is delete the employee from database if exist.
   * </p>
   * @param id is used for searching the employee from employee Data by id.
   * @return The deleted employee object.
   */
  public Employee deleteEmployee(int id) {
   try {
		  Connection conn = DbConnection.getInstance();
		  String sql = "delete FROM employee where id = ?";
		  PreparedStatement ps = conn.prepareStatement(sql);
		  ps.setInt(1, id);
		  ps.executeUpdate();
		  return null;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
  }
	/**
   * <p>
   * It gets the all employee data from database.
   * </p>
   * @return The List of employee objects if exist or empty list.
   */
  public List<Employee> fetchAllEmployee() {
    List<Employee> employeeData = new ArrayList<>();
		try {
		Connection conn = DbConnection.getInstance();
		String sql = "SELECT * FROM employee";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
		  employeeData.add(new Employee(rs.getInt(1), rs.getString(2), new java.util.Date(rs.getDate(3).getTime()), rs.getString(4), rs.getString(5), rs.getString(6)));
		}
    return employeeData;
		} catch(SQLException e) {
			System.out.println(e);
			return null;
		}
		
  }
  
  
}