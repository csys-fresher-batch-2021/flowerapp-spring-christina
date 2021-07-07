package in.bloomapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.bloomapp.exception.DBException;
import in.bloomapp.model.Flower;
import in.bloomapp.util.ConnectionUtil;

public class FlowerManagerDAO {
	/**
	 * Adds a flower to the database
	 * 
	 * @param newFlower
	 * @throws SQLException
	 * @throws Exception
	 */
	public void saveFlower(Flower newFlower) throws DBException {
		// Getting connection
		Connection connection = null;
		PreparedStatement pst = null;
		try {
			connection = ConnectionUtil.getConnection();
			// Prepare data to insert into the driver
			String sql = "insert into flowersData (name,category,price,status) values ( ?,?,?,1)";
			pst = connection.prepareStatement(sql);
			pst.setString(1, newFlower.getType());
			pst.setString(2, newFlower.getCategory());
			pst.setInt(3, newFlower.getPrice());
			// Executes the Query
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new DBException("Unable to add flower");
		} finally {
			// Null Check - to avoid Null Pointer Exception
			ConnectionUtil.close(pst, connection);
		}
	}

	/**
	 * remove a flower from the database
	 * 
	 * @param oldFlower
	 * @throws SQLException
	 * @throws Exception
	 */
	public void removeFlower(Flower oldFlower) throws DBException {
		Connection connection = null;
		PreparedStatement pst = null;
		try {
			connection = ConnectionUtil.getConnection();

			// Preparing the data
			String sql = "update flowersdata set status=0 where name=?;";

			pst = connection.prepareStatement(sql);
			pst.setString(1, oldFlower.getType());
			// Executes the Query
			System.out.println(oldFlower.getType());
			pst.executeUpdate();
			System.out.println("sql delete");
		} catch (SQLException e) {
			// If cannot add flower shows exception
			throw new DBException("Unable to delete flower");
		} finally {
			ConnectionUtil.close(null, pst, connection);
		}
	}

	/**
	 * Gets the all flowers which is in the database
	 * 
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<Flower> getFlower() throws DBException {

		List<Flower> flower = new ArrayList<>();
		// Step 1: Get the connection
		Connection con = ConnectionUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			// Step 1: Get the connection
			con = ConnectionUtil.getConnection();
			// Step 2: Query
			String sql = "select category,name,price from flowersData where status=1";
			pst = con.prepareStatement(sql);
			// Step 3: execute query
			rs = pst.executeQuery();
			while (rs.next()) {
				String category = rs.getString("category");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				// Store the data in model
				Flower subject = new Flower();
				subject.setCategory(category);
				subject.setType(name);
				subject.setPrice(price);
				// Store all flowers in list
				flower.add(subject);
			}
		}
		// If unable to get flowers throws exception
		catch (SQLException e) {
			throw new DBException("Unable to fetch flowers");
		} finally {
			// Closes the connection
			ConnectionUtil.close(rs, pst, con);
		}
		return flower;
	}
}
