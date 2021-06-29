package in.bloomapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import in.bloomapp.exception.DBException;
import in.bloomapp.model.User;
import in.bloomapp.util.ConnectionUtil;

public class UserManagerDAO {

	/**
	 * Registered user details will be saved in a data base
	 * 
	 * @param newUser
	 * @throws DBException
	 */
	public void save(User newUser) throws DBException {
		// Getting connection
		Connection connection = null;
		PreparedStatement pst = null;
		try {
			connection = ConnectionUtil.getConnection();
			// Prepare data to insert into the driver
			String sql = "insert into user_data (name,password,email,mobileNo,address) values (?,?,?,?,?)";
			pst = connection.prepareStatement(sql);
			pst.setString(1, newUser.getName());
			pst.setString(2, newUser.getPassword());
			pst.setString(3, newUser.getEmail());
			pst.setLong(4, newUser.getMobileNo());
			pst.setString(5, newUser.getAddress());
			// Executes the Query
			pst.executeUpdate();
		} catch (SQLException | DBException e) {
			throw new DBException("Unable to add user");
		} finally {
			ConnectionUtil.close(null, pst, connection);
		}
	}

	/**
	 * To list the users who are registered
	 * 
	 * @return
	 * @throws DBException
	 */
	public List<User> get() throws DBException {

		List<User> user = new ArrayList<>();
		// Step 1: Get the connection
		Connection con = ConnectionUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			// Step 1: Get the connection
			con = ConnectionUtil.getConnection();
			// Step 2: Query
			String sql = "select name,password,email,mobileNo,address from user_data";
			pst = con.prepareStatement(sql);
			// Step 3: execute query
			rs = pst.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				String password = rs.getString("password");
				String email = rs.getString("email");
				long mobileNo = rs.getLong("mobileNo");
				String address = rs.getString("address");
				// Store the data in model
				User subject = new User();
				subject.setName(name);
				subject.setPassword(password);
				subject.setEmail(email);
				subject.setMobileNo(mobileNo);
				subject.setAddress(address);
				// Store all flowers in list
				user.add(subject);
			}
		}
		// If unable to get flowers throws exception
		catch (SQLException e) {
			throw new DBException("Unable to fetch Users");
		} finally {
			// Closes the connection
			ConnectionUtil.close(rs, pst, con);
		}
		return user;
	}
}
