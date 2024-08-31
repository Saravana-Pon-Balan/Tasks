package employeeManagement.course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.*;

import employeeManagement.DBConnection.DbConnection;
import employeeManagement.employee.Employee;
/**
 * <p>
 * Implementation to access/insert/delete course in database.
 * </p>
 */
class CourseDAO {
	
  private static Map<Integer, Course> courses = new HashMap<>();
  private int courseId;
 
	/**
   * <p>
   * It get the course if exist.
   * </p>
   * @param id is used for searching the course from course Data by id.
   * @return The course object if exist or null.
   */
  public Course getCourseIfExist(int id) {
		try {
			Connection conn = DbConnection.getInstance();
			Statement st = conn.createStatement();
			String sql = "select * from course where id = " + id;
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			return new Course(rs.getInt(1), rs.getString(2), rs.getString(3));
		} catch(SQLException e) {
			System.out.println(e);
			return null;
		}
  }
  
	public List<Course> getCourseOfEmployee(Employee employee) {
		List<Course> courses = new ArrayList<>();
		try {
			Connection conn = DbConnection.getInstance();
			Statement st = conn.createStatement();
			int employeeId = employee.getId();
			String sql = "select * from employee_course_mapper where employee_id = " + employeeId;
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				courses.add(getCourseIfExist(rs.getInt(3)));
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
   * @param branchData is used for insert course object into database.
   * @return The course object.
   */
  public Course insertCourse(Course courseData) {
		try {
			Connection conn = DbConnection.getInstance();
			String name = courseData.getName();
			String description = courseData.getDescription();
			String sql = "insert into course(name, description) values(?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, name);
			ps.setString(2, description);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			courseData.setId(rs.getInt(1));
			return courseData;
		} catch(SQLException e) {
			System.out.println(e);
			return null;
		}
  }
  
	/**
   * <p>
   * It get the course if exist.
   * </p>
   * @param id - is used for searching the course from course Data by id.
   * @return The course object if exist or null.
   */
  public Course fetchCourse(int id) {
    return courses.get(id);
  }
  
	/**
   * <p>
   * It delete the course from database.
   * </p>
   * @param id - is used for searching the course from course Data by id.
   * @return The deleted course object.
   */
  public Course deleteCourse(int id) {
    if (courses.containsKey(id)) {
      return courses.remove(id);
    } else {
      return null;
    }
  }
	
	public void mapEmployeeWithCourse(Employee employee, Course course) {
		try {
			Connection conn = DbConnection.getInstance();
			int employeeId = employee.getId();
			int courseId = course.getId();
			String sql = "insert into employee_course_mapper(employee_id, course_id) values(?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, employeeId);
			ps.setInt(2, courseId);
			ps.executeUpdate();
		} catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	public void removeEmployeeFromCourse(int employeeId) {
		try {
			Connection conn = DbConnection.getInstance();
			String sql = "delete from employee_course_mapper where employee_id = " + employeeId;
			PreparedStatement ps = conn.prepareStatement(sql); 
			ps.executeQuery();
		} catch(SQLException e) {
			System.out.println(e);
		}
	}

}  