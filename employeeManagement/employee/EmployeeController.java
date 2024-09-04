package employeeManagement.employee;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import employeeManagement.util.DateUtil;

import employeeManagement.branch.Branch;
/**
 * <p>
 * Implementation to manage Employee Details
 * </p>
 */
public class EmployeeController {
	
  private static Scanner scanner = new Scanner(System.in);
  private static EmployeeService employeeService = new EmployeeService();
  
	/**
   * <p>
   * It get the Employee if exist.
   * </p>
   * @param employeeId is used for searching the employee from employee Data by employeeId.
   * @return The Employee object if exist or null.
   */
  public Employee getEmployeeIfExist(int employeeId) {
		return employeeService.getEmployeeIfExist(employeeId);
    
	}
	
	/**
   * <p>
   * Its for getting input from user for creating the new Employee.
   * </p>
   * @return The Employee object.
   */
  public Employee addEmployeeDetails() {
    System.out.println("Enter Employee name: ");
    String name = scanner.nextLine();
    System.out.println("Enter Employee Date of Birth: (yyyy-mm-dd)");
    String dobStr = scanner.nextLine();
    Date dob = DateUtil.strToDate(dobStr);
    System.out.println("Enter Employee Mobile Number: ");
    String mobileNumber = scanner.nextLine();
    System.out.println("Enter Employee role: ");
    String role = scanner.nextLine();
    System.out.println("Enter Employee address: ");
    String address = scanner.nextLine();
		System.out.println("Enter Employee Branch Id: ");
		int branchId = scanner.nextInt();
		scanner.nextLine();
    return employeeService.addEmployee(name, dob, mobileNumber, role, address, branchId);
    
  }
  
  public void printAnEmployee(Map<String, Object> employee) {
    employee.forEach((field, value) -> {
      System.out.println(field + ": " + value);
    });
    System.out.println();
  }
  
  public void renderAnEmplyee() {
    System.out.println("Enter Employee id to get details: ");
    int searchId = scanner.nextInt();
    if (employeeService.getEmployeeIfExist(searchId) != null) {
      Map<String, Object> employee = employeeService.getEmployee(searchId);
      printAnEmployee(employee);
    } else {
      System.out.println("\nEmployee not found\n");
    }
	} 
  
	/**
   * <p>
   * Its for update the primary Employee details.
   * </p>
	 * @param choice is used for select which field the user want to update in Employee.
   * @param employeeId is used for get that employee data and update that.
  */
  public void updateEmployeeDetails(int choice, Employee employee) {
	  switch(choice) {
	    case 1:
        System.out.println("Enter name you want to update: ");
        String updateName = scanner.nextLine();
        employeeService.updateEmployeeName(employee, updateName);
		    break;
      case 2:
        System.out.println("Enter Date of Birth you want to update: ");
        String updateDobStr = scanner.nextLine();
        Date updateDob = DateUtil.strToDate(updateDobStr);
        employeeService.updateEmployeeDob(employee, updateDob);
        break;
      case 3:
        System.out.println("Enter Mobile Number you want to update: ");
        String updateMobileNumber = scanner.nextLine();
        employeeService.updateEmployeeMobileNumber(employee, updateMobileNumber);
        break;
      case 4:
        System.out.println("Enter role you want to update: ");
        String updateRole = scanner.nextLine();
        employeeService.updateEmployeeRole(employee, updateRole);
        break;
      case 5:
        System.out.println("Enter address you want to update: ");
        String updateAddress = scanner.nextLine();
        employeeService.updateEmployeeAddress(employee, updateAddress);
        break;
	  }
  }
  
	/**
	 * It updates the employee data in database.
	 * @param employee is used for get employee data.
	 * @return the updated Employee Object.
	 */
	public Employee updateEmployeeInDB(Employee employee) {
	  return employeeService.updateEmployeeInDB(employee);
	}
	
	/**
	 * <p>
   * Deletes the entire Employee and that references also.
	 * </p>
	 */
  public void removeEmployee(int employeeId) {
	  employeeService.removeEmployee(employeeId);
  }
  
  public void renderAllEmployee() {
    System.out.println("\t\t All Employee Data");
    List<Map<String, Object>> employeeData = employeeService.getAllEmployee();
    for (Map<String, Object> employee : employeeData) {
      printAnEmployee(employee);
    }
  }
  
	/**
	 * <p>
	 * It simply add employee reference to Branch and viceversa
	 * </p>
	 * @param employeeId is used to specify the employee.
	 * @param branch is used to specify the Branch.
	 * @return The Employee object if employee exist or null.
	 */
  public Employee bindEmployeeWithBranch(Employee employee, Branch branch) {
		return employeeService.bindBranch(employee, branch);
	} 
}