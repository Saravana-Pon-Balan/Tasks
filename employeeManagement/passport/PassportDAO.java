package employeeManagement.passport;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;


import employeeManagement.DBConnection.DbConnection;
import employeeManagement.employee.Employee;
/**
 * <p>
 * Implementation to access/insert/delete passport in database.
 * </p>
 */
class PassportDAO {
		
	/**
   * <p>
   * It get the passport if exist.
   * </p>
   * @param employee is used for searching the passport along with that employee.
   * @return The passport object if exist or null.
   */
  public Passport getPassportIfExist(Employee employee) {
    try(Connection conn = DbConnection.getInstance()) {
      String sql = "SELECT * FROM passport where employee_id = ?";
		  PreparedStatement preparedStatement = conn.prepareStatement(sql);
		  preparedStatement.setInt(1, employee.getId());
		  ResultSet resultSet = preparedStatement.executeQuery();
		  if(resultSet.next()) {
			  return new Passport(resultSet.getInt(1), employee, resultSet.getString(2), resultSet.getString(3), resultSet.getDate(4));
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
   * It is insert the passport object into database.
   * </p>
   * @param passportData is used for insert passport object into database.
   * @return The passport object.
   */
  public Passport insertPassport(Passport passportData) {
		try(Connection conn = DbConnection.getInstance()) {
			String placeOfBirth = passportData.getPlaceOfBirth();
			String passportNumber = passportData.getPassportNumber();
			
			Date dateOfExpiry = new Date(passportData.getDateOfExpiry().getTime());
			
			int employeeId = passportData.getEmployee().getId();
			
			String sql = "insert into passport(place_of_birth, passport_number, date_of_expiry, employee_id) values(?, ?, ?, ?)";
			PreparedStatement prepareStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			prepareStatement.setString(1, placeOfBirth);
			prepareStatement.setString(2, passportNumber);
			prepareStatement.setDate(3, dateOfExpiry);
			prepareStatement.setInt(4, employeeId);
			prepareStatement.executeUpdate();
			ResultSet resultSet = prepareStatement.getGeneratedKeys();
			if(resultSet.next()) {
			  passportData.setId(resultSet.getInt(1));
			  return passportData;	
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
   * It is updates the passport data into database.
   * </p>
   * @param passport is used for update passport into database.
   */
	public void updatePassport(Passport passport) {
		try(Connection conn = DbConnection.getInstance()) {
			String placeOfBirth = passport.getPlaceOfBirth();
			String passportNumber = passport.getPassportNumber();
			Date dateOfExpiry = new Date(passport.getDateOfExpiry().getTime());
			int employeeId = passport.getEmployee().getId();
			String sql = "update passport set place_of_birth = ?, passport_number = ?, date_of_expiry = ?, employee_id = ? where id = " + passport.getId();
			PreparedStatement prepareStatement = conn.prepareStatement(sql);
			prepareStatement.setString(1, placeOfBirth);
			prepareStatement.setString(2, passportNumber);
			prepareStatement.setDate(3, dateOfExpiry);
			prepareStatement.setInt(4, employeeId);
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	/**
   * <p>
   * It delete the passport from database.
   * </p>
   * @param id it is used for find the specific passport.
   */
  public void deletePassport(int id) {
    try(Connection conn = DbConnection.getInstance()) {
		  String sql = "delete FROM passport where id = ?";
		  PreparedStatement preparedStatement = conn.prepareStatement(sql);
		  preparedStatement.setInt(1, id);
		  preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
  }
  
  
}  