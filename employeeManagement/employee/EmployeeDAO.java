package employeeManagement.employee;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import employeeManagement.DBConnection.DbConnection;
/**
 * <p>
 * Implementation to access/modify/delete employee in database.
 * </p>
 */
public class EmployeeDAO {	
	/**
   * <p>
   * It get the employee if exist.
   * </p>
   * @param id is used for searching the employee from employee Data by id.
   * @return The list of employee object if exist or null.
   */
	 
  public List<Object> getEmployeeIfExist(int id) {
    List<Object> employeeData = new ArrayList<>();
		try(Connection conn = DbConnection.getInstance()) {
			String sql = "SELECT * FROM employee where id = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				Employee employee = new Employee(resultSet.getInt(1), resultSet.getString(2), new java.util.Date(resultSet.getDate(3).getTime()), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
				employeeData.add(employee);
				employeeData.add(resultSet.getInt(7));
				return employeeData;	
			} else {
				return null;
			}
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
	 * @param branchId is used for insert branchId into employee table.
   * @return The employee object.
   */
  public Employee insertEmployee(Employee employeeData, int branchId) {
 
    try(Connection conn = DbConnection.getInstance()) {
			String name = employeeData.getName();
			Date dob = new Date(employeeData.getDob().getTime());
			String mobileNumber = employeeData.getMobileNumber();
			String role = employeeData.getRole();
			String address = employeeData.getAddress();
			String sql = "insert into employee(name, dob, mobile_number, employee_role, address, branch_id) values(?, ?, ?, ?, ?, ?)";
			PreparedStatement prepareStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			prepareStatement.setString(1, name);
			prepareStatement.setDate(2, dob);
			prepareStatement.setString(3, mobileNumber);
			prepareStatement.setString(4, role);
			prepareStatement.setString(5, address);
			prepareStatement.setInt(6, branchId);
			prepareStatement.executeUpdate();
			ResultSet resultSet = prepareStatement.getGeneratedKeys();
			if(resultSet.next()) {
			  employeeData.setId(resultSet.getInt(1));
			  return employeeData;	
			} else {
				return null;
			}
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
   * @return The list of employee object if exist or null.
   */
  public List<Object> fetchEmployee(int id) {
		List<Object> employeeData = new ArrayList<>();
		try(Connection conn = DbConnection.getInstance()) {
		  String sql = "SELECT * FROM employee where id = ?";
		  PreparedStatement preparedStatement = conn.prepareStatement(sql);
		  preparedStatement.setInt(1, id);
		  ResultSet resultSet = preparedStatement.executeQuery();
		  if(resultSet.next()) {
			  Employee employee = new Employee(resultSet.getInt(1), resultSet.getString(2), resultSet.getDate(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
			  employeeData.add(employee);
		    employeeData.add(resultSet.getInt(7));
        return employeeData;	
			} else {
				return null;
			}
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
  }
	
	/**
   * <p>
   * It update the employee if exist.
   * </p>
   * @param employee is used for searching the employee from database by id.
   * @return The employee object if exist or null.
   */
	public Employee updateEmployee(Employee employee) {
		 try(Connection conn = DbConnection.getInstance()) {
			String name = employee.getName();
			Date dob = new Date(employee.getDob().getTime());
			String mobileNumber = employee.getMobileNumber();
			String role = employee.getRole();
			String address = employee.getAddress();
			String sql = "update employee set name = ?, dob = ?, mobile_number = ?, employee_role = ?, address = ? where id = " + employee.getId();
			PreparedStatement prepareStatement = conn.prepareStatement(sql);
			prepareStatement.setString(1, name);
			prepareStatement.setDate(2, dob);
			prepareStatement.setString(3, mobileNumber);
			prepareStatement.setString(4, role);
			prepareStatement.setString(5, address);
			prepareStatement.executeUpdate();
			return employee;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}
  
	/**
   * <p>
   * It update the employee branch if exist.
   * </p>
   * @param employee is used for searching the employee from database by id.
   * @return The employee object if exist or null.
   */
	public Employee updateEmployeeBranch(Employee employee) {
		try(Connection conn = DbConnection.getInstance()) {
			int branchId = employee.getBranch().getId();
			String sql = "update employee set branch_id = ? where id = " + employee.getId();
			PreparedStatement prepareStatement = conn.prepareStatement(sql);
			prepareStatement.setInt(1, branchId);
			prepareStatement.executeUpdate();
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
  public void deleteEmployee(int id) {
   try(Connection conn = DbConnection.getInstance()) {
		  String sql = "delete FROM employee where id = ?";
		  PreparedStatement preparedStatement = conn.prepareStatement(sql);
		  preparedStatement.setInt(1, id);
		  preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
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
		try(Connection conn = DbConnection.getInstance()) {
		  String sql = "SELECT * FROM employee";
		  PreparedStatement preparedStatement = conn.prepareStatement(sql);
		  ResultSet resultSet = preparedStatement.executeQuery();
		  while(resultSet.next()) {
		    employeeData.add(new Employee(resultSet.getInt(1), resultSet.getString(2), new java.util.Date(resultSet.getDate(3).getTime()), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6)));
		  }
      return employeeData;
		} catch(SQLException e) {
			System.out.println(e);
			return null;
		}
		
  }
  
  
}