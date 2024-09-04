package employeeManagement.course;


import java.util.Scanner;

import employeeManagement.employee.Employee;

/**
 * <p>
 * Implementation to manage Course Details.
 * </p>
 */
public class CourseController {
	
  private Scanner scanner = new Scanner(System.in);
  private CourseService courseService = new CourseService();
  
	/**
   * <p>
   * It getting input for creating new course.
   * </p>
   * @param employee is used for initially bind specified employee to new course .
	 * @param courseId is used for bind employee with existing course. 
   */
  public void addCourseDetails(Employee employee, int courseId) {
    Course course = courseService.getCourseIfExist(courseId);
		System.out.println(employee.getRole());
    if (!employee.getRole().equals("Admin") && course != null) {
      if(courseService.addCourseToEmployee(employee, course) == 1) {
				System.out.println("\nEmployee binded with that course Exist\n");
			} else {
				System.out.println("\nYou are already Enrolled \n");
			}
      
    }  
		
    else if(employee.getRole().equals("Admin")) {
      System.out.println("Enter Name of Course: ");
      String name = scanner.nextLine();
      System.out.println("Enter Course Description: ");
      String description = scanner.nextLine();
      if (courseService.addCourse(name, description, employee) != null) {
        System.out.println("\nCourse added successfuly\n");
      } else {
        System.out.println("\nSomething went wrong\n");
      }
		}
		else {
			System.out.println("You cannot create course");
		}
  }	
  
	/**
   * <p>
   * This method is used for update course data such as Name, Description.
   * </p>
   * @param employee is used for bind the employee to Specific course or enroll with existing course .
   */
  public void updateCourseDetails(Employee employee) {
    System.out.println("Enter Course id to update details: ");
    int courseId = scanner.nextInt();
		scanner.nextLine();
		Course course = courseService.getCourseIfExist(courseId);
    if (course != null && employee.getRole().equals("Admin")) {
      System.out.println("\nwhat are the fields you want to update\n");
      System.out.println("1) Name\n2) Description\n"
                + "3) Add Employee\n"
                + "\n(give field numbers separated by single space)");
      String[] fieldNumbers = scanner.nextLine().split(" ");
      for (String s : fieldNumbers) {
        switch(Integer.parseInt(s)) {
          case 1:
            System.out.println("Enter Name you want to update: ");
            String name = scanner.nextLine();
            courseService.updateName(course, name);
            break;
          case 2:
            System.out.println("Enter Description you want to update: ");
            String description = scanner.nextLine();
            courseService.updateDescription(course, description);
            break;
          case 3:
            if(courseService.updateEmployee(course, employee) == 0) {
							System.out.println("\nyou are already enrolled\n");
						}
            break;
          
          default:
            System.out.println("Choose correct field");
        }
      }
			courseService.updateCourseInDb(course);
      System.out.println("\nCourse data updated successfully\n");
    } else if(employee.getRole().equals("Admin")) {
			System.out.println("\nCreating new course\n");
      addCourseDetails(employee, courseId);
    } else {
			System.out.println("\nYou cannot update the course details\n");
			System.out.println("\nYou can enroll this course press 1 for Enroll\n");
			if(scanner.nextInt() == 1) {
				addCourseDetails(employee, courseId);
				
			}	
		}
  }
  
	/**
   * <p>
   * It is simply remove employee from a Course in Database.
   * </p>
   * @param employeeId - is used for find the employee in specific Course enrollment list.
   */
  public void removeEmployeeFromCourse(int employeeId) {
    Course course = courseService.removeEmployeeFromCourse(employeeId);
    if(course != null) {
      System.out.println("Employee removed from course");
    } else {
      System.out.println("Employee is not removed from course");
    }
    
  
  }
  
  
}