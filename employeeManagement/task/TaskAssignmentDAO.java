package employeeManagement.task;

import java.sql.Connection;
import java.sql.Date;
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
 * Implementation to access/insert/delete task in database.
 * </p>
 */
class TaskAssignmentDAO {
	  
	/**
   * <p>
   * It get the task if exist.
   * </p>
   * @param employee is used for searching the task associate with employee from database.
   * @return The list of TaskAssignment object if exist or null.
   */
  public List<TaskAssignment> getTaskIfExist(Employee employee) {
		List<TaskAssignment> taskData = new ArrayList<>();
    try(Connection conn = DbConnection.getInstance()) {
      String sql = "SELECT * FROM task where employee_id = ?";
		  PreparedStatement preparedStatement = conn.prepareStatement(sql);
		  preparedStatement.setInt(1, employee.getId());
		  ResultSet resultSet = preparedStatement.executeQuery();
		  while(resultSet.next()) {
				taskData.add(new TaskAssignment(resultSet.getInt(1), new java.util.Date(resultSet.getDate(2).getTime()), resultSet.getInt(3), resultSet.getString(4).toString(), employee));
			}
			return taskData;
		} catch(SQLException e) {
			System.out.println(e);
			return null;
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
			PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setDate(1, givenDate);
			preparedStatement.setInt(2, duration);
			preparedStatement.setString(3, status);
			preparedStatement.setInt(4, employeeId);
			preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			if(resultSet.next()) {
			  taskData.setId(resultSet.getInt(1));
			  return taskData;	
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
   * It get the task if exist.
   * </p>
   * @param taskId is used for searching the task from task Data by id.
   * @param employee is used add that in task object.
   * @return The TaskAssignment object if exist or null.
   */
  public TaskAssignment fetchTask(int taskId, Employee employee) {
    try(Connection conn = DbConnection.getInstance()) {
      String sql = "SELECT * FROM task where id = ?";
		  PreparedStatement preparedStatement = conn.prepareStatement(sql);
		  preparedStatement.setInt(1, taskId);
		  ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
			  return new TaskAssignment(resultSet.getInt(1), new java.util.Date(resultSet.getDate(2).getTime()), resultSet.getInt(3), resultSet.getString(4).toString(), employee);
			} else {
				return null;
			}
		} catch(SQLException e) {
			System.out.println(e);
			return null;
		}
  }
	
	/** <p>
   * It updates the task if exist.
   * </p>
   * @param task is used for searching the task from task Data by id.
   */
	public void updateTask(TaskAssignment task) {
		try(Connection conn = DbConnection.getInstance()) {
			Date givenDate = new Date(task.getGivenDate().getTime());
			int duration = task.getDuration();
			String status = task.getStatus();
			int employeeId = task.getEmployee().getId();
			String sql = "update task set given_date = ?, duration = ?, status = ?::task_status, employee_id = ? where id = " + task.getId();
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setDate(1, givenDate);
			preparedStatement.setInt(2, duration);
			preparedStatement.setString(3, status);
			preparedStatement.setInt(4, employeeId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	/**
   * <p>
   * It delete the task from database.
   * </p>
   * @param task is used for find the specific task from database.
   * @return true if deleted otherwise false.
   */
  public Boolean deleteTask(TaskAssignment task) {
    try(Connection conn = DbConnection.getInstance()) {
		  String sql = "delete FROM task where id = ?";
		  PreparedStatement preparedStatement = conn.prepareStatement(sql);
		  preparedStatement.setInt(1, task.getId());
		  preparedStatement.executeUpdate();
		  return true;
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
  }
}  