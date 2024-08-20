class Insurance {
	// one to many
	private String insuranceId;
	private String employeeId;
	private String personName;
	private String relation;
	private int coverage;
	
	public Insurance(String insuranceId, String employeeId, 
						String personName, String relation, int coverage) {
		this.insuranceId = insuranceId;
		this.employeeId = employeeId;
		this.personName = personName;
		this.relation = relation;
		this.coverage = coverage;
	}	
	
	public String getInsuranceId() {
		return this.insuranceId;
	}
	
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	
	public String getEmployeeId() {
		return this.employeeId;
	}
	
	public void setPersonName(String name) {
		this.personName = name;
	}
	
	public String getPersonName() {
		return this.personName;
	}
	
	public void setRelation(String relation) {
		this.relation = relation;
	}
	
	public String getRelation() {
		return this.relation;
	}
	
	public void setCoverage(int coverage) {
		this.coverage = coverage;
	}
	
	public int getCoverage() {
		return this.coverage;
	}
	
	
}
