class TaskAssignment {
	// one to one
	private String taskId;
	private String taskName;
	private String givenDate;
	private int durationInDays;
	private String status;
	
	public TaskAssignment(String taskId, String taskName, 
							String givenDate, int durationInDays, String status) {
		this.taskId = taskId;
		this.taskName = taskName;
		this.givenDate = givenDate;
		this.durationInDays = durationInDays;
		this.status = status;
	}
	
	public String getTaskId() {
		return this.taskId;
	}
	
	public void setTaskName(String name) {
		this.taskName = name;
	}
	
	public String getTaskName() {
		return this.taskName;
	}
	public void setGivenDate(String date) {
		this.givenDate = date;
	}
	
	public String getGivenDate() {
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
	
	
}