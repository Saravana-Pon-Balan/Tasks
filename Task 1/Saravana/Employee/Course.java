import java.util.ArrayList;
import java.util.List;
class Course {
	// many to many
	private String id;
	private String name;
	private String description;
	private List<String> employeeIds;
	
	public Course(String id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.employeeIds = new ArrayList<>();
	}
	
	public String getId() {
		return this.id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void addEmployeeId(String EmployeeId) {
		this.employeeIds.add(EmployeeId);
	}
	
	public List<String> getEmployeeIds() {
		return this.employeeIds;
	}
	
	
	
	
	
	
	
}

