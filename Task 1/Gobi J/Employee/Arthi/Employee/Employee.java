class Employee {
  private String id;
  private String name;
  private String role;
  private String dept;

  protected Employee(String id, String name, String role, String dept) {
    this.id = id;
    this.name = name;
    this.role = role;
    this.dept = dept;
  }
  public String getID() {
    return this.id;
  }
  public String getName() {
    return this.name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getRole() {
    return this.role;
  }
  public void setRole(String role) {
    this.role = role;
  }
  public String getDept() {
    return this.dept;
  }
  public void setDept(String dept) {
    this.dept = dept;
  }
  public String toString() {
    return "EmpID: " + id + "\nName: " + name + "\nRole: " + role + "\nDept: " + dept + "\n";
  }
}