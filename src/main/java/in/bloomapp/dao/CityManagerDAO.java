package in.bloomapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import in.bloomapp.exception.DBException;
import in.bloomapp.model.City;
import in.bloomapp.util.ConnectionUtil;

public class CityManagerDAO {

	/**
	 * Adds a new city into the data base
	 * 
	 * @param city
	 * @throws DBException
	 */
	public void save(City city) throws DBException {
		// Getting connection
		Connection connection = null;
		PreparedStatement pst = null;
		try {
			connection = ConnectionUtil.getConnection();
			// Prepare data to insert into the driver
			String sql = "insert into cities (district_code,city,delivary_charge,status) values ( ?,?,?,?)";
			pst = connection.prepareStatement(sql);
			pst.setInt(1, city.getDistrictCode());
			pst.setString(2, city.getCity());
			pst.setInt(3, city.getDelivaryCharge());
			pst.setInt(4, city.getStatus());
			// Executes the Query
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new DBException("Unable to add city");
		} finally {
			// Null Check - to avoid Null Pointer Exception
			ConnectionUtil.close(pst, connection);
		}
	}

	/**
	 * Update delivery charge to the available city
	 * 
	 * @param city
	 * @throws DBException
	 */
	public void update(City city) throws DBException {
		Connection connection = null;
		PreparedStatement pst = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "update cities set delivary_charge=? WHERE city=?";
			pst = connection.prepareStatement(sql);
			pst.setInt(1, city.getDelivaryCharge());
			pst.setString(2, city.getCity());
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new DBException("Unable to update city");
		} finally {
			ConnectionUtil.close(pst, connection);
		}
	}

	/**
	 * Sets the status of the city to 0
	 * 
	 * @param city
	 * @throws DBException
	 */
	public void delete(City city) throws DBException {
		Connection connection = null;
		PreparedStatement pst = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "update cities set status=0 WHERE city=?";
			pst = connection.prepareStatement(sql);
			pst.setString(1, city.getCity());
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new DBException("Unable to update city");
		} finally {
			ConnectionUtil.close(pst, connection);
		}

	}

	/**
	 * Gets the list of cities from the data base
	 * 
	 * @return
	 * @throws DBException
	 */
	public List<City> getCity() throws DBException {
		List<City> list = new ArrayList<>();
		// Step 1: Get the connection
		Connection con = ConnectionUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			// Step 1: Get the connection
			con = ConnectionUtil.getConnection();
			// Step 2: Query
			String sql = "select district_code,city,delivary_charge from cities WHERE status=1";
			pst = con.prepareStatement(sql);
			// Step 3: execute query
			rs = pst.executeQuery();
			while (rs.next()) {
				int districtCode = rs.getInt("district_code");
				String city = rs.getString("city");
				int delivaryCharge = rs.getInt("delivary_charge");
				// Store the data in model
				City subject = new City();
				subject.setDistrictCode(districtCode);
				subject.setCity(city);
				subject.setDelivaryCharge(delivaryCharge);
				// Store all flowers in list
				list.add(subject);
			}
		}
		// If unable to get cities throws exception
		catch (SQLException e) {
			throw new DBException("Unable to fetch city details");
		} finally {
			// Closes the connection
			ConnectionUtil.close(rs, pst, con);
		}
		return list;
	}
}
