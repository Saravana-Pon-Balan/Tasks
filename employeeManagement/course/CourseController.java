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
   * @param employeeId - is used for initially bind specified employee to new course .
	 * @param courseId - is used for bind employee with existing course. 
   */
  public void addCourseDetails(Employee employee, int courseId) {
    
    Course course = courseService.getCourseIfExist(courseId);
		System.out.println(course);
		System.out.println(employee.getRole());
    if (course != null) {
			System.out.println(course);
      courseService.addCourseToEmployee(employee, course);
      System.out.println("\nEmployee binded with that course Exist\n");
      return; 
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
   * @param employeeId is used for bind the employee to Specific course .
   */
  public void updateCourseDetails(Employee employee) {
    System.out.println("Enter Course id to update details: ");
    int courseId = scanner.nextInt();
		scanner.nextLine();
    if (courseService.getCourseIfExist(courseId)!= null) {
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
            courseService.updateName(courseId, name);
            break;
          case 2:
            System.out.println("Enter Description you want to update: ");
            String description = scanner.nextLine();
            courseService.updateDescription(courseId, description);
            break;
          case 3:
            courseService.updateEmployee(courseId, employee);
            break;
          
          default:
            System.out.println("Choose correct field");
        }
      }
      System.out.println("\nCourse data updated successfully\n");
    } else {
      addCourseDetails(employee, courseId);
    }
  }
	
	/**
   * <p>
   * This method is used for remove the entire course.
   * </p>
   */
  public void removeCourse() {
    System.out.println("Enter Course id to delete details: ");
    int courseId = scanner.nextInt();
    if (courseService.getCourseIfExist(courseId)!= null) {
      if (courseService.removeCourse(courseId) != null) {
        System.out.println("\nSuccessfuly deleted the Course details\n");
      } else {
        System.out.println("Something went wrong");
      }
    }
    else
      System.out.println("\nGiven id not found\n");
  }
  
	/**
   * <p>
   * It is simply remove employee reference from a Course Object.
   * </p>
	 * @param courseId - is used for find the specific course.
   * @param employeeId - is used for find the employee reference in specific Course object.
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