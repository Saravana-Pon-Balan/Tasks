package employeeManagement.branch;


import java.util.Scanner;

import employeeManagement.employee.EmployeeService;
import employeeManagement.employee.Employee;

/**
 * <p>
 * Implementation to manage Branch Details
 * </p>
 */

public class BranchController {
	
  private Scanner scanner = new Scanner(System.in);
  private EmployeeService employeeService = new EmployeeService();
  private BranchService branchService = new BranchService();
  
	/**
   * <p>
   * It get the branch if exist.
   * </p>
   * @param branchId is used for searching the branch from Branch Data by branchId.
   * @return The Branch object if exist or null.
   */
	public Branch getBranchIfExist(int branchId) {
		return branchService.getBranchIfExist(branchId);
	}
	
	/**
   * <p>
   * It getting input for creating new branch.
   * </p>
   * @param employeeId is used for initially bind specified employee to Branch.
   * @return The Branch object if exist or null.
   */
  public Branch addBranchDetails(Employee employee) {
    System.out.println("Enter Name of Branch: ");
    String name = scanner.nextLine();
    System.out.println("Enter Branch Location: ");
    String location = scanner.nextLine();
    Branch branch = branchService.addBranch(name, location, employee);
    if (branch != null) {
      System.out.println("\nBranch created successfuly\n");
			return branch;
    } else {
      System.out.println("\nSomething went wrong\n");
			return null;
    }
  }
  
	/**
   * <p>
   * Its for update the branch details or move employee from one branch to another branch.
   * </p>
   * @param employeeId is used for get that employee's branch and edit that or change the branch.
   * @return The Branch object.
   */
  public Branch updateBranchDetails(Employee employee) {
    Branch branch = branchService.getBranchIfExist(employee.getBranch().getId());
    if(employee.getRole().equals("Admin") && branch != null) {
      System.out.println("\nwhat are the fields you want to update\n");
      System.out.println("1) Name\n2) Location\n"
                          + "3) Move to another branch\n4) Exit\n"
                          + "\n(give field numbers separated by single space)");
      String[] fieldNumbers = scanner.nextLine().split(" ");
      for (String s : fieldNumbers) {
        switch(Integer.parseInt(s)) {
          case 1:
						System.out.println("Enter Name you want to update: ");
					 	String name = scanner.nextLine();
            branchService.updateName(branch, name);
            break;
				  case 2:
            System.out.println("Enter Location you want to update: ");
            String location = scanner.nextLine();
            branchService.updateLocation(branch, location);
            break;
          case 3:
					  System.out.println("Enter Existing Branch ID: ");
						int newBranchId = scanner.nextInt();
						Branch newBranch = branchService.getBranchIfExist(newBranchId);
		        employee.setBranch(newBranch);
            if(employeeService.updateEmployeeBranch(employee) == null) {
							System.out.println("\nSpecified branch doesnt't exist\n");
						}
            break;
          case 4:
            break;
          default:
            System.out.println("Choose correct field");
        }
      }
			branchService.updateBranchInDb(branch);
      System.out.println("\nBranch data updated successfully\n");
      return branch;
    } else if(employee.getRole().equals("Admin") && branch == null) {
			System.out.println("New Branch creating");
      return addBranchDetails(employee); 
		} else {
      System.out.println("You cannot update branch details");
			return  null;
	  }   
  }
  
	/**
	 * <p>
   * Deletes the entire branch.
	 * </p>
  */
  public void removeBranch() {
    System.out.println("Enter Branch id to delete details: ");
    int branchId = scanner.nextInt();
    if (branchService.getBranchIfExist(branchId)!= null) {
      if (branchService.removeBranch(branchId) != null) {
        System.out.println("\nSuccessfuly deleted the Branch details\n");
      } else {
        System.out.println("Something went wrong");
      }
    }
    else
      System.out.println("\nGiven id not found\n");
  }
  
	/**
   * <p>
   * It is simply remove employee reference from a Branch Object.
   * </p>
	 * @param branchId is used for find the specific branch.
   * @param employeeId is used for find the employee reference in specific Branch object.
   */
  public void removeEmployeeFromBranch(int branchId, int employeeId) {
    Branch branch = branchService.removeEmployeeFromBranch(branchId, employeeId);
    if(branch != null) {
      System.out.println("Employee removed from branch");
    } else {
      System.out.println("Employee is not removed from branch");
    }
  }
	
  
}