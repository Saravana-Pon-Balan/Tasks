package employeeManagement.passport;

import java.util.HashMap;
import java.util.Map;
import java.sql.*;

import employeeManagement.DBConnection.DbConnection;
import employeeManagement.employee.Employee;
/**
 * <p>
 * Implementation to access/insert/delete passport in database.
 * </p>
 */
class PassportDAO {
	
  private static Map<String, Passport> passports = new HashMap<>();
	
	/**
   * <p>
   * It get the passport if exist.
   * </p>
   * @param id is used for searching the passport from passport Data by id.
   * @return The passport object if exist or null.
   */
  public Passport getPassportIfExist(Employee employee) {
    try {
	    Connection conn = DbConnection.getInstance();
      String sql = "SELECT * FROM passport where employee_id = ?";
		  PreparedStatement ps = conn.prepareStatement(sql);
		  ps.setInt(1, employee.getId());
		  ResultSet rs = ps.executeQuery();
		  rs.next();
			return new Passport(rs.getInt(1), employee, rs.getString(2), rs.getString(3), rs.getDate(4));
		} catch(SQLException e) {
			System.out.println(e);
			return null;
		}
  }
  
	/**
   * <p>
   * It is insert the passport object into database.
   * </p>
   * @param passportData is used for insert passport object into database.
   * @return The passport object.
   */
  public Passport insertPassport(Passport passportData) {
		try {
		  Connection conn = DbConnection.getInstance();
			String placeOfBirth = passportData.getPlaceOfBirth();
			String passportNumber = passportData.getPassportNumber();
			
			Date dateOfExpiry = new Date(passportData.getDateOfExpiry().getTime());
			
			int employeeId = passportData.getEmployee().getId();
			
			String sql = "insert into passport(place_of_birth, passport_number, date_of_expiry, employee_id) values(?, ?, ?, ?)";
			PreparedStatement myStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			myStmt.setString(1, placeOfBirth);
			myStmt.setString(2, passportNumber);
			myStmt.setDate(3, dateOfExpiry);
			myStmt.setInt(4, employeeId);
			int execution = myStmt.executeUpdate();
			ResultSet rs = myStmt.getGeneratedKeys();
			rs.next();
			passportData.setId(rs.getInt(1));
			return passportData;
		} catch(SQLException e) {
			System.out.println(e);
			return null;
		}
  }
  
	/**
   * <p>
   * It get the passport if exist.
   * </p>
   * @param id is used for searching the passport from passport Data by id.
   * @return The passport object if exist or null.
   */
  public Passport fetchPassport(int id) {
    return passports.get(id);
  }
  
	
	public void updatePassport(Passport passport) {
		try {
			Connection conn = DbConnection.getInstance();
			String placeOfBirth = passport.getPlaceOfBirth();
			String passportNumber = passport.getPassportNumber();
			Date dateOfExpiry = new Date(passport.getDateOfExpiry().getTime());
			int employeeId = passport.getEmployee().getId();
			String sql = "update passport set place_of_birth = ?, passport_number = ?, date_of_expiry = ?, employee_id = ? where id = " + passport.getId();
			PreparedStatement myStmt = conn.prepareStatement(sql);
			myStmt.setString(1, placeOfBirth);
			myStmt.setString(2, passportNumber);
			myStmt.setDate(3, dateOfExpiry);
			myStmt.setInt(4, employeeId);
			int execution = myStmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	/**
   * <p>
   * It delete the passport from database.
   * </p>
   * @param id it is used for find the specific passport.
   * @return The passport object if exist or null.
   */
  public Passport deletePassport(int id) {
    try {
		  Connection conn = DbConnection.getInstance();
		  String sql = "delete FROM passport where id = ?";
		  PreparedStatement ps = conn.prepareStatement(sql);
		  ps.setInt(1, id);
		  ps.executeUpdate();
		  return null;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
  }
  
  
}  