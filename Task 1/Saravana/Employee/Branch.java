import java.util.List;
import java.util.ArrayList;

class Branch {
	// many to one
	private String id;
	private String name;
	private String location;
	private List<String> employeeIds;

	public Branch(String id, String name, String location) {
		this.id = id;
		this.name = name;
		this.location = location;
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

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocation() {
		return this.location;
	}
	
	public void addEmployeeId(String EmployeeId) {
		this.employeeIds.add(EmployeeId);
	}
	
	public List<String> getEmployeeIds() {
		return this.employeeIds;
	}

}
