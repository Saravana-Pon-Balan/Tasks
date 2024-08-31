package employeeManagement.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.*;

import employeeManagement.DBConnection.DbConnection;
import employeeManagement.employee.Employee;
/**
 * <p>
 * Implementation to access/insert/delete task in database.
 * </p>
 */
class TaskAssignmentDAO {
	
  private static Map<String, TaskAssignment> tasks = new HashMap<>();
  
	/**
   * <p>
   * It get the task if exist.
   * </p>
   * @param id is used for searching the task from task Data by id.
   * @return The TaskAssignment object if exist or null.
   */
  public List<TaskAssignment> getTaskIfExist(Employee employee) {
		List<TaskAssignment> taskData = new ArrayList<>();
    try {
	    Connection conn = DbConnection.getInstance();
      String sql = "SELECT * FROM task where employee_id = ?";
		  PreparedStatement ps = conn.prepareStatement(sql);
		  ps.setInt(1, employee.getId());
		  ResultSet rs = ps.executeQuery();
		  while(rs.next()) {
				taskData.add(new TaskAssignment(rs.getInt(1), new java.util.Date(rs.getDate(2).getTime()), rs.getInt(3), rs.getString(4).toString(), employee));
			}
			return taskData;
		} catch(SQLException e) {
			System.out.println(e);
			return null;
		}
  }
  
	public void setTaskToEmployee(Employee employee) {
    try {
			Connection conn = DbConnection.getInstance();
			Statement st = conn.createStatement();
			String sql = "select * from task where employee_id = " + employee.getId();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				TaskAssignment task = new TaskAssignment(rs.getInt(1), rs.getDate(2), rs.getInt(3), rs.getString(4), employee);
				employee.setTask(task);
			}
		} catch(SQLException e) {
			System.out.println(e);
		}
  }
	/**
   * <p>
   * It is insert the task object into database.
   * </p>
   * @param taskData is used for insert task object into database.
   * @return The TaskAssignment object.
   */                   
  public TaskAssignment insertTask(TaskAssignment taskData) {
		try (Connection conn = DbConnection.getInstance()) {
			Date givenDate = new Date(taskData.getGivenDate().getTime());
			int duration = taskData.getDuration();
			
			String status = taskData.getStatus();
			
			int employeeId = taskData.getEmployee().getId();
			
			String sql = "insert into task(given_date, duration, status, employee_id) values(?, ?, ?::task_status, ?)";
			PreparedStatement myStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			myStmt.setDate(1, givenDate);
			myStmt.setInt(2, duration);
			myStmt.setString(3, status);
			myStmt.setInt(4, employeeId);
			int execution = myStmt.executeUpdate();
			ResultSet rs = myStmt.getGeneratedKeys();
			rs.next();
			taskData.setId(rs.getInt(1));
			return taskData;
		} catch(SQLException e) {
			System.out.println(e);
			return null;
		}
  }
  
	/**
   * <p>
   * It get the task if exist.
   * </p>
   * @param id is used for searching the task from task Data by id.
   * @return The TaskAssignment object if exist or null.
   */
  public TaskAssignment fetchTask(int taskId, Employee employee) {
    try {
	    Connection conn = DbConnection.getInstance();
      String sql = "SELECT * FROM task where id = ?";
		  PreparedStatement ps = conn.prepareStatement(sql);
		  ps.setInt(1, taskId);
		  ResultSet rs = ps.executeQuery();
			rs.next();
			return new TaskAssignment(rs.getInt(1), new java.util.Date(rs.getDate(2).getTime()), rs.getInt(3), rs.getString(4).toString(), employee);
		} catch(SQLException e) {
			System.out.println(e);
			return null;
		}
  }
	
	public void updateTask(TaskAssignment task) {
		try {
			Connection conn = DbConnection.getInstance();
			Date givenDate = new Date(task.getGivenDate().getTime());
			int duration = task.getDuration();
			String status = task.getStatus();
			int employeeId = task.getEmployee().getId();
			String sql = "update task set given_date = ?, duration = ?, status = ?::task_status, employee_id = ? where id = " + task.getId();
			PreparedStatement myStmt = conn.prepareStatement(sql);
			myStmt.setDate(1, givenDate);
			myStmt.setInt(2, duration);
			myStmt.setString(3, status);
			myStmt.setInt(4, employeeId);
			int execution = myStmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	/**
   * <p>
   * It delete the task from database.
   * </p>
   * @param id is used for find the specific task from database.
   * @return The TaskAssignment object if exist or null.
   */
  public Boolean deleteTask(TaskAssignment task) {
    try {
		  Connection conn = DbConnection.getInstance();
		  String sql = "delete FROM task where id = ?";
		  PreparedStatement ps = conn.prepareStatement(sql);
		  ps.setInt(1, task.getId());
		  ps.executeUpdate();
		  return true;
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
  }
  
}  