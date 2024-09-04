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
  
	/**
   * <p>
   * It get the course list along with the specific employee.
   * </p>
   * @param employee is used for searching the course from course Data by id.
   * @return The list of courses if exist or null.
   */
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
      Course course = new Course(0, name, description);
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
  public Course updateName(Course course, String name) {
    if (course != null) {
      course.setName(name);
      return course;
    }
    return course;  
  }
  
	/**
   * <p>
   * It updates the course name on reference.
   * </p>
   * @param courseId is used for find the course.
	 * @param description is used for update new description for the course.
   * @return The course object or null if course doesn't exist.
   */
  public Course updateDescription(Course course, String description) {
    if (course != null) {
      course.setDescription(description);
      return course;
    }
    return course;
  }
	
	public void updateCourseInDb(Course course) {
		courseDao.updateCourseInDb(course);
	}
  
	/**
   * <p>
   * It bind the employee with course and viceversa.
   * </p>
   * @param courseId is used for find the course.
	 * @param employee is used for get employee details.
   * @return The course object or null if course doesn't exist.
   */
  public int updateEmployee(Course course, Employee employee) {
    if (course != null) {
      course.setEmployee(employee);
      return bindCourse(employee, course);
    }
    return 0;
  }
 
  
	/**
	 * <p>
   * This method is for binding course with employee.
	 * </p>
	 * @param employee is used for find the employee.
	 * @param course is used for find the specific course.
	 * @return The number of column updated.
   */
  public int addCourseToEmployee(Employee employee, Course course) {
    return bindCourse(employee, course);
  }
  
	/**
	* <p>
  * It handle the employee reference from the course.
	* </p>
	* @param employeeId is used for find the employee.
	* @return It return the updated course data if employee in course else null.
  */
  public Course removeEmployeeFromCourse(int employeeId) {
    courseDao.removeEmployeeFromCourse(employeeId);
		return null;
  }
	
	/**
	 * <p>
   * This method is for binding course with employee.
	 * </p>
	 * @param employee is used for find the employee.
	 * @param course is used for find the specific course.
	 * @return The number of column updated.
   */
	public int bindCourse(Employee employee, Course course) {
    return courseDao.mapEmployeeWithCourse(employee, course);
  }
}