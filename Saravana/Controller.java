import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Controller {
	
	private static Scanner scanner = new Scanner(System.in);
	private static EmployeeService service = new EmployeeService();
	private static void collectEmployeeDetails() {
		System.out.println("Enter Employee ID: ");
		String id = scanner.nextLine();
		if (service.isEmployeeFound(id)) {
			System.out.println("\nEmployee already Exist\n");
			return; 
		}	
		System.out.println("Enter Employee name: ");
		String name = scanner.nextLine();
		System.out.println("Enter Employee Date of Birth: ");
		String dob = scanner.nextLine();
		System.out.println("Enter Employee Mobile Number: ");
		String mobileNumber = scanner.nextLine();
		System.out.println("Enter Employee role: ");
		String role = scanner.nextLine();
		System.out.println("Enter Employee address: ");
		String address = scanner.nextLine();
		
		if (service.addEmployee(id, name, dob, mobileNumber, role, address)) {
			System.out.println("\nEmployee added successfuly\n");
		} else {
			System.out.println("\nSomething went wrong\n");
		}
	}
	
	public static void printAnEmployee(Map<String, Object> employee) {
		employee.forEach((k, v) -> {
			System.out.println(k + ": " + v);
		});
		System.out.println();
	}
	
	public static void printEmployeeDetails() {
		System.out.println("Enter Employee id to get details: ");
		String searchId = scanner.nextLine();
		if (service.isEmployeeFound(searchId)) {
			Map<String, Object> employee = service.getEmployee(searchId);
			printAnEmployee(employee);
		} else
			System.out.println("\nEmployee not found\n");
	} 
	
	public static void updateEmployeeDetails() {
		System.out.println("Enter Employee id to update details: ");
		String updateId = scanner.nextLine();
		if (service.isEmployeeFound(updateId)) {
			System.out.println("which you want to update");
			System.out.println("1) Name\n2) Date of Birth\n3) Age\n"
								+ "4) Mobile Number\n5) Role\n6)Address");
			int field = scanner.nextInt();
			scanner.nextLine();
			switch(field) {
				case 1:
					System.out.println("Enter name you want to update: ");
					String updateName = scanner.nextLine();
					service.updateEmployeeName(updateId, updateName);
					break;
				case 2:
					System.out.println("Enter Date of Birth you want to update: ");
					String UpdateDob = scanner.nextLine();
					service.updateEmployeeDob(updateId, UpdateDob);
					break;
				case 3:
					System.out.println("Enter Mobile Number you want to update: ");
					String updateMobileNumber = scanner.nextLine();
					service.updateEmployeeMobileNumber(updateId, updateMobileNumber);
					break;
				case 5:
					System.out.println("Enter role you want to update: ");
					String updateRole = scanner.nextLine();
					service.updateEmployeeRole(updateId, updateRole);
					break;
				case 6:
					System.out.println("Enter address you want to update: ");
					String updateAddress = scanner.nextLine();
					service.updateEmployeeAddress(updateId, updateAddress);
					break;
				
				default:
					System.out.println("Choose correct field");
				
			}
			System.out.println("\nEmployee data updated successfully\n");
		} else
			System.out.println("\nEmployee not found\n");
	}
	public static void deleteEmployee() {
		System.out.println("Enter Employee id to delete details: ");
		String deleteId = scanner.nextLine();
		if (service.isEmployeeFound(deleteId)) {
			if (service.removeEmployee(deleteId)) {
				System.out.println("\nSuccessfuly deleted the Employee details\n");
			} else {
				System.out.println("Something went wrong");
			}
		}
		else
			System.out.println("\nGiven id not found\n");
	}
	public static void displayAllEmployee() {
		System.out.println("\t\t All Employee Data");
		List<Map<String, Object>> employeeData = service.getAllEmployee();
		for (Map<String, Object> employee : employeeData) {
			printAnEmployee(employee);
		}
	}
	
	public static void manageEmployee() {
		int choice;
		
		do {
			System.out.println("\nChoose first\n");
			System.out.println("1) Add Employee\n2) get Employee Details\n"
									+ "3) Update Employee\n4) Delete Employee\n"
									+ "5) Show all Employee Details\n6) Exit");
			choice = scanner.nextInt();
			scanner.nextLine();
			switch(choice) {
				case 1:
					collectEmployeeDetails();
					break;
				case 2:
					printEmployeeDetails();
					break;
				case 3:
					updateEmployeeDetails();
					break;
				case 4:
					deleteEmployee();
					break;
				case 5:
					displayAllEmployee();
					break;
				case 6:
					break;
				default :
					System.out.println("\nChoose correct choice\n");
	
		
		
			}
		}while(choice != 6);
	}
	
	public static void main(String args[]) {
		manageEmployee();
	}
}