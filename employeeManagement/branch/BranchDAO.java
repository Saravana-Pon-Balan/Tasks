package employeeManagement.branch;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.sql.*;

import employeeManagement.DBConnection.DbConnection;

/**
 * <p>
 * Implementation to access/insert/delete Branch in database.
 * </p>
 */
class BranchDAO {
	
  private static Map<Integer, Branch> branches = new HashMap<>();
  
	/**
   * <p>
   * It get the branch if exist from database.
   * </p>
   * @param id is used for searching the branch from Branch Data by id.
   * @return The Branch object if exist or null.
   */
  public Branch getBranchIfExist(int id) {
    try {
		Connection conn = DbConnection.getInstance();
		Statement st = conn.createStatement();
		String sql = "select * from branch where id = " + id;
		ResultSet rs = st.executeQuery(sql);
		rs.next();
		return new Branch(rs.getInt(1), rs.getString(2), rs.getString(3));
		} catch(SQLException e) {
			System.out.println(e);
			return null;
		}
  }
  
	/**
   * <p>
   * It insert the branch into database.
   * </p>
   * @param branchData is used for insert branch object into database.
   * @return The Branch object.
   */
  public Branch insertBranch(Branch branchData) {
		int branchId = branchData.getId();
		branches.put(branchId, branchData);
		return branches.get(branchId);
  }
  
	
	public void updateBranchInDb(Branch branch) {
		try {
			Connection conn = DbConnection.getInstance();
			String name = branch.getName();
			String location = branch.getLocation();
			String sql = "update branch set name = ?, location = ? where id = " + branch.getId();
			PreparedStatement myStmt = conn.prepareStatement(sql);
			myStmt.setString(1, name);
			myStmt.setString(2, location);
			int execution = myStmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	/**
   * <p>
   * It get the branch if exist.
   * </p>
   * @param id - is used for searching the branch from Branch Data by id.
   * @return The Branch object if exist or null.
   */
  public Branch fetchBranch(int id) {
    return branches.get(id);
  }
  
	/**
   * <p>
   * It delete the branch from database.
   * </p>
   * @param id - is used for searching the branch from Branch Data by id.
   * @return The deleted branch object.
   */
  public Branch deleteBranch(int id) {
    if (branches.containsKey(id)) {
      return branches.remove(id);
    } else {
      return null;
    }
  }
  
}  