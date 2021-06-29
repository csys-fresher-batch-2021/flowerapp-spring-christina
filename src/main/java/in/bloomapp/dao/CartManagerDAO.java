package in.bloomapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import in.bloomapp.exception.DBException;
import in.bloomapp.model.Flower;
import in.bloomapp.util.ConnectionUtil;

public class CartManagerDAO {

	/**
	 * adds the flower into the cart
	 * 
	 * @param flower
	 * @throws DBException
	 */
	public void save(Flower flower) throws DBException {
		// Getting connection
		Connection connection = null;
		PreparedStatement pst = null;
		try {
			connection = ConnectionUtil.getConnection();
			// Prepare data to insert into the driver
			String sql = "insert into bill (flower_id,category,name,quantity,price,username,order_date,status) values (?,?,?,?,?,?,?,?)";
			pst = connection.prepareStatement(sql);
			pst.setInt(1, getFlowerId(flower.getCategory(), flower.getType()));
			pst.setString(2, flower.getCategory());
			pst.setString(3, flower.getType());
			pst.setLong(4, flower.getQuantity());
			pst.setInt(5, flower.getPrice());
			pst.setString(6, flower.getBuyer());
			LocalDate orderDate = LocalDate.now();
			pst.setObject(7, orderDate);
			pst.setInt(8, 1);
			// Executes the Query
			pst.executeUpdate();
		} catch (SQLException | DBException e) {
			throw new DBException("Unable to add item");
		} finally {
			ConnectionUtil.close(pst, connection);
		}
	}

	/**
	 * To update the quantity of the flower
	 * 
	 * @param newFlower
	 * @throws DBException
	 */
	public void update(Flower newFlower) throws DBException {

		Connection connection = null;
		PreparedStatement pst = null;
		try {
			// Get Connection
			connection = ConnectionUtil.getConnection();
			// prepare data
			String sql = "update bill set quantity=? WHERE name=? AND category=? AND username=?";
			pst = connection.prepareStatement(sql);
			pst.setInt(1, newFlower.getQuantity());
			pst.setString(2, newFlower.getType());
			pst.setString(3, newFlower.getCategory());
			pst.setString(4, newFlower.getBuyer());
			// Execute Query
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Unable to add flower quantity");
		} finally {
			ConnectionUtil.close(pst, connection);
		}
	}

	/**
	 * TO make it as deleted changes the status to 0
	 * 
	 * @param flower
	 * @throws DBException
	 */
	public void delete(Flower flower) throws DBException {
		Connection connection = null;
		PreparedStatement pst = null;
		try {
			connection = ConnectionUtil.getConnection();
			// Prepare data to insert into the driver

			String sql = "update bill set status=0 WHERE name=? AND category=? AND username=?";
			pst = connection.prepareStatement(sql);
			pst.setString(1, flower.getType());
			pst.setString(2, flower.getCategory());
			pst.setString(3, flower.getBuyer());
			// Executes the Query
			pst.executeUpdate();
		} catch (SQLException | DBException e) {
			throw new DBException("Unable to delete item");
		} finally {
			ConnectionUtil.close(pst, connection);
		}
	}

	/**
	 * Gets the cart of the user
	 * 
	 * @param userName
	 * @return
	 * @throws DBException
	 */
	public List<Flower> getCart(String userName) throws DBException {
		List<Flower> flower = new ArrayList<>();
		// Step 1: Get the connection
		Connection con = ConnectionUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			// Step 1: Get the connection
			con = ConnectionUtil.getConnection();
			// Step 2: Query
			String sql = "select category,name,quantity,price,username from bill WHERE status=1 AND username=?";
			pst = con.prepareStatement(sql);
			pst.setString(1, userName);
			// Step 3: execute query
			rs = pst.executeQuery();
			while (rs.next()) {
				String category = rs.getString("category");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				int quantity = rs.getInt("Quantity");
				String userNameGot = rs.getString("username");
				// Store the data in model

				Flower subject = new Flower();
				subject.setCategory(category);
				subject.setType(name);
				subject.setPrice(price);
				subject.setQuantity(quantity);
				subject.setBuyer(userNameGot);
				// Store all flowers in list
				flower.add(subject);
			}
		}
		// If unable to get flowers throws exception
		catch (SQLException e) {
			throw new DBException("Unable to fetch cart items");
		} finally {
			// Closes the connection
			ConnectionUtil.close(rs, pst, con);
		}
		return flower;
	}

	/**
	 * Gets flower id from the flower data entry table
	 * 
	 * @param flower
	 * @return
	 * @throws DBException
	 */
	public int getFlowerId(String category, String type) throws DBException {
		// Getting connection
		int flowerId;
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			connection = ConnectionUtil.getConnection();
			// Prepare data to insert into the driver
			String sql = "select id from flowersdata WHERE name=? AND category=?";
			pst = connection.prepareStatement(sql);
			pst.setString(1, type);
			pst.setString(2, category);
			rs = pst.executeQuery();
			rs.next();
			flowerId = rs.getInt("id");
		} catch (SQLException | DBException e) {
			throw new DBException("Unable to get id");
		} finally {
			ConnectionUtil.close(rs, pst, connection);
		}
		return flowerId;
	}
}
