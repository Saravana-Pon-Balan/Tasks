package employeeManagement.branch;

import java.util.List;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.sql.*;

import employeeManagement.employee.Employee;

/**
 * <p>
 * Implementation to business logic for Branch details.
 * </p>
 */
public class BranchService {
    private BranchDAO branchDao = new BranchDAO();
    private static int autoId = 0;


  /**
   * <p>
   * It get the branch if exist.
   * </p>
   * @param branchId is used for searching the branch from Branch Data by branchId.
   * @return The Branch object if exist or null.
   */
  public Branch getBranchIfExist(int branchId) {
    return branchDao.getBranchIfExist(branchId);
  }
  
	/**
   * <p>
   * It creates the new branch object.
   * </p>
	 * @param name is used for add branch name.
	 * @param location is used for add branch location.
   * @param employeeId is used for initially bind specified employee to Branch.
   * @return The Branch object.
   */
  public Branch addBranch(String name, String location, Employee employee) {
    if(employee != null) {
			int branchId = ++autoId;
      Branch branch = new Branch(branchId, name, location);
			System.out.println(branch);
      Branch branchData =  branchDao.insertBranch(branch);  
      Employee bindedEmployee = bindBranch(employee, branchData);
      return branchData;
    } else {
      return null;
    }
  }
  
	/**
   * <p>
   * It updates the branch name on reference.
   * </p>
   * @param branchId is used for find the Branch.
	 * @param name is used for update new name for the branch.
   * @return The Branch object or null if branch doesn't exist.
   */
  public Branch updateName(Branch branch, String name) {
      branch.setName(name);
      return branch; 
  }
  
	/**
   * <p>
   * It updates the branch location on reference.
   * </p>
   * @param branchId is used for find the Branch.
	 * @param location is used for update new location for the branch
   * @return The Branch object or null if branch doesn't exist.
   */
  public Branch updateLocation(Branch branch, String location) {
      branch.setLocation(location);
      return branch;
  }

  /**
   * <p>
   * It remove the employee from old branch and add to new branch.
   * </p>
   * @param branchId is used for find the Branch.
	 * @param employeeId is used for get employee details.
   * @return The Branch object or null if branch doesn't exist.
   */
  public void updateBranchInDb(Branch branch) {
		branchDao.updateBranchInDb(branch);
  }
  
	/**
	 * <p>
   * Deletes the entire Branch.
	 * </p>
	 * @param branchId is used for find the specific branch.
	 * @return The deleted Branch data.
   */
  public Branch removeBranch(int branchId) {
    return branchDao.deleteBranch(branchId);
  }
  
	/**
	 * <p>
   * This method is for binding branch with employee.
	 * </p>
	 * @param employeeId is used for find the employee.
	 * @param branchId is used for find the specific branch.
	 * @return The updated Branch data.
   */
  /*public Branch addBranchToEmployee(int employeeId, int branchId){
    Branch branch = getBranchIfExist(branchId);
    Employee employee = employeeService.bindBranch(employeeId, branch);
    branch.setEmployee(employee);
    return branch;
  }*/	
  
	/**
	 * <p>
   * It remove the employee reference the from branch.
	 * </p>
	 * @param employeeId is used for find the employee.
	 * @param branchId is used for find the specific branch.
	 * @return The updated Branch data if employee in branch else null.
   */
  public Branch removeEmployeeFromBranch(int branchId, int employeeId) {
    Branch branch = getBranchIfExist(branchId);
    Set<Employee> employees = branch.getEmployee();
    for(Employee employee : employees) {
      if(employee.getId() == employeeId) {
        employees.remove(employee);
        return branch;
      } 
    }
    return null;
  }
  
	
	/**
	 * <p>
   * It is bind the employee with specific Branch.
	 * </p>
	 * @param employeeId is used for find the specific employee.
	 * @param branch is used for binding the branch to employee.
	 * @return The employee data if employee exist or null.
   */
	public Employee bindBranch(Employee employee, Branch branch) {
    if (employee != null) {
      employee.setBranch(branch);
			branch.setEmployee(employee);
      return employee;
    }
    return null;
  }
}