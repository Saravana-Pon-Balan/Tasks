import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    boolean tryAgain = true;

    do {
      System.out.println("1. Add Employee\n2. Get Employee by ID\n3. Update Employee\n4. Delete Employee\n5. View All Employees\n6. Close");
      int option = Integer.parseInt(scanner.nextLine());
      EmployeeService employeeService = new EmployeeService();

      switch(option) {
        case 1:
          System.out.println("Enter Employee ID: ");
          String id = scanner.nextLine();
          System.out.println("Enter Employee Name: ");
          String name = scanner.nextLine();
          System.out.println("Enter Employee Role: ");
          String role = scanner.nextLine();
          System.out.println("Enter Employee Department: ");
          String dept = scanner.nextLine();
          boolean isEmployeeAdded = employeeService.addEmployee(id, name, role, dept);
          if(isEmployeeAdded) {
            System.out.println("Employee Added Successfully!!\n");
          } else {
            System.out.println("Employee ID taken, Try new ID.\n");
          }
          break;

        case 2:
          System.out.println("Enter Employee ID to view details: ");
          id = scanner.nextLine();
          Employee employee = employeeService.getEmployee(id);
          if(employee == null)
            System.out.println("Employee not present.\n");
          else
            System.out.println(employee);
          break;

        case 3:
          System.out.println("Enter Employee ID: ");
          id = scanner.nextLine();
          System.out.println("Enter Updated Details");
          System.out.println("Name: ");
          name = scanner.nextLine();
          System.out.println("Role: ");
          role = scanner.nextLine();
          System.out.println("Department: ");
          dept = scanner.nextLine();
          boolean isEmployeeUpdated = employeeService.updateEmployeeDetails(id, name, role, dept);
          if(isEmployeeUpdated) {
            System.out.println("Employee Updated Successfully!!\n");
          } else {
            System.out.println("Employee Not Available. Try Again with correct ID or Add New Employee.\n");
          }
          break;

        case 4:
          System.out.println("Enter Employee ID to delete: ");
          id = scanner.nextLine();
          boolean isEmployeeDeleted = employeeService.deleteEmployee(id);
          if(isEmployeeDeleted) {
            System.out.println("Employee Deleted Successfully!!\n");
          } else {
            System.out.println("Employee Not Available.\n");
          }
          break;

        case 5:
          Map<String, Employee> employees = employeeService.getAllEmployees();
          if(employees.size() > 0) {
            for(String empID : employees.keySet()) {
              System.out.println(employees.get(empID));
            }
          } else {
            System.out.println("No Employee Present.\n");
          }
          break;

        case 6:
          tryAgain = false;
          System.out.println("---------- Thank You ----------");
          scanner.close();
          break;

        default:
          System.out.println("Enter valid option");
      }
    } while(tryAgain == true);
  }
}