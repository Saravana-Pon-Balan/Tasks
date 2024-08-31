package employeeManagement.course;

import java.util.List;

import employeeManagement.employee.Employee;
/**
 * <p>
 * Implementation to business logic for Course.
 * </p>
 */
public class CourseService {
  private CourseDAO courseDao = new CourseDAO();
	private static int autoId = 0;
	
	/**
   * <p>
   * It get the Course if exist.
   * </p>
   * @param courseId is used for searching the course from Course Data by courseId.
   * @return The Course object if exist or null.
   */
  public Course getCourseIfExist(int courseId) {
    return courseDao.getCourseIfExist(courseId);
  }
  
	public List<Course> getCourseOfEmployee(Employee employee) {
		return courseDao.getCourseOfEmployee(employee);
	}
	/**
   * <p>
   * It creates the new course object.
   * </p>
	 * @param name It specify the course name.
	 * @param decription It specify the course description.
   * @param employeeId is used for initially bind specified employee to course.
   * @return The course object if added or null.
   */
  public Course addCourse(String name, String description, Employee employee) {
    if(employee != null) {
			int courseId = ++autoId;
      Course course = new Course(courseId, name, description);
      Course courseData = courseDao.insertCourse(course);  
      bindCourse(employee, courseData);
      return courseData;
    } else {
      return null;
    }
  }
  
	/**
   * <p>
   * It updates the course name on reference.
   * </p>
   * @param courseId is used for find the course.
	 * @param name is used for update new name for the course.
   * @return The course object or null if course doesn't exist.
   */
  public Course updateName(int courseId, String name) {
    Course courseDataToUpdate = courseDao.fetchCourse(courseId);
    if (courseDataToUpdate != null) {
      courseDataToUpdate.setName(name);
      return courseDataToUpdate;
    }
    return courseDataToUpdate;  
  }
  
	/**
   * <p>
   * It updates the course name on reference.
   * </p>
   * @param courseId is used for find the course.
	 * @param description is used for update new description for the course.
   * @return The course object or null if course doesn't exist.
   */
  public Course updateDescription(int courseId, String description) {
    Course courseDataToUpdate = courseDao.fetchCourse(courseId);
    if (courseDataToUpdate != null) {
      courseDataToUpdate.setDescription(description);
      return courseDataToUpdate;
    }
    return courseDataToUpdate;
  }
  
	/**
   * <p>
   * It bind the employee with course and viceversa.
   * </p>
   * @param courseId is used for find the course.
	 * @param employeeId is used for get employee details.
   * @return The course object or null if course doesn't exist.
   */
  public Course updateEmployee(int courseId, Employee employee) {
    Course courseDataToUpdate = courseDao.fetchCourse(courseId);    
    if (courseDataToUpdate != null) {
      courseDataToUpdate.setEmployee(employee);
      bindCourse(employee, courseDataToUpdate);
      return courseDataToUpdate;
    }
    return null;
  }
  
  /**
	 * <p>
   * Deletes the entire Course.
	 * </p>
	 * @param courseId is used for find the specific course.
	 * @return The deleted course data.
   */
  public Course removeCourse(int courseId) {
    return courseDao.deleteCourse(courseId);
  }
  
	/**
	 * <p>
   * This method is for binding course with employee.
	 * </p>
	 * @param employeeId is used for find the employee.
	 * @param courseId is used for find the specific course.
	 * @return The updated course data.
   */
  public Course addCourseToEmployee(Employee employee, Course course) {
    bindCourse(employee, course);
    return course;
  }
  
	/**
	* <p>
  * It handle the employee reference from the course.
	* </p>
	* @param courseId(String) - is used for find the specific course.
	* @param employeeId(String) - is used for find the employee.
	* @return It return the updated course data if employee in course else null.
  */
  public Course removeEmployeeFromCourse(int employeeId) {
    courseDao.removeEmployeeFromCourse(employeeId);
		return null;
  }
	
	public void bindCourse(Employee employee, Course course) {
    courseDao.mapEmployeeWithCourse(employee, course);
  }
}