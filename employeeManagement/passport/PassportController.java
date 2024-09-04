package employeeManagement.passport;

import java.util.Date;
import java.util.Scanner;

import employeeManagement.employee.Employee;
import employeeManagement.employee.EmployeeService;
import employeeManagement.util.DateUtil;


public class PassportController {
	
  private static Scanner scanner = new Scanner(System.in);
  private static PassportService passportService = new PassportService();
  private EmployeeService employeeService = new EmployeeService();
  
	/**
   * <p>
   * It getting user input for creates the new Passport.
   * </p>
   * @param employee is used for initially bind specified employee to Branch.
	 */
  public void addPassportDetails(Employee employee) {
    System.out.println("Enter Place of Birth: ");
    String placeOfBirth = scanner.nextLine();
    System.out.println("Enter Passport Number: ");
    String passportNumber = scanner.nextLine();
    System.out.println("Enter Passport expiry date: (yyyy-mm-dd)");
    Date expiryDate = DateUtil.strToDate(scanner.nextLine());
    
    if (passportService.addPassport(employee, placeOfBirth, passportNumber, expiryDate) != null) {
      System.out.println("\nPassport added successfuly\n");
    } else {
      System.out.println("\nSomething went wrong\n");
    }
  }
  
	/**
   * <p>
   * Its for update the Passport details or delete the entire passport.
   * </p>
   * @param employeeI is used for get that employee's passport and edit that.
   */
  public void updatePassportDetails(Employee employee) {
    int passportId;
		Passport passport = passportService.getPassportIfExist(employee);
    if(passport != null) {
			
      passportId = passport.getId();
			
      System.out.println("\nwhat are the fields you want to update\n");
      System.out.println("1) Delete Passport\n2) placeOfBirth\n"
                + "3) passport Number\n4) Expiry Date\n"
                + "\n(give field numbers separated by single space)");
      String[] fieldNumbers = scanner.nextLine().split(" ");
      for (String s : fieldNumbers) {
        switch(Integer.parseInt(s)) {
          case 1:
            passportService.removePassport(passportId);
            return;
          case 2:
            System.out.println("Enter place of Birth you want to update: ");
            String placeOfBirth = scanner.nextLine();
            passportService.updatePlaceOfBirth(passport, placeOfBirth);
            break;
          case 3:
            System.out.println("Enter passport Number you want to update: ");
            String updatePassportNumber = scanner.nextLine();
            passportService.updatePassportNumber(passport, updatePassportNumber);
            break;
          case 4:
            System.out.println("Enter Expiry Date you want to update: ");
            Date parsedExpiryDate = DateUtil.strToDate(scanner.nextLine());
            passportService.updateDateOfExpiry(passport, parsedExpiryDate);
            break;
          
          default:
            System.out.println("Choose correct field");
          
        }
      }
			passportService.updatePassport(passport);
      System.out.println("\nPassport data updated successfully\n");
    } else {
      System.out.println("Adding new passport");
      addPassportDetails(employee);
    }
      
  }
	
	/**
	 * <p>
   * Deletes the entire Passport.
	 * </p>
	 * @param employee is used for get employee details and remove passport from there.
   */
  public void removePassport(Employee employee) {
    int idToDelete;
    if(employee != null) {
      idToDelete = employee.getPassport().getId();
    } else {
      System.out.println("\nGiven employee not found\n");
			return;
    }
    if (passportService.getPassportIfExist(employee)!= null) {
      passportService.removePassport(idToDelete);
      System.out.println("\nSuccessfuly deleted the Passport details\n");
    }
    else
      System.out.println("\nThis employee don't have passport\n");
  }
  
  
  
}