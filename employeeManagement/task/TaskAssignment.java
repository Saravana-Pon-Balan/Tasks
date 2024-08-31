package employeeManagement.task;

import java.util.Date;

import employeeManagement.employee.Employee; 

/**
 * It is represents an TaskAssignment.
 * It associate with single employee object.
 */
public class TaskAssignment {
	// one to many
	//private enum TaskStatus {
 //   inprogress,
   // notstarted,
   // completed,
		//cancelled
//	}
  private int taskId;
  private Date givenDate;
  private int durationInDays;
	// enum type (inprogress, notstarted, completed, cancelled)
  private String status;
  private Employee employee;
  
  public TaskAssignment(int id, Date givenDate, int durationInDays,
                          String status, Employee employee) {
    this.taskId = id;
    this.givenDate = givenDate;
    this.durationInDays = durationInDays;
    this.status = status;
    this.employee = employee;
  }
  
  public int getId() {
    return this.taskId;
  }
	
	public void setId(int id) {
		this.taskId = id;
	}
  
  public void setGivenDate(Date date) {
    this.givenDate = date;
  }
  
  public Date getGivenDate() {
    return this.givenDate;
  }
  public void setDuration(int days) {
    this.durationInDays = days;
  }
  
  public int getDuration() {
    return this.durationInDays;
  }
  
  public void setStatus(String status) {
    this.status = status;
  }
  
  public String getStatus() {
    return this.status;
  }
  
  public void setEmployee(Employee employee) {
    this.employee = employee;
  }
  
  public Employee getEmployee() {
    return this.employee;
  }
  
  public String toString() {
    return "\n\t\t{\n\t\tId: " + this.taskId + "\n\t\tGiven Date: " + this.givenDate + 
        "\n\t\tDuration: " + this.durationInDays + 
        "\n\t\tStatus: " + this.status + "\n\t\t}\n";
  }
}