package employeeManagement.task;

import java.util.Date;
import java.util.List;
import java.util.Map;

import employeeManagement.employee.Employee;

/**
 * <p>
 * Implementation to business logic for TaskAssignment.
 * </p>
 */
public class TaskAssignmentService {
  private TaskAssignmentDAO taskDao = new TaskAssignmentDAO();
	private static int autoId = 0;
	
	/**
   * <p>
   * It get the Task if exist.
   * </p>
   * @param id is used for searching the task from task Data by passportId.
   * @return The Task object if exist or null.
   */
  public List<TaskAssignment> getTaskIfExist(Employee employee) {
    return taskDao.getTaskIfExist(employee);
  }
	
	public TaskAssignment getTask(int taskId, Employee employee) {
		return taskDao.fetchTask(taskId, employee);
	}
  
	/**
   * <p>
   * It creates the new TaskAssignment object.
   * </p>
	 * @param givenDate It specify the task assignment Date.
	 * @param durationInDays It specify the durationInDays of task.
	 * @param status It specify the status of task.
	 * @param employeeId It specify the employeeId.
	 * @return The TaskAssignment object.
   */
  public TaskAssignment addTask(Date givenDate, int durationInDays, 
                  String status, Employee employee) {
    if(employee != null) {
			int taskId = ++autoId;
      TaskAssignment task = new TaskAssignment(taskId, givenDate, durationInDays, status, employee);
      TaskAssignment taskData = taskDao.insertTask(task);
      bindTask(employee, taskData);
      return taskData;
    } else {
      return null;
    }
    
  }
  
	/**
   * <p>
   * It updates the task given date on reference.
   * </p>
   * @param id is used for find the task.
	 * @param givenDate is used for update new givenDate for the Task.
   * @return The task object or null if task doesn't exist.
   */
  public TaskAssignment updateGivenDate(TaskAssignment task, Date givenDate) {
    task.setGivenDate(givenDate);
		return task;
      
  }
  
	/**
   * <p>
   * It updates the Task name on reference.
   * </p>
   * @param id is used for find the Task.
	 * @param duration is used for update new duration for the Task.
   * @return The Task object or null if Task doesn't exist.
   */
  public TaskAssignment updateDuration(TaskAssignment task, int duration) {
    task.setDuration(duration);
    return task;
  }
  
	/**
   * <p>
   * It updates the Task name on reference.
   * </p>
   * @param id is used for find the Task.
	 * @param status is used for update new status for the Task.
   * @return The Task object or null if Task doesn't exist.
   */
  public TaskAssignment updateStatus(TaskAssignment task, String status) {
    task.setStatus(status);
		return task;
  }
  
	/**
   * <p>
   * It remove the employee from old Task and add to new Task.
   * </p>
   * @param branchId is used for find the Task.
	 * @param newEmployeeId is used for get new employee details.
	 * @param oldEmployeeId is used for get old employee details.
   * @return The Task object or null if Task doesn't exist.
   */
  public TaskAssignment updateEmployee(TaskAssignment task, Employee newEmployee) {
    
    
    if (task != null) {
      task.setEmployee(newEmployee);
      return task;
    } else {
      return null;
    }
    
    
  }
	
	public void updateTask(TaskAssignment task) {
		taskDao.updateTask(task);
	}
  
	/**
	 * <p>
   * Deletes the entire Task.
	 * </p>
	 * @param id is used for find the specific task.
	 * @return The deleted TaskAssignment data.
   */
  public Boolean removeTask(TaskAssignment task) {
    return taskDao.deleteTask(task);
  }
  
	public void setTaskToEmployee(Employee employee) {
		taskDao.setTaskToEmployee(employee);
	}
  
	public Employee bindTask(Employee employee, TaskAssignment task) {
    if (employee != null) {
      employee.setTask(task);
      return employee;
    }
    return null;
  }
}