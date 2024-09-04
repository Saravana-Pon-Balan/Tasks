package employeeManagement.course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import employeeManagement.DBConnection.DbConnection;
import employeeManagement.employee.Employee;
/**
 * <p>
 * Implementation to access/insert/delete course in database.
 * </p>
 */
class CourseDAO {
	
	/**
   * <p>
   * It get the course if exist.
   * </p>
   * @param id is used for searching the course from course Data by id.
   * @return The course object if exist or null.
   */
  public Course getCourseIfExist(int id) {
		try(Connection conn = DbConnection.getInstance()) {
			Statement statement = conn.createStatement();
			String sql = "select * from course where id = " + id;
			ResultSet resultSet = statement.executeQuery(sql);
			if(resultSet.next()) {
			  return new Course(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));	
			} else {
				return null;
			}
		} catch(SQLException e) {
			System.out.println(e);
			return null;
		}
  }
  
	/**
   * <p>
   * It fetch the course list along with the specific employee.
   * </p>
   * @param employee is used for search the courses from mapper table by employee id.
   * @return The list of courses if exist or null.
   */
	public List<Course> getCourseOfEmployee(Employee employee) {
		List<Course> courses = new ArrayList<>();
		try(Connection conn = DbConnection.getInstance()) {
			Statement statement = conn.createStatement();
			int employeeId = employee.getId();
			String sql = "select * from employee_course_mapper where employee_id = " + employeeId;
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				courses.add(getCourseIfExist(resultSet.getInt(3)));
			}
			return courses;
		} catch(SQLException e) {
			System.out.println(e);
			return null;
		}
	}
	
	/**
   * <p>
   * It insert the course into database.
   * </p>
   * @param coureData is used for insert course object into database.
   * @return The inseted course object.
   */
  public Course insertCourse(Course courseData) {
		try(Connection conn = DbConnection.getInstance()) {
			String name = courseData.getName();
			String description = courseData.getDescription();
			String sql = "insert into course(name, description) values(?, ?)";
			PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, description);
			preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			if(resultSet.next()) {
			  courseData.setId(resultSet.getInt(1));
			  return courseData;
			} else {
				return null;
			}
		} catch(SQLException e) {
			System.out.println(e);
			return null;
		}
  }
	
	/**
   * <p>
   * It updates the course data in database.
   * </p>
   * @param coure is used for get the field data of course object.
   */
	public void updateCourseInDb(Course course) {
		try(Connection conn = DbConnection.getInstance()) {
			String name = course.getName();
			String description = course.getDescription();
			String sql = "update course set name = ?, description = ? where id = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, description);
			preparedStatement.setInt(3, course.getId());
			preparedStatement.executeUpdate();	
		} catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	/**
   * <p>
   * It map the specific employee with specific course in mapper table.
   * </p>
   * @param employee is used to get employee Id.
   * @param coure is used to get course Id.
   * @return The inseted course object.
   */
	public int mapEmployeeWithCourse(Employee employee, Course course) {
		try(Connection conn = DbConnection.getInstance()) {
			int employeeId = employee.getId();
			int courseId = course.getId();
			String sql = "insert into employee_course_mapper(employee_id, course_id) values(?, ?)";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, employeeId);
			preparedStatement.setInt(2, courseId);
			int isInserted = preparedStatement.executeUpdate();
			return isInserted;
		} catch(SQLException e) {
			System.out.println(e);
			return 0;
		}
	}
	
	/**
   * <p>
   * It delete the employee record in mapper table.
   * </p>
   * @param employeeId is used to find employee Id.
   * @return The inseted course object.
   */
	public void removeEmployeeFromCourse(int employeeId) {
		try(Connection conn = DbConnection.getInstance()) {
			String sql = "delete from employee_course_mapper where employee_id = " + employeeId;
			PreparedStatement preparedStatement = conn.prepareStatement(sql); 
			preparedStatement.executeQuery();
		} catch(SQLException e) {
			System.out.println(e);
		}
	}

}  